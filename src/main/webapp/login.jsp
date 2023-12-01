<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>RegistrationForm_v1 by Colorlib</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<!-- MATERIAL DESIGN ICONIC FONT -->
		<link rel="stylesheet" href="account/fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">

		<!-- STYLE CSS -->
		<link rel="stylesheet" href="css/style.css">
	</head>

	<body>
		<div class="wrapper" style="background-image: url('/images/bg-registration-form-1.jpg');">

			<div class="inner">
				<div>
					<a href="index.jsp">
						<img style="width: 30px;" src="fonts/arrow-return-left.svg" alt=""/>
					</a>
				</div>
				<div class="image-holder">
					<img src="images/registration-form-1.jpg" alt="">
				</div>

				<form action="user" method="post">
					<h3>Login</h3>
					<div class="form-wrapper">
						<input type="email" name="email" placeholder="Email" class="form-control">
						<i class="zmdi zmdi-account"></i>
					</div>
					<div class="form-wrapper">
						<input type="password" name="password" placeholder="Password" class="form-control">
						<i class="zmdi zmdi-lock"></i>
					</div>
<%--					<input hidden="hidden" name="login" value="Log in">--%>
					<a href ='product?delete=" + product.getId() + "'><button name="login">Login
						<i class="zmdi zmdi-arrow-right"></i>
					</button></a>

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
		</div>
		
	</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>