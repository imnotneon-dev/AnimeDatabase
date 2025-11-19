package com.anime.model;

import java.time.LocalDate;

public class EpisodeReview {

    private int review_id;
    private int user_id;
    private int episode_id;
    private String user_review;
    private LocalDate date_reviewed; 

    public EpisodeReview(int review_id, int user_id, int episode_id, String user_review, LocalDate date_reviewed) {
        this.review_id = review_id;
        this.user_id = user_id;
        this.episode_id = episode_id;
        this.user_review = user_review;
        this.date_reviewed = date_reviewed;
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

    public String getReview() {
        return user_review;
    }

    public void setReview(String user_review) {
        this.user_review = user_review;
    }

    public LocalDate getDate_reviewed() {
        return date_reviewed;
    }

    public void setDate_reviewed(LocalDate date_reviewed) {
        this.date_reviewed = date_reviewed;
    }    
}
