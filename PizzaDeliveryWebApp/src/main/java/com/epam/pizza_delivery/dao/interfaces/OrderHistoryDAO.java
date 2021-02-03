package com.epam.pizza_delivery.dao.interfaces;


import com.epam.pizza_delivery.entity.OrderItem;
import java.util.List;

public interface OrderHistoryDAO {

    List<OrderItem> getOrderByUserId(long userId);

    List<OrderItem> getAll();
}
