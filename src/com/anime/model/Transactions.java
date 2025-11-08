public class Transactions {


    //should this even be directly in this file or shd i make a new class for this lmk
    public void likeEpisode(int userId, int episodeId) throws SQLException {
    //check if episode is already liked
    String check = "SELECT COUNT(*) FROM LikedEpisode WHERE user_id = ? AND episode_id = ?";
    PreparedStatement checkStmt = conn.prepareStatement(check);
    checkStmt.setInt(1, userId);
    checkStmt.setInt(2, episodeId);
    ResultSet rs = checkStmt.executeQuery();
    rs.next();

    if (rs.getInt(1) > 0) {
        System.out.println("You already liked this episode!");
        return;
    }

    //if episode is not liked yet, insert the new like
    String sql = "INSERT INTO LikedEpisode (user_id, episode_id) VALUES (?, ?)";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setInt(1, userId);
    ps.setInt(2, episodeId);
    ps.executeUpdate();

    System.out.println("Episode liked!");
    }

}
