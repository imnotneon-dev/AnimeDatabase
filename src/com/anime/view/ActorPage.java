package com.anime.view;

import javax.swing.*;
import java.awt.*;

public class ActorPage extends JPanel{
    private JPanel actorInfoPnl = new JPanel();
    private JPanel rolesPnl = new JPanel();
    private JLabel actorPhoto = new JLabel();
    private JLabel nameLb = new JLabel("Name");
    private JLabel ageLb = new JLabel("40");
    private JLabel placeOfBirthLb = new JLabel("Tokyo");
    private JLabel agencyNameLb = new JLabel("Agency");
//    private List<ActorRole> rolesList;
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
    public ActorPage() {
        init();
    }
    private void init() {
        setOpaque(true);
        setVisible(true);
        setBackground(Color.BLUE);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        initComponents();
    }

    private void initComponents(){
        actorInfoPnl.setLayout(new BoxLayout(actorInfoPnl,BoxLayout.Y_AXIS));
        actorInfoPnl.setBackground(Color.gray);
        actorInfoPnl.setPreferredSize(new Dimension(1280/2, 720));
        actorInfoPnl.setMaximumSize(new Dimension(1280/2, 720));
        actorInfoPnl.setAlignmentY(Component.CENTER_ALIGNMENT);
//        actorInfoPnl.setMinimumSize(new Dimension(1280/2,720));
        actorInfoPnl.setAlignmentX(Component.CENTER_ALIGNMENT);

        actorInfoPnl.add(Box.createVerticalGlue());
        actorInfoPnl.add(Box.createHorizontalGlue());
        actorPhoto.setAlignmentX(Component.CENTER_ALIGNMENT);
        actorInfoPnl.add(actorPhoto);
        nameLb.setAlignmentX(Component.CENTER_ALIGNMENT);
        actorInfoPnl.add(nameLb);
        ageLb.setAlignmentX(Component.CENTER_ALIGNMENT);
        actorInfoPnl.add(ageLb);
        placeOfBirthLb.setAlignmentX(Component.CENTER_ALIGNMENT);
        actorInfoPnl.add(placeOfBirthLb);
        agencyNameLb.setAlignmentX(Component.CENTER_ALIGNMENT);
        actorInfoPnl.add(agencyNameLb);
        actorInfoPnl.add(Box.createHorizontalGlue());
        actorInfoPnl.add(Box.createVerticalGlue());

        add(actorInfoPnl);

    }
}
