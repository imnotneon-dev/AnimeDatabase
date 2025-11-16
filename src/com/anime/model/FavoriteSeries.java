package com.anime.model;

import java.time.LocalDate;

public class FavoriteSeries{

    private int user_id;
    private int series_id;
    private LocalDate added_date;

    public FavoriteSeries(int user_id, int series_id, LocalDate added_date) {
        this.user_id = user_id;
        this.series_id = series_id;
        this.added_date = added_date;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
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
}