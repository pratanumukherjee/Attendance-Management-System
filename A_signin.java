package com.attendanceMS.backend;
import com.attendanceMS.dbConnection.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class A_signin
 */
public class A_signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public A_signin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("uname");
		String password = request.getParameter("pwd");
		Connection con = null;
		try {
			con = new DBConnection().getConnection();
			 PreparedStatement ps=con.prepareStatement("select * from admincell where uname=? and password = ?");
			 ps.setString(1, username);
			 ps.setString(2, password);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next()) {
				 HttpSession session = request.getSession();
				 session.setAttribute("username",username);
				 RequestDispatcher rd = request.getRequestDispatcher("Admin_Official_Portal.html");
				 rd.include(request, response);
			 }
			 else {
				 
				 out.println("<html>");
		         out.println("<head>");      
		         out.println("<title>Invalid  Credentials... Please enter correct username/password</title>");    
		         out.println("</head>");
		         out.println("<style>\r\n" + 
		          		"body\r\n" + 
		          		"{\r\n" + 
		          		"	background-image:linear-gradient(rgba(0,0,0,0.5),rgba(0,0,0,0.5)) \r\n" + 
		          		"	height: 100vh;\r\n" + 
		          		"	\r\n" + 
		          		"	background-size: cover;\r\n" + 
		          		"	background-position: center\r\n" + 
		          		"	font-family: sans-serif;\r\n" + 
		          		"}\r\n" + 
		          		"\r\n" + 
		          		"</style>");
		         out.println("<body>");
		         out.println("<center>");
		         out.println("Invalid  Credentials... Please enter correct username/password");
		         out.println("<h1>Please try again later</h1>");
		     
		         out.println("<a href=Admin_Login.htmll>Click here</a>");
			 }
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
