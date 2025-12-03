package com.org;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo", "root", "Radhe@1111");

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE registeru SET EmailU=?, PasswordU=? WHERE U_id=?");

            ps.setString(1, email);
            ps.setString(2, pwd);
            ps.setInt(3, id);

            int i = ps.executeUpdate();

            con.close();

            if (i > 0) {
                response.sendRedirect("UserDataServlet"); // Refresh table
            } else {
                response.getWriter().println("Update failed!");
            }

        } catch (Exception e) {
            response.getWriter().println("Error: " + e);
        }
    }
}
