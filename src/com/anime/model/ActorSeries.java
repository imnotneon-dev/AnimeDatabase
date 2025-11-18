package com.anime.model;

public class ActorSeries {
    
    private int actId;
    private int actorId;
    private int seriesId;
    private String characterName;

    public ActorSeries(int actId, int aId, int sId, String characterName){
        this.actId = actId;
        this.actorId = aId;
        this.seriesId = sId;
        this.characterName = characterName;
    }

    public ActorSeries(int aId, int sId, String characterName){
        this.actorId = aId;
        this.seriesId = sId;
        this.characterName = characterName;
    }

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
}