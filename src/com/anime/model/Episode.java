package com.anime.model;

import java.time.LocalDate;
import java.util.List;

public class Episode {
    private String epTitle;
    private String sypnosis;
    private int runtime;
    private LocalDate releaseDate;
    private int episodeId;
    private int seriesId;
    private List<Review> listOfReviews;

    public Episode(int eid, String t, String s, int r, LocalDate rD) {
        episodeId = eid;
        epTitle = t;
        sypnosis = s;
        runtime = r;
        releaseDate = rD;
    }

    public int getSeriesId(){
        return seriesId;
    }

    public int getEpisodeId(){
        return episodeId;
    }

    public String getTitle(){
        return epTitle;
    }

    public String getSypnosis(){
        return sypnosis;
    }

    public int getRuntime(){
        return runtime;
    }

    public LocalDate getReleaseDate(){
        return releaseDate;
    }

    public List<Review> getListOfReviews(){
        return listOfReviews;
    }
}