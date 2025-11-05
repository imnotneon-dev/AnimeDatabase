package com.anime.view;

import javax.swing.*;
import java.awt.*;

public class AnimeFrame extends JFrame{
    // VARIABLES
    // private AppController controller

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
        setSize(new Dimension(1280,720));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
