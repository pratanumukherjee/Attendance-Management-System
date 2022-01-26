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
/**
 * Servlet implementation class Tot_Atten_next
 */
public class Tea_tot_atten extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tea_tot_atten() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String sub_code = request.getParameter("sub_code");
		 Connection con=null;
		 Statement st = null;
		 ResultSet rs = null;
		  ArrayList al=null;
		  ArrayList al1=null;
		  try {
				 con = new DBConnection().getConnection();
			
				  ArrayList TA1 =new ArrayList();
				  String query = "select t.firstname,t.lastname,t.Teacherid,count(*) from teacher t inner join teacher_attendance ta on t.Teacherid=ta.Teacher_id where ta.attendance='"+0+"' group by t.Teacherid";
				  
				  System.out.println("query " + query);
				  st = con.createStatement();
				  rs = st.executeQuery(query);


				  while(rs.next()){
				  al1  = new ArrayList();
				  
				  al1.add(rs.getString(1));
				  al1.add(rs.getString(2));
				  al1.add(rs.getString(3));
				  al1.add(rs.getString(4));
				  
				  System.out.println("al1 :: "+al1);
				  TA1.add(al1);
				  }
				  request.setAttribute("TA1", TA1);
				
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
		  
		 try {
			 con = new DBConnection().getConnection();
		
			  ArrayList TA =new ArrayList();
			  String query = "select t.firstname,t.lastname,t.Teacherid,count(*) from teacher t inner join teacher_attendance ta on t.Teacherid=ta.Teacher_id where ta.attendance='"+1+"' group by t.Teacherid";
			  
			  System.out.println("query " + query);
			  st = con.createStatement();
			  rs = st.executeQuery(query);


			  while(rs.next()){
			  al  = new ArrayList();
			  
			  al.add(rs.getString(1));
			  al.add(rs.getString(2));
			  al.add(rs.getString(3));
			  al.add(rs.getString(4));
			  
			  System.out.println("al :: "+al);
			  TA.add(al);
			  }
			  request.setAttribute("TA", TA);
			  RequestDispatcher dispatcher = request.getRequestDispatcher("Total_Attendance_t.jsp");
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
