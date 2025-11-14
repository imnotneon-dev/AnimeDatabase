package com.anime.view;

import java.time.LocalDate;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EpisodePage extends JPanel{
    private JButton button = new JButton("SeriesPage");
    private JPanel videoPanel = new JPanel();
    private JPanel episodeInfoPnl = new JPanel();
    private JLabel titleLb = new JLabel("Episode Title");
    private JLabel seriesLb = new JLabel("Series Title");
    private List<ReviewCard> reviews = List.of(new ReviewCard("user1", "review 1 wowee"),
                                        new ReviewCard("user2",
                                                "jeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdsh" +
                                                        "jeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdsh" +
                                                        "jeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdsh" +
                                                        "jeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdsh" +
                                                        "jeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdsh" +
                                                        "jeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdsh" +
                                                        "jeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdsh" +
                                                        "jeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdsh" +
                                                        "jeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdsh" +
                                                        "jeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdsh" +
                                                        "jeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdsh" +
                                                        "jeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshend"));
    private JLabel commentsLb = new JLabel("Comments");
    private JTextArea reviewTextArea = new JTextArea(3, 15);
    private JPanel reviewsPanel = new JPanel();
    private JButton submitCommentBtn = new JButton("Submit");
    private JScrollPane reviewTextAreaScroll = new JScrollPane(reviewTextArea);
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
        videoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 550));
        videoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        episodeInfoPnl.setLayout(new BoxLayout(episodeInfoPnl,BoxLayout.Y_AXIS));
        episodeInfoPnl.setBackground(Color.gray);
        episodeInfoPnl.setPreferredSize(new Dimension(1300, 80));
        episodeInfoPnl.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        episodeInfoPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        episodeInfoPnl.setBorder(new EmptyBorder(20,30,20,30));
        episodeInfoPnl.add(titleLb);
        episodeInfoPnl.add(seriesLb);

        /**
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(1,Integer.MAX_VALUE));
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
         */
        reviewTextArea.setLineWrap(true);
        reviewTextArea.setWrapStyleWord(true);
        reviewTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        reviewTextArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, reviewTextArea.getPreferredSize().height));
        reviewTextArea.setBorder(new EmptyBorder(5,5,5,5));

        reviewTextAreaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        reviewTextAreaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        reviewTextAreaScroll.setWheelScrollingEnabled(true);
        reviewTextAreaScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, reviewTextArea.getPreferredSize().height));
        reviewTextAreaScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        reviewTextAreaScroll.getVerticalScrollBar().setUnitIncrement(16);
//        reviewTextAreaScroll.getVerticalScrollBar().setPreferredSize(new Dimension(1,Integer.MAX_VALUE));
        reviewTextAreaScroll.setBorder(new EmptyBorder(10, 30, 10, 30));
        reviewTextAreaScroll.setBorder(BorderFactory.createTitledBorder("Reviews"));
        reviewTextAreaScroll.setBorder(BorderFactory.createEmptyBorder());

        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));
        reviewsPanel.setBackground(Color.lightGray);
        reviewsPanel.setBorder(new EmptyBorder(10, 30, 10, 30));
//        reviewsPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, Inte));

        submitCommentBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        commentsLb.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel leaveComment = new JLabel("Leave Comment");
        leaveComment.setAlignmentX(Component.LEFT_ALIGNMENT);
        reviewsPanel.add(leaveComment);
        reviewsPanel.add(Box.createVerticalStrut(5));
        reviewsPanel.add(reviewTextAreaScroll);
        reviewsPanel.add(Box.createVerticalStrut(5));
        reviewsPanel.add(submitCommentBtn);
        reviewsPanel.add(Box.createVerticalStrut(5));
        reviewsPanel.add(commentsLb);
        reviewsPanel.add(Box.createVerticalStrut(10));
        for(ReviewCard r: reviews){
            ReviewCard revCard = r;
            revCard.setAlignmentX(Component.LEFT_ALIGNMENT);
            reviewsPanel.add(revCard);
            reviewsPanel.add(Box.createVerticalStrut(10));
        }

        reviewsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        reviewsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        reviewsScrollPane.setWheelScrollingEnabled(true);
//        reviewsScrollPane.setPreferredSize(new Dimension(1280, 230));
        reviewsScrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, reviewsPanel.getPreferredSize().height));
        reviewsScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        reviewsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        reviewsScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(1,Integer.MAX_VALUE));
        reviewsScrollPane.setBorder(BorderFactory.createTitledBorder("Reviews"));
        reviewsScrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(videoPanel);
        add(episodeInfoPnl);
        add(reviewsScrollPane);
        add(Box.createVerticalGlue());
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
