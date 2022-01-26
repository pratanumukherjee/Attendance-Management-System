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
 * Servlet implementation class TSA_next
 */
public class TSA_next extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TSA_next() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 Connection con=null;
		 Statement st = null;
		 ResultSet rs = null;
		  ArrayList al=null;
		 try {
			 con = new DBConnection().getConnection();
		
			  ArrayList T_list =new ArrayList();
			  String query = "select firstname,lastname,uname from teacher";
			  
			  System.out.println("query " + query);
			  st = con.createStatement();
			  rs = st.executeQuery(query);


			  while(rs.next()){
			  al  = new ArrayList();
			  
			  al.add(rs.getString(1));
			  al.add(rs.getString(2));
			  al.add(rs.getString(3));
			//  al.add(rs.getString(4));
			  
			  System.out.println("al :: "+al);
			  T_list.add(al);
			  }
			  request.setAttribute("T_list", T_list);
			  RequestDispatcher dispatcher = request.getRequestDispatcher("Teacher_Subject_Assignment.jsp");
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
