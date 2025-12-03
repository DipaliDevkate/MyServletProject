package com.org;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id")); // U_id coming from link

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo", "root", "Radhe@1111");

            PreparedStatement ps = con.prepareStatement("DELETE FROM registeru WHERE U_id=?");
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            con.close();

            // If row deleted successfully → Redirect to servlet that shows table
            if (rows > 0) {
                response.sendRedirect("UserDataServlet");
            } else {
                response.getWriter().println("<h3>Delete failed!</h3>");
            }

        } catch (Exception e) {
            response.getWriter().println("<h3>Error: " + e + "</h3>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
