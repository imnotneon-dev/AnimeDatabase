package com.anime.model.dao;

import com.anime.model.TopSeriesOfTheWeek;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TopSeriesOfTheWeekDAO {
    private Connection connection;

    public TopSeriesOfTheWeekDAO(Connection connection) {
        this.connection = connection;
    }

    public List<TopSeriesOfTheWeek> getTopSeriesLastWeek() throws SQLException {
    List<TopSeriesOfTheWeek> topSeriesList = new ArrayList<>();

    LocalDate today = LocalDate.now();
    LocalDate thisMonday = today.with(java.time.DayOfWeek.MONDAY);
    LocalDate lastMonday = thisMonday.minusWeeks(1);

    String totalUsersQuery =
        "SELECT COUNT(DISTINCT userId) AS totalUsers " +
        "FROM WatchHistory " +
        "WHERE watchDate >= ? AND watchDate < ?";

    String topSeriesQuery =
        "SELECT s.title, COUNT(DISTINCT wh.userId) AS viewers " +
        "FROM WatchHistory wh " +
        "JOIN Episode e ON wh.episode_id = e.id " +
        "JOIN Series s ON e.series_id = s.id " +
        "WHERE wh.watchDate >= ? AND wh.watchDate < ? " +
        "GROUP BY s.title " +
        "ORDER BY viewers DESC " +
        "LIMIT 5";


    int totalUsers = 0;
    
    try (PreparedStatement totalStmt = connection.prepareStatement(totalUsersQuery)) {
        totalStmt.setDate(1, Date.valueOf(lastMonday));
        totalStmt.setDate(2, Date.valueOf(thisMonday));
        ResultSet rs = totalStmt.executeQuery();
        if (rs.next()) {
            totalUsers = rs.getInt("totalUsers");
        }
    }

    try (PreparedStatement topStmt = connection.prepareStatement(topSeriesQuery)) {
        topStmt.setDate(1, Date.valueOf(lastMonday));
        topStmt.setDate(2, Date.valueOf(thisMonday));
        ResultSet rs = topStmt.executeQuery();
        while (rs.next()) {
            String title = rs.getString("title");
            int no_of_views = rs.getInt("viewers");
            double percentage = totalUsers > 0 ? (no_of_views * 100.0 / totalUsers) : 0.0;
            topSeriesList.add(new TopSeriesOfTheWeek(title, no_of_views, percentage));
        }
    }

        return topSeriesList;
    }
}
