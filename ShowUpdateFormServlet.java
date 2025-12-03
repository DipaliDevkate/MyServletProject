package com.org;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/ShowUpdateFormServlet")
public class ShowUpdateFormServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo", "root", "Radhe@1111");

            PreparedStatement ps = con.prepareStatement(
                    "select * from registeru where U_id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                out.println("<h2>Update User</h2>");
                out.println("<form action='UpdateUserServlet' method='post'>");

                out.println("<input type='hidden' name='id' value='" + id + "'>");

                out.println("Email: <input type='text' name='email' value='" 
                            + rs.getString("EmailU") + "'><br><br>");

                out.println("Password: <input type='text' name='pwd' value='" 
                            + rs.getString("PasswordU") + "'><br><br>");

                out.println("<input type='submit' value='Update'>");
                out.println("</form>");
            }

            con.close();

        } catch (Exception e) {
            out.println("Error: " + e);
        }
    }
}
