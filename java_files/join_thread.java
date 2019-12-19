

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

/**
 * Servlet implementation class join_thread
 */
@WebServlet("/join_thread")
public class join_thread extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public join_thread() {
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
		else{
			try (Connection conn = DriverManager.getConnection(Config.url, Config.user, Config.password))
			{
				conn.setAutoCommit(false);
				
				try(PreparedStatement pStmt1 = conn.prepareStatement("insert into participants values ( ? , ? , NOW())");
					PreparedStatement pStmt2 = conn.prepareStatement("insert into questions values (DEFAULT, ?, ?, NOW(), ?)");
					)
				{
					
					String uid = (String) session.getAttribute("uid");
					String question = request.getParameter("question");
					int thread_id = Integer.parseInt(request.getParameter("thread_id"));
				
					pStmt1.setInt(1,thread_id);
					pStmt1.setString(2,uid);
					pStmt1.executeUpdate();
					
					pStmt2.setInt(1,thread_id);
					pStmt2.setString(2,question);
					pStmt2.setString(3,uid);
					pStmt2.executeUpdate();
					
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
