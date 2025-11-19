package com.anime.view.customcards;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
//import com.anime.model.Episode;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

public class SeriesEpisodeCard extends JPanel {
//    private Episode episode;
    private final int episode_id;
    public SeriesEpisodeCard(int eid, String title, String syp, int runtime, LocalDate releaseDate){
//        episode = ep;
        this.episode_id = eid;
        setOpaque(true);
        setVisible(true);
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(5,5,5,5));
        setPreferredSize(new Dimension(200,160));
        setMaximumSize(new Dimension(200,160));
        setToolTipText(syp);

        JLabel epTitle = new JLabel(title);
        JLabel synopsis = new JLabel(syp);
        JLabel runTime = new JLabel(String.valueOf(runtime)+"m");
        JLabel relDate = new JLabel(String.valueOf(releaseDate));

        add(epTitle);
        add(synopsis);
        add(runTime);
        add(relDate);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(Color.decode("#F8481C"));
            }

            @Override
            public void mouseReleased(MouseEvent e){
                setBackground(Color.WHITE);
            }
        });
    }

    public int getEpisodeId(){
        return episode_id;
    }
}
