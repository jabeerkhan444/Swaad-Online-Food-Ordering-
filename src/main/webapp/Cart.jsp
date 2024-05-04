<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="com.swaad.CartCreator" %>
<%@ page import="com.swaad.CartItem" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        /* General styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        
        header {
            background-color: #ccc;
            color: white;
            padding: 0px;
            text-align: center;
            position: fixed;
            width: 100%;
            z-index: 1000;
            height: 70px;
        }
        
        .navigation {
            display: flex;
            justify-content: flex-end;
            align-items: flex-start;
            padding: 10px 30px 0 0;
        }

        .navigation input[type="text"] {
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            margin-right: 10px;
            margin-left: 15px;
        }

        .navigation #searchButton {
            background-color: #f42f06;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .navigation #searchButton:hover {
            background-color: white;
            color:#f42f06;
        }

        .navigation a {
            text-decoration: none;
            color: black;
            margin-left: 20px;
        }

        .navigation a:nth-child(1) {
            margin-right: 20px;
        }

        .navigation a i {
            font-size: 35px;
            margin-right: 10px;
            text-decoration: none;
            color: #f42f06;
        }

        .navigation button {
            background-color: #f42f06;
            color: white;
            border: none;
            font-weight: bold;
            padding: 10px 18px;
            margin-left: 10px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .navigation button:hover {
            background-color: white;
            color:#f42f06;
        }

        .app-name {
            position: relative;
            top: -40px;
            right: 400px;
            font-size: 55px;
            color: #f42f06;
            text-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            font-family: fantasy;
        }

        .cart-container {
            max-width: 800px;
            margin: 0 auto;
            padding: 40px;
            text-align: center;
            font-weight: bold;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            padding: 50px;
            color:#f42f06;
        }

        /* Cart item styles */
        .cart-item {
            border: 1px solid gray;
            border-radius: 10px;
            margin-bottom: 20px;
            padding: 12px;
            overflow: hidden;
        }

        .cart-item img {
            width: 100px;
            height: 135px;
            float: left;
            margin-right: 10px;
            border-radius: 6px;
        }

        .cart-details {
            float: left;
        }

        .cart-name {
            margin: 0;
        }

        .cart-price, .cart-quantity {
            margin: 5px 0;
        }

        /* Cart total styles */
        .cart-total {
            text-align: right;
            margin-top: 20px;
            font-weight: bold;
            display: flex;
            justify-content: space-between; /* Space between total and button */
        }

        /* Links styles */
        a {
            color: blue;
            text-decoration: none;
            margin-right: 10px;
        }

        a:hover {
            text-decoration: underline;
        }

        /* Quantity button styles */
        .quantity-container {
            display: flex;
            align-items: center;
        }

        .quantity-button {
            padding: 5px 10px;
            cursor: pointer;
            border: 1px solid #090909;
            background-color: #f45408;
            border-radius: 5px;
            font-size: 20px;
            color: #f9fcfa;
            font-weight: bold;
        }

        .quantity {
            margin: 0 10px;
            color: #f45408;
            font-weight: bold;
        }

        /* Checkout button styles */
        .checkout-button {
            color: #f9fcfa;
            background-color: #f45408;
            border: 1px solid black;
            font-weight: bold;
            padding: 10px 20px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            border-radius: 10px;
        }

        .checkout-button:hover {
            background-color: #f9fcfa;
            color: #f45408;
        }

        .cart-update,
        .cart-actions {
            margin-top: 10px;
        }

        button {
            background-color: #f45408;
            color: #f9fcfa;
            border: 1px solid black;
            border-radius: 10px;
            padding: 10px 25px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            margin-top: 5px;
            font-weight:bold;
        }

        .quantity-button:hover {
            background-color: #f9fcfa;
            color: #f45408;
        }

        /* Hover effect for Remove button */
        .cart-actions button:hover {
            background-color: #f9fcfa;
            color: #f45408;
        }

        /* Hover effect for Add More Items button */
        .cart-add-more button:hover {
            background-color: #f9fcfa;
            color: #f45408;
        }

        /* Hover effect for Proceed to Checkout button */
        .cart-action button:hover {
            background-color: #f9fcfa;
            color: #f45408;
        }

        .cart-update form,
        .cart-actions form,
        .cart-actions div {
            display: inline-block;
            margin-right: 10px;
        }

        .cart-actions div:last-child {
            margin-right: 0;
        }

        .branding {
            display: flex;
            align-items: center;
            font-size: 55px;
            color: #f42f06;
            text-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            font-family: fantasy;
            position: absolute;
            top: 0px;
            left:10%;
        }

        .branding img {
            width: 50px;
            height: auto;
            margin-right: 10px;
            border-radius: 30px;
        }

        /* Dark mode styles */
        .dark-mode {
            background-color: #222;
            color: #fff;
        }

        .navigation input[type="text"] {
    padding: 10px;
    border-radius: 5px;
    border: 1px solid #ccc;
    margin-right: 10px;
    width: 210px;
}

        .dark-mode .navigation button,
        .dark-mode button,
        .dark-mode .checkout-button {
            background-color: #f45408;
            color: #fff;
            border-color: #333;
        }

        .dark-mode a {
            color: #0ff;
        }

        .dark-mode .cart-item {
            border-color: #444;
            background-color: #333;
            color: #fff;
        }

        .dark-mode .cart-item img {
            border-color: #555;
        }

        .dark-mode .cart-actions button,
        .dark-mode .cart-add-more button,
        .dark-mode .cart-action button {
            background-color: #f45408;
            color: #fff;
            border-color: #444;
        }

        .dark-mode .cart-actions button:hover,
        .dark-mode .cart-add-more button:hover,
        .dark-mode .cart-action button:hover {
            background-color: #f9fcfa;
            color: #f45408;
        }

        .dark-mode .quantity-button {
            background-color: #f45408;
            color: #fff;
            border-color: #555;
        }

        .dark-mode .quantity-button:hover {
            background-color: #f9fcfa;
            color: #f45408;
        }

        .dark-mode .cart-total {
            color: #0ff;
        }
    </style>
