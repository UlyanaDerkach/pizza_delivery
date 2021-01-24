package com.epam.pizza_delivery.dao.impl;

import com.epam.pizza_delivery.connection.ConnectionPool;
import com.epam.pizza_delivery.dao.interfaces.CategoryDAO;
import com.epam.pizza_delivery.entity.Category;
import com.epam.pizza_delivery.entity.Product;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    private static final String GET_ALL_CATEGORIES = "SELECT * FROM category ORDER BY category_id";
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    private ConnectionPool connectionPool;
    private Connection connection;
    @Override
    public void create(Category object) {

    }

    @Override
    public void update(long id, Category object) {

    }

    @Override
    public Category getByID(long id)  {
        return null;
    }

    @Override
    public List<Category> getAll()  {
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

        } catch (SQLException throwables) {
            LOGGER.error(throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return categories;
    }
    private void setCategoryParam(Category category, ResultSet resultSet) {
        try {
            category.setId(resultSet.getLong("category_id"));
            category.setName(resultSet.getString("category_name"));
        } catch (SQLException throwables) {
            LOGGER.error(throwables);
        }


    }
}
