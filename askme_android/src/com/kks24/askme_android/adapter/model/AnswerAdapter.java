package com.kks24.askme_android.adapter.model;

import java.util.ArrayList;

import com.kks24.askme_android.R;
import com.kks24.askme_android.model.Answer;
import com.kks24.askme_android.model.Question;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AnswerAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Answer> ansItems;

	public AnswerAdapter(Context context, ArrayList<Answer> ansItems) {
		this.context = context;
		this.ansItems = ansItems;	
	}

	@Override
	public int getCount() {
		return ansItems.size();
	}

	@Override
	public Object getItem(int position) {
		return ansItems.get(position);
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
			convertView = mInflater.inflate(R.layout.answer_list, null);
		}
		
		TextView questionTitle = (TextView) convertView.findViewById(R.id.answerContent);
		questionTitle.setText(ansItems.get(position).getAnswerContent());
		
		TextView questionPostedBy = (TextView) convertView.findViewById(R.id.answerPostedBy);
		questionPostedBy.setText("Posted On : " + ansItems.get(position).getAnswerDate() + "  By " +ansItems.get(position).getAnswerPostedBy());
		
//		TextView foodDriveLocation = (TextView) convertView.findViewById(R.id.foodDriveLocation);
//		foodDriveLocation.setText("Location : " + navDrawerItems.get(position).getLocation());		
		
//		TextView foodDriveQuantity = (TextView) convertView.findViewById(R.id.foodDriveQuantity);		
//		foodDriveQuantity.setText("Total Item(s) Scanned: " + navDrawerItems.get(position) + "");
		
		return convertView;
	}

}
