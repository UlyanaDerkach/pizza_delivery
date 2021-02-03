package com.epam.pizza_delivery.dao.impl;

import com.epam.pizza_delivery.connection.ConnectionPool;
import com.epam.pizza_delivery.dao.interfaces.OrderDAO;
import com.epam.pizza_delivery.entity.Order;
import org.apache.log4j.Logger;
import java.sql.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static final String ADD_ORDER = "INSERT INTO order_base (order_date,order_time,delivery_date, delivery_time, total_sum, user_id, status_id) VALUES (?,?,?,?,?,?,?)";
    private static final String GET_ORDER_BY_DATA = "SELECT * FROM order_base WHERE order_date = ? and order_time = ? and user_id = ?";
    private static final String GET_ORDER_BY_USER_ID = "SELECT * FROM order_base WHERE  user_id = ?";
    private static final String GET_ALL_NOT_DELIVERED_ORDERS = "SELECT * FROM order_base WHERE status_id != 4";
    private static final String UPDATE_STATUS_ID = "UPDATE order_base SET status_id = ?  WHERE order_id = ?";
    private ConnectionPool connectionPool;
    private Connection connection;
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    @Override
    public void create(Order order){
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER)) {
            preparedStatement.setObject(1, order.getOrderDate());
            preparedStatement.setObject(2, order.getOrderTime());
            preparedStatement.setObject(3,order.getDeliveryDate());
            preparedStatement.setObject(4,order.getDeliveryTime());
            preparedStatement.setInt(5,order.getTotalSum());
            preparedStatement.setLong(6,order.getUserId());
            preparedStatement.setLong(7,order.getStatusId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.error(throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }


    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_NOT_DELIVERED_ORDERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                setOrderParam(order, resultSet);
                orders.add(order);
            }
        } catch (SQLException throwables) {
            LOGGER.error(throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return orders;
    }

    @Override
    public Order getOrderByData(Order order)  {
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        Order completedOrder = new Order();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_DATA)) {
            preparedStatement.setObject(1, order.getOrderDate());
            preparedStatement.setObject(2, order.getOrderTime());
            preparedStatement.setLong(3, order.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setOrderParam(completedOrder,resultSet);
            }
        } catch (SQLException throwables) {
            LOGGER.error(throwables);
        } finally {

            connectionPool.releaseConnection(connection);
        }
        return completedOrder;
    }

    @Override
    public List<Order> getOrderByUserId(long userId) {
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                setOrderParam(order,resultSet);
                orders.add(order);
            }
        } catch (SQLException throwables) {
            LOGGER.error(throwables);
        } finally {

            connectionPool.releaseConnection(connection);
        }
        return orders;
    }

    @Override
    public void updateOrderStatus(long orderId, long statusId) {
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATUS_ID)) {
            preparedStatement.setLong(1, statusId);
            preparedStatement.setLong(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.error(throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private void setOrderParam(Order order, ResultSet resultSet) {
        try {
            order.setId(resultSet.getLong("order_id"));
        order.setUserId(resultSet.getLong("user_id"));
        order.setStatusId(resultSet.getLong("status_id"));
        order.setOrderDate(resultSet.getDate("order_date").toLocalDate());
        order.setOrderTime(resultSet.getTime("order_time").toLocalTime().minus(6, ChronoUnit.HOURS));
        order.setDeliveryDate(resultSet.getDate("delivery_date").toLocalDate());
        order.setDeliveryTime(resultSet.getTime("delivery_time").toLocalTime().minus(6, ChronoUnit.HOURS));
        order.setTotalSum(resultSet.getInt("total_sum"));
        } catch (SQLException throwables) {
            LOGGER.error(throwables);
        }
    }
}
