package com.swaad.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swaad.Menu;
import com.swaad.daoImpl.MenuDaoImpl;
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		
		MenuDaoImpl m=new MenuDaoImpl();
		
		List<Menu> menu=m.getMenuByRestaurant(restaurantId);
		req.setAttribute("menus", menu);
		
		RequestDispatcher rd=req.getRequestDispatcher("displayMenu.jsp");
		
		rd.forward(req, resp);
	}
}
