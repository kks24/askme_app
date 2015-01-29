package com.kks24.askme_android;

import java.util.Date;

import com.kks24.askme_android.model.Question;
import com.kks24.askme_android.service.impl.AnswerService;
import com.kks24.askme_android.service.impl.QuestionService;
import com.kks24.askme_android.utility.AlertDialogManager;
import com.kks24.askme_android.utility.UtilityTools;
import com.kks24.askme_android.R;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

@ContentView(R.layout.activity_post_question)
public class PostQuestionActivity extends RoboActivity {
	
	@InjectView(R.id.editText_title) EditText editText_title;
    @InjectView(R.id.editText_qncontent) EditText editText_qncontent;
    @InjectView(R.id.editText_qnname) EditText editText_qname;
    @InjectView(R.id.button_submitQn) Button button_submitQn;
    QuestionService qnSvc;
    UtilityTools ul;

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_question);
		setTitle("Post Question");
		qnSvc = new QuestionService(this,this);
		
		button_submitQn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String qnTitle = editText_title.getText().toString();
        		String qnContent = editText_qncontent.getText().toString();
        		String qnPostedBy = editText_qname.getText().toString();
        		@SuppressWarnings("static-access")
				String currentTime = ul.getDateNTime();
        		
        		if(qnTitle.equals("")||qnContent.equals("")||qnPostedBy.equals("")){
        			
					AlertDialogManager.showAlertDialog(PostQuestionActivity.this, "Please fill up the blanks", "Empty fields", false);
        		}
        		else
        		{
        			Question question = new Question();
        			question.setQuestionTitle(qnTitle);
        			question.setQuestionContent(qnContent);
        			question.setQuestionDate(currentTime);
        			question.setQuestionPostedBy(qnPostedBy);
        			
        			qnSvc.createQuestion(question);
        		}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post_question, menu);
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
	
	public void goMain(){
    	Intent intent = new Intent(this, QuestionActivity.class);
		startActivity(intent);
		finish();
    }
}
