package com.anime.model;

public class EpisodeReview {

    private int review_id;
    private String username;
    private int episode_id;
    private String comment;

    public EpisodeReview(int review_id, String username, int episode_id, String comment) {
        this.review_id = review_id;
        this.username = username;
        this.episode_id = episode_id;
        this.comment = comment;
    }

    public int getReviewId() {
        return review_id;
    }

    public void setReviewId(int review_id) {
        this.review_id = review_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getEpisodeId() {
        return episode_id;
    }

    public void setEpisodeId(int episode_id) {
        this.episode_id = episode_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}