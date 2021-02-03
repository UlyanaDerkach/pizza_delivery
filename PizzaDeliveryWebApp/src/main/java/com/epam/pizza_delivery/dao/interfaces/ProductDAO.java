package com.epam.pizza_delivery.dao.interfaces;


import com.epam.pizza_delivery.entity.Product;
import java.util.List;

public interface ProductDAO  {

    void create(Product product);

    void delete(long productId);

    Product getByID(long id);

    List<Product> getAll();
}
