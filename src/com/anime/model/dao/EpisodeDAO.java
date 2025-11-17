package com.anime.model.dao;

import com.anime.model.Episode;
import com.anime.model.EpisodeReview;

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
    public void addEpisode(String epTitle, LocalDate release, String synopsis, int runtime) throws SQLException {
        String sql = "INSERT INTO episodes (title, release_date, synopsis, runtime) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, epTitle);
        ps.setDate(2, Date.valueOf(release));
        ps.setString(3, synopsis);
        ps.setInt(4, runtime);
        ps.executeUpdate();
    }

    public void updateEpisode(int eid, String epTitle, LocalDate release, String synopsis, int runtime) throws SQLException {
        String sql = "UPDATE episodes SET title =?, release_date=?, synopsis=?, runtime=? WHERE episode_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, epTitle);
        ps.setDate(2, Date.valueOf(release));
        ps.setString(3, synopsis);
        ps.setInt(4, runtime);
        ps.setInt(5, eid);
        ps.executeUpdate();
    }
    public Episode selectEpisodeById(String episodeId) throws SQLException {
        String sql = " SELECT episode_id, title, release_date, synopsis, views, runtime, series_id FROM Episodes WHERE episodeId = ? ";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, episodeId);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {

                    Date sqlDate = rs.getDate("release_date");
                    LocalDate releaseDate = null;
                    if (sqlDate != null) {
                        releaseDate = sqlDate.toLocalDate();
                    }
                    return new Episode(
                            rs.getInt("episode_id"),
                            rs.getString("title"),
                            rs.getString("synopsis"),
                            rs.getInt("runtime"),
                            rs.getInt("views"),
                            releaseDate,
                            rs.getInt("series_id"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
      return null;
    }
    public List<Episode> selectAllEpisodes() throws SQLException {
        List<Episode> catalog = new ArrayList<>();

        String sql = " SELECT episode_id, title, release_date, synopsis, views, runtime, series_id FROM episodes ";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
//            ps.setString(1, episodeId);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()) {

                    Date sqlDate = rs.getDate("release_date");
                    LocalDate releaseDate = null;
                    if (sqlDate != null) {
                        releaseDate = sqlDate.toLocalDate();
                    }
                    Episode ep = new Episode(
                            rs.getInt("episode_id"),
                            rs.getString("title"),
                            rs.getString("synopsis"),
                            rs.getInt("runtime"),
                            rs.getInt("views"),
                            releaseDate,
                            rs.getInt("series_id"));
                    catalog.add(ep);
                }
                return catalog;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void addEpToWatchHistoryByUser(String username, int episode_id, LocalDate wd) throws SQLException {
        String sql = "INSERT INTO watch_history (username, episode_id, watch_date) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setInt(2, episode_id);
        ps.setDate(3, Date.valueOf(wd));
        ps.executeUpdate();
    }

    public List<EpisodeReview> getReviewByEpisodeId(String episodeId) throws SQLException{
        List<EpisodeReview> reviews = new ArrayList<>();

        String sql = "SELECT r.user_review, u.name, r.episode_id, r.series_id " +
                "FROM reviews r " +
                "LEFT JOIN account u ON r.account_id = u.account_id " +
                "WHERE r.episode_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, episodeId);
            try (ResultSet rs = ps.executeQuery()){
                while(rs.next()) {
                    EpisodeReview data = new EpisodeReview(
                            rs.getInt("review_id"),
                            rs.getString("username"),
                            rs.getInt("series_id"),
                            rs.getString("comment")
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