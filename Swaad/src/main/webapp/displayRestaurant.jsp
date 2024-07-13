<%@ page import = " com.swaad.Restaurant,java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 

    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <title>List of Restaurants</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
   <style>

    body {
        margin: 0;
        text-align: center;
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

    .container {    
        display: flex;
        justify-content: space-between;
        width: 75%;
        margin: 0 auto;
        flex-wrap: wrap;
        margin-top: 60px; /* Adjust this value to account for header height */
    }

    .row {
        width: 100%;
        display: flex;
        justify-content: space-between;
        margin-bottom: 10px;
    }

    .item {
        width: calc(30% - 20px);
        height: -50%;
        margin: 10px;
        padding: 20px;
        box-shadow: 0 0 10px rgb(232, 228, 228);
        border-radius: 4%;
        position: relative;
        display: flex;
        flex-direction: column;
    }

    .item:hover {
        transform: scale(1.1);
    }
    
    .item1 {
    width: calc(60% - 20px); /* Adjust the width of each item */
    height: auto; /* Auto height to accommodate variable content */
    margin: 10px;
    padding: 20px;
    
    border-radius: 4%;
    position: relative; /* Position relative for absolute positioning */
    display: flex; /* Display flex to allow flex items */
    flex-direction: row; /* Arrange flex items horizontally */
    justify-content: space-between; /* Space between images */
}

.item1 img {
    cursor: pointer;
    width: 25%; /* Set width to 48% to fit two images with a small gap */
    height: auto; /* Auto height */
    object-fit: cover; /* Scale image while maintaining aspect ratio */
    border-radius: 10%;
}

    img {
        cursor: pointer;
        width: 100%;
        height: 200px;
        object-fit: cover;
        border-radius: 10%;
    }

    h3 {
        color: black;
        text-shadow: 0 0px 0px black;
        margin: 5px 0;
        flex: 1;
    }

    h5 {
        color: gray;
        margin: 0;
    }

    p {
        margin: 0px 0;
        text-align: left;
    }

    .rating-container {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-top: 10px;
    }

    .rating-box {
        background-color: green;
        color: white;
        padding: 5px;
        border-radius: 8px;
        font-size: 14px;
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

    .container.shifted {
        margin-right: 300px;
        transition: margin-right 0.3s ease-in-out;
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
    .subtitle1 {
    position: relative;
    top: 90px;
    right: 33%;
    font-weight: bolder;
    font-size: 25px;
    color: black;
    margin-bottom: 40px;
}

    .subtitle {
    position: relative;
    right: 7%;
    font-weight: bolder;
    font-size: 25px;
    color: black;
    margin-bottom: 40px;
}

    .top-restaurants {
        text-align: left;
        margin-top: 10px;
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
            <button onclick="location.href='SignUp.jsp'">SignUp</button>
            <button onclick="location.href='LogIn.jsp'">Login</button>
        </div>
	<div class="branding">
                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEfzs4V33FD1666L5fHHyQMcpi691vnWMPJg&usqp=CAU" alt="Logo">SwaaD
         </div>

     </header>
     
     <div class="subtitle1">Hey! What's on your mind ?</div>
        
     <div class="container">
     
     <div class="row">
        <div class="item1"> 
            <img src="https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_288,h_360/v1675667625/PC_Creative%20refresh/Biryani_2.png" alt="Food Image" >
            <img src="https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_288,h_360/v1674029845/PC_Creative%20refresh/3D_bau/banners_new/Burger.png" alt="Food Image" >
            <img src="https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_288,h_360/v1675667625/PC_Creative%20refresh/North_Indian_4.png" alt="Food Image" >
            <img src="https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_288,h_360/v1675667626/PC_Creative%20refresh/South_Indian_4.png" alt="Food Image" >
            <img src="https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_288,h_360/v1674029850/PC_Creative%20refresh/3D_bau/banners_new/Dosa.png" alt="Food Image" >
            <img src="https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_288,h_360/v1674029858/PC_Creative%20refresh/3D_bau/banners_new/Rolls.png" alt="Food Image" >
            <img src="https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_288,h_360/v1674029856/PC_Creative%20refresh/3D_bau/banners_new/Pizza.png" alt="Food Image" >
        </div>
    </div>

     <div class="subtitle">
Best Food in Bengaluru Restaurants</div>
        <% int counter = 0; %>
        <div class="row">
            <!-- JSP Code -->
            <%
                List<Restaurant> list = (List<Restaurant>)request.getAttribute("restaurants");
                for (Restaurant restaurant : list) {
            %>
                <% if (counter == 4) { %>
                    </div><div class="row">
                    <% counter = 0; %>
                <% } %>
                <div class="item"> 
                    <a href="MenuServlet?restaurantId=<%= restaurant.getRestaurantId()%>">
                        <img src="<%= restaurant.getImagePath()%>" alt="<%= restaurant.getName() %> Image">
                    </a>
                    <div class="text-container">
                        <div class="rating-container">
                            <h3><%= restaurant.getName() %></h3>
                            <div class="rating-box"><%= restaurant.getRating() %><span>&#9733;</span></div>
                        </div>
                        <div class="eta-time text-left"><%= restaurant.getEta() + " mins" %></div>
                        <div class="cusinietype text-left"><p><%= restaurant.getCuisineType() %></p></div> 
                        <div class="address text-left"><p><%= restaurant.getAddress() %></p></div> 
                    </div>
                </div>
                <% counter++; %>
            <% } %>
        </div>
    </div>

</body>
</html>
