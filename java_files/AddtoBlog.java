

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

/**
 * Servlet implementation class AddtoBlog
 */
@WebServlet("/AddtoBlog")
public class AddtoBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddtoBlog() {
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
		int thread_id = Integer.parseInt(request.getParameter("thread_id"));
		
		try (Connection conn = DriverManager.getConnection(Config.url, Config.user, Config.password))
		{

			conn.setAutoCommit(false);
			
			try(PreparedStatement pStmt1 = conn.prepareStatement("update thread set type=1 where thread_id=?;");
					PreparedStatement pStmt2 = conn.prepareStatement("insert into participants values ( ? , ? , NOW())");)
			{
				String uid = (String) session.getAttribute("uid");
				pStmt1.setInt(1, thread_id);
				pStmt1.executeUpdate();
				
				
				pStmt2.setInt(1,thread_id);
				pStmt2.setString(2,uid);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
