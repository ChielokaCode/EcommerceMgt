package com.chielokacodes.e_commerceproject.dao;

import com.chielokacodes.e_commerceproject.config.DatabaseConfiguration;
import com.chielokacodes.e_commerceproject.model.User;
import com.chielokacodes.e_commerceproject.services.UserService;
import com.chielokacodes.e_commerceproject.dtos.LoginRequestDto;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Properties;
import java.util.function.Function;
import java.util.logging.Logger;

public class UserDao {
    private Logger logger = Logger.getGlobal();
    private Connection connection;

/////CREATE JDBC CONNECTION////////////////////
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

    //////////////REGISTER USER DAO REQUEST///////////////////
    public Function<User, Boolean> saveUser = (user -> {
        try {
            connect.compile();
        } catch (SQLException | ClassNotFoundException e) {
            logger.warning("SQL exception"+e.getMessage());
            throw new RuntimeException(e);
        }
        String query = "INSERT INTO ecommerces.users (name, email, password, role) VALUES (?,?,?,'user')";

        PreparedStatement preparedStatement = null;
        try
        {

            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setString(1, user.getFirstname());
            //preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setDouble(4, new BigDecimal(50000000).doubleValue());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            //preparedStatement.setString(6, user.getGender());
            Boolean isGot = preparedStatement.execute();
            System.out.println(isGot);
            return isGot;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    });


    //////////////LOGIN USER DAO REQUEST///////////////////////////
    public Function<LoginRequestDto, User> findUser = (user -> {
       try {
           connect.compile();
       }catch (SQLException | ClassNotFoundException e) {
           logger.warning("SQL exception" + e.getMessage());
           throw new RuntimeException("Error while finding user by email", e);
       }
           String query = "SELECT * FROM ecommerces.users WHERE email = ?";
           PreparedStatement preparedStatement = null;

            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, user.getEmail());
                ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        return User.builder()
                                .id(resultSet.getLong("id"))
                                //.firstname(resultSet.getString("firstname"))
                                .username(resultSet.getString("name"))
                                .email(resultSet.getString("email"))
                                .password(resultSet.getString("password"))
                                //.gender(resultSet.getString("gender"))
                                .build();
                    }
                } catch (SQLException e) {
                     throw new RuntimeException(e);
        }
        return null; // Return null if user is not found
    });

    //////////UPDATE USER BALANCE//////////////////
    public Function<User, Boolean> updateUserBalance = (user -> {
        try {
            connect.compile();
        } catch (SQLException | ClassNotFoundException e) {
            logger.warning("SQL exception"+e.getMessage());
            throw new RuntimeException(e);
        }
        String query = "UPDATE ecommerces.users SET balance = ? WHERE id = ? ";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(2, user.getId());
            preparedStatement.setDouble(1, user.getBalance().doubleValue());
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    });


    ///////////////////FIND USER BY ID/////////////////////
    public Function<Long, User> findUserById = (id -> {
        try {
            connect.compile();
        } catch (SQLException | ClassNotFoundException e) {
            logger.warning("SQL exception"+e.getMessage());
            throw new RuntimeException(e);
        }
        String query = "SELECT * FROM ecommerces.users WHERE id = ?";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return User.builder()
                        .id(resultSet.getLong(1))
                        .email(resultSet.getString(2))
                        .password(resultSet.getString(3))
                        .username(resultSet.getString(4))
                        .balance(resultSet.getBigDecimal(5)).build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    });





}
