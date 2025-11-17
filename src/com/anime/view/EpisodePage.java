package com.anime.view;

import com.anime.model.Episode;
import com.anime.model.EpisodeReview;
import com.anime.view.customcards.ReviewCard;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EpisodePage extends JPanel{
    private Episode episode;
    private JPanel videoPanel = new JPanel();

    private JPanel episodeInfoPnl = new JPanel();
    private JLabel titleLb = new JLabel("Episode Title");
    private JLabel seriesLb = new JLabel("Series Title");
    private JButton likeEpisodeBtn = new JButton("👍");

    private JPanel reviewsPanel = new JPanel();
    private JLabel commentsLb = new JLabel("Comments");
    private JTextArea reviewTextArea = new JTextArea(3, 15);
    private JButton submitCommentBtn = new JButton("Submit");
    private JScrollPane reviewTextAreaScroll = new JScrollPane(reviewTextArea);
    private JScrollPane reviewsScrollPane = new JScrollPane(reviewsPanel);

    private List<ReviewCard> reviewCards = new ArrayList<>();
    private List<EpisodeReview> reviewsList = new ArrayList<EpisodeReview>();
    private GridBagLayout gb = new GridBagLayout();

    /*private List<ReviewCard> reviews = List.of(new ReviewCard("user1", "review 1 wowee"),
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
                                                        "jeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshjeladhsalkdshend"));*/

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
        JPanel epInfoHSpacer = new JPanel();
        epInfoHSpacer.setOpaque(false);

        videoPanel.setLayout(new BoxLayout(videoPanel,BoxLayout.Y_AXIS));
        videoPanel.setBackground(Color.black);
        videoPanel.setPreferredSize(new Dimension(1280, 550));
        videoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 550));
        videoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

//        episodeInfoPnl.setLayout(new BoxLayout(episodeInfoPnl,BoxLayout.Y_AXIS));

        gb.setConstraints(seriesLb, new GridBagConstraints(
                0,0,
                1,1,
                0.0,1.0,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                new Insets(0,0,0,0),
                0,0));
        gb.setConstraints(titleLb, new GridBagConstraints(
                0,1,
                1,1,
                0.0,0.0,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                new Insets(0,0,0,0),
                0,0));
        gb.setConstraints(epInfoHSpacer, new GridBagConstraints(
                1,0,
                1,2,
                1.0,0.0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                new Insets(0,0,0,0),0,0));
        gb.setConstraints(likeEpisodeBtn, new GridBagConstraints(
                2,0,
                1,2,
                0.0,0.0,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                new Insets(0,10,0,10),
                0,0));

        likeEpisodeBtn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        likeEpisodeBtn.setPreferredSize(new Dimension(80, 80));
        likeEpisodeBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        likeEpisodeBtn.setBorder(new EmptyBorder(0,0,0,0));
        likeEpisodeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        episodeInfoPnl.setLayout(gb);
        episodeInfoPnl.setBackground(Color.gray);
        episodeInfoPnl.setPreferredSize(new Dimension(1300, 80));
        episodeInfoPnl.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        episodeInfoPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        episodeInfoPnl.setBorder(new EmptyBorder(20,30,20,30));
        episodeInfoPnl.add(seriesLb);
        episodeInfoPnl.add(titleLb);
        episodeInfoPnl.add(epInfoHSpacer);
        episodeInfoPnl.add(likeEpisodeBtn);

        /*
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

        reviewsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        reviewsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        reviewsScrollPane.setWheelScrollingEnabled(true);
//        reviewsScrollPane.setPreferredSize(new Dimension(1280, 230));
        reviewsScrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, reviewsPanel.getPreferredSize().height));
        reviewsScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        reviewsScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        reviewsScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(1,Integer.MAX_VALUE));
        reviewsScrollPane.setBorder(BorderFactory.createTitledBorder("Reviews"));
        reviewsScrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(videoPanel);
        add(episodeInfoPnl);

        loadReviewCards();
        add(reviewsScrollPane);
        add(Box.createVerticalGlue());
    }

    public void addReview(String reviewText) {
        JLabel reviewLabel = new JLabel("- " + reviewText);
        reviewLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        reviewsPanel.add(reviewLabel);
        reviewsPanel.add(Box.createVerticalStrut(5));
    }
    public void loadEpisodeLabels(){
        titleLb.setText(this.episode.getTitle());
        // Assuming Episode has getSeriesTitle() or similar access
        seriesLb.setText("From Series: " +
                (titleLb.getText() != null ?
                        seriesLb.getText() : "N/A"));
    }
    public void loadReviewCards(){
        reviewsPanel.removeAll();
        reviewCards.clear();
        for(EpisodeReview r: reviewsList){
            ReviewCard revCard = new ReviewCard(r.getUsername(),r.getUserReview());
            revCard.setAlignmentX(Component.LEFT_ALIGNMENT);
            reviewCards.add(revCard);
            reviewsPanel.add(revCard);
            reviewsPanel.add(Box.createVerticalStrut(10));
        }

        reviewsPanel.revalidate();
        reviewsPanel.repaint();
    }
    public JPanel getVideoPanel() { return videoPanel; }
    public void setVideoPanel(JPanel videoPanel) { this.videoPanel = videoPanel; }

    public JPanel getEpisodeInfoPnl() { return episodeInfoPnl; }
    public void setEpisodeInfoPnl(JPanel episodeInfoPnl) { this.episodeInfoPnl = episodeInfoPnl; }

    public JLabel getTitleLb() { return titleLb; }
    public void setTitleLb(JLabel titleLb) { this.titleLb = titleLb; }

    public JLabel getSeriesLb() { return seriesLb; }
    public void setSeriesTitle(String seriesTitle) {
        seriesLb.setText(seriesTitle);
    }

    public JButton getLikeEpisodeBtn() { return likeEpisodeBtn; }
    public void setLikeEpisodeBtn(JButton likeEpisodeBtn) { this.likeEpisodeBtn = likeEpisodeBtn; }

    public List<ReviewCard> getReviewsCards() { return reviewCards; }
    public void setReviews(List<ReviewCard> reviewCards) { this.reviewCards = reviewCards; }

    public JTextArea getReviewTextArea() { return reviewTextArea; }
    public void setReviewTextArea(JTextArea reviewTextArea) { this.reviewTextArea = reviewTextArea; }

    public JPanel getReviewsPanel() { return reviewsPanel; }
    public void setReviewsPanel(JPanel reviewsPanel) { this.reviewsPanel = reviewsPanel; }

    public JButton getSubmitCommentBtn() { return submitCommentBtn; }
    public void setSubmitCommentBtn(JButton submitCommentBtn) { this.submitCommentBtn = submitCommentBtn; }

    public Episode getEpisode() { return episode; }
    public void setEpisode(Episode episode) { this.episode = episode; loadEpisodeLabels(); }

    public List<EpisodeReview> getReviewsList(){ return reviewsList; }
    public void setReviewsList(List<EpisodeReview> reviewsList){
        this.reviewsList = reviewsList;
        loadReviewCards();
        // update after adding new reviews
    }

}
