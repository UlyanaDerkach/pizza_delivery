package com.epam.pizza_delivery.dao.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface DefaultDAO<T> {

    void create(T object) throws SQLException;

    void update(long id, T object) throws SQLException;

    T getByID(long id) throws SQLException, IOException;

    List<T> getAll() throws SQLException, IOException;

}
