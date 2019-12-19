

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class open_qa
 */
@WebServlet("/open_qa")
public class open_qa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public open_qa() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
HttpSession session = request.getSession();
		
		PrintWriter out = response.getWriter();
		if(session.getAttribute("uid") == null) { //not logged in
			response.sendRedirect("Login");
		}
		else{
			try (Connection conn = DriverManager.getConnection(Config.url, Config.user, Config.password))
			{
				conn.setAutoCommit(false);
				
				try(PreparedStatement pStmt1 = conn.prepareStatement("select * from questions where thread_id= ? order by timestamp");
					PreparedStatement pStmt2 = conn.prepareStatement("with temp as\n" + 
							"(select q_id from questions where thread_id= ? order by timestamp)\n" + 
							"select q_id,text from answers natural join temp order by q_id,timestamp\n" + 
							"");	
					)
				{
					int thread_id = Integer.parseInt(request.getParameter("thread_id"));
					pStmt1.setInt(1, thread_id);
					pStmt2.setInt(1, thread_id);
					ResultSet rs = pStmt1.executeQuery();
					ResultSet rss = pStmt2.executeQuery();
					
					JSONArray questions = new JSONArray();
					JSONArray answers = new JSONArray();
					
					
					while(rs.next()) {
						JSONObject question = new JSONObject();
						question.put("q_id", rs.getInt(1));
						question.put("text", rs.getString(3));
						questions.put(question);						
						
					}
					
					while(rss.next()) {
						JSONObject answer = new JSONObject();
						answer.put("q_id", rss.getInt(1));
						answer.put("text", rss.getString(2));
						answers.put(answer);
					}
					
					JSONObject result = new JSONObject();
					result.put("ques",questions);
					result.put("ans", answers);
					
					out.println(result);
					
				}
				catch(Exception ex)
				{
					conn.rollback();
					throw ex;
				}
				finally
				{
					conn.setAutoCommit(true);
				}
			}
			catch (Exception sqle)
			{
				System.out.println("Exception : " + sqle);
			}
		}

	}

}
