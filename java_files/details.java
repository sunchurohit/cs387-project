

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

import org.json.JSONObject;

/**
 * Servlet implementation class details
 */
@WebServlet("/details")
public class details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public details() {
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
		
		try (Connection conn = DriverManager.getConnection(Config.url, Config.user, Config.password))
		{

			conn.setAutoCommit(false);
			
			try(PreparedStatement pStmt = conn.prepareStatement("select name from users where uid= ?");)
			{

				pStmt.setString(1, uid);
				ResultSet rs = pStmt.executeQuery();
		
				JSONObject finalobj = new JSONObject();
				
				while(rs.next()) {
					finalobj.put("name", rs.getString(1));
					finalobj.put("uid", uid);
				}
				
				out.println(finalobj);
				
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
