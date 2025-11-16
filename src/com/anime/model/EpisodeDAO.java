package com.anime.model;

import java.sql.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class EpisodeDAO {

    private final Connection conn;

    public EpisodeDAO(Connection conn) {
        this.conn = conn;
    }

    public Episode selectEpisodeById(String episodeId) throws SQLException {
        String sql = " SELECT episodeId, title, releaseDate, sypnosis, views, runtime FROM Episodes WHERE episodeId = ? ";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, episodeId);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {

                    Date sqlDate = rs.getDate("releaseDate");
                    LocalDate releaseDate = null;
                    if (sqlDate != null) {
                        releaseDate = sqlDate.toLocalDate();
                    }
                    return new Episode(
                            rs.getInt("episodeId"),
                            rs.getString("title"),
                            rs.getString("sypnosis"),
                            rs.getInt("views"),
                            rs.getInt("runtime"),
                            releaseDate;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
      return null;
    }

    public List<Review> getReviewByEpisodeId(String episodeId) throws SQLException{
        List<Review> reviews = new ArrayList<>();

        String sql = "SELECT r.user_review, u.name, r.episode_id, r.series_id " +
                "FROM reviews r " +
                "LEFT JOIN account u ON r.account_id = u.account_id " +
                "WHERE r.episode_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, episodeId);
            try (ResultSet rs = ps.executeQuery()){
                while(rs.next()) {
                    Review data = new Review(
                            rs.getString("name"),
                            rs.getInt("series_id"),
                            rs.getInt("episode_id"),
                            rs.getString("user_review")
                    );
                    reviews.add(data);
                }
                return reviews;
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}