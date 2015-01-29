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
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kks24.askme_android.AnswerActivity;
import com.kks24.askme_android.IPAddressClass;
import com.kks24.askme_android.PostAnswerActivity;
import com.kks24.askme_android.PostQuestionActivity;
import com.kks24.askme_android.QuestionActivity;
import com.kks24.askme_android.inteface.AnswerInterface;
import com.kks24.askme_android.model.Answer;
import com.kks24.askme_android.utility.AlertDialogManager;
import com.kks24.askme_android.utility.JSONParser;

public class AnswerService implements AnswerInterface {
	
	private String retrieveAnswerListURL = "RetrieveAnswer";
	private String createAnswerURL = "CreateAnswerServlet";
	
	private Context context;
	ProgressDialog pd;
	private Activity act;
	
	public AnswerService(Context c ,Activity a){
		this.context = c;
		this.act = a;
		this.retrieveAnswerListURL = "http://" + IPAddressClass.getIPAddress() + "/" + retrieveAnswerListURL;
		this.createAnswerURL = "http://" + IPAddressClass.getIPAddress() + "/" + createAnswerURL;
		
	}
	

	@Override
	public void listAnswer(final int questionID) {
		// TODO Auto-generated method stub
		AsyncTask<String, Void, ArrayList<Answer>> restCallTask = new AsyncTask<String, Void, ArrayList<Answer>>() {
			String jsonString;
			
			@Override
			protected void onPreExecute() {
				pd =  ProgressDialog.show(act, "", "Retriving Answers", true);
				
			}
			
			@Override
			protected ArrayList<Answer> doInBackground(String... params) {
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
				jsonString = convertToJSON(questionID);
				Log.e("cateName", jsonString);		
				HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
				HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();

        		restTemplate.getMessageConverters().add(formHttpMessageConverter);
        		restTemplate.getMessageConverters().add(stringHttpMessageConverternew);
        		
        		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        		map.add("jsonString", jsonString);
        		String jsonString2 = restTemplate.postForObject(params[0], map, String.class);
    			ArrayList<Answer> result = fromJSON(new TypeReference<ArrayList<Answer>>(){}, jsonString2);

				return result;
			}
			
			@SuppressWarnings("null")
			protected void onPostExecute(ArrayList<Answer> answerList) {
				
				if(answerList!=null || !answerList.toString().equals("[]")){
					((AnswerActivity) context).setAnswerList(answerList);

					pd.dismiss();
				}
				else{

					pd.dismiss();
					AlertDialogManager.showAlertDialog((AnswerActivity) context, "No Answer", "No one answered this question.", false);
				}
				
				if(pd!=null){

					pd.dismiss();
				}
				
            }
			
		};
		
		restCallTask.execute(retrieveAnswerListURL);
		
	}

	@Override
	public void createQuestion(final Answer answer) {
		// TODO Auto-generated method stub
		AsyncTask<String, Void, Boolean> task = new AsyncTask<String, Void, Boolean>() {
			String jsonString;
			Boolean result;
			String sth;
			@Override
			protected void onPreExecute() {
				pd =  ProgressDialog.show(((PostAnswerActivity)context), "", "Posting Your Answer", true);
				
			}
				
			@Override
			protected Boolean doInBackground(String... param) {
				try {
					RestTemplate restTemplate = new RestTemplate();
					restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
					jsonString = JSONParser.convertToJSON(answer);
					Log.e("answer", jsonString);
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

					Toast.makeText(((PostAnswerActivity)context), "Answer Submitted", Toast.LENGTH_SHORT).show();	
					((PostAnswerActivity)context).goMain();
					
				}else{
					
					AlertDialog.Builder builder = new AlertDialog.Builder(context);
    	        	builder.setMessage("Please try again later").setTitle("Unable to submit answer");
    	        	
    	        	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
    	                public void onClick(DialogInterface dialog, int id) {

    						((PostAnswerActivity)context).goMain();
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
		task.execute(createAnswerURL);
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
	
	public static <T> T fromJSON(final TypeReference<T> type, final String dataString) {
	    T data = null;	 
	    try {
	    	data = new ObjectMapper().readValue(dataString, type);
	    } catch (Exception e) {	       
	    }
    	return data;
	}

}
