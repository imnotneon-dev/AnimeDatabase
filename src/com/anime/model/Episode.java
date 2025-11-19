package com.anime.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Episode {
    private String epTitle;
    private String sypnosis;
    private int runtime;
    private int views;
    private LocalDate releaseDate;
    private int episodeId;
    private int seriesId;
    private List<EpisodeReview> listOfReviews;

    public Episode(int episodeId, String epTitle, String sypnosis, int runtime, int views, LocalDate releaseDate, int seriesId) {
        this.episodeId = episodeId;
        this.epTitle = epTitle;
        this.sypnosis = sypnosis;
        this.runtime = runtime;
        this.views = views;
        this.releaseDate = releaseDate;
        this.seriesId = seriesId;
        this.listOfReviews = new ArrayList<>();
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

    public List<EpisodeReview> getListOfReviews(){
        return listOfReviews;
    }

    public void setListOfReviews(List<EpisodeReview> listOfReviews){
        this.listOfReviews = listOfReviews;
    }

}