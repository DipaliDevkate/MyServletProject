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
 * Servlet implementation class RegisterFormServlet
 */
@WebServlet("/register")
public class RegisterFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegisterFormServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String username=request.getParameter("uname");
		String email_id=request.getParameter("email");
		String phone_no=request.getParameter("phone");
		String address_user=request.getParameter("address");
		String gender_user=request.getParameter("gender");
        String password=request.getParameter("pwd");
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","Radhe@1111");
			PreparedStatement psmt=con.prepareStatement("select * from registeru where EmailU=?");
			psmt.setString(1, email_id);
			ResultSet rs=psmt.executeQuery();
			
			RequestDispatcher rd;
			
			if(rs.next()) {
				out.println("<h1>your Email is allready existed Please try with another Email..</h1>");
			}
			else {
				PreparedStatement psInsert = con.prepareStatement(
						"INSERT INTO registeru(UName, EmailU, mobile_no, AddressU, GenderU, PasswordU) VALUES(?, ?, ?, ?, ?, ?)"
					);

					psInsert.setString(1, username);
					psInsert.setString(2, email_id);
					psInsert.setString(3, phone_no);
					psInsert.setString(4, address_user);
					psInsert.setString(5, gender_user);
					psInsert.setString(6, password);

					int i = psInsert.executeUpdate();

					if (i > 0) {
						out.println("<h2 style='color:green;'>Registration successful!</h2>");
						rd=request.getRequestDispatcher("login.html");//servlet to servlet
			        	rd.forward(request, response);
						
					} else {
						out.println("<h2 style='color:red;'>Registration failed. Try again.</h2>");
						rd=request.getRequestDispatcher("index.html");//servlet to servlet
		            	rd.include(request, response);
					}

					psInsert.close();
			}
			psmt.close();
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
