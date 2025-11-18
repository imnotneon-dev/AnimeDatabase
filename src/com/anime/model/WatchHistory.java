package com.anime.model;

import java.time.LocalDate;

public class WatchHistory {
    private String username;
    private int seriesId;
    private int episodeId;
    private LocalDate watchDate;

    public WatchHistory(String un, int sid, int eid, LocalDate wd){
        username = un;
        seriesId = sid;
        episodeId = eid;
        watchDate = wd;
    }

    public String getUsername() {
        return username;
    }

    public void setUserId(String username) {
        this.username = username;
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
