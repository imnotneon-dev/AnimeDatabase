package com.anime.view;

import com.anime.model.LikedEpisode;
import com.anime.model.WatchHistory;
import com.anime.view.customcards.PlainEpisodeCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LikeHistoryPage extends JPanel{
//    private JPanel actorInfoPnl = new JPanel();
    private JPanel likePnl = new JPanel();
//    private JLabel actorPhoto = new JLabel();
//    private JLabel nameLb = new JLabel("Name");
//    private JLabel ageLb = new JLabel("40");
//    private JLabel placeOfBirthLb = new JLabel("Tokyo");
//    private JLabel agencyNameLb = new JLabel("Agency");
    private JScrollPane likeHistoryScrollPane = new JScrollPane(likePnl);
//    private List<ActorRole> rolesList;
    /*private List<PlainEpisodeCard> episodeList = List.of(
            new PlainEpisodeCard("Kaiju No. 8: Season 2", "Second Wave", LocalDate.now()));*/
    private List<PlainEpisodeCard> likedEpisodesCards = new ArrayList<>();
    private List<LikedEpisode> likedEpisodesList = new ArrayList<>();

    public LikeHistoryPage() {
        init();
    }
    private void init() {
        setOpaque(true);
        setVisible(true);
        setBackground(Color.decode("#282828"));
//        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents(){
        JLabel likesHistory = new JLabel("Liked Episodes");

        likesHistory.setAlignmentX(Component.LEFT_ALIGNMENT);
        likesHistory.setAlignmentY(Component.TOP_ALIGNMENT);


        likePnl.setLayout(new BoxLayout(likePnl, BoxLayout.Y_AXIS));
        likePnl.setBackground(Color.decode("#282828"));
//        likePnl.setPreferredSize(new Dimension(1280, 720));
        likePnl.setMaximumSize(new Dimension(900, Integer.MAX_VALUE));
        likePnl.setAlignmentY(Component.CENTER_ALIGNMENT);
//        likePnl.setMinimumSize(new Dimension(1280/2,720));
        likePnl.setAlignmentX(Component.LEFT_ALIGNMENT);
        likePnl.setBorder(new EmptyBorder(10,10,10,10));

        likePnl.add(likesHistory);
        likePnl.add(Box.createVerticalStrut(10));

        loadEpisodeCards();

        likeHistoryScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        likeHistoryScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        likeHistoryScrollPane.setWheelScrollingEnabled(true);
        likeHistoryScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        likeHistoryScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        likeHistoryScrollPane.setBorder(BorderFactory.createEmptyBorder());
        likeHistoryScrollPane.getViewport().setBackground(Color.decode("#282828")); 
//        likePnl.setBorder(new EmptyBorder(10,10,10,10));

        add(likeHistoryScrollPane, BorderLayout.CENTER);
    }

    private void loadEpisodeCards(){
        likePnl.removeAll();
        likedEpisodesCards.clear();
        if(likedEpisodesList!=null){
            for(LikedEpisode e: likedEpisodesList){
                LocalDate wd = e.getDateAdded();
                PlainEpisodeCard card = new PlainEpisodeCard(wd);
                card.putClientProperty("episode_id",e.getEpisodeId());
                card.setAlignmentX(Component.LEFT_ALIGNMENT);
                card.setBackground(Color.decode("#282828"));
                card.setForeground(Color.WHITE);
                likedEpisodesCards.add(card);
                likePnl.add(card);
                likePnl.add(Box.createVerticalStrut(10));
            }
        }
        else {
            likePnl.add(new JLabel("No episodes yet..."));
            noEpisodesLabel.setForeground(Color.WHITE);
            likePnl.add(noEpisodesLabel);
        }
        likePnl.revalidate();
        likePnl.repaint();
    }
    public void setLikedEpisodesList(List<LikedEpisode> likedEpisodesList) {
        this.likedEpisodesList = likedEpisodesList;
        loadEpisodeCards();
    }

    public List<PlainEpisodeCard> getLikedEpisodesCards() {
        return likedEpisodesCards;
    }
}

