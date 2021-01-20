package com.epam.pizza_delivery.dao.interfaces;

import com.epam.pizza_delivery.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public interface UserDAO extends DefaultDAO<User> {
    User getUserByLoginPassword(String login, String password) throws SQLException, IOException;

    User getByID(long id) throws SQLException, IOException;

    void changePassword(String password, long id) throws SQLException;

    boolean isUserExist(String login) throws SQLException;

    void delete(long id) throws SQLException;
}
