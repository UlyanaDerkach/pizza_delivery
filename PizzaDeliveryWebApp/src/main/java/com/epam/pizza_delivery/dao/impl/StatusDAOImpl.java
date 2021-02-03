package com.epam.pizza_delivery.dao.impl;

import com.epam.pizza_delivery.connection.ConnectionPool;
import com.epam.pizza_delivery.dao.interfaces.StatusDAO;
import com.epam.pizza_delivery.entity.StatusDict;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusDAOImpl implements StatusDAO {
    private static final String GET_ALL_STATUSES = "SELECT * FROM status_dict";
    private ConnectionPool connectionPool;
    private Connection connection;
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    @Override
    public List<StatusDict> getAll() {
        List<StatusDict> statusDictList = new ArrayList<>();
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_STATUSES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StatusDict statusDict = new StatusDict();
                setStatusParam(statusDict, resultSet);
                statusDictList.add(statusDict);
            }
        } catch (SQLException throwables) {
            LOGGER.error(throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return statusDictList;
    }
    private void setStatusParam(StatusDict statusDict, ResultSet resultSet) {
        try {
            statusDict.setId(resultSet.getLong("status_id"));
            statusDict.setName(resultSet.getString("status_name"));
        } catch (SQLException throwables) {
            LOGGER.error(throwables);
        }
    }
}
