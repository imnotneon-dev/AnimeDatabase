package com.anime.model;

public class TopGenreOfTheWeek {
    private String genre;
    private int totalViewers;
    private double percentage;

    public TopGenreOfTheWeek(String genre, int totalViewers, double percentage) {
        this.genre = genre;
        this.totalViewers = totalViewers;
        this.percentage = percentage;
    }

    public String getGenre() {
        return genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalViewers() {
        return totalViewers;
    }

    public void setTotalViewers(int totalViewers) {
        this.totalViewers = totalViewers;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return String.format(genre, totalViewers, percentage);
    }
}

