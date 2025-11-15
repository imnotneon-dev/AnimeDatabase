package com.anime.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.anime.model.Series;

public class SeriesDAO{

    private final Connection conn;

    public Series getSeriesById(int series_id) {
        String sql = "SELECT * FROM series_t WHERE series_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, series_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Series(
                    rs.getInt("series_id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getInt("release_year"),
                    rs.getInt("total_episode_count"),
                    rs.getString("status_of_series")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    
}



