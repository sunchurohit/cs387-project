import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.Scanner ;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AllConversations
 */
@WebServlet("/AllCourses")
public class AllCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllCourses() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		if(session.getAttribute("uid") == null) { //not logged in
			response.sendRedirect("Login");
		}
		else{
			try (Connection conn = DriverManager.getConnection(Config.url, Config.user, Config.password))
			{
				System.out.println("hello112");
				conn.setAutoCommit(false);
				
				try(PreparedStatement pStmt1 = conn.prepareStatement("with t as (select course_id,sec_id,semester,year,brownie_points from takes where uid=?),\n" + 
						"s as (select * from course natural inner join t),\n" + 
						"r as (select s.course_id,s.sec_id,s.semester,s.year,t1.uid,s.title,s.brownie_points from s,teaches t1 where s.course_id=t1.course_id and\n" + 
						"s.sec_id=t1.sec_id and s.year=t1.year and s.semester=t1.semester) \n" + 
						"select users.uid,name,title,sec_id,course_id,year,semester,brownie_points from r join users on users.uid=r.uid order by r.title;");
					PreparedStatement pStmt2 = conn.prepareStatement("with t as (select course_id,sec_id,semester,year from teaches where uid=?),\n" + 
							"s as (select * from course natural inner join t),\n" + 
							"r as (select s.course_id,s.sec_id,s.semester,s.year,t1.uid,s.title from s,teaches t1 where s.course_id=t1.course_id and\n" + 
							"s.sec_id=t1.sec_id and s.year=t1.year and s.semester=t1.semester) \n" + 
							"select users.uid,name,title,sec_id,course_id,year,semester from r join users on users.uid=r.uid order by r.title;");)
				{
					String role = (String) session.getAttribute("role");
					String uid = (String) session.getAttribute("uid");
					pStmt1.setString(1,uid);
					pStmt2.setString(1,uid);
					ResultSet rs;
					if(role.equals("instructor")) {
						rs = pStmt2.executeQuery();
					}else {
						rs = pStmt1.executeQuery();
					}
					conn.commit();
					// System.out.println(rs);
					if(!rs.isBeforeFirst()) // checks whether rs is null
					{
						JSONObject finalobj = new JSONObject();
						out.println(finalobj);
					}
					else
					{
						JSONObject finalobj = new JSONObject();
						rs.next();
						ResultSetMetaData tsmd = rs.getMetaData();
						int col = tsmd.getColumnCount();
						String temp;
						JSONArray head = new JSONArray();
						for(int i=1;i<=col;i++)
						{
							head.put(tsmd.getColumnName(i));
						}
						try {
							finalobj.put("header", head);
						} catch (Exception e) {
							e.printStackTrace();
						}
						JSONArray body  = new JSONArray();
						
						do
						{
							JSONObject temp1 = new JSONObject();
							for(int i=1;i<=col;i++)
							{
								temp1.put(tsmd.getColumnName(i),rs.getString(i));
							}
							body.put(temp1);
						}
						while(rs.next());
						finalobj.put("data",body);
						out.println(finalobj);
					}
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
	
	/**
	 * For testing other methods in this class.
	 */
}