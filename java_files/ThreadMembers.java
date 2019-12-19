

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
@WebServlet("/ThreadMembers")
public class ThreadMembers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThreadMembers() {
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
				
				try(PreparedStatement pStmt = conn.prepareStatement("with t as (select uid from participants where thread_id=? order by timestamp) "
						+ "select name from t natural join users;");)
				{
					System.out.println("hello1111");
//					String uid = (String) session.getAttribute("uid");
					pStmt.setInt(1,thread_id);
					
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
						System.out.println("hello3");
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
						System.out.println("hello");
						System.out.println(finalobj.toString());
						out.println(finalobj.toString());
						//out.println("Objective Achieved!");
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