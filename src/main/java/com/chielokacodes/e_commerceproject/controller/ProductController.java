package com.chielokacodes.e_commerceproject.controller;

import com.chielokacodes.e_commerceproject.dao.OrderDao;
import com.chielokacodes.e_commerceproject.dao.ProductDao;
import com.chielokacodes.e_commerceproject.dao.UserDao;
import com.chielokacodes.e_commerceproject.model.Cart;
import com.chielokacodes.e_commerceproject.model.User;
import com.chielokacodes.e_commerceproject.model.Product;

import com.chielokacodes.e_commerceproject.model.Order;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "product", value = "/product")
public class ProductController extends HttpServlet {
    private ProductDao productDao = new ProductDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();


        String viewProduct = req.getParameter("admin");
        String editProduct = req.getParameter("edit");
        String deleteProduct = req.getParameter("delete");
        String buyproduct = req.getParameter("buy");
        String payment = req.getParameter("payment");
        Cart cart = new Cart();
        Order makeOrderPayment = (Order) req.getSession().getAttribute("order");
        Cart paymentCart = (Cart) req.getSession().getAttribute("cart");

        ////////MAKE ORDER
        if (makeOrderPayment!=null){
            UserDao userDao = new UserDao();
            OrderDao orderDao = new OrderDao();
            Long id = (Long) req.getSession().getAttribute("userID");
            User user = userDao.findUserById.apply(id);
            if (user.getBalance().doubleValue()>makeOrderPayment.getTotalPrice().doubleValue()){
                BigDecimal balance = user.getBalance().subtract(makeOrderPayment.getTotalPrice());
                System.out.println(balance);
                user.setBalance(balance);
                userDao.updateUserBalance.apply(user);
                orderDao.saveOrder.apply(makeOrderPayment);
                req.setAttribute("paid", "Payment made successfully, your product will be delivered shortly!");
                req.getSession().setAttribute("order", null);
                RequestDispatcher dispatcher = req.getRequestDispatcher("payment-successful.jsp");
                dispatcher.forward(req, resp);
            }
            else {
                req.getSession().setAttribute("order", null);
                req.setAttribute("paid", "Insufficient Balance in your account!");
                RequestDispatcher dispatcher = req.getRequestDispatcher("payment-successful.jsp");
                dispatcher.forward(req, resp);
            }
        }
/////////////////////////////////IF PAYMENT AND CART IS NOT NULL, MAKE ORDER
        if (payment!=null&&paymentCart!=null){
            Order order = new Order();
            final BigDecimal[] totalPrice = {new BigDecimal(0)};
            List<Product> productList = new ArrayList<>();
            paymentCart.getProductIds().forEach(id->{
                order.setProductIds((order.getProductIds() != null ? order.getProductIds()+"," : "") + id); /// check
                Product product = productDao.findProductById.apply(id);
                //to prevent multithreading problems primitive array is used here is primitive datatype are thread safe
                totalPrice[0] = totalPrice[0].add(product.getProductPrice());
                productList.add(product);
            });
            HttpSession orderSession = req.getSession();
            order.setTotalPrice(totalPrice[0]);
            orderSession.setAttribute("order", order);
            req.setAttribute("product-list", productList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("payment.jsp");
            dispatcher.forward(req, resp);
        }

        //////////////BUY PRODUCT
        if (buyproduct!=null){
            if (req.getSession().getAttribute("cart")!=null){
                cart = (Cart) req.getSession().getAttribute("cart");
                cart.getProductIds().add(Long.parseLong(buyproduct));
            }
            else {
                HttpSession cartSession = req.getSession();
                Long id = (Long) cartSession.getAttribute("userID");
                cart.setUserId(id);
                List<Long> productIds = new ArrayList<>();
                productIds.add(Long.parseLong(buyproduct));
                cart.setProductIds(productIds);
                cartSession.setAttribute("cart", cart);
            }
            System.out.println(cart);
            List<Product> productList = productDao.findAllProducts.get();
            req.setAttribute("product-list", productList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("prod_dashboard.jsp"); //loggedin.jsp
            dispatcher.forward(req, resp);
        }


        //////////////EDIT PRODUCT
        if(editProduct!=null){
            Product product =  productDao.findProductById.apply(Long.parseLong(editProduct));
            req.setAttribute("edit-product", product);
            RequestDispatcher dispatcher = req.getRequestDispatcher("admin_add_product.jsp");
            dispatcher.forward(req, resp);
        }

        ////////////////DELETE PRODUCT
        if (deleteProduct!=null){
            Product product = productDao.deleteProductById.apply(Long.parseLong(deleteProduct));
            req.setAttribute("delete-product", product);
            RequestDispatcher dispatcher = req.getRequestDispatcher("admin_page.jsp");
            dispatcher.forward(req, resp);
        }


        ///////////////VIEW PRODUCT
        if (viewProduct!=null){
            List<Product> productList = productDao.findAllProducts.get();
            req.setAttribute("product-list", productList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("admin_page.jsp");
            dispatcher.forward(req, resp);
        }

        /////////////////////////////
        if (req.getAttribute("shop")!=null) {
            List<Product> productList = productDao.findAllProducts.get();
            req.setAttribute("product-list", productList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("prod_dashboard.jsp"); //loggedin.jsp
            dispatcher.forward(req, resp);
        }

        ////////////////////////
        List<Product> productList = productDao.findAllProducts.get();
        req.setAttribute("product-list", productList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("loggedin.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminAddProduct = req.getParameter("admin");
        String editProduct = req.getParameter("edit");
        String id = req.getParameter("id");

        ///////////////////ADMIN EDIT/UPDATE PRODUCT
        if (editProduct!=null){
            String productName = req.getParameter("product-name");
            String productPrice = req.getParameter("product-price");
            String productQuantity = req.getParameter("product-quantity");
            String productCategory = req.getParameter("product-category");
            String productImage = req.getParameter("product-image");
            Product product = new Product(productName, productCategory, Long.parseLong(productQuantity), new BigDecimal(productPrice), productImage);
            product.setId(Long.parseLong(id));
            productDao.updateProduct.apply(product);
            resp.sendRedirect("product?admin=view-product");
        }
        ///////////ADMIN ADD PRODUCT
        if (adminAddProduct!=null){
            String productName = req.getParameter("product-name");
            String productPrice = req.getParameter("product-price");
            String productQuantity = req.getParameter("product-quantity");
            String productCategory = req.getParameter("product-category");
            String productImage = req.getParameter("product-image");
            Product product = new Product(productName, productCategory, Long.parseLong(productQuantity), new BigDecimal(productPrice), productImage);
            if (!productDao.saveProduct.apply(product)){
                List<Product> productList = productDao.findAllProducts.get();
                req.setAttribute("product-list", productList);
                RequestDispatcher dispatcher = req.getRequestDispatcher("admin_page.jsp");
                dispatcher.forward(req, resp);
            }
            else{
                req.setAttribute("error", "Could not add product");
                RequestDispatcher dispatcher = req.getRequestDispatcher("admin_add_product.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }
}