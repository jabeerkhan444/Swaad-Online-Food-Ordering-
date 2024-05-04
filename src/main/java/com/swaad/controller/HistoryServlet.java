package com.swaad.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swaad.OrderItems;
import com.swaad.daoImpl.OrderItemsDaoImpl;
@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int userId=(int)session.getAttribute("userId");
		
		OrderItemsDaoImpl daoI = new OrderItemsDaoImpl();
		List<OrderItems> orderitem = daoI.getOrderItemByUserId(userId);
		session.setAttribute("orderitem", orderitem);
		
		resp.sendRedirect("History.jsp");
	}
}
