

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
 * Servlet implementation class AllThreads
 */
@WebServlet("/givepoints")
public class givepoints extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public givepoints() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 //response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		
		int thread_id = Integer.parseInt(request.getParameter("thread_id"));
		
		PrintWriter out = response.getWriter();
		if(session.getAttribute("uid") == null) { //not logged in
			response.sendRedirect("Login");
		}
		else{
			try (Connection conn = DriverManager.getConnection(Config.url, Config.user, Config.password))
			{
				System.out.println("hello112");
				conn.setAutoCommit(false);
				
				try(PreparedStatement pStmt = conn.prepareStatement("with s as (select thread_id,uid from participants where thread_id=?), "
						+ "t as (select s.uid,t1.course_id,t1.sec_id,t1.year,t1.semester from s natural join thread t1) "
						+ "update takes r set brownie_points=brownie_points+10 from t where r.uid=t.uid and r.course_id=t.course_id and"
						+ " r.sec_id=t.sec_id and r.semester=t.semester and r.year=t.year;\n" 
					);)
				{
					System.out.println("hello1111");
//					String uid = (String) session.getAttribute("uid");
					pStmt.setInt(1,thread_id);
					
					int ok=pStmt.executeUpdate();
					conn.commit();
					// System.out.println(rs);
					
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