package com.anime.model.dao;

import com.anime.model.Actor;
import com.anime.model.ActorSeries;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorSeriesDAO {

    private final Connection conn;

    public ActorSeriesDAO(Connection conn) {
        this.conn = conn;
    }
    
    public List<ActorSeries> getAllActorSeries() {
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

        } catch (SQLException e) {
            System.err.println("Error fetching all ActorSeries: " + e.getMessage());
        }

        return actorSeriesList;
    }

    public List<ActorSeries> getById(int actId) {
        String sql = "SELECT * FROM actorSeries WHERE act_id = ?";
        List<ActorSeries> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, actId);
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

        } catch (SQLException e) {
            System.err.println("Error fetching ActorSeries by ID: " + e.getMessage());
        }
        return null;
    }

    public List<ActorSeries> getCharacterByActor(int actors_id) {
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

        } catch (SQLException e) {
            System.err.println("Error fetching ActorSeries by ID: " + e.getMessage());
        }
        return null;
    }

    public List<ActorSeries> getByCharacterName(String characterName) {
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

        } catch (SQLException e) {
            System.err.println("Error fetching ActorSeries by character name: " + e.getMessage());
        }

        return actorSeriesList;
    }

    public boolean insertActorSeries(ActorSeries as) {
        String sql = "INSERT INTO actorSeries (actors_id, series_id, character_name) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, as.getActorId());
            ps.setInt(2, as.getSeriesId());
            ps.setString(3, as.getCharacterName());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error inserting ActorSeries: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteActorSeries(int actId) {
        String sql = "DELETE FROM actorSeries WHERE act_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, actId);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error deleting ActorSeries: " + e.getMessage());
            return false;
        }
    }

    public boolean updateActorSeries(ActorSeries as) {
        String sql = "UPDATE actorSeries SET actors_id = ?, series_id = ?, character_name = ? WHERE act_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, as.getActorId());
            ps.setInt(2, as.getSeriesId());
            ps.setString(3, as.getCharacterName());
            ps.setInt(4, as.getActId());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error updating ActorSeries: " + e.getMessage());
            return false;
        }
    }
}
