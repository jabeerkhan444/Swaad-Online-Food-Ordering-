package com.swaad.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swaad.CartCreator;

@WebServlet("/UpdateQuantityServlet")
public class UpdateQuantityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        // Update the quantity in the cart
        CartCreator cartCreator = (CartCreator) request.getSession().getAttribute("cartCreator");
        if (cartCreator != null) {
            cartCreator.updateQuantity(menuId, quantity);
        }
        
        // Redirect back to the shopping cart page
        response.sendRedirect("Cart.jsp");
    }
}
