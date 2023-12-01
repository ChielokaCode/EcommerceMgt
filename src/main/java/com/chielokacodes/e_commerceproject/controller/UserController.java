package com.chielokacodes.e_commerceproject.controller;

import com.chielokacodes.e_commerceproject.dao.ProductDao;
import com.chielokacodes.e_commerceproject.dao.UserDao;
import com.chielokacodes.e_commerceproject.dtos.LoginRequestDto;
import com.chielokacodes.e_commerceproject.dtos.UserDto;
import com.chielokacodes.e_commerceproject.model.User;
import com.chielokacodes.e_commerceproject.serviceImpl.UserServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "user", value = "/user")
public class UserController extends  HttpServlet {
    private static final long serialVersionUID = 1L;
    //private final Logger logger = Logger.getGlobal();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login") == null ? null : req.getParameter("login");
        String admin = req.getParameter("admin");

        if (admin != null && Objects.equals(admin, true)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("admin_add_product.jsp"); //login.jsp
            dispatcher.forward(req, resp);
        }
        if(login!=null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Displaying user information now...");
        UserServiceImpl userService = new UserServiceImpl();
        UserDto userDto = new UserDto();
        UserDao userDao = new UserDao();


        ///////////LOGIN USER
        String login = req.getParameter("login")==null?null:req.getParameter("login");

        if (login != null) {
            LoginRequestDto loggedInUser = new LoginRequestDto(req.getParameter("email"), req.getParameter("password"));
             User user = userDao.findUser.apply(loggedInUser);
            loggedInUser.setHashPassword(user.getPassword());
            Boolean isVerified = userService.verifyPassword.apply(loggedInUser);
            System.out.println(isVerified);
            if (isVerified) {
                HttpSession session = req.getSession();
                session.setAttribute("userID", user.getId());
                session.setAttribute("userName", user.getUsername());
                req.setAttribute("product-list", new ProductDao().findAllProducts.get()); ////////check
                RequestDispatcher dispatcher = req.getRequestDispatcher("prod_dashboard.jsp");
                dispatcher.forward(req, resp);
                System.out.println("Successfully Logged In");

            } else {
                System.out.println("Error: Incorrect password");
                RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
                dispatcher.forward(req, resp);
                System.out.println("Login details not found");
            }
        }
        else {
            // Email doesn't exist, handle accordingly
            System.out.println("Error: Email not found");
            RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
            dispatcher.forward(req, resp);
        }

        //userDto.setFirstname(req.getParameter("firstname"));
        //userDto.setLastname(req.getParameter("lastname"));


        ////////////REGISTER USER
        userDto.setUsername(req.getParameter("name"));
        userDto.setEmail(req.getParameter("email"));
        userDto.setPassword(req.getParameter("password"));
        //userDto.setGender(req.getParameter("gender"));

        Boolean isSaved = userDao.saveUser.apply(new User(userDto));
        System.out.println(isSaved);
        RequestDispatcher dispatcher = req.getRequestDispatcher("success.jsp");
        dispatcher.forward(req, resp);

    }
}
