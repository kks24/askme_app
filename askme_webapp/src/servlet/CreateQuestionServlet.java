package servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Question;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.QuestionManager;

/**
 * Servlet implementation class CreateQuestionServlet
 */
@WebServlet("/CreateQuestionServlet")
public class CreateQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jsonString = request.getParameter("jsonString");
		
		Question question = fromJSON(new TypeReference<Question>(){}, jsonString);
		QuestionManager questionManager = new QuestionManager();
		boolean result = false;
		result = questionManager.createQuestion(question);

		
		 ObjectMapper mapper = new ObjectMapper();
		    String jsonResponse = "";
		    try {
				jsonResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		    OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
			writer.write(jsonResponse);
			writer.flush();
		    writer.close();
		
//		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
//		writer.write("OK");
//		writer.flush();
//	    writer.close();
	}
	
	public static <T> T fromJSON(final TypeReference<T> type, final String dataString) {
	    T data = null;	 
	    try {
	    	data = new ObjectMapper().readValue(dataString, type);
	    } catch (Exception e) {	       
	    }
    	return data;
	}
	

}
