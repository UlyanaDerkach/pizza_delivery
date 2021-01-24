package com.epam.pizza_delivery.dao.interfaces;

import com.epam.pizza_delivery.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends DefaultDAO<Order>{
Order getOrderByData(Order order);
List<Order> getOrderByUserId(long userId);
void updateOrderStatus(long orderId, long statusId);

}

