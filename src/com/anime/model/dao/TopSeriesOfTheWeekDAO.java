package dao;

import report.TopSeriesOfTheWeek;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopSeriesOfTheWeekDAO {
    private Connection connection;

    public TopSeriesOfTheWeekDAO(Connection connection) {
        this.connection = connection;
    }

    public List<TopSeriesOfTheWeek> getTopSeries(int weekNumber) throws SQLException {
        List<TopSeriesOfTheWeek> topSeriesList = new ArrayList<>();

        String totalUsersQuery = "SELECT COUNT(DISTINCT user_id) AS total_users FROM WatchHistory WHERE WEEK(watch_date) = ?";
        String topSeriesQuery = """
            SELECT s.title, COUNT(DISTINCT wh.user_id) AS viewers
            FROM WatchHistory wh
            JOIN SeriesEpisodes se ON wh.episode_id = se.episode_id
            JOIN Series s ON se.series_id = s.series_id
            WHERE WEEK(wh.watch_date) = ?
            GROUP BY s.series_id
            ORDER BY viewers DESC
            LIMIT 5
        """;

        int totalUsers = 0;
        try (PreparedStatement totalStmt = connection.prepareStatement(totalUsersQuery)) {
            totalStmt.setInt(1, weekNumber);
            ResultSet rs = totalStmt.executeQuery();
            if (rs.next()) {
                totalUsers = rs.getInt("total_users");
            }
        }

        try (PreparedStatement topStmt = connection.prepareStatement(topSeriesQuery)) {
            topStmt.setInt(1, weekNumber);
            ResultSet rs = topStmt.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                int viewers = rs.getInt("viewers");
                double percentage = totalUsers > 0 ? (viewers * 100.0 / totalUsers) : 0.0;
                topSeriesList.add(new TopSeriesOfTheWeek(title, viewers, percentage));
            }
        }

        return topSeriesList;
    }
}