package com.kks24.askme_android;

import java.util.ArrayList;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import com.kks24.askme_android.adapter.model.AnswerAdapter;
import com.kks24.askme_android.adapter.model.QuestionAdapter;
import com.kks24.askme_android.model.Answer;
import com.kks24.askme_android.model.Question;
import com.kks24.askme_android.service.impl.AnswerService;
import com.kks24.askme_android.service.impl.QuestionService;
import com.kks24.askme_android.utility.AlertDialogManager;
import com.kks24.askme_android.utility.ConnectionDetector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

@ContentView(R.layout.activity_answer)
public class AnswerActivity extends RoboActivity {
	
	@InjectView(R.id.answer_list) ListView viewList;
	@InjectView(R.id.squestionTitle) TextView qTitle;
	@InjectView(R.id.squestionPostedBy) TextView qPostedBy;
	@InjectView(R.id.squestionContent) TextView qContent;
	@InjectView(R.id.button_postAns) Button button_postAns;
	AnswerService aservice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer);
		setTitle("Answer");
		Bundle b = getIntent().getExtras();
		
		qTitle.setText(b.getString("qTitle"));
		qPostedBy.setText(b.getString("qPostedBy"));
		qContent.setText(b.getString("qContent"));
		final int qID = b.getInt("questionID");
		
		
		if(ConnectionDetector.isConnectingToInternet(getApplicationContext())){
			aservice = new AnswerService(this,this);
			aservice.listAnswer(b.getInt("questionID"));
		}
		button_postAns.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goPost(qID);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.answer, menu);
		return true;
	}
	
	public void setAnswerList(ArrayList<Answer> list){
		if(list!=null){
			AnswerAdapter answerAdapter = new AnswerAdapter(AnswerActivity.this, list);
			
			viewList.setAdapter(answerAdapter);
		}
		else{
			AlertDialogManager.showAlertDialog(AnswerActivity.this, "No Answer Given", "No one answered this question, so sorry.", false);
			
		}
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
	
	protected void alertCancel() {
		finishThisActivity();	
	}

	protected void finishThisActivity() {
		finish();
	}
	
	@Override
	public void onBackPressed() {
	    alertCancel();
	}
	
	public void goPost(int qID){
		Intent intent = new Intent(this, PostAnswerActivity.class);
		intent.putExtra("qID", qID);
		startActivity(intent);
		finish();
	}
}
