package com.anime.model.dao;

import com.anime.model.Series;
import com.anime.model.TopGenreOfTheWeek;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TopGenreOfTheWeekDAO {
//    private Connection connection;
//
//    public TopGenreOfTheWeekDAO(Connection connection) {
//        this.connection = connection;
//    }

    public List<TopGenreOfTheWeek> getTop5GenreSeriesOfTheWeek(LocalDate startDate, LocalDate endDate) throws SQLException {
        List<TopGenreOfTheWeek> topGenreOfTheWeek= new ArrayList<>();

        String totalUsersQuery = "SELECT COUNT(DISTINCT user_id) AS total_users " +
                                    "FROM WatchHistory " +
                                    "WHERE watch_date >= ? AND watch_date <= ?";

        String topGenreQuery = """
                SELECT s.genre, SUM(e.no_of_views) as totalViews
                FROM watchHistory h
                LEFT JOIN episodes e ON h.episode_id = e.episode_id
                LEFT JOIN series s ON e.series_id = s.series_id
                WHERE h.watch_date >= ? AND h.watch_date<= ?
                GROUP BY s.genre
                ORDER BY totalViews DESC
                LIMIT 5;
                """;


        int totalUsers = 0;
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement totalStmt = conn.prepareStatement(totalUsersQuery)) {
            totalStmt.setDate(1, Date.valueOf(startDate));
            totalStmt.setDate(2, Date.valueOf(endDate));
            ResultSet rs = totalStmt.executeQuery();
            if (rs.next()) {
                totalUsers = rs.getInt("total_users");
            }
        }

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement topStmt = conn.prepareStatement(topGenreQuery.toString())) {
            topStmt.setDate(1, Date.valueOf(startDate));
            topStmt.setDate(2, Date.valueOf(endDate));
            ResultSet rs = topStmt.executeQuery();
            while (rs.next()) {
                String genre = rs.getString("genre");
                int viewers = rs.getInt("totalViews");
                double percentage = totalUsers > 0 ? (viewers * 100.0 / totalUsers) : 0.0;
                topGenreOfTheWeek.add(new TopGenreOfTheWeek(genre, viewers, percentage));
            }
        }

        return topGenreOfTheWeek;
    }

    public List<Series> getSeriesByGenre(String genre){
        List<Series> series = new ArrayList<>();

        String q = "SELECT * " +
                "FROM Series " +
                "WHERE genre = ? " +
                "LIMIT 5";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(q)) {

            ps.setString(1, genre);
            ResultSet resSet = ps.executeQuery();

            if (resSet.next()) {
                series.add(new Series(
                        resSet.getInt("series_id"),
                        resSet.getString("title"),
                        resSet.getString("genre"),
                        resSet.getInt("release_year"),
                        resSet.getInt("total_episodes"),
                        resSet.getString("status"),
                        resSet.getString("series_photo")
                        )
                );
            }
            return series;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
