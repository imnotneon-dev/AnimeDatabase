package com.anime.controller;

import com.anime.model.WatchHistory;
import com.anime.model.dao.*;
import com.anime.model.*;
import com.anime.view.*;
import com.anime.view.customcards.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        init_catalog_listeners();
        init_seriespage_listeners();
        init_episodepage_listeners();
        init_actorpage_listeners();
        init_admin_panel_listeners();

    }
    private boolean acc_signup_DisplayHasError(Component parentComp, String username, String password, String confirmPw, String dob, String country){
        String errorMsg = null;

        if(username.isEmpty() || password.isEmpty() || confirmPw.isEmpty() || dob == null || country.isEmpty()){
            errorMsg = "Username and password cannot be empty";
        }
        else if(username.equalsIgnoreCase("admin")){
            errorMsg = "Username cannot be admin.";
        }
        else if (!password.equals(confirmPw)){
            errorMsg = "Passwords do not match or is empty";
        }

        if(errorMsg!=null){
            JOptionPane.showMessageDialog(
                parentComp,
                errorMsg,
                "Input Validation Error",
                JOptionPane.ERROR_MESSAGE
            );
            return true;
        }
        return false; // passed validation
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
                if(!(account.getPassword().equals(pw))){
//                    System.out.println("Passwords don't match please try again");
                    JOptionPane.showMessageDialog(
                            this.view,
                            "Passwords don't match please try again",
                            "Input Validation Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;

                }
                if(account!=null){
                    this.currentSession = account;

                    int acc_id = account.getUserId();

                    // get the list of Favorites (user id, series id, date added)
                    List<FavoriteSeries> favoriteSeries = model.getFavoriteSeriesDAO().getFavorites(acc_id);

                    // get the list of WatchHistory (user id, episode id, date added)
                    List<WatchHistory> watchedSeries = model.getWatchHistoryDAO().getWatchedListByUser(acc_id);

                    // declare an empty list: FaveSeries -> Series
                    List<Series> favoriteListConverted = new ArrayList<>();

                    // declare an empty list: WatchHistory -> Series
                    List<Series> watchHistoryConverted = new ArrayList<>();

                    // Loop through the favoriteSeries list, getting the series_id to get the
                    // Series details to store into a Series object
                    for(FavoriteSeries f: favoriteSeries){
                        int series_id = f.getSeriesId();
                        Series series = model.getSeriesDAO().getSeriesById(series_id);
                        favoriteListConverted.add(series);
                    }

                    // Loop through the watch list, getting the series_id to get the
                    // Series details to store into a Series object
                    for(WatchHistory w: watchedSeries){
                        int episode_id = w.getEpisodeId();
                        int series_id = model.getEpisodeDAO().selectEpisodeById(episode_id).getSeriesId();
                        Series series = model.getSeriesDAO().getSeriesById(series_id);
                        watchHistoryConverted.add(series);
                    }

                    view.getHomePage().setFavoriteList(favoriteListConverted);
                    view.getHomePage().setWatchingList(watchHistoryConverted);
                    System.out.println("Login Success");
                    view.switchView(view.HOME);
                } else {
                    String error = "Login failed: Invalid username or password";
                    JOptionPane.showMessageDialog(
                            this.view,
                            error,
                            "Input Validation Error",
                            JOptionPane.ERROR_MESSAGE
                    );
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
            if(acc_signup_DisplayHasError(this.view, username, password, confirmPw, dob, country)){
                return;
            }
            LocalDate date;
            try{
                date = LocalDate.parse(dob);
            } catch (Exception ex){
                String error = ("Invalid date");
                JOptionPane.showMessageDialog(
                        this.view,
                        error,
                        "Input Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            try {
                Account checker = model.getAccountDAO().selectAccountByUsername(username);
                if(checker!=null){
                    String error = ("Account already exist");
                    JOptionPane.showMessageDialog(
                            this.view,
                            error,
                            "Input Validation Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                else {
                    model.getAccountDAO().addUser(username,password, String.valueOf(date),country);
                    Account account = model.getAccountDAO().selectAccountByUsername(username);

                    if(account!=null){
                        this.currentSession = account;
                        System.out.println("Signup Success. Logging in...");
                        int acc_id = account.getUserId();

                        // get the list of Favorites (user id, series id, date added)
                        List<FavoriteSeries> favoriteSeries = model.getFavoriteSeriesDAO().getFavorites(acc_id);

                        // get the list of WatchHistory (user id, episode id, date added)
                        List<WatchHistory> watchedSeries = model.getWatchHistoryDAO().getWatchedListByUser(acc_id);

                        // declare an empty list: FaveSeries -> Series
                        List<Series> favoriteListConverted = new ArrayList<>();

                        // declare an empty list: WatchHistory -> Series
                        List<Series> watchHistoryConverted = new ArrayList<>();

                        // Loop through the favoriteSeries list, getting the series_id to get the
                        // Series details to store into a Series object
                        for(FavoriteSeries f: favoriteSeries){
                            int series_id = f.getSeriesId();
                            Series series = model.getSeriesDAO().getSeriesById(series_id);
                            favoriteListConverted.add(series);
                        }

                        // Loop through the watch list, getting the series_id to get the
                        // Series details to store into a Series object
                        for(WatchHistory w: watchedSeries){
                            int episode_id = w.getEpisodeId();
                            int series_id = model.getEpisodeDAO().selectEpisodeById(episode_id).getSeriesId();
                            Series series = model.getSeriesDAO().getSeriesById(series_id);
                            watchHistoryConverted.add(series);
                        }

                        view.getHomePage().setFavoriteList(favoriteListConverted);
                        view.getHomePage().setWatchingList(watchHistoryConverted);
                        view.switchView(view.HOME);
                    }
                    else{
                        String error = ("Account created, but auto login failed.");
                        JOptionPane.showMessageDialog(
                                this.view,
                                error,
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );

                        view.getLoginPanel().getLoginContainer().setVisible(true);
                        view.getLoginPanel().getSignupContainer().setVisible(false);
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
        header.getCatalogLb().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                view.switchView(view.CATALOG);
            }
        });
        header.getLogoutItem().addActionListener(e-> {
            currentSession = null;
            view.getLoginPanel().getLoginContainer().setVisible(true);
            view.getLoginPanel().getSignupContainer().setVisible(true);
            view.switchView(view.LOGIN);
        });

        header.getWatchHistoryItem().addActionListener(e->{

            try {
                List<WatchHistory> accountWatchHist = model.getWatchHistoryDAO().getWatchedListByUser(currentSession.getUserId());
                view.getWatchHistoryPage().setWatchedEpisodesList(accountWatchHist);
                view.switchView(view.WATCH_HISTORY);
            }
            catch (SQLException ex){
                System.err.println("DB Error loading watch history: " + ex.getMessage());
                System.out.println("Could not load watch history due to a database error.");
            }
        });

        header.getLikesItem().addActionListener(e->{
            // deal with likedepisode
            try {
                List<LikedEpisode> accountLikedEps = model.getLikedEpisodeDAO().getLikesByUser(currentSession.getUserId());
                view.getLikeHistoryPage().setLikedEpisodesList(accountLikedEps);
                view.switchView(view.LIKE_HISTORY);
            } catch (SQLException ex) {
                System.err.println("DB Error loading watch history: " + ex.getMessage());
                throw new RuntimeException(ex);
            }
        });

        header.getAccStatsItem().addActionListener(e->{
            view.switchView(view.REPORT);
        });

        header.getWrappedItem().addActionListener(e->{
            view.switchView(view.WRAPPED);
        });
    }

    private void init_catalog_listeners(){
        CatalogPage catalog = view.getCatalogPage();
        List<SeriesCard> seriesCards = catalog.getCatalogSeriesCards();

        for(SeriesCard card: seriesCards){
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int series_id = card.getSeriesID();
                    try {
                        // might be redundant since series alr has the info but ill keep it here still
                        Series series = model.getSeriesDAO().getSeriesById(series_id);
                        // get episode list of the series
                        List<Episode> episodeList = model.getEpisodeDAO().selectEpisodeBySeries(series.getSeriesId());
                        // get actor list of the series
                        List<Actor> actorList = model.getActorDAO().getActorsBySeries(series.getSeriesId());
                        if(series!=null) {
                            // load series info
                            view.getSeriesPage().setSeries(series);
                            view.getSeriesPage().setEpisodeList(episodeList);
                            view.getSeriesPage().setActorsList(actorList);
                            view.switchView(view.SERIES);
                        } else {
                            String error = ("Series details cannot be found.");
                            JOptionPane.showMessageDialog(
                                    view.getMainFrame(),
                                    error,
                                    "Input Validation Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            return;
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
                        // get episode list of the series
                         List<Episode> episodeList = model.getEpisodeDAO().selectEpisodeBySeries(series.getSeriesId());
                        // get actor list of the series
                         List<Actor> actorList = model.getActorDAO().getActorsBySeries(series.getSeriesId());
                        if(series!=null) {
                            // load series info
                            view.getSeriesPage().setSeries(series);
                            view.getSeriesPage().setEpisodeList(episodeList);
                            view.getSeriesPage().setActorsList(actorList);
                            view.switchView(view.SERIES);
                        } else {
                            String error= ("Series details cannot be found.");
                            JOptionPane.showMessageDialog(
                                    view.getMainFrame(),
                                    error,
                                    "Input Validation Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            return;
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

    private void init_seriespage_listeners(){
        SeriesPage seriesPage = view.getSeriesPage();
        List<SeriesEpisodeCard> episodeCards = seriesPage.getEpisodeCards();
        List<JLabel> actorLabelCards = seriesPage.getActorLabelCards();
        Series seriesInfo = seriesPage.getSeries();

        for(SeriesEpisodeCard card: episodeCards){
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int ep_id = card.getEpisodeId();
                    try {
                        Episode episode = model.getEpisodeDAO().selectEpisodeById(ep_id);
                        String seriesTitle = seriesInfo.getTitle();
                        if(episode!=null) {
                            view.getEpisodePage().setEpisode(episode);
                            view.getEpisodePage().setSeriesTitle(seriesTitle);

                            String currentUserTopGenre = model.getSeriesDAO().getTopGenreOfUser(currentSession.getUserId());
                            currentSession.setTopGenre(currentUserTopGenre);
                            model.getAccountDAO().updateTopGenre(currentSession.getUserId(), currentUserTopGenre);
                            // add to watchlist of user
                            boolean check = model.getWatchHistoryDAO().addWatchHistoryByUser(
                                    currentSession.getUserId(), episode.getEpisodeId(), LocalDate.now()
                            );

                            if (check) {
                                // load review list
                                List<EpisodeReview> reviewList = model.getEpisodeReviewDAO().getReviewsByEpisodeId(ep_id);
                                view.getEpisodePage().setReviewsList(reviewList);

                                view.switchView(view.EPISODE);
                            }
                            else{
                                System.out.println("Could not add to watch history and thus not opening the episode...");
                            }
                        } else {
                            String error = ("Episode details cannot be found.");
                            JOptionPane.showMessageDialog(
                                    view.getMainFrame(),
                                    error,
                                    "Input Validation Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }
                    }
                    catch (SQLException ex) {
                        System.err.println("DB Error during episode loading: " + ex.getMessage());
                        throw new RuntimeException(ex);
                    }

                }
            });
        }
        for(JLabel labelCard: actorLabelCards){
            labelCard.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel sourceLb = (JLabel) e.getSource();
                    int actor_id = (int)sourceLb.getClientProperty("actor_id");
                    try {
                        Actor actor = model.getActorDAO().getActorById(actor_id);

                        if(actor!=null) {
                            view.getActorPage().setActorInfo(actor);

                            // load roles info
                            List<ActorSeries> actorRoles = model.getActorSeriesDAO().getCharacterByActor(actor.getId());
                            view.getActorPage().setRolesList(actorRoles);
                            view.switchView(view.ACTOR);
                        } else {
                            String error = ("Actor details cannot be found.");
                            JOptionPane.showMessageDialog(
                                    view.getMainFrame(),
                                    error,
                                    "Input Validation Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }
                    }
                    catch (SQLException ex) {
                        System.err.println("DB Error during actor loading: " + ex.getMessage());
                        throw new RuntimeException(ex);
                    }

                }
            });
        }
        seriesPage.getFaveBtn().addActionListener(e->{
            boolean checker = model.getFavoriteSeriesDAO().favoriteChecker(currentSession.getUserId(), seriesInfo.getSeriesId());
            if(checker) {
                // if true, meaning it has been favorited before, delete the favorited series
                model.getFavoriteSeriesDAO().removeFavoriteSeries(currentSession.getUserId(), seriesInfo.getSeriesId());
                // TODO: add changes to color button
            }
            else {
                // if false meaning it has NOT been favorited before, add the favorited series
                model.getFavoriteSeriesDAO().addFavoriteSeries(currentSession.getUserId(), seriesInfo.getSeriesId());
            }
        });
    }

    private void init_episodepage_listeners(){
        EpisodePage episodePage = view.getEpisodePage();
        Episode episodeInfo = episodePage.getEpisode();

        episodePage.getSeriesLb().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int series_id = episodePage.getEpisode().getSeriesId();
                try {
                    // might be redundant since series alr has the info but ill keep it here still
                    Series series = model.getSeriesDAO().getSeriesById(series_id);
                    // get episode list of the series
                    List<Episode> episodeList = model.getEpisodeDAO().selectEpisodeBySeries(series.getSeriesId());
                    // get actor list of the series
                    List<Actor> actorList = model.getActorDAO().getActorsBySeries(series.getSeriesId());
                    if(series!=null) {
                        // load series info
                        view.getSeriesPage().setSeries(series);
                        view.getSeriesPage().setEpisodeList(episodeList);
                        view.getSeriesPage().setActorsList(actorList);
                        view.switchView(view.SERIES);
                    } else {
                        String error=("Series details cannot be found.");
                        JOptionPane.showMessageDialog(
                                view.getMainFrame(),
                                error,
                                "Input Validation Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                }
                catch (SQLException ex) {
                    System.err.println("DB Error during series loading: " + ex.getMessage());
                    throw new RuntimeException(ex);
                }

            }
        });
        episodePage.getLikeEpisodeBtn().addActionListener(e->{
            // same logic !
            boolean checker;
            try {
                checker = model.getLikedEpisodeDAO().isLiked(currentSession.getUserId(),episodeInfo.getEpisodeId());

                if(checker) {
                    // if true, meaning it has been liked before, delete the liked series
                    model.getLikedEpisodeDAO().removeLike(currentSession.getUserId(),episodeInfo.getEpisodeId());
                }
                else {
                    // if false meaning it has NOT been liked before, add the liked series
                    model.getLikedEpisodeDAO().addLike(currentSession.getUserId(),episodeInfo.getEpisodeId());
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        episodePage.getSubmitCommentBtn().addActionListener(e-> {
            try {
                // might be redundant since series alr has the info but ill keep it here still
                String username = currentSession.getUsername();
                int ep_id = episodeInfo.getEpisodeId();
                String reviewText = episodePage.getReviewTextArea().getText();

                model.getEpisodeReviewDAO().addReview(currentSession.getUserId(), ep_id, reviewText);

                // reload series info after adding the new reviews
                List<EpisodeReview> reviewList = model.getEpisodeReviewDAO().getReviewsByEpisodeId(ep_id);
                view.getEpisodePage().setReviewsList(reviewList);
                view.switchView(view.EPISODE);
            } catch (SQLException ex) {
                System.err.println("DB Error during reviews loading: " + ex.getMessage());
                throw new RuntimeException(ex);
            }
            episodePage.getReviewTextArea().setText("");
        });
    }

    private void init_actorpage_listeners(){
        ActorPage actor = view.getActorPage();
        List<RoleCard> rolesCards = actor.getRolesCard();

        for(RoleCard card: rolesCards){
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int series_id = card.getRole_s_SeriesId();

                    try {
                        // might be redundant since series alr has the info but ill keep it here still
                        Series series = model.getSeriesDAO().getSeriesById(series_id);
                        // get episode list of the series
                        List<Episode> episodeList = model.getEpisodeDAO().selectEpisodeBySeries(series.getSeriesId());
                        // get actor list of the series
                        List<Actor> actorList = model.getActorDAO().getActorsBySeries(series.getSeriesId());

                        if(series!=null) {
                            // load series info
                            view.getSeriesPage().setSeries(series);
                            view.getSeriesPage().setEpisodeList(episodeList);
                            view.getSeriesPage().setActorsList(actorList);
                            view.switchView(view.SERIES);
                        } else {
                            String error = ("Series details cannot be found.");
                            JOptionPane.showMessageDialog(
                                    view.getMainFrame(),
                                    error,
                                    "Input Validation Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            });
        }
    }
    private void init_admin_panel_listeners(){
        AdminPage adminPage = view.getAdminPage();
        ManageActorPanel actor = adminPage.getMngActorPnl();
        ManageSeriesPanel series = adminPage.getMngSeriesPnl();
        ManageEpisodePanel episode = adminPage.getMngEpisodePnl();
        ManageActorSeriesPanel role = adminPage.getMngActorSeriesPnl();

        init_admin_actor_panel(actor);
        init_admin_series_panel(series);
        init_admin_episode_panel(episode);
        init_admin_actorseries_panel(role);
    }

    // USED FOR ADMIN RELATED WORK
    private Integer selectedActorId = null;
    private Integer selectedSeriesId = null;
    private Integer selectedEpisodeId = null;

    private void init_admin_actor_panel(ManageActorPanel actor){
        List<PlainActorCard> actorCards = actor.getActorCards();


        for(PlainActorCard card: actorCards){
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectedActorId = (Integer)card.getClientProperty("actor_id");
                    try{
                        Actor actorInfo = model.getActorDAO().getActorById(selectedActorId);

                        actor.getAddBtn().setEnabled(false);
                        actor.getUpdateBtn().setEnabled(true);
                        actor.getClearBtn().setEnabled(true);

                        actor.getFirstNameField().setText(actorInfo.getFirstName());
                        actor.getLastNameField().setText(actorInfo.getLastName());
                        actor.getSexCb().setSelectedItem(String.valueOf(actorInfo.getGender()));
                        actor.getDobField().setText(String.valueOf(actorInfo.getDob()));
                        actor.getPobField().setText(actorInfo.getPob());
                        actor.getAgencyField().setText(actorInfo.getAgency());

                    } catch(SQLException ex){
                        System.err.println("DB Error during reviews loading: " + ex.getMessage());
                        throw new RuntimeException(ex);
                    }
                }
            });
        }

        actor.getClearBtn().addActionListener(e->{
            actor.getFirstNameField().setText("");
            actor.getLastNameField().setText("");
            actor.getSexCb().setSelectedItem("Other");
            actor.getDobField().setText("");
            actor.getPobField().setText("");
            actor.getAgencyField().setText("");
        });

        actor.getAddBtn().addActionListener(e->{
            String ln = actor.getLastNameField().getText().trim();
            String fn = actor.getFirstNameField().getText().trim();
            String g = actor.getSexCb().getSelectedItem().toString().trim();
            String dob = actor.getDobField().getText().trim();
            String pob = actor.getPobField().getText().trim();
            String a = actor.getAgencyField().getText().trim();

            if(ln.isEmpty() || fn.isEmpty() || g.isEmpty() || dob.isEmpty() ||
                pob.isEmpty() || a.isEmpty()){
                String error  = ("No field can be empty");
                JOptionPane.showMessageDialog(
                        this.view,
                        error,
                        "Input Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            try {
                boolean check = model.getActorDAO().addActor(ln, fn, g, dob, pob, a);
                if (check) {
                    actor.getFirstNameField().setText("");
                    actor.getLastNameField().setText("");
                    actor.getSexCb().setSelectedItem("Other");
                    actor.getDobField().setText("");
                    actor.getPobField().setText("");
                    actor.getAgencyField().setText("");
                } else {
                    String error = ("Failed to add actor. Already exists.");
                    JOptionPane.showMessageDialog(
                            this.view,
                            error,
                            "Input Validation Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        actor.getUpdateBtn().addActionListener(e->{

            String ln = actor.getLastNameField().getText().trim();
            String fn = actor.getFirstNameField().getText().trim();
            String g = actor.getSexCb().getSelectedItem().toString().trim();
            String dob = actor.getDobField().getText().trim();
            String pob = actor.getPobField().getText().trim();
            String a = actor.getAgencyField().getText().trim();

            if(ln.isEmpty() || fn.isEmpty() || g.isEmpty() || dob.isEmpty() ||
                    pob.isEmpty() || a.isEmpty()){
                String error = "No field can be empty";
                JOptionPane.showMessageDialog(
                        this.view,
                        error,
                        "Input Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            try {
                boolean check = model.getActorDAO().editActor(selectedActorId, ln, fn, g, dob, pob, a);
                if (check) {
                    actor.getFirstNameField().setText("");
                    actor.getLastNameField().setText("");
                    actor.getSexCb().setSelectedItem("Other");
                    actor.getDobField().setText("");
                    actor.getPobField().setText("");
                    actor.getAgencyField().setText("");
                } else {
                    String error = ("Failed to edit actor.");
                    JOptionPane.showMessageDialog(
                            this.view,
                            error,
                            "Input Validation Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void init_admin_series_panel(ManageSeriesPanel series) {
        List<PlainSeriesCard> seriesCards = series.getSeriesCards();

        for (PlainSeriesCard card : seriesCards) {
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                   selectedEpisodeId = (Integer)card.getClientProperty("series_id");
                   series.getAddBtn().setEnabled(false);
                   series.getClearBtn().setEnabled(true);
                   series.getUpdateBtn().setEnabled(true);
                   series.getFilepathToPosterField().setEditable(false);
                   try{
                       Series seriesInfo = model.getSeriesDAO().getSeriesById(selectedEpisodeId);

                       series.getTitleField().setText(seriesInfo.getTitle());
                       series.getGenreField().setText(seriesInfo.getGenre());
                       series.getReleaseYearField().setText(String.valueOf(seriesInfo.getReleaseYear()));
                       series.getEpCountField().setText(String.valueOf(seriesInfo.getTotalEpisodes()));
                       series.getStatusCb().setSelectedItem(String.valueOf(seriesInfo.getStatus()));
                       series.getFilepathToPosterField().setText(seriesInfo.getSeriesPhoto());
                   } catch(SQLException ex){
                       System.err.println("DB Error during episode loading: " + ex.getMessage());
                       throw new RuntimeException(ex);
                   }
               }
            });
        }

        series.getClearBtn().addActionListener(e->{
            series.getAddBtn().setEnabled(true);
            series.getClearBtn().setEnabled(true);
            series.getUpdateBtn().setEnabled(false);
            series.getFilepathToPosterField().setEditable(true);

            series.getTitleField().setText("");
            series.getGenreField().setText("");
            series.getReleaseYearField().setText("");
            series.getEpCountField().setText("");
            series.getStatusCb().setSelectedItem("On-Going");
            series.getFilepathToPosterField().setText("");

        });

        series.getAddBtn().addActionListener(e->{
            String t = series.getTitleField().getText().trim();
            String g = series.getGenreField().getText().trim();
            String ry = series.getReleaseYearField().getText().trim();
            String c = series.getEpCountField().getText().trim();
            String s = series.getStatusCb().getSelectedItem().toString().trim();
            String photo = series.getFilepathToPosterField().getText();


            int intRy;
            int intC;

            try {
                intRy = Integer.parseInt(ry);
                intC = Integer.parseInt(c);

            } catch (NumberFormatException ex) {
                String error = ("Input Error: Release Year or Episode Count is not a valid number.");
                JOptionPane.showMessageDialog(
                        this.view,
                        error,
                        "Input Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if(t.isEmpty() || g.isEmpty() || ry.isEmpty() || c.isEmpty() ||
                    s.isEmpty()){
                String error = "No field can be empty";
                JOptionPane.showMessageDialog(
                        this.view,
                        error,
                        "Input Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            try{
                Series seriesToBeAdded = new Series(t,g,intRy,intC,s, photo);
                boolean check = model.getSeriesDAO().addSeries(seriesToBeAdded);
                if(check){
                    series.getTitleField().setText("");
                    series.getGenreField().setText("");
                    series.getReleaseYearField().setText("");
                    series.getEpCountField().setText("");
                    series.getStatusCb().setSelectedItem("On-Going");
                    series.getFilepathToPosterField().setText("");

                    // updating gui/list
                    List<Series> sList = model.getSeriesDAO().getAllSeries();
                    series.setSeriesList(sList);
                }
            } catch (SQLException ex){
                System.err.println("DB Error during episode loading: " + ex.getMessage());
                throw new RuntimeException(ex);
            }
        });

        series.getUpdateBtn().addActionListener(e->{
            String t = series.getTitleField().getText().trim();
            String g = series.getGenreField().getText().trim();
            String ry = series.getReleaseYearField().getText().trim();
            String c = series.getEpCountField().getText().trim();
            String s = series.getStatusCb().getSelectedItem().toString().trim();
            String photo = series.getFilepathToPosterField().getText();

            int intRy;
            int intC;

            try {
                intRy = Integer.parseInt(ry);
                intC = Integer.parseInt(c);

            } catch (NumberFormatException ex) {
//                System.err.println("Input Error: Release Year or Episode Count is not a valid number.");
                String error = "Input Error: Release Year or Episode Count is not a valid number.";
                JOptionPane.showMessageDialog(
                        this.view,
                        error,
                        "Input Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if(t.isEmpty() || g.isEmpty() || ry.isEmpty() || c.isEmpty() ||
                    s.isEmpty()){
                String error = "No field can be empty";
                JOptionPane.showMessageDialog(
                        this.view,
                        error,
                        "Input Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            try{
                Series seriesToBeAdded = new Series(selectedSeriesId,t,g,intRy,intC,s,photo);
                boolean check = model.getSeriesDAO().updateSeries(seriesToBeAdded);
                if(check){
                    series.getTitleField().setText("");
                    series.getGenreField().setText("");
                    series.getReleaseYearField().setText("");
                    series.getEpCountField().setText("");
                    series.getStatusCb().setSelectedItem("On-Going");
                    series.getFilepathToPosterField().setText("");


                    // updating gui/list
                    List<Series> sList = model.getSeriesDAO().getAllSeries();
                    series.setSeriesList(sList);
                }
            } catch (SQLException ex){
                System.err.println("DB Error during episode loading: " + ex.getMessage());
                throw new RuntimeException(ex);
            }
        });
    }
    private void init_admin_episode_panel(ManageEpisodePanel episode){
        List<PlainEpisodeCard> episodeCards = episode.getEpisodeCards();
//        int series_id;

        for(PlainEpisodeCard card: episodeCards){
            final Series parentSeries;
            try {
                int series_id = (int)card.getClientProperty("series_id");
                parentSeries = model.getSeriesDAO().getSeriesById(series_id);
                String series_title = parentSeries.getTitle();
                card.setSeriesTitle(series_title);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            card.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectedEpisodeId = (Integer)card.getClientProperty("episode_id");
                    episode.getAddBtn().setEnabled(false);
                    episode.getClearBtn().setEnabled(true);
                    episode.getUpdateBtn().setEnabled(true);
                    episode.getSeriesTitleCb().setEditable(false);
                    try{
                        Episode episodeInfo = model.getEpisodeDAO().selectEpisodeById(selectedEpisodeId);

                        episode.getTitleField().setText(episodeInfo.getTitle());
                        episode.getSeriesTitleCb().setSelectedItem(parentSeries);
                        episode.getSynopsisTA().setText(episodeInfo.getSypnosis());
                        episode.getReleaseDateField().setText(String.valueOf((episodeInfo.getReleaseDate())));
                        episode.getRuntimeField().setText(String.valueOf(episodeInfo.getRuntime()));
                    } catch(SQLException ex){
                        System.err.println("DB Error during episode loading: " + ex.getMessage());
                        throw new RuntimeException(ex);
                    }
                }
            });
        }

        episode.getClearBtn().addActionListener(e->{
            episode.getAddBtn().setEnabled(true);
            episode.getClearBtn().setEnabled(true);
            episode.getUpdateBtn().setEnabled(false);

            episode.getTitleField().setText("");
            episode.getSeriesTitleCb().setSelectedItem("Select a Series");
            episode.getSynopsisTA().setText("");
            episode.getReleaseDateField().setText("");
            episode.getRuntimeField().setText("");

        });

        episode.getAddBtn().addActionListener(e->{

            Object selectedSeries = episode.getSeriesTitleCb().getSelectedItem();

            if(!(selectedSeries instanceof Series selectedS)){
                String error = ("Please select a series title");
                JOptionPane.showMessageDialog(
                        this.view,
                        error,
                        "Input Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }
            int series_id = selectedS.getSeriesId();
            String t = episode.getTitleField().getText().trim();
            String st = episode.getSeriesTitleCb().getSelectedItem().toString().trim();
            String syn = episode.getSynopsisTA().getText().trim();
            String rd = episode.getReleaseDateField().getText().trim();
            String run = episode.getRuntimeField().getText().trim();

            LocalDate ldRd;
            int intRun;

            try {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                ldRd = LocalDate.parse(rd, dateFormat);
                intRun = Integer.parseInt(run);

            } catch (NumberFormatException ex) {
                String error = ("Input Error: Release Year or Episode Count is not a valid number.");
                JOptionPane.showMessageDialog(
                        this.view,
                        error,
                        "Input Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if(t.isEmpty() || syn.isEmpty() || rd.isEmpty() || run.isEmpty() ||
                    st.equals("Select a Series") || !(selectedSeries instanceof Series)){
                String error  = ("No field can be empty");
                JOptionPane.showMessageDialog(
                        this.view,
                        error,
                        "Input Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            try{
//                Episode seriesToBeAdded = new Series(t,g,intRy,intC,s);
                boolean check = model.getEpisodeDAO().addEpisode(t, ldRd, syn,intRun, series_id);
                if(check){
                    episode.getAddBtn().setEnabled(true);
                    episode.getClearBtn().setEnabled(true);
                    episode.getUpdateBtn().setEnabled(false);

                    episode.getTitleField().setText("");
                    episode.getSeriesTitleCb().setSelectedItem("Select a Series");
                    episode.getSynopsisTA().setText("");
                    episode.getReleaseDateField().setText("");
                    episode.getRuntimeField().setText("");

                    // updating gui/list
                    List<Episode> eList = model.getEpisodeDAO().selectAllEpisodes();
                    episode.setEpisodeList(eList);
                }
            } catch (SQLException ex){
                System.err.println("DB Error during episode loading: " + ex.getMessage());
                throw new RuntimeException(ex);
            }
        });

        episode.getUpdateBtn().addActionListener(e->{
            Object selectedSeries = episode.getSeriesTitleCb().getSelectedItem();

            if(!(selectedSeries instanceof Series selectedS)){
                String error = ("Please select a series title");
                JOptionPane.showMessageDialog(
                        this.view,
                        error,
                        "Input Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            String t = episode.getTitleField().getText().trim();
            String st = episode.getSeriesTitleCb().getSelectedItem().toString().trim();
            String syn = episode.getSynopsisTA().getText().trim();
            String rd = episode.getReleaseDateField().getText().trim();
            String run = episode.getRuntimeField().getText().trim();

            LocalDate ldRd;
            int intRun;

            try {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                ldRd = LocalDate.parse(rd, dateFormat);
                intRun = Integer.parseInt(run);

            } catch (NumberFormatException ex) {
                String error = ("Input Error: Release Year or Episode Count is not a valid number.");
                JOptionPane.showMessageDialog(
                        this.view,
                        error,
                        "Input Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;

            }

            if(t.isEmpty() || syn.isEmpty() || rd.isEmpty() || run.isEmpty() ||
                    st.equals("Select a Series") || !(selectedSeries instanceof Series)){
                String error = ("No field can be empty");
                JOptionPane.showMessageDialog(
                        this.view,
                        error,
                        "Input Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            try{
//                Episode seriesToBeAdded = new Series(t,g,intRy,intC,s);
                model.getEpisodeDAO().updateEpisode(selectedEpisodeId,t, ldRd, syn,intRun);

                    episode.getAddBtn().setEnabled(true);
                    episode.getClearBtn().setEnabled(true);
                    episode.getUpdateBtn().setEnabled(false);

                    episode.getTitleField().setText("");
                    episode.getSeriesTitleCb().setSelectedItem("Select a Series");
                    episode.getSynopsisTA().setText("");
                    episode.getReleaseDateField().setText("");
                    episode.getRuntimeField().setText("");

                    // updating gui/list
                    List<Episode> eList = model.getEpisodeDAO().selectAllEpisodes();
                    episode.setEpisodeList(eList);

            } catch (SQLException ex){
                System.err.println("DB Error during episode loading: " + ex.getMessage());
                throw new RuntimeException(ex);
            }
        });
    }
    private void init_admin_actorseries_panel(ManageActorSeriesPanel role){
        List<PlainActorCard> actorCards = role.getActorSeriesCards();

        role.getActorSeriesCb().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                role.getDeleteBtn().setEnabled(true);
                role.getAddBtn().setEnabled(false);
                role.getActorRole().setEditable(false);
//                role.getSeriesTitleCb().setEnabled(false);
//                role.getActorSeriesCb().setEnabled(true);
                Object selectedItem = e.getItem();

                if (selectedItem instanceof ActorSeries selectedSeries) {

                    int seriesId = selectedSeries.getSeriesId();
                    String character = selectedSeries.getCharacterName();

                    role.getActorRole().setText(character);
                }
            }
        });

        role.getSeriesTitleCb().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                role.getDeleteBtn().setEnabled(false);
                role.getAddBtn().setEnabled(true);
                role.getActorRole().setEditable(true);
//                role.getSeriesTitleCb().setEnabled(false);
//                role.getActorSeriesCb().setEnabled(true);
//                Object selectedItem = e.getItem();

                /*if (selectedItem instanceof Series selectedSeries) {

                    int seriesId = selectedSeries.getSeriesId();
                    String character = selectedSeries.getCharacterName();

                    role.getActorRole().setText(character);
                    // ... Logic to update other fields or fetch data ...
                }*/
            }
        });

        for(PlainActorCard card: actorCards){
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectedActorId = (Integer)card.getClientProperty("actor_id");
                    try{
//                        role.getDeleteBtn().setEnabled(true);
//                        role.getAddBtn().setEnabled(false);
//                        role.getSeriesTitleCb().setEnabled(false);
//                        role.getActorSeriesCb().setEnabled(true);
                        role.getActorNameField().setEditable(false);
//                        role.getActorRole().setEditable(false);

                        role.getActorNameField().setText(card.getNameLabel().getText());

                        //update list for gui
                        List<ActorSeries> actorSeriesList = model.getActorSeriesDAO().getCharacterByActor(selectedActorId);
                        role.setActorSeriesList(actorSeriesList);

                    } catch(SQLException ex){
                        System.err.println("DB Error during reviews loading: " + ex.getMessage());
                        throw new RuntimeException(ex);
                    }
                }
            });
        }

        role.getClearBtn().addActionListener(e->{

            role.getAddBtn().setEnabled(true);
            role.getDeleteBtn().setEnabled(true);
            role.getSeriesTitleCb().setEnabled(true);
            role.getActorSeriesCb().setEnabled(true);
            role.getActorNameField().setEditable(false);
            role.getActorNameField().setText("");
            role.getActorRole().setEditable(true);
        });

        role.getDeleteBtn().addActionListener(e-> {

            if (selectedActorId == null) {
                String error = ("Error: No actor selected.");
                JOptionPane.showMessageDialog(
                        this.view,
                        error,
                        "Input Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            Object selectedItem = role.getActorSeriesCb().getSelectedItem();

            if (selectedItem instanceof ActorSeries selectedRole){
                int actIdToDelete = selectedRole.getActId();

                try {
                    boolean success = model.getActorSeriesDAO().deleteActorSeries(actIdToDelete);

                    if (success) {
                        role.getActorRole().setText("");
                        role.getActorNameField().setText("");
                        role.getAddBtn().setEnabled(true);
                        role.getDeleteBtn().setEnabled(true);
                        role.getSeriesTitleCb().setEnabled(true);
                        role.getActorSeriesCb().setEnabled(true);
                        role.getActorNameField().setEditable(false);
                        role.getActorRole().setEditable(true);

                        // update gui list by setting all
                    } else {
                        String error = ("Failed to delete role from database.");
                        JOptionPane.showMessageDialog(
                                this.view,
                                error,
                                "Input Validation Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                } catch(Exception ex) {
                    System.err.println("Error deleting ActorSeries: " + ex.getMessage());
                    throw new RuntimeException(ex);
                }
            }
        });

        role.getAddBtn().addActionListener(e-> {

            if (selectedActorId == null) {
                String error = ("Error: No actor selected.");
                JOptionPane.showMessageDialog(
                        this.view,
                        error,
                        "Input Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            Object selectedItem = role.getSeriesTitleCb().getSelectedItem();

            if (selectedItem instanceof Series selectedSeries){
                int seriesIdToAdd = selectedSeries.getSeriesId();
                int actorIdToAdd = selectedActorId;
                String roleToAdd = role.getActorRole().getText();
                ActorSeries actorSeriesToAdd = new ActorSeries(actorIdToAdd, seriesIdToAdd, roleToAdd);

                try {
                    boolean success = model.getActorSeriesDAO().insertActorSeries(actorSeriesToAdd);

                    if (success) {
                        role.getActorRole().setText("");
                        role.getActorNameField().setText("");
                        role.getAddBtn().setEnabled(true);
                        role.getDeleteBtn().setEnabled(true);
                        role.getSeriesTitleCb().setEnabled(true);
                        role.getActorSeriesCb().setEnabled(true);
                        role.getActorNameField().setEditable(false);
                        role.getActorRole().setEditable(true);

                        // update gui list

                    } else {
                        String error = ("Failed to add role to database.");
                        JOptionPane.showMessageDialog(
                                this.view,
                                error,
                                "Input Validation Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                } catch(Exception ex) {
                    System.err.println("Error deleting ActorSeries: " + ex.getMessage());
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}

