package com.epam.pizza_delivery.dao.interfaces;

import com.epam.pizza_delivery.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends DefaultDAO<Order>{
Order getOrderByData(Order order) throws SQLException;
List<Order> getOrderByUserId(long userId) throws SQLException;
void updateOrderStatus(long orderId, long statusId) throws SQLException;

}

