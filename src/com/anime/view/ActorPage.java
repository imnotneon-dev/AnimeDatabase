package com.anime.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    private ImageIcon actorPhotoIcon   = new ImageIcon("/resources/imgs/takoroll_logo.png");
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
        JLabel actorInfo = new JLabel("Actor's Information");
        JLabel filmography = new JLabel("Filmography");

        actorInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        actorInfo.setAlignmentY(Component.TOP_ALIGNMENT);
        filmography.setAlignmentX(Component.LEFT_ALIGNMENT);
        filmography.setForeground(Color.white);

        actorInfoPnl.setLayout(new BoxLayout(actorInfoPnl,BoxLayout.Y_AXIS));
        actorInfoPnl.setBackground(Color.gray);
        actorInfoPnl.setPreferredSize(new Dimension((int)(1280/2.5), 720));
        actorInfoPnl.setMaximumSize(new Dimension((int)(1280/2.5), 720));
//        actorInfoPnl.setAlignmentY(Component.CENTER_ALIGNMENT);
//        actorInfoPnl.setMinimumSize(new Dimension(1280/2,720));
        actorInfoPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        // fix this line
        actorInfoPnl.setBorder(new EmptyBorder(10,10,10,10));

        String TAKOROLL_LOGO = "/imgs/takoroll_logo.png";
        BufferedImage biActorPic = loadImage(TAKOROLL_LOGO);
        Image scaled = biActorPic.getScaledInstance(360,500,Image.SCALE_SMOOTH);
        actorPhotoIcon = new ImageIcon(scaled);
        actorPhoto.setIcon(actorPhotoIcon);
        actorPhoto.setBackground(Color.WHITE);
        actorPhoto.setOpaque(true);

        actorPhoto.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLb.setAlignmentX(Component.CENTER_ALIGNMENT);
        ageLb.setAlignmentX(Component.CENTER_ALIGNMENT);
        placeOfBirthLb.setAlignmentX(Component.CENTER_ALIGNMENT);
        agencyNameLb.setAlignmentX(Component.CENTER_ALIGNMENT);

        actorInfoPnl.add(Box.createHorizontalGlue());
        actorPhoto.setIcon(actorPhotoIcon);
        actorInfoPnl.add(actorInfo);
        actorInfoPnl.add(Box.createVerticalGlue());
        actorInfoPnl.add(actorPhoto);
        actorInfoPnl.add(nameLb);
        actorInfoPnl.add(ageLb);
        actorInfoPnl.add(placeOfBirthLb);
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
        rolesScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        rolesScrollPane.setBorder(BorderFactory.createEmptyBorder());
        rolesPnl.setBorder(new EmptyBorder(10,10,10,10));

        add(actorInfoPnl, BorderLayout.WEST);
        add(rolesScrollPane, BorderLayout.CENTER);
    }
    public static BufferedImage loadImage(String iresPath)
    {
        BufferedImage image = null;
        try { image = ImageIO.read(AccountPanel.class.getResource(iresPath)); }
        catch (IOException e) { e.printStackTrace(); }
        return image;
    }
}
