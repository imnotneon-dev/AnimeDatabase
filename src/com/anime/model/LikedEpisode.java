package com.anime.model;

import java.time.LocalDate;

public class LikedEpisode {

    private int like_id;
    private String username;
    private int episodeId;
    private LocalDate dateAdded;

    public LikedEpisode(int like_id, String username, int episodeId, LocalDate dateAdded) {
        this.like_id = like_id;
        this.username = username;
        this.episodeId = episodeId;
        this.dateAdded = dateAdded;
    }

    public String getUsername() {
        return username;
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

    public int getLike_id() {
        return like_id;
    }

    public void setLike_id(int like_id) {
        this.like_id = like_id;
    }
}
