package com.chielokacodes.e_commerceproject.dao;

import com.chielokacodes.e_commerceproject.config.DatabaseConfiguration;
import com.chielokacodes.e_commerceproject.model.Product;
import com.chielokacodes.e_commerceproject.services.UserService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Logger;


public class ProductDao {
    private Connection connection;
    private Logger logger = Logger.getGlobal();

    public UserService connect = () -> {
        Class.forName("com.mysql.cj.jdbc.Driver");
        DatabaseConfiguration configuration = new DatabaseConfiguration();
        Properties properties = new Properties();
        properties.setProperty("User", configuration.getDB_URL());
        properties.setProperty("Password", configuration.getDB_PASSWORD());
        if (connection==null|| connection.isClosed()){
            connection = DriverManager.getConnection(configuration.getDB_URL(), configuration.getDB_USERNAME(), configuration.getDB_PASSWORD());
        }
    };

    ///SAVE PRODUCT DAO REQUEST
    public Function<Product, Boolean> saveProduct = (product -> {
        try {
            connect.compile();
        } catch (SQLException | ClassNotFoundException e) {
            logger.warning("SQL exception"+e.getMessage());
            throw new RuntimeException(e);
        }
        String query = "INSERT INTO ecommerces.product  (name, category, quantity, price, image ) VALUES (?, ?, ?, ?, ?) ";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getCategory());
            preparedStatement.setLong(3, product.getQuantity());
            preparedStatement.setDouble(4, product.getProductPrice().doubleValue());
            preparedStatement.setString(5, product.getImage());

            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    });


    ////////UPDATE PRODUCT DAO REQUEST
    public Function<Product, Boolean> updateProduct = (product -> {
        try {
            connect.compile();
        } catch (SQLException | ClassNotFoundException e) {
            logger.warning("SQL exception"+e.getMessage());
            throw new RuntimeException(e);
        }
        String query = "UPDATE ecommerces.product SET name =?, category=?, quantity=?, price=?, image=? WHERE id =? ";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getCategory());
            preparedStatement.setLong(3, product.getQuantity());
            preparedStatement.setDouble(4, product.getProductPrice().doubleValue());
            preparedStatement.setString(5, product.getImage());
            preparedStatement.setLong(6, product.getId());
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    });


    ///////FIND ALL PRODUCTS
    public Supplier<List<Product>> findAllProducts = () ->{
        try {
            connect.compile();
        } catch (SQLException | ClassNotFoundException e) {
            logger.warning("SQL exception"+e.getMessage());
            throw new RuntimeException(e);
        }
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM ecommerces.product";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Product product = Product.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .category(resultSet.getString("category"))
                        .productPrice(resultSet.getBigDecimal("price"))
                        .quantity(resultSet.getLong("quantity"))
                        .image(resultSet.getString("image"))
                        .build();
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    ///////FIND ALL PRODUCTS BY ID
    public Function<Long, Product> findProductById = (id) ->{
        try {
            connect.compile();
        } catch (SQLException | ClassNotFoundException e) {
            logger.warning("SQL exception"+e.getMessage());
            throw new RuntimeException(e);
        }
        String query = "SELECT * FROM ecommerces.product where id = ?";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return Product.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .category(resultSet.getString("category"))
                        .productPrice(resultSet.getBigDecimal("price"))
                        .quantity(resultSet.getLong("quantity"))
                        .image(resultSet.getString("image"))
                        .build();
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };


    //////////////DELETE PRODUCT
    public Function<Long, Product> deleteProductById = (id) -> {
        try {
            connect.compile();
        } catch (SQLException | ClassNotFoundException e) {
            logger.warning("SQL exception: " + e.getMessage());
            throw new RuntimeException(e);
        }

        String query = "DELETE FROM ecommerces.product WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Assuming you want to return the deleted product or handle the deletion in some way
        return new Product(); // You may need to adjust this based on your Product class
    };


}
