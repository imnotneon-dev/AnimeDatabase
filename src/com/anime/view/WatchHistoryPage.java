package com.anime.view;

import com.anime.model.Episode;
import com.anime.model.WatchHistory;
import com.anime.view.customcards.PlainEpisodeCard;
import com.anime.view.customcards.SeriesEpisodeCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WatchHistoryPage extends JPanel{
//    private JPanel actorInfoPnl = new JPanel();
    private JPanel historyPnl = new JPanel();
//    private JLabel actorPhoto = new JLabel();
//    private JLabel nameLb = new JLabel("Name");
//    private JLabel ageLb = new JLabel("40");
//    private JLabel placeOfBirthLb = new JLabel("Tokyo");
//    private JLabel agencyNameLb = new JLabel("Agency");
    private JScrollPane watchHistoryScrollPane = new JScrollPane(historyPnl);
//    private List<ActorRole> rolesList;
//    private List<PlainEpisodeCard> episodeList = List.of(
//            new PlainEpisodeCard("Kaiju No. 8: Season 2", "Second Wave", LocalDate.now()));
    /*
    * public ActorPage(Actor actor, List<ActorRole> roles){
    *   actorPhoto.
    *   nameLb.setText(actor.name);
    *   ageLb.setText();
    *   placeOfBirthLb.setText();
    *   agencyNameLb.setText();
    *   rolesList = roles;
    * }
    * */

    private List<PlainEpisodeCard> watchedEpisodesCards = new ArrayList<>();
    private List<WatchHistory> watchedEpisodesList = new ArrayList<>();

    public WatchHistoryPage() {
        init();
    }
    private void init() {
        setOpaque(true);
        setVisible(true);
        setBackground(Color.BLUE);
//        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents(){
        JLabel watchHistory = new JLabel("Watch History");

        watchHistory.setAlignmentX(Component.LEFT_ALIGNMENT);
        watchHistory.setAlignmentY(Component.TOP_ALIGNMENT);


        historyPnl.setLayout(new BoxLayout(historyPnl, BoxLayout.Y_AXIS));
        historyPnl.setBackground(Color.black);
//        historyPnl.setPreferredSize(new Dimension(1280, 720));
//        historyPnl.setMaximumSize(new Dimension(1280, 720));
        historyPnl.setAlignmentY(Component.CENTER_ALIGNMENT);
//        historyPnl.setMinimumSize(new Dimension(1280/2,720));
        historyPnl.setAlignmentX(Component.LEFT_ALIGNMENT);
        historyPnl.setBorder(new EmptyBorder(10,10,10,10));

        historyPnl.add(watchHistory);
        historyPnl.add(Box.createVerticalStrut(10));
        loadEpisodeCards();

        watchHistoryScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        watchHistoryScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        watchHistoryScrollPane.setWheelScrollingEnabled(true);
        watchHistoryScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        watchHistoryScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        watchHistoryScrollPane.setBorder(BorderFactory.createEmptyBorder());
//        historyPnl.setBorder(new EmptyBorder(10,10,10,10));

        add(watchHistoryScrollPane, BorderLayout.CENTER);
    }

    private void loadEpisodeCards(){
        historyPnl.removeAll();
        watchedEpisodesCards.clear();
        if(watchedEpisodesList!=null){
            for(WatchHistory e: watchedEpisodesList){
                LocalDate wd = e.getWatchDate();
                PlainEpisodeCard card = new PlainEpisodeCard(wd);
                card.putClientProperty("episode_id",e.getEpisodeId());
                card.setAlignmentX(Component.LEFT_ALIGNMENT);
                watchedEpisodesCards.add(card);
                historyPnl.add(card);
                historyPnl.add(Box.createVerticalStrut(10));
            }
        }
        else {
            historyPnl.add(new JLabel("No episodes yet..."));
        }
        historyPnl.revalidate();
        historyPnl.repaint();
    }

    public void setWatchedEpisodesList(List<WatchHistory> watchedEpisodesList) {
        this.watchedEpisodesList = watchedEpisodesList;
        loadEpisodeCards();
    }

    public List<PlainEpisodeCard> getWatchedEpisodesCards() {
        return watchedEpisodesCards;
    }
}
