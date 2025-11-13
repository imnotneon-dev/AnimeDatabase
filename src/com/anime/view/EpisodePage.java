package com.anime.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

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

    private JPanel reviewsPanel = new JPanel();
    private JScrollPane reviewsScrollPane = new JScrollPane(reviewsPanel);
    
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

        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));
        reviewsPanel.setBackground(Color.lightGray);
        reviewsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        reviewsPanel.setPreferredSize(new Dimension(1280, 600)); 
        reviewsPanel.setMaximumSize(new Dimension(1400, 600));  

        // checking reviews onleh
        addReview("Great episode!");
        addReview("Animation was amazing this week.");
        addReview("WOWOWOWOWOWOW");

        reviewsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        reviewsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        reviewsScrollPane.setWheelScrollingEnabled(true);
        reviewsScrollPane.setPreferredSize(new Dimension(1500, 230));
        reviewsScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        reviewsScrollPane.setAlignmentY(Component.CENTER_ALIGNMENT);
        reviewsScrollPane.setBorder(BorderFactory.createTitledBorder("Reviews"));

        infoPnl.add(episodeInfoPnl);
        infoPnl.add(commentsPnl);
        add(videoPanel);
        add(scrollPane);
        add(reviewsScrollPane);
    }

    public void addReview(String reviewText) {
        JLabel reviewLabel = new JLabel("- " + reviewText);
        reviewLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        reviewsPanel.add(reviewLabel);
        reviewsPanel.add(Box.createVerticalStrut(5));
    }

    public JButton getButton(){
        return button;
    }
}
