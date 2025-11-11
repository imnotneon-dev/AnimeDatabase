package com.anime.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EpisodePage extends JPanel{
    private JButton button = new JButton("SeriesPage");
    private JPanel videoPanel = new JPanel();
    private JPanel episodeInfoPnl = new JPanel();
    private JPanel commentsPnl = new JPanel();
    private JPanel infoPnl = new JPanel();
    private JScrollPane scrollPane= new JScrollPane(infoPnl);
    private JLabel titleLb = new JLabel("Episode Title");
    private JLabel seriesLb = new JLabel("Series Title");
    private JLabel sypnosisLb = new JLabel("Sypnosis");
    private JLabel releaseDataLb = new JLabel("Rlease Date");
    private JLabel runtimeLb = new JLabel("runtime");
    public EpisodePage() {
        init();
    }
    private void init() {
        setOpaque(true);
        setVisible(true);
        setBackground(Color.BLUE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initComponents();
    }

    private void initComponents(){
        videoPanel.setLayout(new BoxLayout(videoPanel,BoxLayout.Y_AXIS));
        videoPanel.setBackground(Color.black);
        videoPanel.setPreferredSize(new Dimension(1280, 550));
        videoPanel.setMaximumSize(new Dimension(1280, 550));

        videoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);


        episodeInfoPnl.setLayout(new BoxLayout(episodeInfoPnl,BoxLayout.Y_AXIS));
        episodeInfoPnl.setBackground(Color.gray);
        episodeInfoPnl.setPreferredSize(new Dimension(Integer.MAX_VALUE, 230));
        episodeInfoPnl.setMaximumSize(new Dimension(Integer.MAX_VALUE, 230));
        episodeInfoPnl.setAlignmentY(Component.CENTER_ALIGNMENT);
//        episodeInfoPnl.setMinimumSize(new Dimension(1280/2,720));
        episodeInfoPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
//        episodeInfoPnl.setBorder(new EmptyBorder(10,10,10,10));
        episodeInfoPnl.add(titleLb);
        episodeInfoPnl.add(seriesLb);
        episodeInfoPnl.add(sypnosisLb);
        episodeInfoPnl.add(releaseDataLb);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setAlignmentY(Component.CENTER_ALIGNMENT);
        episodeInfoPnl.setPreferredSize(new Dimension(1280, 230));
        episodeInfoPnl.setMaximumSize(new Dimension(1300, 230));
//        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoPnl.add(episodeInfoPnl);
        infoPnl.add(commentsPnl);
        add(videoPanel);
        add(scrollPane);
    }
    public JButton getButton(){
        return button;
    }
}
