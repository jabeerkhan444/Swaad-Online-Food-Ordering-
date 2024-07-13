<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Order Confirmation</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 30px;
            padding: 0px;
            background-color: #f5f5f5;
        }
        
        header {
    background-color: #ccc;
    color: white;
    padding: 0px;
    text-align: center;
    position: fixed;
    top: 0%;
    right: 0%;
    width: 100%;
    z-index: 1000;
    height: 70px;
}

        .container {
    max-width: 700px;
    margin: 150px auto 0;
    padding: 20px;
    background-color: #ccc;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

        .title {
            font-size: 30px;
            font-weight: bold;
            margin-bottom: 20px;
            color: #f44d04;
        }

        .subtitle {
            font-size: 15px;
            color: #666;
            margin-bottom: 20px;
        }

        .input-container {
            margin-bottom: 20px;
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }

        .input-box {
            width: calc(50% - 10px); /* Adjust width as needed */
        }

        input[type="text"], input[type="email"], input[type="tel"], input[type="number"], select {
            width: 100%;
            padding: 12px;
            border-radius: 30px;
            border: 1px solid #ccc;
            background-color: #f9f9f9;
            box-sizing: border-box;
            outline: none;
        }

        .button {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 8px;
            background-color: #f44d04;
            color: #fff;
            font-size: 20px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .button:hover {
            background-color: white;
            color: #f44d04;
        }

        .or {
            text-align: center;
            margin: 20px 0;
            color: #666;
        }

        .login-link {
            text-align: center;
            color: #6a1b9a;
            margin-top: 20px;
        }

        .message {
            color: green;
            text-align: center;
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
        width: 210px;
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

    div {
        display: block;
        unicode-bidi: isolate;
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
        </div>
	<div class="branding">
                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEfzs4V33FD1666L5fHHyQMcpi691vnWMPJg&usqp=CAU" alt="Logo">SwaaD
         </div>

     </header>

    <div class="container">
        <div class="title">Order Confirmation</div>
        <div class="content">
            <form action="confirmservlet">
                <div class="user-details input-container">
                    <div class="input-box">
                        <span class="details">Name</span>
                        <input type="text" placeholder="Enter your name" name="name" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Phone Number</span>
                        <input type="tel" placeholder="Enter your number" name="number" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Pin code</span>
                        <input type="number" placeholder="Enter your zip code" name="zipcode" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Flat, House no., Building, Company, Apartment</span>
                        <input type="text" placeholder="Enter your address" name="address" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Area, Street, Sector, Village </span>
                        <input type="text" placeholder="Enter your state" name="state" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Landmark </span>
                        <input type="text" placeholder="Enter your state" name="state">
                    </div>
                    <div class="input-box">
                        <span class="details">City</span>
                        <input type="text" placeholder="Enter your city" name="city" required>
                    </div>
                    <div class="input-box">
                        <span class="details">State </span>
                        <input type="text" placeholder="Enter your state" name="state" required>
                    </div>
                    
                    <div class="input-box">
                        <span class="details">Choose a payment method:</span>
                        <select id="payment-method" name="payment_method" required>
                            <option value="Cash">Cash on Delivery (COD)</option>
                            <option value="UPI">Unified Payments Interface (UPI)</option>
                            <option value="Debit Card">Debit Card</option>
                            <option value="Credit Card">Credit Card</option>
                        </select>
                    </div>
                    <button class="button">Confirm</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
