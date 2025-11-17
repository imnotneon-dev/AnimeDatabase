package com.anime.controller;

import com.anime.model.WatchHistory;
import com.anime.model.dao.*;
import com.anime.model.*;
import com.anime.view.*;
import com.anime.view.customcards.*;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppController {
    private AnimeFrame view = new AnimeFrame();
    private AppModel model = new AppModel();
    private Account currentSession;
    public AppController(AnimeFrame view, AppModel model){
        this.view = view;
        this.model = model;

        init_application();
    }

    private void init_application(){
        init_header_listeners();
        init_accpnl_listeners();
        init_homepage_listeners();

    }

    private void init_accpnl_listeners() {
        AccountPanel login = view.getLoginPanel();

        login.getLoginBtn().addActionListener(e->{
            String username = login.getLoginName();
            String pw = login.getLoginPassword();
            if(username.isEmpty() || pw.isEmpty()){
                System.out.println("Username and password cannot be empty");
                return;
            }
            try {
                Account account = model.getAccountDAO().selectAccountByUsername(username);
                if(account!=null){
                    this.currentSession = account;
                    System.out.println("Login Success");
                    String accountUsername = account.getUsername();

                    // get the list of Favorites (user id, series id, date added)
                    List<FavoriteSeries> favoriteSeries = model.getFavoriteSeriesDAO().getFavorites(accountUsername);

                    // declare an empty list: FaveSeries -> Series
                    List<Series> favoriteListConverted = new ArrayList<>();
                    List<Series> watchHistoryConverted = new ArrayList<>();
                    // Loop through the favoriteSeries list, getting the series_id to get the
                    // Series details to store into a Series object
                    for(FavoriteSeries f: favoriteSeries){
                        int series_id = f.getSeriesId();
                        Series series = model.getSeriesDAO().getSeriesById(series_id);
                        favoriteListConverted.add(series);
                    }

                    /* TODO: WATCH HISTORY INTIALIZATION */

                    view.getHomePage().setFavoriteList(favoriteListConverted);
                    view.getHomePage().setWatchingList(watchHistoryConverted);
                    view.switchView(view.HOME);
                } else {
                    System.out.println("Login failed: Invalid username or password");
                    return;
                }


            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        login.getSignupBtn().addActionListener(e->{
            login.getLoginContainer().setVisible(false);
            login.getSignupContainer().setVisible(true);
        });

        login.getAlreadyHasAccountBtn().addActionListener(e->{
            login.getLoginContainer().setVisible(true);
            login.getSignupContainer().setVisible(false);
        });

        login.getSubmitSignUpBtn().addActionListener(e->{
            String username = login.getSignName();
            String password = login.getSignPassword();
            String confirmPw = login.getSignConfirm();
            String dob = login.getSignDob();
            String country = login.getCountry();
            if(username.isEmpty() || password.isEmpty() || confirmPw.isEmpty() || dob == null || country.isEmpty()){
                System.out.println("Username and password cannot be empty");
                return;
            }
            else if (!password.equals(confirmPw)){
                System.out.println("Passwords do not match or is empty");
                return;
            }

            LocalDate date;
            try{
                date = LocalDate.parse(dob);
            } catch (Exception ex){
                System.out.println("Invalid date");
                return;
            }
            try {
                Account checker = model.getAccountDAO().selectAccountByUsername(username);
                if(checker!=null){
                    System.out.println("Account already exist");
                }
                else {
                    model.getAccountDAO().addUser(username,password, String.valueOf(dob),country,"None");
                    Account account = model.getAccountDAO().selectAccountByUsername(username);

                    if(account!=null){
                        this.currentSession = account;
                    System.out.println("Signup Success. Logging in...");
                    view.switchView(view.HOME);
                    }
                    else{
                        System.out.println("Account created, but auto login failed.");
                    }

                }
            } catch (SQLException ex) {
                System.err.println("DB Error during signup: " + ex.getMessage());
                throw new RuntimeException(ex);
            }
        });
    }

    private void init_header_listeners(){
        HeaderPanel header = view.getHeaderPanel();
        String name = currentSession.getUsername();
        header.setAccountName("Welcome, " + name);
        header.getHomeIcon().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.switchView(view.HOME);
            }

        });
        header.getLogoutItem().addActionListener(e-> {
            view.switchView(view.LOGIN);
        });

        header.getWatchHistoryItem().addActionListener(e->{

//            try {
//                List< WatchHistory> history = model.getWatchHistoryDao()
//                view.getWatchHistoryPage().loadHistory(history );
                view.switchView(view.WATCH_HISTORY);
//            }
//            catch (SQLException ex){
//                System.err.println("DB Error loading watch history: " + ex.getMessage());
//                System.out.println("Could not load watch history due to a database error.");
//            }
        });

        header.getLikesItem().addActionListener(e->{
            view.switchView(view.LIKE_HISTORY);

//            try {
//                List< WatchHistory> history = model.getWatchHistoryDao()
//                view.getWatchHistoryPage().loadHistory(history );
//                view.switchView(view.LIKE_HISTORY);
//            }
//            catch (SQLException ex){
//                System.err.println("DB Error loading watch history: " + ex.getMessage());
//                System.out.println("Could not load watch history due to a database error.");
//            }
        });
    }

    private void init_homepage_listeners(){
        HomePage home = view.getHomePage();
        List<SeriesCard> allCards = new ArrayList<>();
        allCards.addAll(home.getWatchingListCard());
        allCards.addAll(home.getFavoriteListCard());

        for(SeriesCard card: allCards){
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int series_id = card.getSeriesID();
                    try {
                        // might be redundant since series alr has the info but ill keep it here still
                        Series series = model.getSeriesDAO().getSeriesById(series_id);

                        if(series!=null) {
                            // load series info
                            view.getSeriesPage().setSeries(series);

                            view.switchView(view.SERIES);
                        } else {
                            System.out.println("Series details cannot be found.");
                        }
                    }
                    catch (SQLException ex) {
                        System.err.println("DB Error during series loading: " + ex.getMessage());
                        throw new RuntimeException(ex);
                    }
                }
            });
        }
    }

    private void init_series_listener(){
        SeriesPage series = view.getSeriesPage();
        List<SeriesEpisodeCard> episodeCards = series.getEpisodeCards();
        List<JLabel> actorLabelCards = series.getActorLabelCards();

        for(SeriesEpisodeCard card: episodeCards){
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int ep_id = card.getEpisodeId();
                    try {
                        // might be redundant since series alr has the info but ill keep it here still
                        Episode episode = model.getEpisodeDAO().selectEpisodeById(ep_id);

                        if(episode!=null) {
                            // load series info
                            view.getEpisodePage().setEpisode(episode);

                            view.switchView(view.EPISODE);
                        } else {
                            System.out.println("Episode details cannot be found.");
                        }
                    }
                    catch (SQLException ex) {
                        System.err.println("DB Error during episode loading: " + ex.getMessage());
                        throw new RuntimeException(ex);
                    }

                }
            });
        }

    }
}
