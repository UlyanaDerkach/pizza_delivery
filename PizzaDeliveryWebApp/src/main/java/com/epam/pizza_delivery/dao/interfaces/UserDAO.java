package com.epam.pizza_delivery.dao.interfaces;

import com.epam.pizza_delivery.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public interface UserDAO extends DefaultDAO<User> {
    User getUserByLoginPassword(String login, String password) ;

    User getByID(long id) ;

    void changePassword(String password, long id);

    boolean isUserExist(String login) ;

    void delete(long id) ;
}
