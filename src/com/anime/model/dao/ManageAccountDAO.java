package com.anime.model.dao;

import java.sql.*;

public class ManageAccountDAO {
    private Connection conn;

    public ManageAccountDAO(Connection conn) {
        this.conn = conn;
    }

    // Create a user
    public void addUser(String username, String password, String dob, String country, String topGenre) throws SQLException {
        String sql = "INSERT INTO Users (username, password, date_of_birth, country, top_genre) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setDate(3, Date.valueOf(dob));
        ps.setString(4, country);
        ps.setString(5, topGenre);
        ps.executeUpdate();
    }

    // Login
    public boolean login(String username, String password) throws SQLException {
        String sql = "SELECT user_id FROM Users WHERE username = ? AND password = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    // View user deets
    public void viewUser(String username) throws SQLException {
        String sql = "SELECT username, date_of_birth, country, top_genre, date_user_created " + "FROM Users WHERE username = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("Username: " + rs.getString("username"));
            System.out.println("DOB: " + rs.getDate("date_of_birth"));
            System.out.println("Country: " + rs.getString("country"));
            System.out.println("Top Genre: " + rs.getString("top_genre"));
            System.out.println("Date Created: " + rs.getDate("date_user_created"));
        } else {
            System.out.println("User not found.");
        }
    }

    // Read a user record and favorites
    public void viewUserWithFavorites(String username) throws SQLException {
        String sql = """
            SELECT u.username, s.title
            FROM Users u
            LEFT JOIN FavoriteSeries f ON u.user_id = f.user_id
            LEFT JOIN Series s ON f.series_id = s.series_id
            WHERE u.username = ?;
        """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        System.out.println("Favorites for " + username + ":");
        boolean hasFavorites = false;

        while (rs.next()) {
            String title = rs.getString("title");
            if (title != null) {
                hasFavorites = true;
                System.out.println("- " + title);
            }
        }

        if (!hasFavorites) {
            System.out.println("(No favorites yet)");
        }
    }

    // Delete user account (in case)
    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM Users WHERE user_id = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
        System.out.println("Error deleting user: " + e.getMessage());
        return false;
        }
    }
}
