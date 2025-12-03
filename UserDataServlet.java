package com.org;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/UserDataServlet")
public class UserDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo", "root", "Radhe@1111");

            PreparedStatement ps = con.prepareStatement("select * from registeru");
            ResultSet rs = ps.executeQuery();

            // CSS Styles
            out.println("<style>");
            out.println("body { background-color: #f0f0f0; font-family: Arial; text-align:center; }");
            out.println("table { width: 80%; margin: auto; border-collapse: collapse; font-size: 18px; }");
            out.println("table, th, td { border: 1px solid black; }");
            out.println("th { background-color: #4CAF50; color: white; padding: 10px; }");
            out.println("td { padding: 8px; }");
            out.println("tr:hover { background-color: #f2f2f2; }");

            out.println(".update-btn { background-color: #2196F3; color: white; padding: 8px 12px; border: none; cursor: pointer; }");
            out.println(".delete-btn { background-color: red; color: white; padding: 8px 12px; border: none; cursor: pointer; }");
            out.println(".update-btn:hover, .delete-btn:hover { opacity: 0.7; }");
            out.println("</style>");
            out.println("<a href='LogoutServlet'><button style='"
                    + "background-color:red;"
                    + "color:white;"
                    + "padding:10px 20px;"
                    + "border:none;"
                    + "border-radius:5px;"
                    + "cursor:pointer;"
                    + "margin:20px;'>Logout</button></a>");

            // Table UI
            out.println("<h2 style='text-align:center;'>User Data</h2>");
            out.println("<table>");
            out.println("<tr>"
                    + "<th>ID</th>"
                    + "<th>User Name</th>"
                    + "<th>Email</th>"
                    + "<th>Password</th>"
                    + "<th>Update</th>"
                    + "<th>Delete</th>"
                    + "</tr>");

            while (rs.next()) {
                int id = rs.getInt("U_id");
                String username = rs.getString("UName");
                String email = rs.getString("EmailU");
                String pwd = rs.getString("PasswordU");

                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + username + "</td>");
                out.println("<td>" + email + "</td>");
                out.println("<td>" + pwd + "</td>");

                out.println("<td><a href='update.html?id=" + id +
                        "&email=" + email +
                        "&pwd=" + pwd +
                        "'><button class='update-btn'>Update</button></a></td>");

                out.println("<td><a href='DeleteServlet?id=" + id +
                        "'><button class='delete-btn'>Delete</button></a></td>");

                out.println("</tr>");
            }

            out.println("</table>");
            
            con.close();

        } catch (Exception e) {
            out.println("<h3 style='color:red;'>Error: " + e + "</h3>");
        }
    }
}
