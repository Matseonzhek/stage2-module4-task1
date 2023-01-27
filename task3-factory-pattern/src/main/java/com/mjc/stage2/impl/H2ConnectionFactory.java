package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {


    @Override
    public Connection createConnection() {
        Properties properties = new Properties();
        try {
            properties.load(H2ConnectionFactory.class.getClassLoader().getResourceAsStream("h2database.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String driver = properties.getProperty("jdbc_driver");
        String url = properties.getProperty("db_url");
        String userName = properties.getProperty("user");
        String password = properties.getProperty("");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

