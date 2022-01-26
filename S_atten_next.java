package com.attendanceMS.backend;
import com.attendanceMS.dbConnection.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date; 
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Student_attendance
 */
public class S_atten_next extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S_atten_next() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		String date = request.getParameter("db");
		String year = request.getParameter("year");
		 String dept = request.getParameter("dept");
		
		String sub_code = request.getParameter("sub_code");
		//String roll = request.getParameter("roll");
		//String attendance = request.getParameter("att");
		 
		//System.out.println(date+" "+dept+" "+sub_code);
		 HttpSession session = request.getSession();
		 session.setAttribute("sub_code", sub_code);
		 Connection con = null;
		 String tot_stud = null;
		 try{
		 	con = new DBConnection().getConnection();
		 	Statement st = con.createStatement();
		 	ResultSet rs = st.executeQuery("select count(*) from student where  year='"+year+"' and dept='"+dept+"'");
		 	while(rs.next()) {
		 		tot_stud = rs.getString("count(*)");
		 	}
		 }catch(Exception e){
		 	e.printStackTrace();
		 }
		 session.setAttribute("tot_stud", tot_stud);
		 session.setAttribute("date", date);
		 session.setAttribute("dept", dept);
		 //System.out.println(tot_stud);
		// Connection con=null;
		 Statement st = null;
		 ResultSet rs = null;
		  ArrayList al=null;
		  ArrayList al1=null;
		 try {
			 con = new DBConnection().getConnection();
		
			  ArrayList S_list =new ArrayList();
			  String query = "select fname,lname,roll from student where year='"+year+"' and dept='"+dept+"'";
			  
			  //System.out.println("query " + query);
			  st = con.createStatement();
			  rs = st.executeQuery(query);


			  while(rs.next()){
			  al  = new ArrayList();
			  
			  al.add(rs.getString(1));
			  al.add(rs.getString(2));
			  al.add(rs.getString(3));
			 // al.add(rs.getString(4));
			  
			//  System.out.println("al :: "+al);
			  S_list.add(al);
			  }
			  request.setAttribute("S_list", S_list);
			  RequestDispatcher dispatcher = request.getRequestDispatcher("/Stud_Attendance_main.jsp");
			  dispatcher.forward(request,response);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }finally {
			 try {
				// rs.close();
				 //st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	
		 	
	}

}
