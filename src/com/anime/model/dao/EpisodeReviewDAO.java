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

    public boolean addReview(int user_id, int episode_id, String user_review) {
        String sql = "INSERT INTO EpisodeReview (user_id, episode_id, user_review) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn == null) {
                System.err.println("Connection failed: addReview()");
                return false;
            }

            ps.setInt(1, user_id);
            ps.setInt(2, episode_id);
            ps.setString(3, user_review);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error adding review: " + e.getMessage());
            return false;
        }
    }
    public boolean deleteReview(int review_id) {
        String sql = "DELETE FROM EpisodeReview WHERE review_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn == null) {
                System.err.println("Connection failed: deleteReview()");
                return false;
            }

            ps.setInt(1, review_id);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error deleting review: " + e.getMessage());
            return false;
        }
    }

    public boolean updateReview(int review_id, String new_review) {
        String sql = "UPDATE EpisodeReview SET user_review = ? WHERE review_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn == null) {
                System.err.println("Connection failed: updateReview()");
                return false;
            }

            ps.setString(1, new_review);
            ps.setInt(2, review_id);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error updating review: " + e.getMessage());
            return false;
        }
    }

    public List<EpisodeReview> getAllReviews() {
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

            if (conn == null) {
                System.err.println("Connection failed: getAllReviews()");
                return list;
            }

            while (rs.next()) {
                EpisodeReview review = new EpisodeReview(
                    rs.getInt("review_id"),
                    rs.getString("username"),
                    rs.getInt("series_id"),
                    rs.getString("comment")
                );
                list.add(review);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching reviews: " + e.getMessage());
        }

        return list;
    }

    public List<EpisodeReview> getReviewsByEpisodeId(int episode_id) {
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

            if (conn == null) {
                System.err.println("Connection failed: getReviewsByEpisodeId()");
                return list;
            }

            ps.setInt(1, episode_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EpisodeReview review = new EpisodeReview(
                    rs.getInt("review_id"),
                    rs.getString("username"),
                    rs.getInt("series_id"),
                    rs.getString("comment")
                );
                list.add(review);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching reviews by episode: " + e.getMessage());
        }

        return list;
    }
}
