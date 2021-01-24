package com.epam.pizza_delivery.dao.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface DefaultDAO<T> {

    void create(T object);

    void update(long id, T object);

    T getByID(long id);

    List<T> getAll();

}
