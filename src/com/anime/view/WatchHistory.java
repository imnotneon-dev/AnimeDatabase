package com.anime.view;

import com.anime.view.customcards.HistoryEpisodeCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class WatchHistory extends JPanel{
//    private JPanel actorInfoPnl = new JPanel();
    private JPanel historyPnl = new JPanel();
//    private JLabel actorPhoto = new JLabel();
//    private JLabel nameLb = new JLabel("Name");
//    private JLabel ageLb = new JLabel("40");
//    private JLabel placeOfBirthLb = new JLabel("Tokyo");
//    private JLabel agencyNameLb = new JLabel("Agency");
    private JScrollPane watchHistoryScrollPane = new JScrollPane(historyPnl);
//    private List<ActorRole> rolesList;
    private List<HistoryEpisodeCard> episodeList = List.of(
            new HistoryEpisodeCard("Kaiju No. 8: Season 2", "Second Wave", LocalDate.now()));
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
    public WatchHistory() {
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
        for(HistoryEpisodeCard h: episodeList){
            HistoryEpisodeCard ep = h;
            ep.setAlignmentX(Component.LEFT_ALIGNMENT);
            historyPnl.add(ep);
            historyPnl.add(Box.createVerticalStrut(10));
        }

        watchHistoryScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        watchHistoryScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        watchHistoryScrollPane.setWheelScrollingEnabled(true);
        watchHistoryScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        watchHistoryScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        watchHistoryScrollPane.setBorder(BorderFactory.createEmptyBorder());
//        historyPnl.setBorder(new EmptyBorder(10,10,10,10));

        add(watchHistoryScrollPane, BorderLayout.CENTER);
    }

}
