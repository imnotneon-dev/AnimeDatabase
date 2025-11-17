package com.anime.model.dao;

import com.anime.model.FavoriteSeries;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FavoriteSeriesDAO {
    private Connection conn;

    public FavoriteSeriesDAO(Connection conn) {
        this.conn = conn;
    }

    public int countFavorites(int username) {
        String sql = "SELECT COUNT(*) AS total FROM FavoriteSeries WHERE username = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, username);
            ResultSet resSet = ps.executeQuery();
            
            if (resSet.next()) {
                return resSet.getInt("total");
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        
        }
        return 0;
    }

    public boolean addFavoriteSeries(int username, int series_id) {
        
        int currentCount = countFavorites(username);
        if (currentCount >= 100) {
            System.out.println("Cannot add more favorite series. 100 favorite series limit reached.");
            return false; 
        }

        String sql = "INSERT INTO FavoriteSeries (username, series_id, added_date) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, username);
            ps.setInt(2, series_id);
            ps.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            
            int rowsAffected = ps.executeUpdate();
            
            return rowsAffected > 0;
        
        } catch (SQLException e) {
            e.printStackTrace();
            
            return false;
        }
    }

    public boolean removeFavoriteSeries(int username, int series_id) {
        String sql = "DELETE FROM FavoriteSeries WHERE username = ? AND series_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, username);
            ps.setInt(2, series_id);
            
            int rowsAffected = ps.executeUpdate();
            
            return rowsAffected > 0;
        
        } catch (SQLException e) {
            e.printStackTrace();
            
            return false;
        }
    }


    public List<FavoriteSeries> getFavorites(int username) {
        List<FavoriteSeries> favorites = new ArrayList<>();
        String sql = "SELECT * FROM FavoriteSeries WHERE username = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, username);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                favorites.add(new FavoriteSeries(
                    rs.getInt("username"),
                    rs.getInt("series_id"),
                    rs.getDate("added_date").toLocalDate()
                ));
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
    }
        return favorites;
    }

    public List<String> getAllSeriesTitles(){
        List<String> titles = new ArrayList<>();
        String sql = "SELECT title FROM Series";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ResultSet resSet = ps.executeQuery();
            
            while (resSet.next()) {
                titles.add(resSet.getString("title"));
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return titles;

    }


    public void counterForTopGenre(int username, List<String> genres, List<Integer> counts) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT s.genre, COUNT(s.genre) AS genre_count ");
        sql.append("FROM FavoriteSeries fs ");
        sql.append("JOIN Series s ON fs.series_id = s.id ");
        sql.append("WHERE fs.username = ? ");
        sql.append("GROUP BY s.genre ");
        
        try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            
            ps.setInt(1, username);
            ResultSet resSet = ps.executeQuery();
            
            while (resSet.next()) {
                genres.add(resSet.getString("genre"));
                counts.add(resSet.getInt("genre_count"));
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateTopGenre(int username){
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
            
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}    
