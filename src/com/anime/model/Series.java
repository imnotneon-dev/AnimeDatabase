public class Series {
    
    private int series_id;
    private String title;
    private String genre;
    private int release_year;
    private int total_episodes;
    private String status;


    public Series(int series_id, String title, String genre, int release_year, int total_episodes, String status) {
        this.series_id = series_id;
        this.title = title;
        this.genre = genre;
        this.release_year = release_year;
        this.total_episodes = total_episodes;
        this.status = status;
    }

    public int getSeriesId() {
        return series_id;
    }

    public void setSeriesId(int series_id) {
        this.series_id = series_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return release_year;
    }

    public void setReleaseYear(int release_year) {
        this.release_year = release_year;
    }

    public int getTotalEpisodes() {
        return total_episodes;
    }

    public void setTotalEpisodes(int total_episodes) {
        this.total_episodes = total_episodes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
