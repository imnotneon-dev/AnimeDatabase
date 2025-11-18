package com.anime.model.dao;

public class AppModel {
    private AccountDAO accountDAO = new AccountDAO();
    private ActorDAO actorDAO = new ActorDAO();
    private ActorSeriesDAO actorSeriesDAO = new ActorSeriesDAO();
    private EpisodeDAO episodeDAO = new EpisodeDAO();
    private EpisodeReviewDAO episodeReviewDAO = new EpisodeReviewDAO();
    private FavoriteSeriesDAO favoriteSeriesDAO = new FavoriteSeriesDAO();
    private SeriesDAO seriesDAO = new SeriesDAO();
    private WatchHistoryDAO watchHistoryDAO = new WatchHistoryDAO();
    private LikedEpisodeDAO likedEpisodeDAO = new LikedEpisodeDAO();

    public AccountDAO getAccountDAO() {
        return accountDAO;
    }

    public ActorDAO getActorDAO() {
        return actorDAO;
    }

    public ActorSeriesDAO getActorSeriesDAO() {
        return actorSeriesDAO;
    }

    public EpisodeDAO getEpisodeDAO() {
        return episodeDAO;
    }

    public EpisodeReviewDAO getEpisodeReviewDAO() {
        return episodeReviewDAO;
    }

    public FavoriteSeriesDAO getFavoriteSeriesDAO() {
        return favoriteSeriesDAO;
    }

    public SeriesDAO getSeriesDAO() {
        return seriesDAO;
    }

    public WatchHistoryDAO getWatchHistoryDAO() {
        return watchHistoryDAO;
    }

    public LikedEpisodeDAO getLikedEpisodeDAO() {
        return likedEpisodeDAO;
    }
}