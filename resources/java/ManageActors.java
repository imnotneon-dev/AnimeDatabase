

public class ManageActors {

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

