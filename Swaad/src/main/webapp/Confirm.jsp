<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            text-align: center;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
            animation: bounce 0.5s infinite alternate;
        }
        h1 {
            color: #333;
            animation: rainbow 3s infinite;
        }
        p {
            color: #666;
            font-size: 18px;
        }
        @keyframes bounce {
            from { transform: translateY(-10px); }
            to { transform: translateY(10px); }
        }
        @keyframes rainbow {
            0% { color: red; }
            20% { color: orange; }
            40% { color: yellow; }
            60% { color: green; }
            80% { color: blue; }
            100% { color: purple; }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Thank You!</h1>
        <p>Your Order Was Confirmed</p>
    </div>
</body>
</html>
