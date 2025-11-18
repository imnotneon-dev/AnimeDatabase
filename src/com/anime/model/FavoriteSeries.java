package com.anime.model;

import java.time.LocalDate;

public class FavoriteSeries{

    private int favorite_id;
    private String username;
    private int series_id;
    private LocalDate added_date;

    public FavoriteSeries(int favorite_id, String username, int series_id, LocalDate added_date) {
        this.favorite_id = favorite_id;
        this.username = username;
        this.series_id = series_id;
        this.added_date = added_date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSeriesId() {
        return series_id;
    }

    public void setSeriesId(int series_id) {
        this.series_id = series_id;
    }

    public LocalDate getAddedDate() {
        return added_date;
    }

    public void setAddedDate(LocalDate added_date) {
        this.added_date = added_date;
    }

    public int getFavorite_id() {
        return favorite_id;
    }

    public void setFavorite_id(int favorite_id) {
        this.favorite_id = favorite_id;
    }

}
