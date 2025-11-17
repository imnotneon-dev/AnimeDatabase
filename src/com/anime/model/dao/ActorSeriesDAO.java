package com.anime.model.dao;

import com.anime.model.ActorSeries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorSeriesDAO {

    private final Connection conn;

    public ActorSeriesDAO(Connection conn) {
        this.conn = conn;
    }

    public List<ActorSeries> getAllActorSeries() throws SQLException {
        List<ActorSeries> actorSeriesList = new ArrayList<>();
        String sql = "SELECT * FROM actorSeries";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
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
    }

        // GET BY act_id
    public ActorSeries getById(int actId) throws SQLException {
        String sql = "SELECT * FROM actorSeries WHERE act_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, actId);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new ActorSeries(
                rs.getInt("act_id"),
                rs.getInt("actors_id"),
                rs.getInt("series_id"),
                rs.getString("character_name")
            );
        }
        return null;
    }

    // GET LIST BY series_id
    public List<ActorSeries> getByCharacterName(String characterName) throws SQLException {
        List<ActorSeries> actorSeriesList = new ArrayList<>();
        String sql = "SELECT * FROM actorSeries WHERE character_name = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, characterName);

        ResultSet rs = ps.executeQuery();
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
    }

    public void insertActorSeries(ActorSeries as) throws SQLException {
        String sql = "INSERT INTO actorSeries (actors_id, series_id, character_name) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, as.getActorId());
        ps.setInt(2, as.getSeriesId());
        ps.setString(3, as.getCharacterName());

        ps.executeUpdate();
    }

    public void deleteActorSeries(int actId) throws SQLException {
        String sql = "DELETE FROM actorSeries WHERE act_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, actId);
        ps.executeUpdate();
    }

    public void updateActorSeries(ActorSeries as) throws SQLException {
        String sql = "UPDATE actorSeries SET actors_id = ?, series_id = ?, character_name = ? WHERE act_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, as.getActorId());
        ps.setInt(2, as.getSeriesId());
        ps.setString(3, as.getCharacterName());
        ps.setInt(4, as.getActId());

        ps.executeUpdate();
    }
}

