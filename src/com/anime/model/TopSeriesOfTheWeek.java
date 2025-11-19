package com.anime.model;

public class TopSeriesOfTheWeek {
    private String title;
    private int totalViewers;
    private double percentage;

    public TopSeriesOfTheWeek(String title, int totalViewers, double percentage) {
        this.title = title;
        this.totalViewers = totalViewers;
        this.percentage = percentage;
    }

    public String getTitle() {
        return title;
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
        return String.format(title, totalViewers, percentage);
    }
}

