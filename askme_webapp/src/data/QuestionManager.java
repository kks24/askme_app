package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Question;

public class QuestionManager {
	
	public ArrayList<Question> retrieveAllQuestion(){
		String sql = "SELECT * FROM ASKME_DB.question";
		ArrayList<Question> questionList=new ArrayList<Question>();
		try {
			Connection conn = DbConnection.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			if(rs != null){
				while(rs.next()){
					Question question = new Question();
					question.setQuestionID(rs.getInt("question_id"));
					question.setQuestionTitle(rs.getString("question_title"));
					question.setQuestionContent(rs.getString("question_content"));
					question.setQuestionDate(rs.getString("question_date"));
					question.setQuestionPostedBy(rs.getString("question_postedby"));
					
					questionList.add(question);

				}
				ps.close();
				conn.close();
				return questionList;
			}
			else					
			{
				ps.close();
				conn.close();
				return null;					
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return questionList;
	}
	
	public boolean createQuestion(Question question) {
		String sql = "INSERT INTO ASKME_DB.question (question_title,question_content,question_date,question_postedby) VALUES(?, ?, ?, ?)";

		try { 
			Connection conn = DbConnection.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, question.getQuestionTitle());
			ps.setString(2, question.getQuestionContent());
			ps.setString(3, question.getQuestionDate());
			ps.setString(4, question.getQuestionPostedBy());
			ps.executeUpdate();
			ps.close();
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
