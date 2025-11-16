package com.anime.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EpisodeReviewDAO {

    private Connection conn;

    public EpisodeReviewDAO(Connection conn) {
        this.conn = conn;
    }

    public List<EpisodeReview> getReviewsByEpisodeId(int episodeId) throws SQLException {
        List<EpisodeReview> reviews = new ArrayList<>();
        String sql = "SELECT review_id, username, episode_id, comment FROM episodeReviews WHERE episode_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, episodeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    EpisodeReview review = new EpisodeReview(
                        rs.getInt("review_id"),
                        rs.getString("username"),
                        rs.getInt("episode_id"),
                        rs.getString("comment")
                    );
                    reviews.add(review);
                }
            }
        }
        return reviews;
    }

    public List<EpisodeReview> getAllReviews() throws SQLException {
        List<EpisodeReview> reviews = new ArrayList<>();
        String sql = "SELECT review_id, username, episode_id, comment FROM episodeReviews";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                EpisodeReview review = new EpisodeReview(
                    rs.getInt("review_id"),
                    rs.getString("username"),
                    rs.getInt("episode_id"),
                    rs.getString("comment")
                );
                reviews.add(review);
            }
        }
        return reviews;
    }

    public void addReview(EpisodeReview review) throws SQLException {
        String sql = "INSERT INTO episode_reviews (username, episode_id, comment) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, review.getUsername());
            ps.setInt(2, review.getEpisodeId());
            ps.setString(3, review.getComment());
            ps.executeUpdate();
        }
    }

    public void deleteReview(int reviewId) throws SQLException {
        String sql = "DELETE FROM episode_reviews WHERE review_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reviewId);
            ps.executeUpdate();
        }
    }

    public void updateReview(EpisodeReview review) throws SQLException {
        String sql = "UPDATE episode_reviews SET username = ?, episode_id = ?, comment = ? WHERE review_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, review.getUsername());
            ps.setInt(2, review.getEpisodeId());
            ps.setString(3, review.getComment());
            ps.setInt(4, review.getReviewId());
            ps.executeUpdate();
        }
    }
}