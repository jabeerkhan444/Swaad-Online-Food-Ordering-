package com.swaad.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swaad.CartCreator;

@WebServlet("/RemoveItemServlet")
public class RemoveItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int menuId = Integer.parseInt(request.getParameter("menuId"));

        HttpSession session = request.getSession();
        CartCreator cartCreator = (CartCreator) session.getAttribute("cartCreator");

        if (cartCreator != null) {
            cartCreator.deleteCartItem(menuId);
            session.setAttribute("cartCreator", cartCreator);
        }

        response.sendRedirect("Cart.jsp");
    }
}
