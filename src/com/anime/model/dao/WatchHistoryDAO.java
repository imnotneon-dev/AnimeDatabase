package com.anime.model.dao;

import com.anime.model.WatchHistory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WatchHistoryDAO {
//    private Connection conn;
//
//    public WatchHistoryDAO(Connection conn) {
//        this.conn = conn;
//    }

    public List<WatchHistory> getWatchedListByUser(int user_id) throws SQLException{
        List<WatchHistory> watched = new ArrayList<>();
        String sql = " SELECT DISTINCT watch_id, user_id, episode_id, watch_date " +
                "FROM watchHistory w " +
                "WHERE user_id = ?; ";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                watched.add(new WatchHistory(
                        rs.getInt("watch_id"),
                        rs.getInt("user_id"),
                        rs.getInt("episode_id"),
                        rs.getDate("watch_date").toLocalDate())
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return watched;
    }

    public boolean addWatchHistoryByUser(int user_id, int episode_id, LocalDate watch_date) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO watchHistory ");
        sql.append("(user_id, episode_id, watch_date) ");
        sql.append("VALUES (?, ?, ?); ");

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString());){

            ps.setInt(1, user_id);
            ps.setInt(2, episode_id);
            ps.setDate(3, Date.valueOf(watch_date));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int countViewsByEpisode(int episode_id) throws SQLException {
        int totalViews = 0;

        // SQL selects the count from watchHistory where the episode_id matches.
        String sql = "SELECT COUNT(watch_id) AS view_count " +
                "FROM watchHistory " +
                "WHERE episode_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, episode_id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Retrieve the count result from the aliased column
                    totalViews = rs.getInt("view_count");
                }
            }

        } catch (SQLException e) {
            System.err.println("DB Error counting episode views: " + e.getMessage());
            throw e; // Re-throw the exception
        }

        return totalViews;
    }

}
