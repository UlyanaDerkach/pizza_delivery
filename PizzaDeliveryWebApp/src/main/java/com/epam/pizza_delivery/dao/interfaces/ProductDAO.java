package com.epam.pizza_delivery.dao.interfaces;

import com.epam.pizza_delivery.entity.Category;
import com.epam.pizza_delivery.entity.Product;
import com.epam.pizza_delivery.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ProductDAO extends DefaultDAO<Product> {
    List<Product> getProductList(long categoryId);
    void delete(long productId);
}
