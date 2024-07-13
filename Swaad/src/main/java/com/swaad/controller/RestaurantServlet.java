package com.swaad.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swaad.Restaurant;
import com.swaad.daoImpl.RestaurantDaoImpl;
import com.swaad.daoImpl.UserDaoImpl;

@WebServlet("/RestaurantServlet")
public class RestaurantServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RestaurantDaoImpl r = new RestaurantDaoImpl();
		UserDaoImpl userDao = new UserDaoImpl(); // Create an instance of UserDaoImpl
		int historyId = ThreadLocalRandom.current().ints().distinct().limit(1).findFirst().orElse(0);
		List<Restaurant> list = r.getallRestaurants();
		
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");
		
		// Get name by email
		String name = userDao.getNameByEmail(email);
		int userId = userDao.getUserIdByEmail(email);
		
		req.setAttribute("restaurants", list);
		req.setAttribute("email", email);
		req.setAttribute("name", name); // Set the name attribute
		session.setAttribute("userId", userId);
		session.setAttribute("historyId", historyId);
		RequestDispatcher rd = req.getRequestDispatcher("LoginRestaurant.jsp");
		
		rd.forward(req, resp);
	}
}

