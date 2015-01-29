package com.kks24.askme_android.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable{
	private int questionID;
	private String questionTitle;
	private String questionContent;
	private String questionDate;
	private String questionPostedBy;
	
	
	public int getQuestionID() {
		return questionID;
	}
	
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	
	public String getQuestionTitle() {
		return questionTitle;
	}


	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	
	public String getQuestionContent() {
		return questionContent;
	}


	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	
	public String getQuestionDate() {
		return questionDate;
	}
	
	public void setQuestionDate(String questionDate) {
		this.questionDate = questionDate;
	}
	
	public String getQuestionPostedBy() {
		return questionPostedBy;
	}


	public void setQuestionPostedBy(String questionPostedBy) {
		this.questionPostedBy = questionPostedBy;
	}
	
	public Question (){
		super();
	}
	
	public Question(Parcel in) {

		this.questionID = in.readInt();
		this.questionTitle = in.readString();
		this.questionContent = in.readString();
		this.questionDate = in.readString();
		this.questionPostedBy = in.readString();
	}


	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel out, int flags) {
		// TODO Auto-generated method stub
	
		out.writeInt(questionID);
		out.writeString(questionTitle);
		out.writeString(questionContent);
		out.writeString(questionDate);
		out.writeString(questionPostedBy);
	}
	
	public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
	
		public Question createFromParcel(Parcel in) {
			return new Question(in);
		}
	
		public Question[] newArray(int size) {
			return new Question[size];
		}
	};
}
	
