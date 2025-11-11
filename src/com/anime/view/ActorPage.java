package com.anime.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ActorPage extends JPanel{
    private JPanel actorInfoPnl = new JPanel();
    private JPanel rolesPnl = new JPanel();
    private JLabel actorPhoto = new JLabel();
    private JLabel nameLb = new JLabel("Name");
    private JLabel ageLb = new JLabel("40");
    private JLabel placeOfBirthLb = new JLabel("Tokyo");
    private JLabel agencyNameLb = new JLabel("Agency");
    private JScrollPane scrollPane = new JScrollPane(rolesPnl);
//    private List<ActorRole> rolesList;
    private List<String> rolesList = List.of("Bakugo","Midoriya", "Todoroki");
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
        // fix this line
        actorInfoPnl.setBorder(new EmptyBorder(10,10,10,10));

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

        rolesPnl.setLayout(new BoxLayout(rolesPnl, BoxLayout.Y_AXIS));
        rolesPnl.setBackground(Color.black);
        rolesPnl.setPreferredSize(new Dimension(1280/2, 720));
        rolesPnl.setMaximumSize(new Dimension(1280/2, 720));
        rolesPnl.setAlignmentY(Component.CENTER_ALIGNMENT);
//        rolesPnl.setMinimumSize(new Dimension(1280/2,720));
        rolesPnl.setAlignmentX(Component.LEFT_ALIGNMENT);
        rolesPnl.setBorder(new EmptyBorder(10,10,10,10));

        for(String r: rolesList){
            JLabel role = new JLabel();
            role.setText(r);
            role.setAlignmentX(Component.LEFT_ALIGNMENT);

            rolesPnl.add(role);
        }

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(actorInfoPnl);
        add(scrollPane);

    }
}
