package com.anime.view;

import java.awt.*;
import javax.swing.*;

public class AnimeFrame extends JFrame {
    // VARIABLES
    // private AppController controller
    /** The login panel for the specified Account/user */
    private AccountPanel login;
    private HomePage home;
    private CatalogPage catalog;
    private SeriesPage series;
    private EpisodePage episode;
    private ActorPage actor;
    private WatchHistory watch;
    private LikeHistory likes;
    private HeaderPanel head;
    private AdminPage adminpg;
    private JPanel container = new JPanel();
    private CardLayout cardLayout = new CardLayout();
    public final String LOGIN = "LOGIN_VIEW";
    public final String HOME = "HOME_VIEW";
    public final String CATALOG = "CATALOG_VIEW";
    public final String SERIES = "SERIES_DETAIL_VIEW";
    public final String EPISODE = "EPISODE_DETAIL_VIEW";
    public final String ACTOR = "ACTOR_DETAIL_VIEW";
    public final String WATCH_HISTORY = "WATCH_HISTORY_VIEW";
    public final String LIKE_HISTORY = "LIKE_HISTORY_VIEW";
    public final String ADMIN = "ADMIN_VIEW";


    /**
     * Private constructor to prevent instantiation without needed parameters
     */
    //private AnimeFrame() { }

    public AnimeFrame() {
        //TODO: add Controller as parameter
        super("Anime Streaming App");
        init();
    }

    /**
     * Initialize the components of the application frame
     */
    private void init(){

        addPanels();
        pack();

        setSize(new Dimension(1280,720));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        setLayout(new BorderLayout());
    }

    private void addPanels() {
        container.setLayout(cardLayout);

        login = new AccountPanel();
        home = new HomePage();
        catalog = new CatalogPage();
        series = new SeriesPage();
        episode = new EpisodePage();
        actor = new ActorPage();
        watch = new WatchHistory();
        likes = new LikeHistory();
        adminpg = new AdminPage();
        head = new HeaderPanel();


      container.add(login, LOGIN);
      container.add(home, HOME);
        container.add(catalog, CATALOG);
      container.add(series, SERIES);
        container.add(episode,EPISODE);
        container.add(actor,ACTOR);
        container.add(watch, WATCH_HISTORY);
        container.add(likes, LIKE_HISTORY);
        container.add(adminpg, ADMIN);
        add(head, BorderLayout.NORTH);
        add(container);
    }

    public CardLayout getCardLayout(){
        return cardLayout;
    }

    public JPanel getContentContainer(){
        return container;
    }

    public void switchView(String vname){
        cardLayout.show(container, vname);
    }

    public AccountPanel getLoginPanel() {
        return login;
    }

    public HomePage getHomePage() {
        return home;
    }

    public CatalogPage getCatalogPage() {
        return catalog;
    }

    public SeriesPage getSeriesPage() {
        return series;
    }

    public EpisodePage getEpisodePage() {
        return episode;
    }

    public ActorPage getActorPage() {
        return actor;
    }

    public WatchHistory getWatchHistoryPage() {
        return watch;
    }

    public LikeHistory getLikeHistoryPage() {
        return likes;
    }

    public HeaderPanel getHeaderPanel() {
        return head;
    }

    public AdminPage getAdminPage() {
        return adminpg;
    }
}
