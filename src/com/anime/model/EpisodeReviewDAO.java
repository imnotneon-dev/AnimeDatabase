package com.anime.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class EpisodeReviewDAO {

    private Connection conn;

    public EpisodeReviewDAO(Connection conn) {
        this.conn = conn;
    }

    // Add new review
    public void addReview(int user_id, int episode_id, String user_review) throws SQLException {
        String sql = "INSERT INTO EpisodeReview (user_id, episode_id, user_review) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, user_id);
        ps.setInt(2, episode_id);
        ps.setString(3, user_review);

        ps.executeUpdate();
    }

    // Delete review
    public void deleteReview(int review_id) throws SQLException {
        String sql = "DELETE FROM EpisodeReview WHERE review_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, review_id);
        ps.executeUpdate();
    }

    // Update review
    public void updateReview(int review_id, String new_review) throws SQLException {
        String sql = "UPDATE EpisodeReview SET user_review = ? WHERE review_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, new_review);
        ps.setInt(2, review_id);
        ps.executeUpdate();
    }

    // Report: Get all reviews

    public List<EpisodeReview> getAllReviews() throws SQLException {
        List<EpisodeReview> list = new ArrayList<>();

        String sql = """
            SELECT 
                r.review_id, r.user_id, r.episode_id, r.user_review, r.date_reviewed,
                u.username,
                e.title AS episode_title
            FROM EpisodeReview r
            JOIN Users u ON r.user_id = u.user_id
            JOIN Episodes e ON r.episode_id = e.episode_id
            ORDER BY r.date_reviewed DESC
        """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            EpisodeReview review = new EpisodeReview(
                rs.getInt("review_id"),
                rs.getInt("user_id"),
                rs.getInt("episode_id"),
                rs.getString("user_review"),
                rs.getDate("date_reviewed").toLocalDate(),
                rs.getString("username"),
                rs.getString("episode_title")
            );
            list.add(review);
        }

        return list;
    }

    // Report: Get reviews per ep

    public List<EpisodeReview> getReviewsByEpisodeId(int episode_id) throws SQLException {
        List<EpisodeReview> list = new ArrayList<>();

        String sql = """
            SELECT 
                r.review_id, r.user_id, r.episode_id, r.user_review, r.date_reviewed,
                u.username,
                e.title AS episode_title
            FROM EpisodeReview r
            JOIN Users u ON r.user_id = u.user_id
            JOIN Episodes e ON r.episode_id = e.episode_id
            WHERE r.episode_id = ?
            ORDER BY r.date_reviewed DESC
        """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, episode_id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            EpisodeReview review = new EpisodeReview(
                rs.getInt("review_id"),
                rs.getInt("user_id"),
                rs.getInt("episode_id"),
                rs.getString("user_review"),
                rs.getDate("date_reviewed").toLocalDate(),
                rs.getString("username"),
                rs.getString("episode_title")
            );
            list.add(review);
        }

        return list;
        
    }
}
