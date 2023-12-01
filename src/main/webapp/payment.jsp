<%@ page import="com.chielokacodes.e_commerceproject.model.Order" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.chielokacodes.e_commerceproject.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <title>Cart</title>
</head>
<body class="bg-light">

<% HttpSession session1 = request.getSession();
  Order order = (Order) session.getAttribute("order");
%>

<div class="container mt-5">
  <div>
    <a href="index.jsp">
      <img style="margin-left: 4rem; width: 30px;" src="fonts/arrow-return-left.svg" alt=""/>
    </a>
  </div>
  <h2>Cart</h2>
  <table class="table">
    <thead>
    <tr>
      <th>Name</th>
      <th>Price</th>
      <th>Image</th>
      <th>Total Price</th>
    </tr>
    </thead>

    <tbody>
    <% List<Product> productList = (List<Product>) request.getAttribute("product-list");
      for (Product product : productList) { %>
    <tr>
      <td><%= product.getName() %></td>
      <td>₦<%= product.getProductPrice() %></td>
      <td><img style="width: 200px;" src='<%= product.getImage() %>' alt='Product Image'></td>
      <td><%= order.getTotalPrice() %></td>
    </tr>
<%--    <td colspan="2">Total Price</td>--%>
    </tr>
    <% }; %>
    </tbody>
  </table>

  <a href='product?pay=<%= order.getId() %>' class="btn btn-primary">Make Payment</a>
</div>

<!-- Bootstrap JS and Popper.js are required for Bootstrap components to work -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
