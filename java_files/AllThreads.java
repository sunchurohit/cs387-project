

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
@WebServlet("/AllThreads")
public class AllThreads extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllThreads() {
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
		
		String course_id = request.getParameter("course_id");
		String sec_id=request.getParameter("sec_id");
		String semester=request.getParameter("semester");
		int year= Integer.parseInt(request.getParameter("year"));
		
		PrintWriter out = response.getWriter();
		if(session.getAttribute("uid") == null) { //not logged in
			response.sendRedirect("Login");
		}
		else{
			try (Connection conn = DriverManager.getConnection(Config.url, Config.user, Config.password))
			{
				conn.setAutoCommit(false);
				
				try(PreparedStatement pStmt = conn.prepareStatement("select t.thread_id,t.subject,p.timestamp,type from thread t,participants p where course_id=? and "
						+ "sec_id=? and year=? and semester=? and (t.type=0 or t.type=1) and t.thread_id"
						+ "=p.thread_id and p.uid=? order by timestamp;");)
				{
					
					String role = (String) session.getAttribute("role");

					String uid=request.getParameter("uid");
					
					pStmt.setString(1,course_id);
					pStmt.setString(2, sec_id);
					pStmt.setInt(3,year);
					pStmt.setString(4,semester);
					pStmt.setString(5, uid);
					
					ResultSet rs = pStmt.executeQuery();
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
							// TODO Auto-generated catch block
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
						out.println(finalobj.toString());
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

}
