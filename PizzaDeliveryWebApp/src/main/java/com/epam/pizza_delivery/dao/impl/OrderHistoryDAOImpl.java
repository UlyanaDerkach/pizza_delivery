package com.epam.pizza_delivery.dao.impl;

import com.epam.pizza_delivery.connection.ConnectionPool;
import com.epam.pizza_delivery.dao.interfaces.OrderHistoryDAO;
import com.epam.pizza_delivery.entity.Order;
import com.epam.pizza_delivery.entity.OrderItem;
import com.epam.pizza_delivery.entity.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDAOImpl implements OrderHistoryDAO {
    private static final String GET_ORDER_BY_USER_ID = "SELECT * FROM user_order_history WHERE user_id = ?";
    private static final String GET_ALL_ORDERS = "SELECT * FROM user_order_history";
    private ConnectionPool connectionPool;
    private Connection connection;
    @Override
    public void create(OrderItem object) throws SQLException {

    }

    @Override
    public void update(long id, OrderItem object) throws SQLException {

    }

    @Override
    public OrderItem getByID(long id) throws SQLException, IOException {
        return null;
    }

    @Override
    public List<OrderItem> getAll() throws SQLException, IOException {
        List<OrderItem> orders = new ArrayList<>();
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderItem orderItem = new OrderItem();
                setOrderItemParam(orderItem, resultSet);
                orders.add(orderItem);
            }
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return orders;
    }

    @Override
    public List<OrderItem> getOrderByUserId(long userId) throws SQLException {
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        List<OrderItem> orderItems = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderItem orderItem = new OrderItem();
                setOrderItemParam(orderItem,resultSet);
                orderItems.add(orderItem);
            }
        } finally {

            connectionPool.releaseConnection(connection);
        }
        return orderItems;
    }
    private void setOrderItemParam(OrderItem orderItem, ResultSet resultSet) throws SQLException {
        orderItem.setOrderId(resultSet.getLong("order_id"));
        orderItem.setUserId(resultSet.getLong("user_id"));
        orderItem.setProductName(resultSet.getString("product_name"));
        orderItem.setProductAmount(resultSet.getInt("product_amount"));
        orderItem.setDeliveryDate(resultSet.getDate("delivery_date").toLocalDate());
        orderItem.setDeliveryTime(resultSet.getTime("delivery_time").toLocalTime().minus(6, ChronoUnit.HOURS));
        orderItem.setPicturePath(resultSet.getString("picture_path"));
    }
}
