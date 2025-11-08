package com.anime.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EpisodeReview {
    private Connection conn;

    public EpisodeReview(Connection conn) {
        this.conn = conn;
    }

    // Generate the Episode Review Log report
    public void generateEpisodeReviewLog(int episodeId, String month) throws SQLException { // user_review and date_reviewed will come from data for episodes so variable name would probs change depending on the review sql file
        String sql = """
            SELECT u.username, r.user_review, r.date_reviewed
            FROM Review r
            JOIN Users u ON r.user_id = u.user_id
            WHERE r.episode_id = ? AND DATE_FORMAT(r.date_reviewed, '%Y-%m') = ?
            ORDER BY r.date_reviewed DESC;
        """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, episodeId);   // which episode to generate the report for
        ps.setString(2, month);    // month format would be "YYYY-MM"
        ResultSet rs = ps.executeQuery();

        System.out.println("Episode Review Log");
        System.out.println("---------------------------");
        System.out.println("Episode: " + episodeId + " | Month: " + month);
        System.out.println();

        boolean hasReviews = false;
        while (rs.next()) {
            hasReviews = true;
            String username = rs.getString("username");
            String review = rs.getString("user_review");
            String date = rs.getString("date_reviewed");

            System.out.printf("[%s] %s: %s%n", date, username, review);
        }

        if (!hasReviews) {
            System.out.println("No reviews found for this episode this month.");
        }

        System.out.println("---------------------------");
    }
}
