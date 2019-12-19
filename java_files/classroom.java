

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
 * Servlet implementation class classroom
 */
@WebServlet("/classroom")
public class classroom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public classroom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
		HttpSession session = request.getSession();
		
		String course_id=request.getParameter("course_id");
		String sec_id=request.getParameter("sec_id");
		String semester=request.getParameter("semester");
		int year= Integer.parseInt(request.getParameter("year")) ;
		
		PrintWriter out = response.getWriter();
		if(session.getAttribute("uid") == null) { //not logged in
			response.sendRedirect("Login");
		}
		else{
			try (Connection conn = DriverManager.getConnection(Config.url, Config.user, Config.password))
			{
				System.out.println("hello112");
				conn.setAutoCommit(false);
				
				try(PreparedStatement pStmt = conn.prepareStatement("select uid,name from takes natural join users where course_id= ? and sec_id= ? and year= ? and semester= ? ");)
				{
					
					pStmt.setString(1,course_id);
					pStmt.setString(2, sec_id);
					pStmt.setInt(3,year);
					pStmt.setString(4,semester);
					
					ResultSet rs = pStmt.executeQuery();
					conn.commit();
					
					JSONArray body  = new JSONArray();
					
					while(rs.next()) {
						JSONObject details = new JSONObject();
						
						details.put("uid", rs.getString(1));
						details.put("name", rs.getString(2));
						body.put(details);
					}
					
					out.println(body);
					
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
