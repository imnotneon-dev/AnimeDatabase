package com.anime.model.dao;

import com.anime.model.Episode;
import com.anime.model.EpisodeReview;

import java.sql.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class EpisodeDAO {

//    private final Connection conn;
//
//    public EpisodeDAO(Connection conn) {
//        this.conn = conn;
//    }

    public boolean addEpisode(String epTitle, LocalDate release, String synopsis, int runtime, int seriesId) throws SQLException {
        String sql = "INSERT INTO episodes (title, release_date, synopsis, runtime, seriesId) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, epTitle);
            ps.setDate(2, Date.valueOf(release));
            ps.setString(3, synopsis);
            ps.setInt(4, runtime);
            ps.setInt(5, seriesId);
//            ps.executeUpdate();
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateEpisode (int eid, String epTitle, LocalDate release, String synopsis,int runtime){
        String sql = "UPDATE episodes SET title =?, release_date=?, synopsis=?, runtime=? WHERE episode_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, epTitle);
            ps.setDate(2, Date.valueOf(release));
            ps.setString(3, synopsis);
            ps.setInt(4, runtime);
            ps.setInt(5, eid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Episode selectEpisodeById (int episodeId) throws SQLException {
        String sql = " SELECT episode_id, title, release_date, synopsis, no_of_views, runtime, series_id FROM Episodes WHERE episode_id = ? ";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, episodeId);
            try (ResultSet rs = ps.executeQuery()) {
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
                            rs.getInt("no_of_views"),
                            releaseDate,
                            rs.getInt("series_id"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public List<Episode> selectEpisodeBySeries (int series_id) throws SQLException {
        String sql = " SELECT episode_id, title, release_date, synopsis, no_of_views, runtime, series_id FROM Episodes WHERE series_id = ? ";
        List<Episode> eplist = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, series_id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    Date sqlDate = rs.getDate("release_date");
                    LocalDate releaseDate = null;
                    if (sqlDate != null) {
                        releaseDate = sqlDate.toLocalDate();
                    }
                    eplist.add (new Episode(
                            rs.getInt("episode_id"),
                            rs.getString("title"),
                            rs.getString("synopsis"),
                            rs.getInt("runtime"),
                            rs.getInt("no_of_views"),
                            releaseDate,
                            rs.getInt("series_id")));
                }
                return eplist;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public List<Episode> selectAllEpisodes () throws SQLException {
        List<Episode> catalog = new ArrayList<>();

        String sql = " SELECT episode_id, title, release_date, synopsis, views, runtime, series_id FROM episodes ";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, episodeId);
            try (ResultSet rs = ps.executeQuery()) {
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

    public void addEpToWatchHistoryByUser (String username,int episode_id, LocalDate wd){
        String sql = "INSERT INTO watch_history (username, episode_id, watch_date) VALUES (?, ?, ?)";


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setInt(2, episode_id);
            ps.setDate(3, Date.valueOf(wd));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<EpisodeReview> getReviewByEpisodeId (String episodeId) throws SQLException {
        List<EpisodeReview> reviews = new ArrayList<>();

        String sql = "SELECT r.user_review, u.user_id, r.episode_id, r.series_id, r.date_reviewed" +
                "FROM reviews r " +
                "LEFT JOIN account u ON r.account_id = u.account_id " +
                "WHERE r.episode_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, episodeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    EpisodeReview data = new EpisodeReview(
                            rs.getInt("review_id"),
                            rs.getInt("user_id"),
                            rs.getInt("episode_id"),
                            rs.getString("user_review"),
                            rs.getDate("date_reviewed").toLocalDate()
                    );
                    reviews.add(data);
                }
                return reviews;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    // Get ep titles for JComboBox in the EpisodeReviewPanel.java GUI
    public List<String> getAllEpisodeTitles () throws SQLException {
        List<String> titles = new ArrayList<>();
        String sql = "SELECT title FROM Episodes ORDER BY title ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                titles.add(rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return titles;
    }

    // Convert ep title to episode_id
    public int getEpisodeIdByTitle (String title) throws SQLException {
        String sql = "SELECT episode_id FROM Episodes WHERE title = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt("episode_id");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return -1; // not found
    }

}


