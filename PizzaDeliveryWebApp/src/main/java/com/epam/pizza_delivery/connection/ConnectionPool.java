package com.epam.pizza_delivery.connection;

import org.apache.log4j.Logger;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public enum  ConnectionPool {
    INSTANCE;
    private String driver;
    private String url;
    private String userName;
    private String password;
    private ResourceBundle properties = ResourceBundle.getBundle("connection");
    private BlockingQueue<Connection> freeConnections;
    private final static int POOL_SIZE = 5;
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    ConnectionPool() {
        setParametersForConnection();
        registerDriver();
        initConnections();

    }
    private void setParametersForConnection(){
        this.driver = properties.getString("db.driver");
        this.url = properties.getString("db.url");
        this.userName = properties.getString("db.userName");
        this.password = properties.getString("db.password");
        freeConnections = new ArrayBlockingQueue<>(POOL_SIZE);
    }
    private void registerDriver(){
        try {
            Driver registeredDriver = (Driver) Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException |
                IllegalAccessException | ClassNotFoundException e) {
            LOGGER.error(e);
        }
    }
    private void initConnections(){
        Connection connection;
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                connection = DriverManager.getConnection(url, userName, password);
                freeConnections.put(connection);
            } catch (SQLException | InterruptedException e) {
                LOGGER.error(e);
            }
        }
    }
    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = freeConnections.take();
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
        return connection;
    }
    public void releaseConnection(Connection connection){
        freeConnections.offer(connection);
    }

    public void destroyPool(){
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().close();
            } catch (SQLException | InterruptedException e) {
                LOGGER.error(e);
            } finally {
                deregisterDrivers();
            }
        }
    }
    private void deregisterDrivers(){
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        });
    }


}
