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
public class S_Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public S_Register() {
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
		String fname = request.getParameter("fname");
		String mname = request.getParameter("mname");
	    String lname = request.getParameter("lname");
	    String gender = request.getParameter("gender");
	    String year = request.getParameter("year");
	    String dept = request.getParameter("dept");
	    String dob = request.getParameter("db");
	    String mobile = request.getParameter("mnum");
	    String email = request.getParameter("email");
	    String rollno = request.getParameter("id");
	    String username = fname.substring(0, 2).toLowerCase()+lname.substring(0, 2).toLowerCase()+strdate2;
	    String password = request.getParameter("password");
	    String cpassword = request.getParameter("cpassword");
	    Connection con=null;
	    if(password.equals(cpassword)) {
			try {
				 con = new DBConnection().getConnection();
				 PreparedStatement ps = con.prepareStatement("insert into student(fname,mname,lname,gender,dob,mobile,email,roll,uname,password,year,dept) values(?,?,?,?,?,?,?,?,?,?,?,?)");
				 ps.setString(1,fname);
				 ps.setString(2,mname);
				 ps.setString(3,lname);
				 ps.setString(4,gender);
				 ps.setString(5,dob);
				 ps.setString(6,mobile);
				 ps.setString(7,email);
				 ps.setString(8,rollno);
				 ps.setString(9,username);
				 ps.setString(10,password);
				 ps.setString(11,year);
				 ps.setString(12,dept);
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
				  out.println("To login with new Username and Password<a href=Student_login.html>Click here</a>"
				  );
				  out.println("</center>");
			         out.println("</body>");
			         out.println("</html>");
				 //response.sendRedirect("Student_login.html");
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
	     
	         out.println("Passwords should be same<a href=Student_registration.html>Click here</a>");
	     }
		
	}

}
