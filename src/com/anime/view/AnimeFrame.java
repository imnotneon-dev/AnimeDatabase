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


//      container.add(login,BorderLayout.CENTER);
      container.add(home);
//        container.add(catalog);
//      container.add(series);
//        container.add(episode);
//        container.add(actor);
//        container.add(watch);
//        container.add(likes);
//        container.add(adminpg);
        add(head, BorderLayout.NORTH);
        add(container);
    }

}
