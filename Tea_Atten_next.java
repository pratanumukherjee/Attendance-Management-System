package com.attendanceMS.backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.attendanceMS.dbConnection.DBConnection;

/**
 * Servlet implementation class Tea_Atten_next
 */
public class Tea_Atten_next extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tea_Atten_next() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Connection con = null;
		 String tot_tea = null;
		 try{
		 	con = new DBConnection().getConnection();
		 	Statement st = con.createStatement();
		 	ResultSet rs = st.executeQuery("select count(*) from teacher");
		 	while(rs.next()) {
		 		tot_tea = rs.getString("count(*)");
		 	}
		 }catch(Exception e){
		 	e.printStackTrace();
		 }
		 System.out.println(tot_tea);
		 session.setAttribute("tot_tea", tot_tea);
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/Teacher_Attendance_new.jsp");
		  dispatcher.forward(request,response);
	}

}
