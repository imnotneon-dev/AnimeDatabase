package com.anime.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String SERVER_URL = "jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC";
    private static final String URL = "jdbc:mysql://localhost:3306/db_anime1?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "p@ssword"; // to be updated

    public static void loadDriver(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("failed to load jdbc driver.", e);
        }
    }
    // Connection to server (no default database) — use for running CREATE DATABASE / USE statements
    public static Connection getServerConnection() throws SQLException {
        loadDriver();
        return DriverManager.getConnection(SERVER_URL, USER, PASSWORD);
    }

    // Connection to the application database db_anime1 — use for normal DAO queries
    public static Connection getConnection() throws SQLException {
        loadDriver();
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
