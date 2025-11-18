package com.anime.model.dao;

import com.anime.model.Series;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeriesDAO {

    private final Connection conn;

    public SeriesDAO(Connection conn) {
        this.conn = conn;
    }

    public Series getSeriesById(int seriesId) throws SQLException {
        String sql = "SELECT * FROM series WHERE series_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, seriesId);
            try (ResultSet resSet = ps.executeQuery()) {
                if (resSet.next()) {
                    return new Series(
                        resSet.getInt("series_id"),
                        resSet.getString("title"),
                        resSet.getString("genre"),
                        resSet.getInt("release_year"),
                        resSet.getInt("total_episodes"),
                        resSet.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;            
        }

        return null;
    }

    public Series getSeriesByTitle(String title) throws SQLException {
        String sql = "SELECT * FROM series WHERE title = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            try (ResultSet resSet = ps.executeQuery()) {
                if (resSet.next()) {
                    return new Series(
                        resSet.getInt("series_id"),
                        resSet.getString("title"),
                        resSet.getString("genre"),
                        resSet.getInt("release_year"),
                        resSet.getInt("total_episodes"),
                        resSet.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return null;
    }

    public List<Series> getAllSeries() throws SQLException {
        List<Series> list = new ArrayList<>();
        String sql = "SELECT * FROM series";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet resSet = ps.executeQuery()) {

            while (resSet.next()) {
                list.add(new Series(
                    resSet.getInt("series_id"),
                    resSet.getString("title"),
                    resSet.getString("genre"),
                    resSet.getInt("release_year"),
                    resSet.getInt("total_episodes"),
                    resSet.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return list;
    }

    public void addSeries(Series s) throws SQLException {
        String sql = "INSERT INTO series (title, genre, release_year, total_episodes, status) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getTitle());
            ps.setString(2, s.getGenre());
            ps.setInt(3, s.getReleaseYear());
            ps.setInt(4, s.getTotalEpisodes());
            ps.setString(5, s.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateSeries(Series s) throws SQLException {
        String sql = "UPDATE series SET title = ?, genre = ?, release_year = ?, total_episodes = ?, status = ? WHERE series_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getTitle());
            ps.setString(2, s.getGenre());
            ps.setInt(3, s.getReleaseYear());
            ps.setInt(4, s.getTotalEpisodes());
            ps.setString(5, s.getStatus());
            ps.setInt(6, s.getSeriesId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void archiveSeries(int seriesId) throws SQLException {
        String sql = "UPDATE series SET status = 'Archived' WHERE series_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, seriesId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void unarchiveSeries(int seriesId) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Unarchiving this series? Select its current status: 1 - Complete, 2 - On-going");
        int choice = sc.nextInt();

        String statusNew = (choice == 1) ? "Complete" : "On-Going";

        String sql = "UPDATE series SET status = ? WHERE series_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, statusNew);
            ps.setInt(2, seriesId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }
}
