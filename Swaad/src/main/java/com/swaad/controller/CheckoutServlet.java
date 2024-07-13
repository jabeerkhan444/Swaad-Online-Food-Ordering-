package com.swaad.controller;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

	// private static int historyId=1;

	@Override

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		int total = Integer.parseInt(req.getParameter("total"));
		
		int userId = (int) session.getAttribute("userId");

		int historyId = ThreadLocalRandom.current().ints().distinct().limit(1).findFirst().orElse(0);

		if (userId <= 0) {

			resp.sendRedirect("LogIn.jsp");

		} else {

			int restaurantId = (int) session.getAttribute("restaurantId");
			
			session.setAttribute("restaurantId", restaurantId);
			
			session.setAttribute("historyId", historyId);
			
			// historyId ++;
			session.setAttribute("total", total);
			RequestDispatcher rd = req.getRequestDispatcher("CheckOut.jsp");
			
			rd.forward(req, resp);
			
		}



	}
}
