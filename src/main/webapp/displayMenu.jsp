<%@ page import="com.swaad.Menu,java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Menu List</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        /* Your existing CSS styles here */
        body {
            margin: 0;
            text-align: center;
        }
        header {
            background-color: #ccc;
            color: white;
            padding: 0px;
            text-align: center;
            top:0px;
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
        .cartTab .btn {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
        }
        .cartTab {
            width: 600px;
            background-color: #353432;
            color: #eee;
            position: fixed;
            top: 0;
            right: -400px;
            bottom: 0;
            display: grid;
            grid-template-rows: 70px 1fr 70px;
            transition: .5s;
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
        .container {  
            display: flex;
            justify-content: space-between;
            width: 50%;
            margin: 10px auto 0 auto;
            flex-wrap: wrap;
            flex-direction: column;
        }
        .row {
            width: 100%;
            display: flex;
            justify-content: space-between;
            margin-bottom: 0px;
        }
        .item {
            width: calc(100% - 100px);
            height: 60%;
            margin: 20px;
            padding: 5px;
            box-shadow: 0px 0px 3px rgb(232, 228, 228);
            border-radius: 10%;
            position: relative;
            display: flex;
            flex-direction: row;
        }
        .content {
            flex: 1;
            padding: 0 10px;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        img.menu-image {
            cursor: pointer;
            width: 30%;
            height: 110px;
            border-radius: 10%;
            float: right;
        }
        img.menu-image:hover{
            transform: scale(1.1);
        }
        .add-button {
            position: absolute;
            top: 88%;
            left: 84%;
            transform: translate(-50%, -50%);
            background-color: #f44e06;
            color: #f9fcfa;
            border-radius: 18%;
            width: 130px;
            height: 40px;
            font-weight: bold;
            font-size: 15px;
            cursor: pointer;
            border: 1px solid black;
        }
        .add-button:hover{
            background-color: #f9fcfa;
            color: #f44e06;
        }
        h3 {
            color: black;
            text-shadow: 0 0px 0px black;
            margin: 5px 0;
            text-align: left;
        }
        p {
            margin: 3px 0;
            text-align: left;
        }
        .rating-box{
            text-align: left;
            color: green;
        }
        h2 {
            color: #f44e06;
            padding-top: 90px;
            font-weight: bolder;
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
        body.dark-mode {
            background-color: #333;
            color: #fff;
        }
        /* Bright mode styles */
        body.bright-mode {
            background-color: #fff;
            color: #333;
        }
    </style>
</head>

<body class="bright-mode">           
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
    <h2>Menu</h2>
    <div class="container">
        <%-- Counter variable to keep track of items displayed in a row --%>
        <% int counter = 0; %>
        <div class="row">
            <!-- JSP Code -->
            <%
                List<Menu> list = (List<Menu>)request.getAttribute("menus");
                for (Menu menu : list) {
            %>
            <%-- Check if the counter reaches 1, then start a new row --%>
            <% if (counter == 1) { %>
            </div><div class="row">
                <% counter = 0; %>
            <% } %>
            <div class="item"> 
                <div class="content">
                    <div class="rating-container">
                        <h3><%= menu.getName() %></h3>
                    </div>
                    <p><%="₹ " +menu.getPrice() %></p>
                    <div class="rating-box"><span>&#9733;</span><%= menu.getRatings() %></div>
                    <p><%= menu.getDescription() %></p>
                </div>
                <img src="<%= menu.getImagePath() %>" class="menu-image" />
                <div>
                    <form action="CartServlet" method="post">
                        <input type="hidden" name="name" value="<%= menu.getName() %>"/>
                        <input type="hidden" name="imagePath" value="<%= menu.getImagePath() %>"/>
                        <input type="hidden" name="menuId" value="<%= menu.getMenuId() %>"/>
                        <input type="hidden" name="restaurantId" value="<%= menu.getRestaurantId() %>"/>
                        <input type="hidden" name="ratings" value="<%= menu.getRatings() %>"/>
                        <input type="hidden" name="price" value="<%= menu.getPrice() %>"/>
                        <input type="hidden" name="description" value="<%= menu.getDescription() %>"/>
                        <input type="hidden" name="quantity" value="1"/>
                        <button class="add-button">ADD</button>
                    </form>
                </div>
            </div>
            <%-- Increment the counter for each item displayed --%>
            <% counter++; %>
            <%
                }
            %>
            <!-- End of JSP Code -->
        </div>
    </div>
    <script>
        // JavaScript to toggle dark mode and bright mode
        const darkModeToggle = document.getElementById('darkModeToggle');
        darkModeToggle.addEventListener('click', function () {
            document.body.classList.toggle('dark-mode'); // Toggle dark mode class
            document.body.classList.toggle('bright-mode'); // Toggle bright mode class
        });
    </script>
</body>
</html>
