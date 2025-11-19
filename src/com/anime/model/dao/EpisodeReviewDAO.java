package com.anime.model.dao;

import com.anime.model.EpisodeReview;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EpisodeReviewDAO {

//    private final Connection conn;
//
//    public EpisodeReviewDAO(Connection conn) {
//        this.conn = conn;
//    }

    public void addReview(int user_id, int episodeId, String user_review) throws SQLException {
        String sql = "INSERT INTO episodeReviews (user_id, episode_id, user_review) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, user_id);
            ps.setInt(2, episodeId);
            ps.setString(3, user_review);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteReview(int reviewId) throws SQLException {
        String sql = "DELETE FROM episodeReviews WHERE review_id = ?";
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
        String sql = "UPDATE episodeReviews SET user_review = ? WHERE review_id = ?";
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
                r.review_id, r.user_id, r.episode_id, r.user_review, r.date_reviewed
            FROM episodeReviews r
            ORDER BY r.date_reviewed DESC
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EpisodeReview review = new EpisodeReview(
                    rs.getInt("review_id"),
                    rs.getInt("user_id"),
                    rs.getInt("episode_id"),
                    rs.getString("user_review"),
                    rs.getDate("date_reviewed").toLocalDate()
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
                r.review_id, r.user_id, r.episode_id, r.user_review, r.date_reviewed
            FROM episodeReviews r
            JOIN users u ON r.user_id = u.user_id
            WHERE r.episode_id = ?
                AND u.status = 'Active'
            ORDER BY r.date_reviewed DESC
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, episodeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    EpisodeReview review = new EpisodeReview(
                        rs.getInt("review_id"),
                        rs.getInt("user_id"),
                        rs.getInt("episode_id"),
                        rs.getString("user_review"),
                        rs.getDate("date_reviewed").toLocalDate()
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
