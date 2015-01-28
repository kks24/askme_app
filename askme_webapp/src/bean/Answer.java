package bean;

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
		
	}
	

}
