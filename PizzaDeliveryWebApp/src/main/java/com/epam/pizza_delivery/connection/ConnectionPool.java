package com.epam.pizza_delivery.connection;

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
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void initConnections(){
        Connection connection;
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                connection = DriverManager.getConnection(url, userName, password);
                freeConnections.put(connection);
            } catch (SQLException | InterruptedException e) {
                //log
            }
        }
    }
    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = freeConnections.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
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
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void deregisterDrivers(){
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


}
