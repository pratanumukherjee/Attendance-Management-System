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

import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class Teacher_attendance
 */
public class Teacher_attendance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Teacher_attendance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String date = request.getParameter("db");
		//String dept = request.getParameter("dept");
		HttpSession session = request.getSession();
		 String ts1 =  (String) session.getAttribute("tot_tea");
			int ts = Integer.parseInt(ts1);
		String[] Teacher_id = new String[ts];
		String attendance[] = new String[ts];
	System.out.println(date+" "+Teacher_id+" "+attendance);
		int i = 0, j = 1;
		for (i = 0; i < ts; i++) {

			Teacher_id[i] = request.getParameter("teacher_id" + j);
			attendance[i] = request.getParameter("att" + j);
			j++;
		}
		
		 Connection con=null;
		 try {
			 con = new DBConnection().getConnection();
			 int k = 0;
				
				for (k = 0; k < ts; k++) {
			 PreparedStatement ps = con.prepareStatement("insert into teacher_attendance(date,Teacher_id,attendance) values(?,?,?)");
			 ps.setString(1,date);
			// ps.setString(2,dept);
			 ps.setString(2,Teacher_id[k]);
			 ps.setString(3,attendance[k]);
			 ps.executeUpdate();
				}
			 out.println("<html>");
	         out.println("<head>");
	         out.println("<meta http-equiv = \"refresh\" content = \"2; url = Teacher_Attendance_new.jsp\" />");
	         out.println("<title>Attendance Submitted</title>");    
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
	         out.println("<h1>Attendance Submitted</h1>");
		
			  out.println("If automatically not redirected <a href=Teacher_Attendance_new.jsp>Click here</a>"
			  );
			  out.println("</center>");
		         out.println("</body>");
		         out.println("</html>");
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
