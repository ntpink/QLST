package com.qlst.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAO4 {
    private static final Properties DB_PROPERTIES = loadProperties();
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = DAO4.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new IllegalStateException("Không tìm thấy db.properties");
            }
            properties.load(input);
            return properties;
        } catch (IOException e) {
            throw new IllegalStateException("Không đọc được db.properties", e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DB_PROPERTIES.getProperty("db.url"),
                DB_PROPERTIES.getProperty("db.username"),
                DB_PROPERTIES.getProperty("db.password"));
    }

    public void close(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                throw new IllegalStateException("Không đóng được tài nguyên JDBC", e);
            }
        }
    }
}
