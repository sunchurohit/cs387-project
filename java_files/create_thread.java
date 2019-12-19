

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class create_thread
 */
@WebServlet("/create_thread")
public class create_thread extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public create_thread() {
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
				
				try(PreparedStatement pStmt1 = conn.prepareStatement("insert into thread values (DEFAULT, ?, ?, ?, ? , ? , ?)");
					PreparedStatement pStmt2 = conn.prepareStatement("insert into participants values ( ? , ? , NOW())");
					PreparedStatement pStmt3 = conn.prepareStatement("select LASTVAL()");
					PreparedStatement pStmt4 = conn.prepareStatement("insert into questions values (DEFAULT, ?, ?, NOW(), ?)");
					)
				{
					
					String uid = (String) session.getAttribute("uid");
					String subject = request.getParameter("subject");
					String question = request.getParameter("question");
					String course_id = request.getParameter("course_id");
					String sec_id= request.getParameter("sec_id");
					int type = Integer.parseInt(request.getParameter("type")) ;
					int year = Integer.parseInt(request.getParameter("year")) ;
					String semester = request.getParameter("semester");

					
					pStmt1.setString(1,course_id);
					pStmt1.setString(2, sec_id);
					pStmt1.setInt(3,year);
					pStmt1.setString(4,semester);
					pStmt1.setInt(5, type);
					pStmt1.setString(6, subject);
					
					pStmt1.executeUpdate();					
					ResultSet rs = pStmt3.executeQuery();
					rs.next();
					
					int thread_id= rs.getInt(1);
					
					pStmt2.setInt(1,thread_id);
					pStmt2.setString(2,uid);
					
					pStmt2.executeUpdate();
					
					pStmt4.setInt(1,thread_id);
					pStmt4.setString(2,question);
					pStmt4.setString(3,uid);
					
					pStmt4.executeUpdate();
					
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

}
