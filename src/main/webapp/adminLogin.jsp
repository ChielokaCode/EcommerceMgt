<%--
  Created by IntelliJ IDEA.
  User: wikiwoo
  Date: 30/11/2023
  Time: 1:51 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- MATERIAL DESIGN ICONIC FONT -->
    <link rel="stylesheet" href="account/fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">

    <!-- STYLE CSS -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div style="margin-left: 8rem; margin-top: 4rem; margin-right: 8rem">
<form action="admin" method="post">
    <h1 style="text-align: center">SignUp</h1>
    <div class="form-wrapper">
        <input type="email" name="email" placeholder="Email" class="form-control">
        <i class="zmdi zmdi-account"></i>
    </div>
    <div class="form-wrapper">
        <input type="password" name="password" placeholder="Password" class="form-control">
        <i class="zmdi zmdi-lock"></i>
    </div>
    <input hidden="hidden" name="adminlogin" value="Log in">
    <button type="submit">Login
        <i class="zmdi zmdi-arrow-right"></i>
    </button>

    <div style="margin-top: 4rem; text-align: center;" class="flex-col-c p-t-155">
						<span class="txt1 p-b-17">
							Or Sign Up
						</span>

        <a href="register.jsp" class="txt2">
            Here
        </a>
    </div>
</form>
</div>
</body>
</html>
