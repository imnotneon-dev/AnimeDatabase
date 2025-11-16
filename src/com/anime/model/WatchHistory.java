package com.anime.model;

import java.time.LocalDate;

public class WatchHistory {
    private int userId;
    private int seriesId;
    private int episodeId;
    private LocalDate watchDate;

    public WatchHistory(int uid, int sid, int eid, LocalDate wd){
        userId = uid;
        seriesId = sid;
        episodeId = eid;
        watchDate = wd;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public LocalDate getWatchDate() {
        return watchDate;
    }

    public void setWatchDate(LocalDate watchDate) {
        this.watchDate = watchDate;
    }
}
