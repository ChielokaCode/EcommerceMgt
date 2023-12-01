package com.chielokacodes.e_commerceproject.controller;

import com.chielokacodes.e_commerceproject.dao.AdminDao;
import com.chielokacodes.e_commerceproject.dao.ProductDao;
import com.chielokacodes.e_commerceproject.dtos.LoginRequestDto;
import com.chielokacodes.e_commerceproject.dtos.UserDto;
import com.chielokacodes.e_commerceproject.model.Product;
import com.chielokacodes.e_commerceproject.model.User;
import com.chielokacodes.e_commerceproject.serviceImpl.UserServiceImpl;
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
import java.util.List;

@WebServlet(name = "admin", value = "/admin")
public class AdminController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminlogin = request.getParameter("adminlogin") == null ? null : request.getParameter("adminlogin");
        if (adminlogin != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("adminLogin.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("adminLogin.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Displaying Admin information now...");

        UserServiceImpl adminService = new UserServiceImpl();
        UserDto adminDto = new UserDto();
        AdminDao adminDao = new AdminDao();
        ProductDao productDao = new ProductDao();
        String editProduct = req.getParameter("edit");
        String adminAddProduct = req.getParameter("admin");
        String id = req.getParameter("id");
        //User admin = new User(adminDto);


//        System.out.println(adminlogin);
//
////         REGISTER REQUEST
//            adminDto.setUsername(req.getParameter("name"));
//            adminDto.setEmail(req.getParameter("email"));
//            adminDto.setPassword(req.getParameter("password"));
//            //userDto.setGender(req.getParameter("gender"));
//
//            Boolean isSaved = adminDao.saveAdmin.apply(new User(adminDto));
//            System.out.println(isSaved);
//            RequestDispatcher dispatcher = req.getRequestDispatcher("adminLogin.jsp");
//            dispatcher.forward(req, resp);

        ///LOGIN REQUESTS
        String adminlogin = req.getParameter("adminlogin")==null?null:req.getParameter("adminlogin");
        if (adminlogin != null) {
            LoginRequestDto loggedInAdmin = new LoginRequestDto(req.getParameter("email"), req.getParameter("password"));
            User admin = adminDao.findAdmin.apply(loggedInAdmin);
            loggedInAdmin.setHashPassword(admin.getPassword());
            Boolean isVerified = adminService.verifyPassword.apply(loggedInAdmin);
            System.out.println(isVerified);
            if (isVerified) {
                HttpSession session = req.getSession();
                session.setAttribute("AdminRole", admin.getRole());
                session.setAttribute("AdminName", admin.getUsername());
                RequestDispatcher dispatcher = req.getRequestDispatcher("admin_page.jsp");
                dispatcher.forward(req, resp);
                System.out.println("Successfully Logged In");

            } else {
                System.out.println("Error: Incorrect password");
                RequestDispatcher dispatcher = req.getRequestDispatcher("adminLogin.jsp");
                dispatcher.forward(req, resp);
                System.out.println("Login details not found");
            }
        }


    }



}