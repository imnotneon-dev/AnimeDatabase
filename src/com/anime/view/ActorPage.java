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
    private JScrollPane rolesScrollPane = new JScrollPane(rolesPnl);
//    private List<ActorRole> rolesList;
    private List<RoleCard> rolesList = List.of(
            new RoleCard("My Hero Academia: Final Season", "Katsuki Bakugo"),
            new RoleCard("My Hero Academia: 3rd Season", "Katsuki Bakugo"),
            new RoleCard("My Hero Academia", "Katsuki Bakugo"),
            new RoleCard("My Hero Academia", "Katsuki Bakugo"),
            new RoleCard("My Hero Academia", "Katsuki Bakugo"),
            new RoleCard("My Hero Academia", "Katsuki Bakugo"),
        new RoleCard("My Hero Academia: 3rd Season", "Katsuki Bakugo"),
        new RoleCard("My Hero Academia", "Katsuki Bakugo"),
        new RoleCard("My Hero Academia", "Katsuki Bakugo"),
        new RoleCard("My Hero Academia", "Katsuki Bakugo"),
        new RoleCard("My Hero Academia", "Katsuki Bakugo"),
        new RoleCard("My Hero Academia: 3rd Season", "Katsuki Bakugo"),
        new RoleCard("My Hero Academia", "Katsuki Bakugo"),
        new RoleCard("My Hero Academia", "Katsuki Bakugo"),
        new RoleCard("My Hero Academia", "Katsuki Bakugo"),
        new RoleCard("My Hero Academia", "Katsuki Bakugo"),
        new RoleCard("My Hero Academia: 3rd Season", "Katsuki Bakugo"),
        new RoleCard("My Hero Academia", "Katsuki Bakugo"),
        new RoleCard("My Hero Academia", "Katsuki Bakugo"),
        new RoleCard("My Hero Academia", "Katsuki Bakugo"),
        new RoleCard("My Hero Academia", "Katsuki Bakugo"));
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
//        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents(){
        JLabel filmography = new JLabel("Filmography");
        filmography.setAlignmentX(Component.LEFT_ALIGNMENT);
        filmography.setForeground(Color.white);
        actorInfoPnl.setLayout(new BoxLayout(actorInfoPnl,BoxLayout.Y_AXIS));
        actorInfoPnl.setBackground(Color.gray);
        actorInfoPnl.setPreferredSize(new Dimension((int)(1280/2.5), 720));
        actorInfoPnl.setMaximumSize(new Dimension((int)(1280/2.5), 720));
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
//        rolesPnl.setPreferredSize(new Dimension(1280, 720));
//        rolesPnl.setMaximumSize(new Dimension(1280, 720));
        rolesPnl.setAlignmentY(Component.CENTER_ALIGNMENT);
//        rolesPnl.setMinimumSize(new Dimension(1280/2,720));
        rolesPnl.setAlignmentX(Component.LEFT_ALIGNMENT);
        rolesPnl.setBorder(new EmptyBorder(10,10,10,10));

        rolesPnl.add(filmography);
        rolesPnl.add(Box.createVerticalStrut(10));
        for(RoleCard r: rolesList){
            RoleCard role = r;
            role.setAlignmentX(Component.LEFT_ALIGNMENT);
            rolesPnl.add(role);
            rolesPnl.add(Box.createVerticalStrut(10));
        }

        rolesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        rolesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        rolesScrollPane.setWheelScrollingEnabled(true);
        rolesScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        rolesScrollPane.setBorder(BorderFactory.createEmptyBorder());
        rolesPnl.setBorder(new EmptyBorder(10,10,10,10));

        add(actorInfoPnl, BorderLayout.WEST);
        add(rolesScrollPane, BorderLayout.CENTER);

    }
}
