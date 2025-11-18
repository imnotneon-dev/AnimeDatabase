package com.anime.model;

import java.util.List;

public class AnimeWrapped {

    private String username;
    private int year;

    private int totalEpisodesWatched;
    private String topGenre;
    private List<SeriesStats> top5Series;
    private List<ActorStats> top3VoiceActors;
    private List<ActorRole> actorRoles;

    public AnimeWrapped() {}

    public AnimeWrapped(String username, int year) {
        this.username = username;
        this.year = year;
    }

    public String getUsername() { 
        return username; 
    }

    public void setUsername(String username) { 
        this.username = username; 
    }

    public int getYear() { 
        return year; 
    }

    public void setYear(int year) { 
        this.year = year; 
    }

    public int getTotalEpisodesWatched() { 
        return totalEpisodesWatched; 
    }

    public void setTotalEpisodesWatched(int totalEpisodesWatched) { 
        this.totalEpisodesWatched = totalEpisodesWatched; 
    }

    public String getTopGenre() { 
        return topGenre; 
    }

    public void setTopGenre(String topGenre) { 
        this.topGenre = topGenre; 
    }

    public List<SeriesStats> getTop5Series() { 
        return top5Series; 
    }

    public void setTop5Series(List<SeriesStats> top5Series) { 
        this.top5Series = top5Series; 
    }

    public List<ActorStats> getTop3VoiceActors() { 
        return top3VoiceActors; 
    }

    public void setTop3VoiceActors(List<ActorStats> top3VoiceActors) { 
        this.top3VoiceActors = top3VoiceActors; 
    }

    public List<ActorRole> getActorRoles() { 
        return actorRoles; 
    }

    public void setActorRoles(List<ActorRole> actorRoles) { 
        this.actorRoles = actorRoles;
    }


    public static class SeriesStats {
        private int seriesId;
        private String title;
        private String genre;
        private String seriesPhoto;
        private int episodesWatched;

        public SeriesStats(int seriesId, String title, String genre, String seriesPhoto, int episodesWatched) {
            this.seriesId = seriesId;
            this.title = title;
            this.genre = genre;
            this.seriesPhoto = seriesPhoto;
            this.episodesWatched = episodesWatched;
        }

        public int getSeriesId() { 
            return seriesId; 
        }

        public String getTitle() { 
            return title; 
        }

        public String getGenre() { 
            return genre; 
        }

        public String getSeriesPhoto() { 
            return seriesPhoto; 
        }

        public int getEpisodesWatched() { 
            return episodesWatched; 
        }

    }

    public static class ActorStats {
        private int actorId;
        private String firstName;
        private String lastName;
        private String agency;
        private int episodesAppearedIn;

        public ActorStats(int actorId, String firstName, String lastName, String agency, int episodesAppearedIn) {
            this.actorId = actorId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.agency = agency;
            this.episodesAppearedIn = episodesAppearedIn;
        }

        public int getActorId() { 
            return actorId; 
        }

        public String getFirstName() { 
            return firstName; 
        }

        public String getLastName() { 
            return lastName; 
        }

        public String getFullName() { 
            return lastName + ", " + firstName; 
        }

        public String getAgency() { 
            return agency; 
        }

        public int getEpisodesAppearedIn() { 
            return episodesAppearedIn; 
        }
    }

    public static class ActorRole {
        private int actorId;
        private int seriesId;
        private String seriesTitle;
        private String characterName;
        private int episodesWatched;

        public ActorRole(int actorId, int seriesId, String seriesTitle, String characterName, int episodesWatched) {
            this.actorId = actorId;
            this.seriesId = seriesId;
            this.seriesTitle = seriesTitle;
            this.characterName = characterName;
            this.episodesWatched = episodesWatched;
        }

        public int getActorId() { 
            return actorId; 
        }
        
        public int getSeriesId() { 
            return seriesId; 
        }

        public String getSeriesTitle() { 
            return seriesTitle; 
        }

        public String getCharacterName() { 
            return characterName; 
        }

        public int getEpisodesWatched() { 
            return episodesWatched; 
        }
    }
}
