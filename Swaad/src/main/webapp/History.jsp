<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<%@ page import="com.swaad.OrderItems" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <title>Order Summary</title>
    <style>
        /* Basic styling for the table */
         body {
            font-family: Arial, sans-serif;
            background-color: #FDFCCD;
            text-align: center;
        }
        h1{
        color: #AF0000;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #fff;
        }
         </style>
</head>
<body>
   <h1>My Orders</h1>
    <table>
        <thead>
            <tr>
                <th>Item Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Restaurant ID</th>
            </tr>
        </thead>
        <tbody>
            <% List<OrderItems> orderitem = (List<OrderItems>) session.getAttribute("orderitem");
               for (OrderItems item : orderitem) { %>
                <tr>
                    <td><%= item.getName() %></td>
                    <td><%= item.getPrice() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td><%= item.getRestaurantId() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>