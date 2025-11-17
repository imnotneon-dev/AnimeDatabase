package com.anime.model;

import java.time.LocalDate;

public class EpisodeReview {

    private int review_id;
    private int user_id;
    private int episode_id;
    private String user_review;
    private LocalDate date_reviewed;
    private String username;
    private String episodeTitle;

    public EpisodeReview(int review_id, int user_id, int episode_id, String user_review, LocalDate date_reviewed, String username, String episodeTitle) {
        this.review_id = review_id;
        this.user_id = user_id;
        this.episode_id = episode_id;
        this.user_review = user_review;
        this.date_reviewed = date_reviewed;
        this.username = username;
        this.episodeTitle = episodeTitle;
    }

    public int getReviewId() {
        return review_id;
    }

    public void setReviewId(int review_id) {
        this.review_id = review_id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getEpisodeId() {
        return episode_id;
    }

    public void setEpisodeId(int episode_id) {
        this.episode_id = episode_id;
    }

    public String getUserReview() {
        return user_review;
    }

    public void setUserReview(String user_review) {
        this.user_review = user_review;
    }

    public LocalDate getDateReviewed() {
        return date_reviewed;
    }

    public void setDateReviewed(LocalDate date_reviewed) {
        this.date_reviewed = date_reviewed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEpisodeTitle() {
        return episodeTitle;
    }

    public void setEpisodeTitle(String episodeTitle) {
        this.episodeTitle = episodeTitle;
    }
}
