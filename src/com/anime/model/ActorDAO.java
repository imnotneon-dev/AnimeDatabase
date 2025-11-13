package com.anime.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.anime.model.Actor;

public class ActorDAO {

    private final Connection conn

    public EpisodeDAO(Connection conn) {
      this.conn = conn;
    }

    public Actor getActorById(int id) {
        String sql = "SELECT * FROM actors WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Actor(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("birthplace"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // if we gonna try listing all roles by actor id
    public ArrayList<String> getRolesByActorId(int actorId) {
        ArrayList<String> roles = new ArrayList<>();
        String sql = "SELECT role_name FROM roles WHERE actor_id = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, actorId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                roles.add(rs.getString("role_name"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public void addActor(String lastName, String firstName, String gender, String dateOfBirth, String placeOfBirth, String agency) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO actors (last_name, first_name, gender, date_of_birth, place_of_birth, agency) ");
        sql.append("VALUES (?, ?, ?, ?, ?, ?);");
    }

    public void deleteActor(int actorId) {
        String sql = "DELETE FROM actors WHERE actor_id = ?;";
    }

    public void editActor(int actorId, String lastName, String firstName, String gender, String dateOfBirth, String placeOfBirth, String agency) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE actors SET last_name = ?, first_name = ?, gender = ?, date_of_birth = ?, place_of_birth = ?, agency = ? ");
        sql.append("WHERE actor_id = ?;");
    }

    public void viewActors() {
        String sql = "SELECT * FROM actors;";
    }   
}
