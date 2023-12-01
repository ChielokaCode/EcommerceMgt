<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.chielokacodes.e_commerceproject.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% PrintWriter out1 = response.getWriter();
out1.println("<!DOCTYPE html>");
out1.println("<html lang=\"en\">");
out1.println("<head>");
out1.println("<meta charset=\"UTF-8\">");
out1.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
out1.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
out1.println("<link rel=\"stylesheet\" href=\"styles.css\">");
out1.println("<title>E-Commerce Admin</title>");
out1.println("</head>");
out1.println("<body>");
out1.println("<header>");
out1.println("<a href=\"#\" class=\"logo\">");
out1.println("	<span class=\"bold-text\">COZA</span> <span class=\"normal-text\">STORE</span>");
out1.println("</a>");
out1.println("<div class=\"admin-profile\">");
out1.println("		<span>Welcome, " + session.getAttribute("AdminName") + "</span>");
out1.println("	<a href=\"index.jsp\" class=\"logout-link\">Logout</a>");
out1.println("</div>");
out1.println("<h1 class=\"text-center\">Admin Dashboard</h1>");
out1.println("</header>");
out1.println("<main class=\"container\">");
out1.println("<a href=\"admin_add_product.jsp\"><button class=\"btn btn-success mt-3 mb-3\">Add Products</button></a>"); //admin_add_product.jsp
out1.println("<a href=\"product?admin=view-product\"><button class=\"btn btn-success mt-3 mb-3\">Refresh Products</button></a>");
out1.println("<table class=\"table\" border="+1+">\n" +
			"  <thead> " +
			"<tr>" +
			"<th>Name </th>  " +
			"<th> Quantity  </th>   " +
			"<th> Price </th>" +
			"<th> Image </th>" +
			"<th> Actions </th>" +
			"</tr>" +
			"</thead>"
	);
	List<Product> productList = (List<Product>) request.getAttribute("product-list");
	productList.forEach(product -> {
				out1.println(
						"<tr>" +
								"<td>" + product.getName() + " " + "</td>" +
								"<td>" + product.getQuantity() + " " + "</td>" +
								"<td>₦" + product.getProductPrice() + " " + "</td>" +
								"<td>" + "<img style=\"width: 200px;\" src =" + product.getImage() +"/>" + " " + "</td>" +
                                "<td>"
				);
				out1.println("<a href ='product?edit=" + product.getId() + "'><button class=\"btn-view\">Edit</button></a>");
				out1.println("<a href ='product?delete=" + product.getId() + "'><button class=\"btn-update\">Delete</button></a>");
				out1.println("		</td>");
				out1.println("	</tr>");
			});
out1.println("	<!-- Add more product rows as needed -->");
out1.println("	</tbody>");
out1.println("</table>");
out1.println("</main>");
out1.println("<footer class=\"text-center mt-3\">");
out1.println("<p>&copy; 2023 E-Commerce Platform</p>");
out1.println("</footer>");
out1.println("<!-- Link to Bootstrap JS (optional if you need Bootstrap JavaScript features) -->");
out1.println("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>");
out1.println("</body>");
out1.println("</html>");
%>