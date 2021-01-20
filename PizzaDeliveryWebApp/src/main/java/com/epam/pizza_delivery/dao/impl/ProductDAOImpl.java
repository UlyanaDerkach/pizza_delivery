package com.epam.pizza_delivery.dao.impl;

import com.epam.pizza_delivery.connection.ConnectionPool;
import com.epam.pizza_delivery.dao.interfaces.ProductDAO;
import com.epam.pizza_delivery.entity.Category;
import com.epam.pizza_delivery.entity.Product;
import com.epam.pizza_delivery.entity.User;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    private static final String GET_PRODUCTS_BY_CATEGORY = "SELECT product_id, product_name, details, " +
            "price, category_id,picture_path FROM product WHERE category_id = ?";
    private static final String GET_PRODUCT_BY_ID = "SELECT product_id, product_name, details, " +
            "price, category_id,picture_path FROM product WHERE product_id = ?";
    private static final String GET_ALL_PRODUCTS = "SELECT * FROM product";
    private static final String ADD_PRODUCT = "INSERT INTO product (product_name, details, " +
            "price, category_id,picture_path) VALUES (?,?,?,?,?)";
    private static final String DELETE_PRODUCT = "DELETE FROM product WHERE product_id = ?";
    private ConnectionPool connectionPool;
    private Connection connection;
    @Override
    public List<Product> getProductList(long categoryId) throws IOException {
        List<Product> products = new ArrayList<>();
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS_BY_CATEGORY)) {
            preparedStatement.setLong(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                setProductParam(product, resultSet);
                products.add(product);
            }

        } catch (SQLException throwables) {
            LOGGER.error(throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
       return products;
    }

    @Override
    public void delete(long productId) {
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {
                preparedStatement.setLong(1, productId);
                preparedStatement.executeUpdate();
            } finally {
                connectionPool.releaseConnection(connection);
            }
        } catch (SQLException throwables) {
            LOGGER.error(throwables);
        }
    }

    @Override
    public void create(Product product)   {
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setLong(4, product.getCategoryId());
            preparedStatement.setString(5, product.getPicturePath());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            LOGGER.error(throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(long id, Product object) throws SQLException {

    }

    @Override
    public Product getByID(long id) throws  IOException {
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        Product product = new Product();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setProductParam(product, resultSet);
            }
        } catch (SQLException throwables) {
            LOGGER.error(throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return product;
    }

    @Override
    public List<Product> getAll() throws  IOException {
        List<Product> products = new ArrayList<>();
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PRODUCTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                setProductParam(product, resultSet);
                products.add(product);
            }

        } catch (SQLException throwables) {
            LOGGER.error(throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return products;
    }
    private void setProductParam(Product product, ResultSet resultSet) throws SQLException{
        product.setId(resultSet.getLong("product_id"));
        product.setName(resultSet.getString("product_name"));
        product.setDescription(resultSet.getString("details"));
        product.setPrice(resultSet.getInt("price"));
        product.setCategoryId(resultSet.getLong("category_id"));
        product.setPicturePath(resultSet.getString("picture_path"));


    }

}
