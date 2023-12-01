<%--
  Created by IntelliJ IDEA.
  User: wikiwoo
  Date: 30/11/2023
  Time: 1:50 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Reg</title>
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
  <%--					<div class="form-group">--%>
  <%--						<input type="text" placeholder="First Name" name="firstname" class="form-control">--%>
  <%--						<input type="text" placeholder="Last Name" name="lastname" class="form-control">--%>
  <%--					</div>--%>
  <div class="form-wrapper">
    <input type="text" placeholder="Username" name="name" class="form-control">
    <i class="zmdi zmdi-account"></i>
  </div>
  <div class="form-wrapper">
    <input type="email" placeholder="Email Address" name="email" class="form-control">
    <i class="zmdi zmdi-email"></i>
  </div>
  <%--					<div class="form-wrapper">--%>
  <%--						<select name="gender" id="" class="form-control">--%>
  <%--							<option value="" disabled selected>Gender</option>--%>
  <%--							<option value="male">Male</option>--%>
  <%--							<option value="female">Female</option>--%>
  <%--							<option value="other">Other</option>--%>
  <%--						</select>--%>
  <%--						<i class="zmdi zmdi-caret-down" style="font-size: 17px"></i>--%>
  <%--					</div>--%>
  <div class="form-wrapper">
    <input type="password" placeholder="Password" name="password" class="form-control">
    <i class="zmdi zmdi-lock"></i>
  </div>
  <div class="form-wrapper">
    <input type="password" placeholder="Confirm Password" name="password" class="form-control">
    <i class="zmdi zmdi-lock"></i>
  </div>
  <input hidden="hidden" name="adminlogin" value="adminlogin">
  <button type="submit">SignUp
    <i class="zmdi zmdi-arrow-right"></i>
  </button>

  <div style="margin-top: 2rem; text-align: center;" class="flex-col-c p-t-155">
    <span class="txt1 p-b-17">Already a member? Login </span>
    <a href="login.jsp" class="txt2">
      Here
    </a>
  </div>
</form>
</div>
</body>
</html>
