package com.anime.model;

import java.sql.*;

public class ManageAccount {
    private Connection conn;

    public ManageAccount(Connection conn) {
        this.conn = conn;
    }

    // Create a user
    public void addUser(String username, String dob, String country, String topGenre) throws SQLException {
        String sql = "INSERT INTO Users (username, password, date_of_birth, country, top_genre) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setDate(2, password);
        ps.setDate(3, Date.valueOf(dob));
        ps.setString(4, country);
        ps.setString(5, topGenre);
        ps.executeUpdate();
    }

    // Read a user record and favorites
    public void viewUserWithFavorites(String username) throws SQLException {
        String sql = """
            SELECT u.username, u.country, u.top_genre, s.title
            FROM Users u
            LEFT JOIN FavoriteSeries f ON u.user_id = f.user_id
            LEFT JOIN Series s ON f.series_id = s.series_id
            WHERE u.username = ?;
        """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        System.out.println("Favorites for " + username + ":");
        while (rs.next()) {
            System.out.println("- " + rs.getString("title"));
        }
    }
}
