package com.kks24.askme_android.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer {
	private String answerContent;
	private String answerDate;
	private int questionID;
	private String answerPostedBy;
	private int answerID;
	
	
	public int getAnswerID() {
		return answerID;
	}

	public void setAnswerID(int answerID) {
		this.answerID = answerID;
	}
	
	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	
	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	
	public String getAnswerDate() {
		return answerDate;
	}


	public void setAnswerDate(String questionContent) {
		this.answerDate = questionContent;
	}
	
	public String getAnswerPostedBy() {
		return answerPostedBy;
	}

	public void setAnswerPostedBy(String answerPostedBy) {
		this.answerPostedBy = answerPostedBy;
	}
	
	public Answer (){
		super();
	}
	
	public Answer(Parcel in) {

		this.answerID = in.readInt();
		this.answerContent = in.readString();
		this.answerDate = in.readString();
		this.answerPostedBy = in.readString();
		this.questionID = in.readInt();
	}


	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void writeToParcel(Parcel out, int flags) {
		// TODO Auto-generated method stub
	
		out.writeInt(answerID);
		out.writeString(answerContent);
		out.writeString(answerDate);
		out.writeString(answerPostedBy);
		out.writeInt(questionID);
	}
	
	public static final Parcelable.Creator<Answer> CREATOR = new Parcelable.Creator<Answer>() {
	
		public Answer createFromParcel(Parcel in) {
			return new Answer(in);
		}
	
		public Answer[] newArray(int size) {
			return new Answer[size];
		}
	};

}
