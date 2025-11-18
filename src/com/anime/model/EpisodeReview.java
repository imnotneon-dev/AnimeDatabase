package com.anime.model;

import java.time.LocalDate;

public class EpisodeReview {

    private int review_id;
    private int user_id;
    private int episode_id;
    private String comment;
    private LocalDate date_reviewed; 
    private String episode_title;

    public EpisodeReview(int review_id, int user_id, int episode_id, String comment, LocalDate date_reviewed, String episode_title) {
        this.review_id = review_id;
        this.user_id = user_id;
        this.episode_id = episode_id;
        this.comment = comment;
        this.date_reviewed = date_reviewed;
        this.episode_title = episode_title;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getEpisode_id() {
        return episode_id;
    }

    public void setEpisode_id(int episode_id) {
        this.episode_id = episode_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate_reviewed() {
        return date_reviewed;
    }

    public void setDate_reviewed(LocalDate date_reviewed) {
        this.date_reviewed = date_reviewed;
    }

    public String getEpisode_title() { 
        return episode_title; 
    }

    public void setEpisode_title(String episode_title) {
        this.episode_title = episode_title;
    }

    
}
