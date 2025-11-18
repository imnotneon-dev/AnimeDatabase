package com.anime.model.dao;

import com.anime.model.Actor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO {

//    private final Connection conn;

//    public ActorDAO(Connection conn) {
//        this.conn = conn;
//    }

    public Actor getActorById(int id) throws SQLException {
        String sql = "SELECT * FROM actors WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Actor(
                            rs.getInt("id"),
                            rs.getString("last_name"),
                            rs.getString("first_name"),
                            rs.getString("gender"),
                            rs.getDate("date_of_birth").toLocalDate(),
                            rs.getString("place_of_birth"),
                            rs.getString("agency"),
                            rs.getString("series_photo")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<String> getRolesByActorId(int actorId) throws SQLException {
        List<String> roles = new ArrayList<>();
        String sql = "SELECT role_name FROM roles WHERE actor_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, actorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    roles.add(rs.getString("role_name"));
                }
            }
            return roles;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Actor> getActorsBySeries(int series_id) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.actor_id, a.last_name, a.first_name a.gender, a.date_of_birth, a.place_of_birth, a.agency");
        sql.append("FROM actors a ");
        sql.append("JOIN actor_series acs ON a.actor_id = acs.actor_id ");
        sql.append("JOIN series s ON acs.series_id = s.series_id ");
        sql.append("WHERE s.series_id = ? LIMIT 5;");
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            ps.setInt(1, series_id);
            ResultSet rs = ps.executeQuery();
            List<Actor> actors = new ArrayList<>();
            while (rs.next()) {
                actors.add(new Actor(
                        rs.getInt("actor_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("gender"),
                        rs.getDate("date_of_birth").toLocalDate(),
                        rs.getString("place_of_birth"),
                        rs.getString("agency"),
                        rs.getString("series_photo")
                ));
            }
            return actors;
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Actor> getActorsByEpisode ( int epId) throws SQLException {
        String sql =
                "SELECT a.actor_id, a.last_name, a.first_name, a.gender, a.date_of_birth, a.place_of_birth, a.agency " +
                        "FROM actors a " +
                        "JOIN actor_series acs ON a.actor_id = acs.actor_id " +
                        "JOIN series s ON acs.series_id = s.series_id " +
                        "WHERE s.ep_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, epId);
            try (ResultSet rs = ps.executeQuery()) {
                List<Actor> actors = new ArrayList<>();
                while (rs.next()) {
                    actors.add(new Actor(
                            rs.getInt("actor_id"),
                            rs.getString("last_name"),
                            rs.getString("first_name"),
                            rs.getString("gender"),
                            rs.getDate("date_of_birth").toLocalDate(),
                            rs.getString("place_of_birth"),
                            rs.getString("agency"),
                            rs.getString("series_photo")
                    ));
                }
                return actors;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean addActor (String lastName, String firstName, String gender, String dateOfBirth, String placeOfBirth, String agency) throws SQLException {
        String sql = "INSERT INTO actors (last_name, first_name, gender, date_of_birth, place_of_birth, agency) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, lastName);
            ps.setString(2, firstName);
            ps.setString(3, gender);
            ps.setDate(4, java.sql.Date.valueOf(dateOfBirth));
            ps.setString(5, placeOfBirth);
            ps.setString(6, agency);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return false;
    }

    public void deleteActor ( int actorId) throws SQLException {
        String sql = "DELETE FROM actors WHERE actor_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, actorId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean editActor (int actorId, String lastName, String firstName, String gender, String dateOfBirth, String placeOfBirth, String agency) throws SQLException {
        String sql = "UPDATE actors SET last_name = ?, first_name = ?, gender = ?, date_of_birth = ?, place_of_birth = ?, agency = ? WHERE actor_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, lastName);
            ps.setString(2, firstName);
            ps.setString(3, gender);
            ps.setString(4, dateOfBirth);
            ps.setString(5, placeOfBirth);
            ps.setString(6, agency);
            ps.setInt(7, actorId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return false;
    }

    public List<Actor> viewActors () throws SQLException {
        String sql = "SELECT * FROM actors";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<Actor> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Actor(
                        rs.getInt("actor_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("gender"),
                        rs.getDate("date_of_birth").toLocalDate(),
                        rs.getString("place_of_birth"),
                        rs.getString("agency"),
                        rs.getString("series_photo")
                ));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
