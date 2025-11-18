package com.anime.model.dao;

import com.anime.model.FavoriteSeries;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FavoriteSeriesDAO {
    private final Connection conn;

    public FavoriteSeriesDAO(Connection conn) {
        this.conn = conn;
    }

    public int countFavorites(int username) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM FavoriteSeries WHERE username = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return 0;
    }

    public void addFavoriteSeries(int username, int seriesId) throws SQLException {
        try {
            int currentCount = countFavorites(username);
            if (currentCount >= 100) {
                throw new SQLException("Cannot add more favorite series. 100 favorite series limit reached.");
            }

            String sql = "INSERT INTO FavoriteSeries (username, series_id, added_date) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, username);
                ps.setInt(2, seriesId);
                ps.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void removeFavoriteSeries(int username, int seriesId) throws SQLException {
        String sql = "DELETE FROM FavoriteSeries WHERE username = ? AND series_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, username);
            ps.setInt(2, seriesId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<FavoriteSeries> getFavorites(int username) throws SQLException {
        List<FavoriteSeries> favorites = new ArrayList<>();
        String sql = "SELECT * FROM FavoriteSeries WHERE username = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    favorites.add(new FavoriteSeries(
                        rs.getInt("favorite_id"),
                        rs.getString("username"),
                        rs.getInt("series_id"),
                        rs.getDate("added_date").toLocalDate()
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return favorites;
    }

    public List<String> getAllSeriesTitles() throws SQLException {
        List<String> titles = new ArrayList<>();
        String sql = "SELECT title FROM Series";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                titles.add(rs.getString("title"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return titles;
    }

    public void counterForTopGenre(int username, List<String> genres, List<Integer> counts) throws SQLException {
        String sql = """
            SELECT s.genre, COUNT(s.genre) AS genre_count
            FROM FavoriteSeries fs
            JOIN Series s ON fs.series_id = s.id
            WHERE fs.username = ?
            GROUP BY s.genre
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    genres.add(rs.getString("genre"));
                    counts.add(rs.getInt("genre_count"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateTopGenre(int username) throws SQLException {
        try {
            List<String> genres = new ArrayList<>();
            List<Integer> counts = new ArrayList<>();
            counterForTopGenre(username, genres, counts);

            String topGenre = null;
            int maxCount = 0;

            for (int i = 0; i < genres.size(); i++) {
                if (counts.get(i) > maxCount) {
                    maxCount = counts.get(i);
                    topGenre = genres.get(i);
                }
            }

            if (topGenre != null) {
                String sql = "UPDATE Users SET top_genre = ? WHERE id = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, topGenre);
                    ps.setInt(2, username);
                    ps.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
