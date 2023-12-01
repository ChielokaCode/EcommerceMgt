package com.chielokacodes.e_commerceproject.dao;

import com.chielokacodes.e_commerceproject.config.DatabaseConfiguration;
import com.chielokacodes.e_commerceproject.dtos.LoginRequestDto;
import com.chielokacodes.e_commerceproject.model.User;
import com.chielokacodes.e_commerceproject.services.UserService;

import java.sql.*;
import java.util.Properties;
import java.util.function.Function;
import java.util.logging.Logger;

public class AdminDao {
    private Logger logger = Logger.getGlobal();
    private Connection connection;


    public UserService connect = () -> {
        Class.forName("com.mysql.cj.jdbc.Driver");

        DatabaseConfiguration configuration = new DatabaseConfiguration();
        Properties properties = new Properties();

        properties.setProperty("User", configuration.getDB_URL());
        properties.setProperty("Password", configuration.getDB_PASSWORD());

        if(connection==null || connection.isClosed()){
            connection = DriverManager.getConnection(configuration.getDB_URL(),configuration.getDB_USERNAME(), configuration.getDB_PASSWORD());
        }
    };


    public Function<User, Boolean> saveAdmin = (admin -> {
        try {
            connect.compile();
        } catch (SQLException | ClassNotFoundException e) {
            logger.warning("SQL exception"+e.getMessage());
            throw new RuntimeException(e);
        }
        String query = "INSERT INTO ecommerces.admin (name, email, password, role) VALUES (?,?,?,'admin')";

        PreparedStatement preparedStatement = null;
        try
        {

            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setString(1, user.getFirstname());
            //preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, admin.getEmail());
            preparedStatement.setString(3, admin.getPassword());
            //preparedStatement.setString(6, user.getGender());
            Boolean isGot = preparedStatement.execute();
            System.out.println(isGot);
            return isGot;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    });


    public Function<LoginRequestDto, User> findAdmin = (admin -> {
        try {
            connect.compile();
        }catch (SQLException | ClassNotFoundException e) {
            logger.warning("SQL exception" + e.getMessage());
            throw new RuntimeException("Error while finding user by email", e);
        }
        String query = "SELECT * FROM ecommerces.admin WHERE email = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, admin.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User admin1 = new User();
                admin1.setId(resultSet.getLong("id"));
                admin1.setUsername(resultSet.getString("name"));
                admin1.setEmail(resultSet.getString("email"));
                admin1.setPassword(resultSet.getString("password"));
                 return admin1;
//                return User.builder()
//                        .id(resultSet.getLong("id"))
//                        //.firstname(resultSet.getString("firstname"))
//                        .username(resultSet.getString("name"))
//                        .email(resultSet.getString("email"))
//                        .password(resultSet.getString("password"))
//                        //.gender(resultSet.getString("gender"))
//                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // Return null if admin is not found
    });

}
