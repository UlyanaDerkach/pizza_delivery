package com.epam.pizza_delivery.dao.interfaces;

import com.epam.pizza_delivery.entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDAO extends DefaultDAO<OrderDetails> {
    List<OrderDetails> getOrderDetailsByOrderId(long orderId) throws SQLException;
}
