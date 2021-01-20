package com.epam.pizza_delivery.dao.impl;

import com.epam.pizza_delivery.connection.ConnectionPool;
import com.epam.pizza_delivery.dao.interfaces.CategoryDAO;
import com.epam.pizza_delivery.entity.Category;
import com.epam.pizza_delivery.entity.Product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    private static final String GET_ALL_CATEGORIES = "SELECT * FROM category ORDER BY category_id";


    private ConnectionPool connectionPool;
    private Connection connection;
    @Override
    public void create(Category object) throws SQLException {

    }

    @Override
    public void update(long id, Category object) throws SQLException {

    }

    @Override
    public Category getByID(long id) throws SQLException, IOException {
        return null;
    }

    @Override
    public List<Category> getAll() throws SQLException, IOException {
        List<Category> categories = new ArrayList<>();
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CATEGORIES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                setCategoryParam(category, resultSet);
                categories.add(category);
            }

        } finally {
            connectionPool.releaseConnection(connection);
        }
        return categories;
    }
    private void setCategoryParam(Category category, ResultSet resultSet) throws SQLException {
        category.setId(resultSet.getLong("category_id"));
        category.setName(resultSet.getString("category_name"));

    }
}
