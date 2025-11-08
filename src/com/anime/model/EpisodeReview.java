public void generateEpisodeReviewLog(int episodeId, String month) throws SQLException {
    String sql = """
        SELECT u.username, r.user_review, r.date_reviewed
        FROM Review r
        JOIN Users u ON r.user_id = u.user_id
        WHERE r.episode_id = ? AND DATE_FORMAT(r.date_reviewed, '%Y-%m') = ?
        ORDER BY r.date_reviewed DESC;
    """;

    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setInt(1, episodeId);
    ps.setString(2, month); // e.g., "2025-11"
    ResultSet rs = ps.executeQuery();

    System.out.println("Reviews for Episode " + episodeId + ":");
    while (rs.next()) {
        System.out.printf("[%s] %s: %s%n",
            rs.getDate("date_reviewed"),
            rs.getString("username"),
            rs.getString("user_review"));
    }
}
