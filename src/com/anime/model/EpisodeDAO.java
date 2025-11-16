package com.anime.model;

import java.sql.*;

import java.util.List;
import com.anime.model.Episode;

public class EpisodeDAO {

    private final Connection conn;

    public EpisodeDAO(Connection conn) {
        this.conn = conn;
    }

    public Episode getEpisodeById(String episodeId) throws SQLException {
        String sql = " SELECT episodeId, title, releaseDate, sypnosis, views, runtime FROM Episodes WHERE episodeId = ? ";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, episodeId);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return new Episode(
                            rs.getInt("episodeId"), rs.getString("title"), rs.getDate("releaseDate").toLocalDate(), rs.getString("sypnosis"), rs.getInt("views"), rs.getInt("runtime"));
                }
            }
        }
      return null;
    }

    public List<Review> getReviewbyEpisodeId(String episodeId){
        List<Review> reviews = new ArrayList<>();

        String sql "SELECT r.user_review, u.name FROM reviews r LEFT JOIN account u ON r.accountId= u.accountId WHERE r.episodeId = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, episodeId);
            try (ResultSet rs = ps.executeQuery()){
                while(rs.next()) {
                    Review data = new Review(
                            rs.getInt("accountId"), rs.getInt("seriesId"), rs.getDate("episodeId"), rs.getString("user_review"));
                    review.add(data);
                }
                return reviews;
            }
        }
        return null;
    }

    public void addEpisodeToSchema(Episode e){
        ps.setString(1, e.getTitle());
        ps.setString(2, e.getReleaseDate());
    }

}
}