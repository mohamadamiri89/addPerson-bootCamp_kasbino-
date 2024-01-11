package com.examole.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DbConnection {
    private final Logger logger = Logger.getLogger(DbConnection.class.getName());

    private Connection connection;

    public DbConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            logger.info("Database connection error");
            throw new RuntimeException(e);
        }
    }

    public void connect() {
        try {
            this.connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "Makeen", "123");
        } catch (SQLException e) {
            logger.info("Fail connection ");
            throw new RuntimeException(e);
        }

    }


    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            this.connection.close();
            logger.info("Connection closed");
        } catch (SQLException e) {
            logger.info("Fail connection ");
            throw new RuntimeException(e);
        }
    }
}
