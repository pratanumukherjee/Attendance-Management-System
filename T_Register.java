package com.attendanceMS.backend;
import com.attendanceMS.dbConnection.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date; 

/**
 * Servlet implementation class S_Register
 */
public class T_Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public T_Register() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		Date date = new Date();
		     String strdate = date.toString();
		   
		     String strdate2 = strdate.substring(11, 13)+strdate.substring(14, 16)+strdate.substring(17, 19);
		     System.out.println(strdate2+" ");
		     String firstname = request.getParameter("firstname");
		String midName = request.getParameter("midName");
	    String lastname = request.getParameter("lastname");
	    System.out.println(firstname+" "+midName+" "+lastname+" ");
	    String gender = request.getParameter("gender");
	    System.out.println(gender+" ");
	    String Dob = request.getParameter("Dob");
	    System.out.println(Dob+" ");
	    String mobile = request.getParameter("mnum");
	    System.out.println(mobile+" ");
	    String email = request.getParameter("email");
	    System.out.println(email+" ");
	    String Teacher_id = request.getParameter("id");
	    System.out.println(Teacher_id+" ");
	   
	    String username = firstname.substring(0, 2).toLowerCase()+lastname.substring(0, 2).toLowerCase()+strdate2;
	    System.out.println(username+" ");
	    String password = request.getParameter("password");
	    System.out.println(password+" ");
	    String cpassword = request.getParameter("conpassword");
	    System.out.println(cpassword+" ");
	    
	   
	    
	   
	    
	    
	    
	    
	    
	    Connection con=null;
	    if(password.equals(cpassword)) {
			try {
				 con = new DBConnection().getConnection();
				 PreparedStatement ps = con.prepareStatement("insert into teacher(firstname,midName,lastname,gender,Dob,mobile,email,Teacherid,uname,password) values(?,?,?,?,?,?,?,?,?,?)");
				 ps.setString(1,firstname);
				 ps.setString(2,midName);
				 ps.setString(3,lastname);
				 ps.setString(4,gender);
				 ps.setString(5,Dob);
				 ps.setString(6,mobile);
				 ps.setString(7,email);
				 ps.setString(8,Teacher_id);
				 ps.setString(9,username);
				 ps.setString(10,password);
				 ps.executeUpdate();
				 
				 out.println("<html>");
		         out.println("<head>");      
		         out.println("<title>Registration Result</title>");    
		         out.println("</head>");
		         out.println("<style>\r\n" + 
		          		"body\r\n" + 
		          		"{\r\n" + 
		          		"	background-image:linear-gradient(rgba(0,0,0,0.5),rgba(0,0,0,0.5))\r\n" + 
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
		         out.println("<h1>Thanks for Registering with us :</h1>");
				  out.println("Your Username for login: "+username);
				  out.println("To login with new Username and Password<a href=Teacher_Login.html>Click here</a>"
				  );
				  out.println("</center>");
			         out.println("</body>");
			         out.println("</html>");
				 //response.sendRedirect("Teacher_Login.html");
			}catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	    else { 
	    	 out.println("<html>");
	         out.println("<head>");      
	         out.println("<title>Passwords does not match</title>");    
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
	         out.println("<h1>Please try again later</h1>");
	     
	         out.println("Passwords should be same<a href=Teacher_Registration.html>Click here</a>");
	     }
		
	}

}
