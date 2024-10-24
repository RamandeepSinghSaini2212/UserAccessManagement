package com.example.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection initializeDatabase() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/user_management_db";  // Update this
        String user = "user_management_user";  // Update this
        String password = "yourPassword";  // Update this

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL Driver not found.", e);
        }

        return DriverManager.getConnection(url, user, password);
    }
}
