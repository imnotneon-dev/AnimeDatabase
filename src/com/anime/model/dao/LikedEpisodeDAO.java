package com.anime.model.dao;

import com.anime.model.LikedEpisode;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LikedEpisodeDAO {

//    private Connection conn;
//
//    public LikedEpisodeDAO(Connection conn) {
//        this.conn = conn;
//    }

    // Like an ep (can only like an ep once)
    public boolean addLike(int userId, int episodeId) throws SQLException {
        // prevent duplicates
        if (isLiked(userId, episodeId)) {
            return false; 
        }

        String sql = "INSERT INTO LikedEpisode (user_id, episode_id) VALUES (?, ?)";

        try(Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, episodeId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Unlike an ep
    public boolean removeLike(int userId, int episodeId) throws SQLException {
        String sql = "DELETE FROM LikedEpisode WHERE user_id = ? AND episode_id = ?";

        try(Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, episodeId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Check if user already liked that ep
    public boolean isLiked(int userId, int episodeId) throws SQLException {
        String sql = "SELECT * FROM LikedEpisode WHERE user_id = ? AND episode_id = ?";

        try(Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, episodeId);
            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all user's likes
    public List<LikedEpisode> getLikesByUser(int userId) throws SQLException {
        List<LikedEpisode> list = new ArrayList<>();

        String sql = "SELECT * FROM LikedEpisode WHERE user_id = ? ORDER BY date_added DESC";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new LikedEpisode(
                        rs.getInt("user_id"),
                        rs.getInt("episode_id"),
                        rs.getDate("date_added").toLocalDate()
                ));
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    // Get all likes per ep
    public List<LikedEpisode> getLikesByEpisode(int episodeId) throws SQLException {
        List<LikedEpisode> list = new ArrayList<>();

        String sql = "SELECT * FROM LikedEpisode WHERE episode_id = ? ORDER BY date_added DESC";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, episodeId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    list.add(new LikedEpisode(
                            rs.getInt("user_id"),
                            rs.getInt("episode_id"),
                            rs.getDate("date_added").toLocalDate()
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Update when an ep was liked
    public boolean updateLikeDate(int userId, int episodeId) throws SQLException {
        String sql = "UPDATE LikedEpisode SET date_added = CURRENT_DATE WHERE user_id = ? AND episode_id = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, episodeId);

            return ps.executeUpdate() > 0;
        
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
