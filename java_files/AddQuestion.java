

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class AddQuestion
 */
@WebServlet("/AddQuestion")
public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		if(session.getAttribute("uid") == null) { //not logged in
			response.sendRedirect("Login");
		}
		String uid = (String) session.getAttribute("uid");
		int thread_id = Integer.parseInt(request.getParameter("thread_id"));
		String text= (String) request.getParameter("text");
		
		try (Connection conn = DriverManager.getConnection(Config.url, Config.user, Config.password))
		{
			System.out.println("hello112");
			conn.setAutoCommit(false);
			
			try(PreparedStatement pStmt = conn.prepareStatement("insert into questions values (DEFAULT,?,?,NOW(),?);");)
			{
				System.out.println("hello1111");
				pStmt.setInt(1, thread_id);
				pStmt.setString(2, text);
				pStmt.setString(3,uid);
				int executed= pStmt.executeUpdate();
				conn.commit();
				
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}