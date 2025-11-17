package com.anime.model.dao;

import java.sql.*;
import com.anime.model.Account;

public class AccountDAO {
    // for login validation
    /*public boolean validateLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password); 
            ResultSet rs = ps.executeQuery();

            return rs.next(); 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }*/
    // Create a user
    public void addUser(String username, String password, String dob, String country, String topGenre) throws SQLException {
        String sql = "INSERT INTO Users (username, password, date_of_birth, country, top_genre) VALUES (?, ?, ?, ?, ?)";
        try(Connection conn = DBConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setDate(3, Date.valueOf(dob));
            ps.setString(4, country);
            ps.setString(5, topGenre);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Login
    public Account selectAccountByUsername(String username) throws SQLException {
        String sql = "SELECT username, password, date_of_birth, country, top_genre, date_user_created " +
                "FROM Users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getDate("date_of_birth"),
                            rs.getString("country"),
                            rs.getString("top_genre"),
                            rs.getDate("date_user_created")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    // Read a user record and favorites
    /*public void viewUserWithFavorites(String username) throws SQLException {
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
    }*/
}