</head>
<body>
    <header>
        <div class="navigation">
            <input type="text" placeholder="Search for Restaurant or Food" id="searchInput">
            <button id="searchButton"><i class="fas fa-search"></i></button>
            <a href="Cart.jsp"><i class="fas fa-shopping-cart"></i></a>
            <button onclick="location.href='RestaurantServlet'">Home</button>
            <button onclick="location.href='HistoryServlet'">Orders</button>
            <button onclick="location.href='LogoutServlet'">Logout</button>
            <!-- Add dark mode toggle button -->
            <button id="darkModeToggle"><i class="fas fa-moon"></i></button>
        </div>
        <div class="branding">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEfzs4V33FD1666L5fHHyQMcpi691vnWMPJg&usqp=CAU" alt="Logo">SwaaD
        </div>
    </header>
    <div class="cart-container">
        <h2>Your Shopping Cart</h2>
        <% 
            CartCreator cartCreator = (CartCreator) session.getAttribute("cartCreator");
            if (cartCreator != null && !cartCreator.getAll().isEmpty()) {
                int total = 0;
                for (CartItem item : cartCreator.getAll().values()) {
                    total += item.getPrice() * item.getQuantity();
        %>
        <div class="cart-item">
            <img class="cart-image" src="<%= item.getImagePath() %>" alt="Product Image">
            <div class="cart-details">
                <h4 class="cart-name"><%= item.getName() %></h4>
                <p class="cart-price">Price: <%= item.getPrice() %></p>
                <div class="quantity-container">
                    <form action="UpdateQuantityServlet" method="post">
                        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                        <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
                        <input class="quantity-button" type="submit" value="-">
                    </form>
                    <span class="quantity"><%= item.getQuantity() %></span>
                    <form action="UpdateQuantityServlet" method="post">
                        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                        <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
                        <input class="quantity-button" type="submit" value="+">
                    </form>
                </div>
                <div class="cart-actions">
                    <form method="post" action="RemoveItemServlet">
                        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                        <button type="submit">Remove</button>
                    </form>
                    <div class="cart-add-more">
                        <a href="MenuServlet?restaurantId=<%=item.getRestaurantId() %>"><button>Add More Items</button></a>
                    </div>
                    <div class="cart-action">
                        <form method="get" action="checkout">
                            <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                            <input type="hidden" name="total" value="<%= total %>"/>
                            <button type="submit">Proceed to Checkout</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <% 
                }
        %>
        <div class="cart-total">
            <div>Total: <%= total %></div>
            <a href="checkout" class="checkout-button">Proceed All to Checkout</a>
        </div>
        <% 
            } else {
        %>
        <p>No items in your cart.</p>
        <% 
            }
        %>
    </div>

    <script>
        // JavaScript to toggle dark mode
        const darkModeToggle = document.getElementById('darkModeToggle');
        const body = document.body;
        darkModeToggle.addEventListener('click', () => {
            body.classList.toggle('dark-mode');
        });
    </script>
</body>
</html>
