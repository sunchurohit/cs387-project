

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
 * Servlet implementation class AddAnswer
 */
@WebServlet("/AddAnswer")
public class AddAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAnswer() {
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
		int q_id = Integer.parseInt(request.getParameter("q_id"));
		int thread_id = Integer.parseInt(request.getParameter("thread_id"));
		String text= (String) request.getParameter("text");
		
		try (Connection conn = DriverManager.getConnection(Config.url, Config.user, Config.password))
		{

			conn.setAutoCommit(false);
			
			try(PreparedStatement pStmt = conn.prepareStatement("insert into answers values (?,?,DEfAULT,?,NOW(),?);");)
			{

				pStmt.setInt(1, q_id);
				pStmt.setInt(2, thread_id);
				pStmt.setString(3, text);
				pStmt.setString(4,uid);
				int executed= pStmt.executeUpdate();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
