package com.swaad.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swaad.CartCreator;
import com.swaad.CartItem;
import com.swaad.daoImpl.UserDaoImpl;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	UserDaoImpl userDao = new UserDaoImpl();
    	
    	String name = req.getParameter("name");
        String imagepath = req.getParameter("imagePath");
        int price = Integer.parseInt(req.getParameter("price"));
        int menuId = Integer.parseInt(req.getParameter("menuId"));
        int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        
        CartItem ci = new CartItem(menuId, restaurantId, name, imagepath, price, quantity);

        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");
        int userId = userDao.getUserIdByEmail(email);
        
        CartCreator cartCreator = (CartCreator) session.getAttribute("cartCreator");

        if (cartCreator == null) {
            cartCreator = new CartCreator();
        }

        cartCreator.addCartItem(ci);
        session.setAttribute("cartCreator", cartCreator);
        session.setAttribute("restaurantId", restaurantId);
        session.setAttribute("userId", userId);
        session.setAttribute("menuId", menuId);
        
        RequestDispatcher rd = req.getRequestDispatcher("Cart.jsp");
		
		rd.forward(req, resp);
    }
}
