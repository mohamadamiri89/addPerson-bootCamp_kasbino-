package com.example.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class DbConnection {
    private final Logger logger = Logger.getLogger(DbConnection.class.getName());
    private Connection connection;

    public DbConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.info("Database connection error");
            throw new RuntimeException(e);
        }
    }

    public void connect() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            this.connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
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
            logger.info("Failed to close connection");
            throw new RuntimeException(e);
        }
    }
}
