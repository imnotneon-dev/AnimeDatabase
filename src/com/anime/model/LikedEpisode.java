package com.anime.model;

import java.time.LocalDate;

public class LikedEpisode {

    private int userId;
    private int episodeId;
    private LocalDate dateAdded;

    public LikedEpisode(int userId, int episodeId, LocalDate dateAdded) {
        this.userId = userId;
        this.episodeId = episodeId;
        this.dateAdded = dateAdded;
    }

    public int getUserId() {
        return userId;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }
}
