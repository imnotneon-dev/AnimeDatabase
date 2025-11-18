package com.anime.model.dao;

import report.TopSeriesOfTheWeek;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopSeriesOfTheWeekDAO {
    private Connection connection;

    public TopSeriesOfTheWeekDAO(Connection connection) {
        this.connection = connection;
    }

    public List<TopSeriesOfTheWeek> getTopSeries(int weekNo) throws SQLException {
        List<TopSeriesOfTheWeek> topSeriesList = new ArrayList<>();

        String totalUsersQuery = "SELECT COUNT(DISTINCT user_id) AS total_users FROM WatchHistory WHERE WEEK(watch_date) = ?";
        
        StringBuilder topSeriesQuery = new StringBuilder();
        topSeriesQuery.append("SELECT s.title, COUNT(wh.user_id) AS viewers ")
                      .append("FROM WatchHistory wh ")
                      .append("JOIN Series s ON wh.series_id = s.id ")
                      .append("WHERE WEEK(wh.watch_date) = ? ")
                      .append("GROUP BY s.title ")
                      .append("ORDER BY viewers DESC ")
                      .append("LIMIT 5");

        int totalUsers = 0;
    
    
        return topSeriesList;
    }

}

