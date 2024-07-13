package com.swaad.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swaad.Restaurant;
import com.swaad.daoImpl.RestaurantDaoImpl;
@WebServlet("/LogoutServlet")
public class LogoutRestaurantServlet extends HttpServlet{
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RestaurantDaoImpl r=new RestaurantDaoImpl();
		
		List<Restaurant> list=r.getallRestaurants();
		
		req.setAttribute("restaurants", list);
		
		RequestDispatcher rd=req.getRequestDispatcher("displayRestaurant.jsp");
		
		rd.forward(req, resp);
	}
}
