package com.epam.pizza_delivery.dao.interfaces;

import com.epam.pizza_delivery.entity.Category;
import com.epam.pizza_delivery.entity.Order;
import com.epam.pizza_delivery.entity.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderHistoryDAO extends DefaultDAO<OrderItem> {
    List<OrderItem> getOrderByUserId(long userId);
}
