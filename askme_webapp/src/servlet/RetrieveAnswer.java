package servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Answer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.AnswerManager;

/**
 * Servlet implementation class RetrieveAnswer
 */
@WebServlet("/RetrieveAnswer")
public class RetrieveAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveAnswer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jsonString = request.getParameter("jsonString");
		String qnID = fromJSON(new TypeReference<String>(){}, jsonString);
		AnswerManager ansM = new AnswerManager();
		ArrayList<Answer> ansList = ansM.retrieveAllAnswer(Integer.parseInt(qnID));
//		for (int i=0; i < proDesc.size(); i++){
//			System.out.println(proDesc.size());
//			System.out.println(proDesc.get(i).getDescriptionName());
//		}
//		if (proDesc!=null){
//			session.setAttribute("loginResult", login);
//			RequestDispatcher rd = request.getRequestDispatcher("RetrieveLoginServlet");
//			rd.forward(request, response);
			response.setStatus(HttpServletResponse.SC_OK);
		    ObjectMapper mapper = new ObjectMapper();
		    String jsonResponse = "";
		    try {
				jsonResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ansList);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		    OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
			writer.write(jsonResponse);
			writer.flush();
		    writer.close();
//		}
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
