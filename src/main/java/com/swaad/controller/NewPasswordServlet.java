package com.swaad.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Base64;

@WebServlet("/newPassword")
public class NewPasswordServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String HASHING_ALGORITHM = "SHA-256";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/swaad";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "Jabeer@2000";

    private String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASHING_ALGORITHM);
            byte[] hashBytes = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String newPassword = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");

        if (newPassword != null && confPassword != null && newPassword.equals(confPassword)) {
            try {
                // Register JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                try (
                    Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                    PreparedStatement pst = con.prepareStatement("UPDATE user SET password = ? WHERE email = ?");
                ) {
                    pst.setString(1, encryptPassword(newPassword));
                    pst.setString(2, (String) session.getAttribute("email"));

                    int rowCount = pst.executeUpdate();
                    if (rowCount > 0) {
                        request.setAttribute("status", "resetSuccess");
                    } else {
                        request.setAttribute("status", "resetFailed");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("status", "resetFailed");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                request.setAttribute("status", "resetFailed");
            }
        } else {
            request.setAttribute("status", "resetFailed");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("LogIn.jsp");
        dispatcher.forward(request, response);
    }
}
