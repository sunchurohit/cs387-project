import javax.servlet.http.HttpSession;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// PrintWriter out = response.getWriter();
		// out.print(login_form);
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		String uid = request.getParameter("uid");
		String password = request.getParameter("password") ;
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		
		if(session.getAttribute("uid") != null) { //not logged in
			String str = (String)session.getAttribute("role");
			if(str.equals("instructor"))
			{
				out.println(html.instructor);
				session.setAttribute("role","instructor"); 
			}
			else
			{							
				out.println(html.student);
				session.setAttribute("role","student"); 
			}
			return;
		}
		
		if(uid == null || password == null)
		{
//			out.println("<html><body>Failed Authentication. Username / password cannot be blank."
//					+ "</body></html>");
			// out.println("<html><body>helo</body></html>");
			out.print(html.login);
		}
		else
		{
			
			try (Connection conn = DriverManager.getConnection(Config.url, Config.user, Config.password))
			{
				conn.setAutoCommit(false);
				
				try(PreparedStatement pStmt = conn.prepareStatement("select * from users where uid = ? and password = ?;");)
				{
					pStmt.setString(1,uid);
					pStmt.setString(2,password);
					ResultSet rs = pStmt.executeQuery();
					conn.commit();
					// System.out.println(rs);
					if(!rs.isBeforeFirst()) // checks whether rs is null
					{
						out.println("<html><body>Failed Authentication. Incorrect username or password."
								+ "</body></html>");
						out.println(html.login);
					}
					else
					{
						rs.next();
						String str1 = rs.getString(4);
						session=request.getSession();
						session.setAttribute("uid",uid);  
						if(str1.equals("I"))
						{
							out.println(html.instructor);
							session.setAttribute("role","instructor"); 
						}
						else
						{							
							out.println(html.student);
							session.setAttribute("role","student"); 
						}
						
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

}
