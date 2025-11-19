package com.anime.view;

import com.anime.model.Actor;
import com.anime.model.ActorSeries;
import com.anime.view.customcards.RoleCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActorPage extends JPanel{
    private Actor actorInfo;
    private JPanel actorInfoPnl = new JPanel();
    private JPanel rolesPnl = new JPanel();
    private JLabel actorPhoto = new JLabel();
    private JLabel nameLb = new JLabel("Name");
    private JLabel genderLb = new JLabel("Gender");
    private JLabel dateOfBirthLb = new JLabel("yyyy-mm-dd");
    private JLabel placeOfBirthLb = new JLabel("Tokyo");
    private JLabel agencyNameLb = new JLabel("Agency");
    private JScrollPane rolesScrollPane = new JScrollPane(rolesPnl);
    private ImageIcon actorPhotoIcon   = new ImageIcon("/resources/imgs/takoroll_logo.png");
    String ACTOR_PHOTO = "/imgs/takoroll_logo.png";
    private List<ActorSeries> rolesList = new ArrayList<>();
    private List<RoleCard> rolesCard = new ArrayList<>();

    /*
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
        new RoleCard("My Hero Academia", "Katsuki Bakugo"));*/
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
        setBackground(Color.decode("#282828"));
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
        filmography.setForeground(Color.WHITE);;

        actorInfoPnl.setLayout(new BoxLayout(actorInfoPnl,BoxLayout.Y_AXIS));
        actorInfoPnl.setBackground(Color.decode("#282828"));
        actorInfo.setForeground(Color.WHITE);
        actorInfoPnl.setPreferredSize(new Dimension((int)(1280/2.5), 720));
        actorInfoPnl.setMaximumSize(new Dimension((int)(1280/2.5), 720));
//        actorInfoPnl.setAlignmentY(Component.CENTER_ALIGNMENT);
//        actorInfoPnl.setMinimumSize(new Dimension(1280/2,720));
        actorInfoPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        // fix this line
        actorInfoPnl.setBorder(new EmptyBorder(10,10,10,10));


        BufferedImage biActorPic = loadImage(ACTOR_PHOTO);
        Image scaled = biActorPic.getScaledInstance(360,500,Image.SCALE_SMOOTH);
        actorPhotoIcon = new ImageIcon(scaled);
        actorPhoto.setIcon(actorPhotoIcon);
        actorPhoto.setBackground(Color.WHITE);
        actorPhoto.setOpaque(true);

        actorPhoto.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLb.setAlignmentX(Component.CENTER_ALIGNMENT);
        genderLb.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateOfBirthLb.setAlignmentX(Component.CENTER_ALIGNMENT);
        placeOfBirthLb.setAlignmentX(Component.CENTER_ALIGNMENT);
        agencyNameLb.setAlignmentX(Component.CENTER_ALIGNMENT);

        actorInfoPnl.add(Box.createHorizontalGlue());
        actorPhoto.setIcon(actorPhotoIcon);
        actorInfoPnl.add(actorInfo);
        actorInfoPnl.add(Box.createVerticalGlue());
        actorInfoPnl.add(actorPhoto);
        actorInfoPnl.add(nameLb);
        actorInfoPnl.add(genderLb);
        actorInfoPnl.add(dateOfBirthLb);
        actorInfoPnl.add(placeOfBirthLb);
        actorInfoPnl.add(agencyNameLb);

        actorInfoPnl.add(Box.createHorizontalGlue());
        actorInfoPnl.add(Box.createVerticalGlue());

        rolesPnl.setLayout(new BoxLayout(rolesPnl, BoxLayout.Y_AXIS));
        rolesPnl.setBackground(Color.decode("#282828"));
//        rolesPnl.setPreferredSize(new Dimension(1280, 720));
//        rolesPnl.setMaximumSize(new Dimension(1280, 720));
        rolesPnl.setAlignmentY(Component.CENTER_ALIGNMENT);
//        rolesPnl.setMinimumSize(new Dimension(1280/2,720));
        rolesPnl.setAlignmentX(Component.LEFT_ALIGNMENT);
        rolesPnl.setBorder(new EmptyBorder(10,10,10,10));

        rolesPnl.add(filmography);
        rolesPnl.add(Box.createVerticalStrut(10));
        loadRolesCards();

        rolesScrollPane.getViewport().setBackground(Color.decode("#282828"));
        rolesScrollPane.setBackground(Color.decode("#282828"));
        rolesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        rolesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        rolesScrollPane.setWheelScrollingEnabled(true);
        rolesScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        rolesScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        rolesScrollPane.setBorder(BorderFactory.createEmptyBorder());
        rolesPnl.setBorder(new EmptyBorder(10,10,10,10));

        add(actorInfoPnl, BorderLayout.WEST);
        add(rolesScrollPane, BorderLayout.CENTER);

        actorInfoPnl.revalidate();
        actorInfoPnl.repaint();
        rolesScrollPane.revalidate();
        rolesScrollPane.repaint();
    }
    public static BufferedImage loadImage(String iresPath)
    {
        BufferedImage image = null;
        try { image = ImageIO.read(AccountPanel.class.getResource(iresPath)); }
        catch (IOException e) { e.printStackTrace(); }
        return image;
    }

    public Actor getActorInfo() { return actorInfo; }

    public void setActorInfo(Actor actorInfo) {
        this.actorInfo = actorInfo;
        loadActorPageLabels();
    }

