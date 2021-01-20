package com.epam.pizza_delivery.dao.impl;

import com.epam.pizza_delivery.connection.ConnectionPool;
import com.epam.pizza_delivery.dao.interfaces.OrderDetailsDAO;
import com.epam.pizza_delivery.entity.Order;
import com.epam.pizza_delivery.entity.OrderDetails;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    private static final String ADD_ORDER_DETAILS = "INSERT INTO order_details (order_id, product_id, product_amount, " +
            "total_cost) VALUES (?,?,?,?)";
    private static final String GET_ORDER_DETAILS_BY_ORDER_ID = "SELECT * FROM order_details WHERE order_id = ?";
    private ConnectionPool connectionPool;
    private Connection connection;
    @Override
    public void create(OrderDetails orderDetails) throws SQLException {
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER_DETAILS)) {
            preparedStatement.setLong(1,orderDetails.getOrderId());
            preparedStatement.setLong(2,orderDetails.getProductId());
            preparedStatement.setInt(3,orderDetails.getProductAmount());

            preparedStatement.setInt(4,orderDetails.getTotalCost());
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(long id, OrderDetails object) throws SQLException {

    }

    @Override
    public OrderDetails getByID(long id) throws SQLException, IOException {
        return null;
    }

    @Override
    public List<OrderDetails> getAll() throws SQLException, IOException {
        return null;
    }

    @Override
    public List<OrderDetails> getOrderDetailsByOrderId(long orderId) throws SQLException {
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_DETAILS_BY_ORDER_ID)) {
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderDetails orderDetails = new OrderDetails();
                setOrderDetailsParam(orderDetails,resultSet);
                orderDetailsList.add(orderDetails);
            }
        } finally {

            connectionPool.releaseConnection(connection);
        }
        return orderDetailsList;
    }


    private void setOrderDetailsParam(OrderDetails orderDetails, ResultSet resultSet) throws SQLException {
        orderDetails.setId(resultSet.getLong("details_id"));
        orderDetails.setOrderId(resultSet.getLong("order_id"));
        orderDetails.setProductId(resultSet.getLong("product_id"));
        orderDetails.setProductAmount(resultSet.getInt("product_amount"));
        orderDetails.setTotalCost(resultSet.getInt("total_cost"));
    }
}