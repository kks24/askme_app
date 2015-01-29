package com.kks24.askme_android;

import com.kks24.askme_android.model.Answer;
import com.kks24.askme_android.model.Question;
import com.kks24.askme_android.service.impl.AnswerService;
import com.kks24.askme_android.service.impl.QuestionService;
import com.kks24.askme_android.utility.AlertDialogManager;
import com.kks24.askme_android.utility.UtilityTools;

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

@ContentView(R.layout.activity_post_answer)
public class PostAnswerActivity extends RoboActivity {

    @InjectView(R.id.editText_anscontent) EditText editText_anscontent;
    @InjectView(R.id.editText_ansname) EditText editText_ansname;
    @InjectView(R.id.button_submitAns) Button button_submitAns;
    AnswerService ansSvc;
    UtilityTools ul;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_answer);
		setTitle("Post Answer");
		ansSvc = new AnswerService(this,this);
		
		final Bundle b = getIntent().getExtras();
		
		button_submitAns.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
        		String ansContent = editText_anscontent.getText().toString();
        		String ansPostedBy = editText_ansname.getText().toString();
        		@SuppressWarnings("static-access")
				String currentTime = ul.getDateNTime();
        		
        		if(ansContent.equals("")||ansPostedBy.equals("")){
        			
					AlertDialogManager.showAlertDialog(PostAnswerActivity.this, "Please fill up the blanks", "Empty fields", false);
        		}
        		else
        		{
        			Answer answer = new Answer();
        			answer.setAnswerContent(ansContent);
        			answer.setAnswerDate(currentTime);
        			answer.setAnswerPostedBy(ansPostedBy);
        			answer.setQuestionID(b.getInt("qID"));
        			
        			ansSvc.createQuestion(answer);
        		}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post_answer, menu);
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
		finish();
    }
}
