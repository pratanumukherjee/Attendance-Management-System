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
 * Servlet implementation class TSA_main
 */
public class TSA_main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TSA_main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String tid = request.getParameter("tid");
		String subject = request.getParameter("subject");
		String scode = request.getParameter("scode");
		 Connection con=null;
		 try {
			 con = new DBConnection().getConnection();
			 PreparedStatement ps = con.prepareStatement("insert into teacher_subject(sub_code,sub_name,teacher_id) values(?,?,?)");
			 ps.setString(1,scode);
			 ps.setString(2,subject);
			 ps.setString(3,tid);
			
			 ps.executeUpdate();
			 
			TSA_next tn = new TSA_next();
			tn.doPost(request, response);
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

}
