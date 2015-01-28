package servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Question;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.QuestionManager;;

/**
 * Servlet implementation class RetrieveAllQuestion
 */
@WebServlet("/RetrieveAllQuestion")
public class RetrieveAllQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveAllQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		QuestionManager qManager = new QuestionManager();
		ArrayList<Question> qnsList = qManager.retrieveAllQuestion();
		response.setStatus(HttpServletResponse.SC_OK);
				
//	    response.setContentType("text/json");
	    ObjectMapper mapper = new ObjectMapper();
	    String jsonResponse = "";
	    try {
			jsonResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(qnsList);
//			System.out.println(jsonResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	    OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
		writer.write(jsonResponse);
		writer.flush();
	    writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
		
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
