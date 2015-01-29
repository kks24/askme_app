package com.kks24.askme_android.adapter.model;

import java.util.ArrayList;

import com.kks24.askme_android.R;
import com.kks24.askme_android.model.Question;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class QuestionAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Question> qnItems;

	public QuestionAdapter(Context context, ArrayList<Question> qnItems) {
		this.context = context;
		this.qnItems = qnItems;	
	}

	@Override
	public int getCount() {
		return qnItems.size();
	}

	@Override
	public Object getItem(int position) {
		return qnItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.question_list, null);
		}
		
		TextView questionTitle = (TextView) convertView.findViewById(R.id.questionTitle);
		questionTitle.setText(qnItems.get(position).getQuestionID() + ". " +qnItems.get(position).getQuestionTitle());
		
		TextView questionPostedBy = (TextView) convertView.findViewById(R.id.questionPostedBy);
		questionPostedBy.setText("Posted On : " + qnItems.get(position).getQuestionDate() + "  By " +qnItems.get(position).getQuestionPostedBy());
		
		TextView questionContent = (TextView) convertView.findViewById(R.id.questionContent);
		questionContent.setText(qnItems.get(position).getQuestionContent());
		
//		TextView foodDriveLocation = (TextView) convertView.findViewById(R.id.foodDriveLocation);
//		foodDriveLocation.setText("Location : " + navDrawerItems.get(position).getLocation());		
		
//		TextView foodDriveQuantity = (TextView) convertView.findViewById(R.id.foodDriveQuantity);		
//		foodDriveQuantity.setText("Total Item(s) Scanned: " + navDrawerItems.get(position) + "");
		
		return convertView;
	}

}
