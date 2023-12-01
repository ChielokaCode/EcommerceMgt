package com.chielokacodes.e_commerceproject.controller;

import com.chielokacodes.e_commerceproject.config.DatabaseConfiguration;
import com.chielokacodes.e_commerceproject.dao.UserDao;
import com.chielokacodes.e_commerceproject.serviceImpl.UserServiceImpl;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.sql.SQLException;

public class MyAppInitializer implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        UserServiceImpl userService = new UserServiceImpl();
        UserDao userDao = new UserDao();
        DatabaseConfiguration configuration = new DatabaseConfiguration();

        try {
            userDao.connect.compile();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error during database connection initialization", e);
        }

        event.getServletContext().setAttribute("userService", userService);
        event.getServletContext().setAttribute("userDao", userDao);
    }

    public void contextDestroyed(ServletContextEvent event) {
        // Perform cleanup tasks here
    }
}

