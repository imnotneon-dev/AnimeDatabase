package com.anime.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/anime_db";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // to be updated

    public static Connection getConnection() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("failed to load jdbc driver.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