//    public JLabel getActorPhoto() { return actorPhoto; }
//    public void setActorPhoto(JLabel actorPhoto) { this.actorPhoto = actorPhoto; }
    private void loadActorPageLabels(){
        nameLb.setText(this.actorInfo.getLastName() +
                ", " +
                this.actorInfo.getFirstName());
        nameLb.setForeground(Color.WHITE);
        genderLb.setText(this.actorInfo.getGender());
        genderLb.setForeground(Color.WHITE);
        dateOfBirthLb.setText(String.valueOf(this.actorInfo.getDob()));
        dateOfBirthLb.setForeground(Color.WHITE);
        placeOfBirthLb.setText(this.actorInfo.getPob());
        placeOfBirthLb.setForeground(Color.WHITE);
        agencyNameLb.setText(this.actorInfo.getAgency());
        agencyNameLb.setForeground(Color.WHITE);
        ACTOR_PHOTO = this.actorInfo.getActorPhoto();

        actorInfoPnl.revalidate();
        actorInfoPnl.repaint();

    }
    public void loadRolesCards(){
        rolesPnl.removeAll();
        rolesCard.clear();
        for(ActorSeries r: rolesList){
            RoleCard card = new RoleCard(r.getSeriesId(),r.getCharacterName());
            card.setAlignmentX(Component.LEFT_ALIGNMENT);
            rolesCard.add(card);
            rolesPnl.add(card);
            rolesPnl.add(Box.createVerticalStrut(10));
        }
        rolesPnl.revalidate();
        rolesPnl.repaint();
    }
    public void setActorPhoto(String iresPath){
        this.ACTOR_PHOTO = iresPath;
    }
    public JLabel getNameLb() { return nameLb; }

    public JLabel getDateOfBirthLb() { return dateOfBirthLb; }

    public JLabel getPlaceOfBirthLb() { return placeOfBirthLb; }

    public JLabel getAgencyNameLb() { return agencyNameLb; }

//    public JScrollPane getRolesScrollPane() { return rolesScrollPane; }
//    public void setRolesScrollPane(JScrollPane rolesScrollPane) { this.rolesScrollPane = rolesScrollPane; }

    public ImageIcon getActorPhotoIcon() { return actorPhotoIcon; }
    public void setActorPhotoIcon(ImageIcon actorPhotoIcon) { this.actorPhotoIcon = actorPhotoIcon; }

    public List<ActorSeries> getRolesList() { return rolesList; }

    public void setRolesList(List<ActorSeries> rolesList) {
        this.rolesList = rolesList;
        loadRolesCards();
    }

    public List<RoleCard> getRolesCard(){
        return rolesCard;
    }


}
