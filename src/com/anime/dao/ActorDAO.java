package com.anime.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.anime.model.Actor;

public class ActorDAO {

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
    // public ArrayList<String> getRolesByActorId(int actorId) {
    //     ArrayList<String> roles = new ArrayList<>();
    //     String sql = "SELECT role_name FROM roles WHERE actor_id = ?";
    //     try(Connection conn = DBConnection.getConnection();
    //         PreparedStatement ps = conn.prepareStatement(sql)) {
    //         ps.setInt(1, actorId);
    //         ResultSet rs = ps.executeQuery();
    //         while(rs.next()) {
    //             roles.add(rs.getString("role_name"));
    //         }
    //     } catch(SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return roles;
    // }
}
