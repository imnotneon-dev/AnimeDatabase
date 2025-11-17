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

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    
}
