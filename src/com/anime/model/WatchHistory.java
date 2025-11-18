package com.anime.model;

import java.time.LocalDate;

public class WatchHistory {
    private int userId;
    private int watchId;
    private int episodeId;
    private LocalDate watchDate;

    public WatchHistory(int wid, int uid, int eid, LocalDate wd){
        watchId = wid;
        userId = uid;
        episodeId = eid;
        watchDate = wd;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWatchId() {
        return watchId;
    }

    public void setWatchId(int seriesId) {
        this.watchId = seriesId;
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
