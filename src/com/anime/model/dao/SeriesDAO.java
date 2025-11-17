package com.anime.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.anime.model.Series;

public class SeriesDAO{

    private final Connection conn;

    public Series getSeriesById(int series_id) {
        String sql = "SELECT * FROM series WHERE series_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, series_id);
            ResultSet resSet = ps.executeQuery();

            if (resSet.next()) {
                return new Series(
                    resSet.getInt("series_id");
                    resSet.getString("title");
                    resSet.getString("genre");
                    resSet.getInt("release_year");
                    resSet.getInt("total_episodes");
                    resSet.getInt("status");
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    
    public Series getSeriesByTitle(String title){
        String sql = "SELECT * FROM series WHERE title = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatements ps = conn.prepareStatement(sql)){

                ps.setString(1, title);
                ResultSet resSet = ps.executeQuery();

                if(resSet.next()){
                    return new Series(
                        resSet.getInt("series_id");
                        resSet.getString("title");
                        resSet.getString("genre");
                        resSet.getInt("release_year");
                        resSet.getInt("total_episodes");
                        resSet.getInt("status");
                    );
                }
                        
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return null;
    }



    
    public List<Series> getAllSeries(){
        List<Series> list = new ArrayList<>();

        String sql = "SELECT * FROM series";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resSet = ps.executeQuery()) {

            while (resSet.next()){
                Series s = new Series(
                    resSet.getInt("series_id");
                        resSet.getString("title");
                        resSet.getString("genre");
                        resSet.getInt("release_year");
                        resSet.getInt("total_episodes");
                        resSet.getInt("status");
                );

                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace():
        }

        return list;
    }


    public boolean addSeries(Series series){
        /* boolean so that we know if na insert na true or false*/
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO series");
        sql.append("(title, genre, release_year, total_episodes, status)");
        sql.append("VALUES (?, ?, ?, ?, ?)");

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getTitle());
            ps.setString(2, s.getGenre());
            ps.setInt(3, s.getReleaseYear());
            ps.setInt(4, s.getTotalEpisodes());
            ps.setString(5, s.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /*made it separate cause technically updating and archive a lot better to separate instead of
    putting them together. Makes it cleaner too*/
    public boolean updateSeries(Series series){
       StringBuilder sql = new StringBuilder();
        sql.append("UPDATE series");
        sql.append("SET title = ?, genre = ?, release_year = ?, total_episodes = ?, status = ?");
        sql.append("WHERE series_id = ?");

        try(Connection conn = DBConnection.getConnection();
           PreparedStatement ps = conn.prepareStatement(sql.toString())){

            ps.setString(1, s.getTitle());
            ps.setString(2, s.getGenre());
            ps.setInt(3, s.getReleaseYear());
            ps.setInt(4, s.getTotalEpisodes());
            ps.setString(5, s.getStatus());
            ps.setInt(6, s.getSeriesId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean archiveSeries(int series_id){
        String sql = "UPDATE series SET status = 'Archived' WHERE series_id = ?";

        try(Connection conn = DB.Connection.getConnection();
            PreparedStatements ps = conn.prepareStatement(sql)){

            ps.setInt(1, series_id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean unarchiveSeries(int series_id){
        Scanner sc = new Scanner(System.in);

        System.out.println("Unarchiving this series? Select it's current status: 1 - Complete, 2 - On-going");
        int choice = sc.nextInt();

        String statusNew;

        if(choice == 1) {
            statusNew = "Complete";
        } else {
            statusNew = "On-Going":
        }

        String sql = "UPDATE series SET status = ? WHERE series_id = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            ps.setString(1, statusNew);
            ps.setInt(2, series_id);
            
            return ps.executeUpdate() > 0;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return false;
        
    }
}


