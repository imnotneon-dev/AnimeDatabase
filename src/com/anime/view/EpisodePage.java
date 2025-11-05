package com.anime.view;

import javax.swing.*;
import java.awt.*;

public class EpisodePage extends JPanel{
    private JButton button = new JButton("SeriesPage");
    public EpisodePage() {
        init();
    }
    private void init() {
        setOpaque(true);
        setVisible(true);
        setBackground(Color.BLUE);
        setLayout(new FlowLayout());
        add(button);
    }

    public JButton getButton(){
        return button;
    }
}
