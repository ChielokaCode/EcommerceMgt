<%--<%@ page import="com.chielokacodes.e_commerceproject.model.Product" %>--%>
<%--<%@ page import="java.util.List" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<% HttpSession session1 = request.getSession();--%>
<%--  session1.setAttribute("userID", session.getAttribute("userID")); %>--%>

<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--  <meta charset="UTF-8">--%>
<%--  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">--%>
<%--  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">--%>
<%--  <title>Product Dashboard</title>--%>
<%--</head>--%>
<%--<body class="bg-light">--%>

<%--<h1 class="text-center mt-5">Cart</h1>--%>
<%--&lt;%&ndash;<a href="product?admin=view-product"><button style="margin-left: 4rem" class="btn btn-success mt-3 mb-3">Refresh Products</button></a>&ndash;%&gt;--%>

<%--<div class="container mt-5">--%>
<%--  <table class="table table-bordered">--%>
<%--    <thead class="table-dark">--%>
<%--    <tr>--%>
<%--      <th>Name</th>--%>
<%--      <th>Price</th>--%>
<%--      <th>Image</th>--%>
<%--      <th>Action</th>--%>
<%--    </tr>--%>
<%--    </thead>--%>
<%--    <tbody>--%>
<%--    <%--%>
<%--      Object productListObj = request.getAttribute("product-list");--%>

<%--      if (productListObj instanceof List) {--%>
<%--      List<Product> productList = (List<Product>) request.getAttribute("product-list");--%>
<%--      if (productList != null) {--%>
<%--      for (Product product : productList) { %>--%>
<%--    <tr>--%>
<%--      <td><%= product.getName() %></td>--%>
<%--      <td>₦<%= product.getProductPrice() %></td>--%>
<%--      <td><img style="width: 200px;" src="<%= product.getImage() %>" alt='Product Image'></td>--%>
<%--      <td><a href='product?buy=<%= product.getId() %>' class="btn btn-primary">Buy</a></td>--%>
<%--    </tr>--%>
<%--    <% }}} %>--%>
<%--    </tbody>--%>
<%--  </table>--%>

<%--  <a href="product?payment=1" class="btn btn-success">Proceed to Payment</a>--%>
<%--</div>--%>

<%--<!-- Bootstrap JS and Popper.js are required for Bootstrap components to work -->--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>--%>
<%--</body>--%>
<%--</html>--%>

<%@ page import="com.chielokacodes.e_commerceproject.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% HttpSession session1 = request.getSession();
  session1.setAttribute("userID", session.getAttribute("userID")); %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <title>Product Dashboard</title>
  <style>
    .product-box {
      border: 1px solid #dee2e6;
      padding: 15px;
      margin-bottom: 20px;
      background-color: #fff;
      border-radius: 10px;
    }

    .product-image {
      max-width: 100%;
      height: auto;
    }
  </style>
</head>
<body class="bg-light">

<h1 class="text-center mt-5">Product Dashboard</h1>
<%
  PrintWriter out1 = response.getWriter();
  out1.println("<div style=\"text-align: right; margin-right: 3rem;\">");
  out1.println("<h3>Welcome, " + session.getAttribute("userName")+ " " + "</h3>");
  out1.println("<a href=\"index.jsp\">Log Out</a>");
  out1.println("</div>");
%>


<div class="container mt-5">
  <div>
    <a href="index.jsp">
      <img style="margin-left: 4rem; width: 30px;" src="fonts/arrow-return-left.svg" alt=""/>
    </a>
  </div>
  <div class="row">
    <%
      Object productListObj = request.getAttribute("product-list");

      if (productListObj instanceof List) {
        List<Product> productList = (List<Product>) request.getAttribute("product-list");
        if (productList != null) {
          for (Product product : productList) { %>
    <div class="col-md-4">
      <div class="product-box">
        <img style="width: 200px;" src="<%= product.getImage() %>" alt='Product Image' class="product-image">
        <h4 class="mt-3"><%= product.getName() %></h4>
        <p><strong>Price:</strong> <%= product.getProductPrice() %></p>
        <a href='product?buy=<%= product.getId() %>' class="btn btn-primary">Buy</a>
      </div>
    </div>
    <% }}} %>
  </div>

  <a href="product?payment=1" class="btn btn-success mt-3">Proceed to Payment</a>
</div>

<!-- Bootstrap JS and Popper.js are required for Bootstrap components to work -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
