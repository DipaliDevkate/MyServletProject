package com.org;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loginRe
 */
@WebServlet("/loginR")
public class loginRe extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public loginRe() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String email_user=request.getParameter("email");
        String password=request.getParameter("pwd");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","Radhe@1111");
			PreparedStatement psmt=con.prepareStatement("select * from registeru where EmailU=? and PasswordU=?");
			psmt.setString(1, email_user);
			psmt.setString(2, password);
			ResultSet rs=psmt.executeQuery();
		        
			
			RequestDispatcher rd;
		        if (rs.next()) {
                    out.println("Success to login !!!");
		        	rd=request.getRequestDispatcher("UDdata.html");//servlet to servlet
		        	rd.forward(request, response);
		        	
		        	
	            } else {
	                out.println("<h3>Invalid Username or Password</h3>");
                  	rd=request.getRequestDispatcher("index.html");
//	            	rd=request.getRequestDispatcher("fail");
	            	rd.include(request, response);
	            }
		       
		con.close();
		}catch(Exception e) {
			out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
