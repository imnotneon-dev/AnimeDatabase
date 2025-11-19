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
    public void addUser(String username, String password, String dob, String country) throws SQLException {
        String sql = """
            INSERT INTO Users (username, password, date_of_birth, country)
            VALUES (?, ?, ?, ?)
        """;
        
        try(Connection conn = DBConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setDate(3, Date.valueOf(dob));
            ps.setString(4, country);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Login
    public boolean login(String username, String password) throws SQLException {
        String sql = """
            SELECT user_id
            FROM Users
            WHERE username = ? AND password = ? AND status = 'Active'
        """;

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
                
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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


    // View user deets
    public void viewUser(String username) throws SQLException {
        String sql = """
            SELECT username, date_of_birth, country, top_genre, date_user_created
            FROM Users
            WHERE username = ?
        """;

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {

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
        } catch (SQLException e) {
            e.printStackTrace();
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

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            
            try (ResultSet rs = ps.executeQuery()) {

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Archive account
    public boolean archiveUser(int userId) {
        String sql = """
            UPDATE Users
            SET status = 'Archived'
            WHERE user_id = ?
        """;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, userId);
                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
    }
}
