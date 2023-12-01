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
					<h3>SignUp</h3>
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
<%--					<input hidden="hidden" name="login" value="login">--%>
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
		</div>
		
	</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>