package com.epam.pizza_delivery.dao.impl;

import com.epam.pizza_delivery.connection.ConnectionPool;
import com.epam.pizza_delivery.dao.interfaces.UserDAO;
import com.epam.pizza_delivery.entity.User;
import com.epam.pizza_delivery.validation.UserDataValidation;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final String ADD_USER = "INSERT INTO user_info (is_admin, is_deleted, first_name, last_name, email, phone, birth_date, address," +
            " login, password_hash) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String CHANGE_USER_DATA = "UPDATE user_info SET first_name = ?, last_name = ?,email = ?, phone = ?, " +
            "birth_date = ?, address = ?, login = ?  WHERE user_id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM user_info";
    private static final String CHANGE_PASSWORD = "UPDATE user_info SET password_hash = ? WHERE user_id = ?";
    private static final String GET_USER_BY_ID = "SELECT user_id,is_admin,is_deleted, first_name, last_name, email, phone, birth_date, address, " +
            " login, password_hash FROM user_info WHERE user_id = ?";
    private static final String GET_USER_BY_LOGIN_PASSWORD = "SELECT user_id, is_admin, is_deleted, first_name, last_name, email, phone, " +
            "birth_date, address, login, password_hash FROM user_info WHERE login = ? and password_hash = ?";
    private static final String DELETE_USER = "UPDATE user_info SET is_delted = 1 WHERE user_id = ?";
    private static final String CHECK_LOGIN = "SELECT * FROM user_info WHERE login = ?";


    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public User getUserByLoginPassword(String login, String password) throws SQLException, IOException {
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN_PASSWORD)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String truePassword = resultSet.getString("password_hash");
                user = new User();
                if (UserDataValidation.isCorrectPassword(password, truePassword)) {
                    setUserParam(user, resultSet);
                }
            }
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public void create(User user) throws SQLException {
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)) {
            preparedStatement.setBoolean(1, user.isAdmin());
            preparedStatement.setBoolean(2, user.isDeleted());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setDate(7, user.getBirthDate());
            preparedStatement.setString(8, user.getAddress());
            preparedStatement.setString(9, user.getLogin());
            preparedStatement.setString(10, user.getPasswordHash());
            preparedStatement.executeUpdate();

        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(long id, User user) throws SQLException {
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_USER_DATA)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setDate(5, user.getBirthDate());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getLogin());
            preparedStatement.setLong(8, id);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public User getByID(long id) throws SQLException, IOException {
        User user = new User();
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setUserParam(user, resultSet);
            }
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public List<User> getAll() throws SQLException, IOException {
        List<User> users = new ArrayList<>();
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                setUserParam(user, resultSet);
                users.add(user);
            }
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return users;
    }

    @Override
    public void changePassword(String password, long id) throws SQLException {
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_PASSWORD)) {
            preparedStatement.setString(1, password);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public boolean isUserExist(String login) throws SQLException {
        boolean isExist = false;
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                isExist = true;
            }
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return isExist;
    }

    @Override
    public void delete(long id) throws SQLException {
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }

    private void setUserParam(User user, ResultSet resultSet) throws SQLException, IOException {

        user.setId(resultSet.getLong("user_id"));
        user.setAdmin(resultSet.getBoolean("is_admin"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPhone(resultSet.getString("phone"));
        user.setBirthDate(resultSet.getDate("birth_date"));
        user.setAddress(resultSet.getString("address"));
        user.setLogin(resultSet.getString("login"));
        user.setPasswordHash(resultSet.getString("password_hash"));
        user.setDeleted(resultSet.getBoolean("is_deleted"));


    }
}
