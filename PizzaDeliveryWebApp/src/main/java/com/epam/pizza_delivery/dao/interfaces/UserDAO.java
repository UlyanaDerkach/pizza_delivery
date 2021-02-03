package com.epam.pizza_delivery.dao.interfaces;

import com.epam.pizza_delivery.entity.User;

public interface UserDAO  {

    User getUserByLoginPassword(String login, String password) ;

    User getByID(long id) ;

    void changePassword(String password, long id);

    boolean isUserExist(String login) ;

    void create(User user);

    void update(long id, User user);



}
