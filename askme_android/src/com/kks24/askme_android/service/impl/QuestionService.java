package com.kks24.askme_android.service.impl;

import java.util.ArrayList;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.kks24.askme_android.IPAddressClass;
import com.kks24.askme_android.PostQuestionActivity;
import com.kks24.askme_android.QuestionActivity;
//import com.application.foodheartmobile.ListInboundSessionActivity;
import com.kks24.askme_android.model.Question;
import com.kks24.askme_android.utility.AlertDialogManager;
//import com.kks.askme.utility.AlertDialogManager;
import com.kks24.askme_android.utility.JSONParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kks24.askme_android.inteface.QuestionInterface;
import com.kks24.askme_android.model.Question;

public class QuestionService implements QuestionInterface {
	private String retrieveQuestionListURL = "RetrieveAllQuestion";
	private String createQuestionURL = "CreateQuestionServlet";
	
	private Context context;
	ProgressDialog pd;
	private Activity act;
	
	public QuestionService(Context c ,Activity a){
		this.context = c;
		this.act = a;
		this.retrieveQuestionListURL = "http://" + IPAddressClass.getIPAddress() + "/" + retrieveQuestionListURL;
		this.createQuestionURL = "http://" + IPAddressClass.getIPAddress() + "/" + createQuestionURL;
		
	}
	
	@Override
	public void listQuestion() {
		// TODO Auto-generated method stub
		AsyncTask<String, Void, ArrayList<Question>> restCallTask = new AsyncTask<String, Void, ArrayList<Question>>() {

				ArrayList<Question> qnList;
				
				@Override
				protected void onPreExecute() {
					pd =  ProgressDialog.show(act, "", "Loading Questions", true);
					
				}

				@Override
				protected ArrayList<Question> doInBackground(String... params) {
					RestTemplate restTemplate = new RestTemplate();
					
					restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
					HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
					HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();
	        		restTemplate.getMessageConverters().add(formHttpMessageConverter);
	        		restTemplate.getMessageConverters().add(stringHttpMessageConverternew);
	        		
			        String jsonString = restTemplate.getForObject(retrieveQuestionListURL, String.class);
			        Log.e("list", jsonString);
			        if(jsonString.equals("[]") || jsonString.equals("[ ]")){
			        	qnList = null;
	                }else{

	                	qnList = JSONParser.convertToObject(new TypeReference<ArrayList<Question>>(){}, jsonString);
	                }
			        
					return qnList;
				}
				
				@Override
	            protected void onPostExecute(ArrayList<Question> qnList) {

					if(qnList!=null){
					Log.e("he", Integer.toString(qnList.size()));
					((QuestionActivity) context).setQuestionList(qnList);
					pd.dismiss();

					}else{

						pd.dismiss();
						AlertDialogManager.showAlertDialog((QuestionActivity) context, "No Question", "History not avaible.", false);
					}
					
						pd.dismiss();
						
//					activity.setCategoryList(categoryList);
//					activity.getChooseCategoryButton().setEnabled(true);
					
	            }
				
			};
			
			restCallTask.execute(retrieveQuestionListURL);
		}
	@Override
	public void createQuestion(final Question question) {
		// TODO Auto-generated method stub
		AsyncTask<String, Void, Boolean> task = new AsyncTask<String, Void, Boolean>() {
			String jsonString;
			Boolean result;
			String sth;
			@Override
			protected void onPreExecute() {
				pd =  ProgressDialog.show(((PostQuestionActivity)context), "", "Posting Your Question", true);
				
			}
				
			@Override
			protected Boolean doInBackground(String... param) {
				try {
					RestTemplate restTemplate = new RestTemplate();
					restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
					jsonString = JSONParser.convertToJSON(question);
					Log.e("question", jsonString);
					HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
					HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();

	        		restTemplate.getMessageConverters().add(formHttpMessageConverter);
	        		restTemplate.getMessageConverters().add(stringHttpMessageConverternew);
	        		
	        		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
	        		map.add("jsonString", jsonString);
	        		sth = restTemplate.postForObject(param[0], map, String.class);
					Log.e("Return before post", sth);

	        		result = JSONParser.convertToObject(new TypeReference<Boolean>(){}, sth);
	        														
	        		
				} catch (HttpClientErrorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return result;

			}
			
			protected void onPostExecute(Boolean v) {
				
				if(v){

					Toast.makeText(((PostQuestionActivity)context), "Question Submitted", Toast.LENGTH_SHORT).show();	
					((PostQuestionActivity)context).goMain();
					
				}else{
					
					AlertDialog.Builder builder = new AlertDialog.Builder(context);
    	        	builder.setMessage("Please try again later").setTitle("Unable to submit question");
    	        	
    	        	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
    	                public void onClick(DialogInterface dialog, int id) {

    						((PostQuestionActivity)context).goMain();
    	                }
    	            });       	
    	        	AlertDialog dialog = builder.create();
    	        	dialog.show();
				}				
				
				if (pd!=null) {
					pd.dismiss();					
				}
				
			}
				
		};
		task.execute(createQuestionURL);
	}
	
	protected String convertToJSON(Object object) {
		ObjectMapper mapper = new ObjectMapper();
	    String jsonResponse = "";
	    try {
			jsonResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	    return jsonResponse;
	}
}
