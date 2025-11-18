package com.anime.model.dao;

import com.anime.model.AnimeWrapped;
import com.anime.model.AnimeWrapped.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimeWrappedDAO {

    private final Connection conn;

    public AnimeWrappedDAO(Connection conn) {
        this.conn = conn;
    }

    public AnimeWrapped getAnimeWrapped(String username, int year) throws SQLException {
        AnimeWrapped wrapped = new AnimeWrapped(username, year);

        wrapped.setTotalEpisodesWatched(getTotalEpisodesWatched(username, year));
        wrapped.setTopGenre(getTopGenre(username, year));
        wrapped.setTop5Series(getTop5Series(username, year));

        List<ActorStats> top3Actors = getTop3VoiceActors(username, year);
        wrapped.setTop3VoiceActors(top3Actors);

        List<ActorRole> allRoles = new ArrayList<>();
        for (ActorStats actor : top3Actors) {
            allRoles.addAll(getActorTop3Roles(username, year, actor.getActorId()));
        }
        wrapped.setActorRoles(allRoles);

        return wrapped;
    }

    public int getTotalEpisodesWatched(String username, int year) throws SQLException {
        String sql = "SELECT COUNT(DISTINCT wh.episode_id) AS total " +
                     "FROM watchHistory wh " +
                     "WHERE wh.username = ? AND YEAR(wh.watch_date) = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setInt(2, year);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("total");
            }
        }
        return 0;
    }

    public String getTopGenre(String username, int year) throws SQLException {
        String sql = "SELECT s.genre, COUNT(DISTINCT wh.episode_id) AS episodes_watched " +
                     "FROM watchHistory wh " +
                     "JOIN seriesEpisodes se ON wh.episode_id = se.episode_id " +
                     "JOIN series s ON se.series_id = s.series_id " +
                     "WHERE wh.username = ? AND YEAR(wh.watch_date) = ? " +
                     "GROUP BY s.genre " +
                     "ORDER BY episodes_watched DESC " +
                     "LIMIT 1";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setInt(2, year);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getString("genre");
            }
        }
        return "N/A";
    }

    public List<SeriesStats> getTop5Series(String username, int year) throws SQLException {
        List<SeriesStats> seriesList = new ArrayList<>();
        String sql = "SELECT s.series_id, s.title, s.genre, s.series_photo, " +
                     "COUNT(DISTINCT wh.episode_id) AS episodes_watched " +
                     "FROM watchHistory wh " +
                     "JOIN seriesEpisodes se ON wh.episode_id = se.episode_id " +
                     "JOIN series s ON se.series_id = s.series_id " +
                     "WHERE wh.username = ? AND YEAR(wh.watch_date) = ? " +
                     "GROUP BY s.series_id, s.title, s.genre, s.series_photo " +
                     "ORDER BY episodes_watched DESC " +
                     "LIMIT 5";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setInt(2, year);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    seriesList.add(new SeriesStats(
                        rs.getInt("series_id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getString("series_photo"),
                        rs.getInt("episodes_watched")
                    ));
                }
            }
        }
        return seriesList;
    }

    public List<ActorStats> getTop3VoiceActors(String username, int year) throws SQLException {
        List<ActorStats> actors = new ArrayList<>();
        String sql = "SELECT a.actors_id, a.first_name, a.last_name, a.agency, " +
                     "COUNT(DISTINCT wh.episode_id) AS episodes_appeared_in " +
                     "FROM watchHistory wh " +
                     "JOIN seriesEpisodes se ON wh.episode_id = se.episode_id " +
                     "JOIN actorSeries acs ON se.series_id = acs.series_id " +
                     "JOIN actors a ON acs.actors_id = a.actors_id " +
                     "WHERE wh.username = ? AND YEAR(wh.watch_date) = ? " +
                     "GROUP BY a.actors_id, a.first_name, a.last_name, a.agency " +
                     "ORDER BY episodes_appeared_in DESC " +
                     "LIMIT 3";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setInt(2, year);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    actors.add(new ActorStats(
                        rs.getInt("actors_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("agency"),
                        rs.getInt("episodes_appeared_in")
                    ));
                }
            }
        }
        return actors;
    }

    public List<ActorRole> getActorTop3Roles(String username, int year, int actorId) throws SQLException {
        List<ActorRole> roles = new ArrayList<>();
        String sql = "SELECT acs.actors_id, s.series_id, s.title AS series_title, " +
                     "acs.character_name, COUNT(DISTINCT wh.episode_id) AS episodes_watched " +
                     "FROM watchHistory wh " +
                     "JOIN seriesEpisodes se ON wh.episode_id = se.episode_id " +
                     "JOIN actorSeries acs ON se.series_id = acs.series_id " +
                     "JOIN series s ON acs.series_id = s.series_id " +
                     "WHERE wh.username = ? AND YEAR(wh.watch_date) = ? AND acs.actors_id = ? " +
                     "GROUP BY acs.actors_id, s.series_id, s.title, acs.character_name " +
                     "ORDER BY episodes_watched DESC " +
                     "LIMIT 3";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setInt(2, year);
            ps.setInt(3, actorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    roles.add(new ActorRole(
                        rs.getInt("actors_id"),
                        rs.getInt("series_id"),
                        rs.getString("series_title"),
                        rs.getString("character_name"),
                        rs.getInt("episodes_watched")
                    ));
                }
            }
        }
        return roles;
    }
}
