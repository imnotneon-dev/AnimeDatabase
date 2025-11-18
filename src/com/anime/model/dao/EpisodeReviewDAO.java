package com.anime.model.dao;

import com.anime.model.EpisodeReview;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EpisodeReviewDAO {

    private final Connection conn;

    public EpisodeReviewDAO(Connection conn) {
        this.conn = conn;
    }

    public void addReview(int userId, int episodeId, String userReview) throws SQLException {
        String sql = "INSERT INTO EpisodeReview (user_id, episode_id, user_review) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, episodeId);
            ps.setString(3, userReview);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteReview(int reviewId) throws SQLException {
        String sql = "DELETE FROM EpisodeReview WHERE review_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reviewId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateReview(int reviewId, String newReview) throws SQLException {
        String sql = "UPDATE EpisodeReview SET user_review = ? WHERE review_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newReview);
            ps.setInt(2, reviewId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

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

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EpisodeReview review = new EpisodeReview(
                    rs.getInt("review_id"),
                    rs.getString("username"),
                    rs.getInt("episode_id"),
                    rs.getString("user_review")
                );
                list.add(review);
            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<EpisodeReview> getReviewsByEpisodeId(int episodeId) throws SQLException {
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

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, episodeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    EpisodeReview review = new EpisodeReview(
                        rs.getInt("review_id"),
                        rs.getString("username"),
                        rs.getInt("episode_id"),
                        rs.getString("user_review")
                    );
                    list.add(review);
                }
            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
