package com.anime.model.dao;

import com.anime.model.ActorSeries;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorSeriesDAO {

//    private final Connection conn;
//
//    public ActorSeriesDAO(Connection conn) {
//        this.conn = conn;
//    }

    public List<ActorSeries> getAllActorSeries() throws SQLException {
        List<ActorSeries> actorSeriesList = new ArrayList<>();
        String sql = "SELECT * FROM actorSeries";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ActorSeries as = new ActorSeries(
                        rs.getInt("act_id"),
                        rs.getInt("actors_id"),
                        rs.getInt("series_id"),
                        rs.getString("character_name")
                );
                actorSeriesList.add(as);
            }
            return actorSeriesList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ActorSeries getById(int actId) throws SQLException {
        String sql = "SELECT * FROM actorSeries WHERE act_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, actId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ActorSeries a = new ActorSeries(
                            rs.getInt("act_id"),
                            rs.getInt("actors_id"),
                            rs.getInt("series_id"),
                            rs.getString("character_name")
                    );

                    return a;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching ActorSeries by ID: " + e.getMessage());
        }
        return null;
    }

    public List<ActorSeries> getCharacterByActor(int actors_id) throws SQLException {
        String sql = "SELECT * FROM actorSeries WHERE actors_id = ?";
        List<ActorSeries> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, actors_id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ActorSeries a = new ActorSeries(
                            rs.getInt("act_id"),
                            rs.getInt("actors_id"),
                            rs.getInt("series_id"),
                            rs.getString("character_name")
                    );
                    list.add(a);
                }
                return list;
            }
//            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<ActorSeries> getByCharacterName(String characterName) throws SQLException {
        List<ActorSeries> actorSeriesList = new ArrayList<>();
        String sql = "SELECT * FROM actorSeries WHERE character_name = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, characterName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ActorSeries as = new ActorSeries(
                            rs.getInt("act_id"),
                            rs.getInt("actors_id"),
                            rs.getInt("series_id"),
                            rs.getString("character_name")
                    );
                    actorSeriesList.add(as);
                }
            }
            return actorSeriesList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean insertActorSeries(ActorSeries as) throws SQLException {
        String sql = "INSERT INTO actorSeries (actors_id, series_id, character_name) VALUES (?, ?, ?)";
        int rows = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, as.getActorId());
            ps.setInt(2, as.getSeriesId());
            ps.setString(3, as.getCharacterName());
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return rows>0;
    }

    public boolean deleteActorSeries(int actId) throws SQLException {
        String sql = "DELETE FROM actorSeries WHERE act_id = ?";
        int rows = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, actId);
            rows = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return rows > 0;
    }

    public void updateActorSeries(ActorSeries as) throws SQLException {
        String sql = "UPDATE actorSeries SET actors_id = ?, series_id = ?, character_name = ? WHERE act_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, as.getActorId());
            ps.setInt(2, as.getSeriesId());
            ps.setString(3, as.getCharacterName());
            ps.setInt(4, as.getActId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
