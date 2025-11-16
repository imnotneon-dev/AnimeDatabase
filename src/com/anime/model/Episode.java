package com.anime.model;

import java.time.LocalDate;
import java.util.List;

public class Episode {
    private String epTitle;
    private String sypnosis;
    private int runtime;
    private int views;
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

    public void setSeriesId(int seriesId){
        this.seriesId = seriesId;
    }

    public int getEpisodeId(){
        return episodeId;
    }

    public void setEpisodeId(int episodeId){
        this.episodeId = episodeId;
    }

    public String getTitle(){
        return epTitle;
    }

    public void setTitle(String title){
        this.epTitle = title;
    }

    public String getSypnosis(){
        return sypnosis;
    }

    public void setSypnosis(String sypnosis){
        this.sypnosis = sypnosis;
    }

    public int getRuntime(){
        return runtime;
    }

    public void setRuntime(int runtime){
        this.runtime = runtime;
    }

    public int getViews(){
        return views;
    }

    public void setViews(int views){
        this.views = views;
    }

    public LocalDate getReleaseDate(){
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate){
        this.releaseDate = releaseDate;
    }

    public List<Review> getListOfReviews(){
        return listOfReviews;
    }

    public void setListOfReviews(List<Review> listOfReviews){
        this.listOfReviews = listOfReviews;
    }


}