package bean;

public class Question {
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
		
	}

}
