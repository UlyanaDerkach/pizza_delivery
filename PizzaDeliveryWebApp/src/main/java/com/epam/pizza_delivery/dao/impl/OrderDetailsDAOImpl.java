package com.epam.pizza_delivery.dao.impl;

import com.epam.pizza_delivery.connection.ConnectionPool;
import com.epam.pizza_delivery.dao.interfaces.OrderDetailsDAO;
import com.epam.pizza_delivery.entity.OrderDetails;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    private static final String ADD_ORDER_DETAILS = "INSERT INTO order_details (order_id, product_id, product_amount, " +
            "total_cost) VALUES (?,?,?,?)";
    private ConnectionPool connectionPool;
    private Connection connection;
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    @Override
    public void create(OrderDetails orderDetails) {
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER_DETAILS)) {
            preparedStatement.setLong(1,orderDetails.getOrderId());
            preparedStatement.setLong(2,orderDetails.getProductId());
            preparedStatement.setInt(3,orderDetails.getProductAmount());

            preparedStatement.setInt(4,orderDetails.getTotalCost());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.error(throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
