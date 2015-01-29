package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Answer;

public class AnswerManager {
	public ArrayList<Answer> retrieveAllAnswer(int questionID){
		String sql = "SELECT * FROM ASKME_DB.answer WHERE question_id = " + questionID;
		ArrayList<Answer> answerList=new ArrayList<Answer>();
		try {
			Connection conn = DbConnection.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			if(rs != null){
				while(rs.next()){
					Answer answer = new Answer();
					answer.setAnswerID(rs.getInt("answer_id"));
					answer.setAnswerContent(rs.getString("answer_content"));
					answer.setAnswerDate(rs.getString("answer_date"));
					answer.setAnswerPostedBy(rs.getString("answer_postedby"));
					
					answerList.add(answer);

				}
				ps.close();
				conn.close();
				return answerList;
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
		return answerList;
	}
	
	public boolean createAnswer(Answer answer) {
		String sql = "INSERT INTO `askme_db`.`answer` (`answer_content`, `answer_date`, `answer_postedby`, `question_id`) VALUES (?, ?, ?, ?)";

		try { 
			Connection conn = DbConnection.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, answer.getAnswerContent());
			ps.setString(2, answer.getAnswerDate());
			ps.setString(3, answer.getAnswerPostedBy());
			ps.setInt(4, answer.getQuestionID());
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
