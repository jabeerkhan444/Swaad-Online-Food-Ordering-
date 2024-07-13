package com.swaad.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swaad.User;
import com.swaad.daoImpl.UserDaoImpl;

@WebServlet("/Restaurant")
public class LoginServlet extends HttpServlet {
	
	public LoginServlet() {
		
	}		
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	UserDaoImpl udi = new UserDaoImpl();
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	HttpSession session = request.getSession();
    	
    	User user = udi.getUserByEmail(email); // Fetch user object from the database
    	Integer attempts = (Integer) session.getAttribute("attempts");
    	
    	if(attempts == null) {
    		attempts = 0;
    	}
    	
    	int maxAttempts = 3;
    	int attemptsLeft = maxAttempts - attempts;
    	
    	if(attempts >= maxAttempts) {
    		request.setAttribute("error", "Contact administration");
    		request.getRequestDispatcher("LogIn.jsp").forward(request, response);
    		return;
    	}
    	
    	if(udi.validateEmailPassword(email, password)) {
    		session.setAttribute("attempts", 0);
    		session.setAttribute("email", email);
    		
    		// Here you should set the User object in the session
    		session.setAttribute("user", user); // Set user object in the session
    		
    		RequestDispatcher requestDispatcher = request.getRequestDispatcher("RestaurantServlet");
			requestDispatcher.forward(request, response);
    	}
    	else {
    		attempts++;
    		session.setAttribute("attempts", attempts);
    		String errorMsg = "Invalid Email or Password. " + attemptsLeft + " attempt(s) left.";
    		request.setAttribute("error", errorMsg); // Correctly set the error message
    		request.getRequestDispatcher("LogIn.jsp").forward(request, response);
    	}
    
    }
}
