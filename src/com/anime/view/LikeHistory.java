package com.anime.view;

import com.anime.view.customcards.HistoryEpisodeCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class LikeHistory extends JPanel{
//    private JPanel actorInfoPnl = new JPanel();
    private JPanel likePnl = new JPanel();
//    private JLabel actorPhoto = new JLabel();
//    private JLabel nameLb = new JLabel("Name");
//    private JLabel ageLb = new JLabel("40");
//    private JLabel placeOfBirthLb = new JLabel("Tokyo");
//    private JLabel agencyNameLb = new JLabel("Agency");
    private JScrollPane likeHistoryScrollPane = new JScrollPane(likePnl);
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
    public LikeHistory() {
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
        JLabel likesHistory = new JLabel("Liked Episodes");

        likesHistory.setAlignmentX(Component.LEFT_ALIGNMENT);
        likesHistory.setAlignmentY(Component.TOP_ALIGNMENT);


        likePnl.setLayout(new BoxLayout(likePnl, BoxLayout.Y_AXIS));
        likePnl.setBackground(Color.black);
//        likePnl.setPreferredSize(new Dimension(1280, 720));
        likePnl.setMaximumSize(new Dimension(900, Integer.MAX_VALUE));
        likePnl.setAlignmentY(Component.CENTER_ALIGNMENT);
//        likePnl.setMinimumSize(new Dimension(1280/2,720));
        likePnl.setAlignmentX(Component.LEFT_ALIGNMENT);
        likePnl.setBorder(new EmptyBorder(10,10,10,10));

        likePnl.add(likesHistory);
        likePnl.add(Box.createVerticalStrut(10));
        for(HistoryEpisodeCard h: episodeList){
            h.setAlignmentX(Component.LEFT_ALIGNMENT);
            likePnl.add(h);
            likePnl.add(Box.createVerticalStrut(10));
        }

        likeHistoryScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        likeHistoryScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        likeHistoryScrollPane.setWheelScrollingEnabled(true);
        likeHistoryScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        likeHistoryScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        likeHistoryScrollPane.setBorder(BorderFactory.createEmptyBorder());
//        likePnl.setBorder(new EmptyBorder(10,10,10,10));

        add(likeHistoryScrollPane, BorderLayout.CENTER);
    }

}
