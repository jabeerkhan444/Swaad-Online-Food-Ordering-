<%@ page import="com.swaad.User,java.util.List" %>

<%@ page import="com.swaad.daoImpl.UserDaoImpl" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <title>Sign Up</title>

     <style>

        body {

            font-family: Arial, sans-serif;

            margin: 30px;

            padding: 0px;

            background-color: #f5f5f5;

        }

        .container {

            max-width: 490px;

            margin: 0 auto;

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

        }

        input[type="text"], input[type="email"], input[type="password"],input[type="phone"] {

            width: 100%;

            padding: 15px;

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

    </style>

</head>

<body>
    <% 

    if (request.getMethod().equals("POST")) {

        String name = request.getParameter("name");

        String email = request.getParameter("email");

        int phone = Integer.parseInt(request.getParameter("phone"));

        String username = request.getParameter("username");

        String password = request.getParameter("password");

        UserDaoImpl u = new UserDaoImpl();

        User u1 = new User(0, name, email, phone, null, username, password, null, null, null);

        if (u.addUser(u1) > 0) {

    %>

            <div class="message">Your registration was successful!</div>

            <script>

                setTimeout(function() {

                    window.location.href = "LogIn.jsp";

                }, 2000); // 2000 milliseconds delay (2 seconds)

            </script>

    <% 

        } else {

    %>

            <div class="message">Sign up failed. Please try again.</div>

    <% 

        }

    }

    %>

    

    <div class="container"><form method="post">

        <h2 class="title">Sign up</h2>

        <p class="subtitle">Create your account</p>

        

         <div class="input-container">

        <input type="text" id="name" name="name" placeholder="Name" required>

        </div>

       <div class="input-container">

        <input type="email" id="email" name="email" placeholder="Email" required>

          </div>

      <div class="input-container">

        <input type="phone" id="phone" name="phone" placeholder="Phone" required>

        </div>

        <div class="input-container"> 

        <input type="text" id="username" name="username" placeholder="Username" required>

        </div>

        <div class="input-container">

        <input type="password" id="password" name="password"  placeholder="Password" required>

        </div>

     <button class="button">Sign up</button>

        <p class="or">Or</p>   

     </button>

        <p class="login-link">Already have an account? <a href="LogIn.jsp">Login</a></p>

    </div>

    </form>

</body>

</html>