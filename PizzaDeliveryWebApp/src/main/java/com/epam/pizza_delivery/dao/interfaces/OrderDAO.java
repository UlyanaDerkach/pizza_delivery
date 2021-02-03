package com.epam.pizza_delivery.dao.interfaces;

import com.epam.pizza_delivery.entity.Order;
import java.util.List;

public interface OrderDAO {

    void create(Order order);

    List<Order> getAll();

    Order getOrderByData(Order order);

    List<Order> getOrderByUserId(long userId);

    void updateOrderStatus(long orderId, long statusId);

}

