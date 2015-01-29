package com.kks24.askme_android;

import java.util.ArrayList;

import com.kks24.askme_android.utility.ConnectionDetector;
import com.kks24.askme_android.adapter.model.QuestionAdapter;
import com.kks24.askme_android.model.Question;
import com.kks24.askme_android.service.impl.QuestionService;
import com.kks24.askme_android.utility.AlertDialogManager;
import com.kks24.askme_android.R;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

@ContentView(R.layout.activity_question)
public class QuestionActivity extends RoboActivity {
	
	@InjectView(R.id.question_list) ListView viewList;
	@InjectView(R.id.button_postQn) Button button_postQn;
	QuestionService qs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
		setTitle("Questions");
		
		if(ConnectionDetector.isConnectingToInternet(getApplicationContext())){
			qs = new QuestionService(this,this);
			qs.listQuestion();
		}
		else{
			
		}
		button_postQn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goPost();
			}
		});
		
	}
	
	public void setQuestionList(ArrayList<Question> list){
		if(list!=null){
			QuestionAdapter questionAdapter = new QuestionAdapter(QuestionActivity.this, list);
			
			viewList.setAdapter(questionAdapter);
			viewList.setOnItemClickListener(new OnItemClickListener(){
	
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position,
						long id) {
					// TODO Auto-generated method stub
					Question questionSelected = (Question)(viewList.getItemAtPosition(position));
					//Intent i = new Intent(this, AnswerActivity.class);
					int qID = questionSelected.getQuestionID();
					String qnTitle = questionSelected.getQuestionTitle();
					String qnPostedBy = questionSelected.getQuestionPostedBy();
					String qnContent = questionSelected.getQuestionContent();
					goIndex(qID, qnTitle, qnPostedBy, qnContent);
					//i.putExtra("questionID", qID);
					//startActivity(i);
					//finish();
					//lss.retrieveSelectedInbound(foodDriveSelected.getFooddriveID());
				}
				
			});
		}
		else{
			AlertDialogManager.showAlertDialog(QuestionActivity.this, "Error", "Kindly Re-launch this app thanks.", false);
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void goIndex(int qID, String qnTitle, String qnPostedBy, String qnContent){
        	Intent i = new Intent(this, AnswerActivity.class);
        	i.putExtra("questionID", qID);
        	i.putExtra("qTitle", qnTitle);
        	i.putExtra("qPostedBy", qnPostedBy);
        	i.putExtra("qContent", qnContent);
    		startActivity(i);
    		finish();
    }
	
	public void goPost(){
		Intent intent = new Intent(this, PostQuestionActivity.class);
		startActivity(intent);
		finish();
	}
}
