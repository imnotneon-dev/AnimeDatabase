package com.anime.model.dao;

import java.sql.*;

public class AccountDAO {

    // for login validation
    public boolean validateLogin(String username, String password) {
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
    }
}
