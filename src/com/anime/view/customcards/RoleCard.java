package com.anime.view.customcards;

import com.anime.view.AccountPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
//import com.anime.model.Review;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class RoleCard extends JPanel {
    //    private Review review;
    private JPanel container = new JPanel();
    private ImageIcon seriesPosterIcon   = new ImageIcon("/resources/imgs/takoroll_logo.png");

    public RoleCard(String seriesTitle, String roleName){
        JLabel role = new JLabel(roleName);
        JLabel title = new JLabel(seriesTitle);
        JLabel seriesPosterLb = new JLabel();
        String TAKOROLL_LOGO = "/imgs/takoroll_logo.png";
        BufferedImage biSeriesPoster = loadImage(TAKOROLL_LOGO);
        Image scaled = biSeriesPoster.getScaledInstance(80,80,Image.SCALE_SMOOTH);
        seriesPosterIcon = new ImageIcon(scaled);
        seriesPosterLb.setIcon(seriesPosterIcon);

        setOpaque(true);
        setVisible(true);
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(new EmptyBorder(5,5,5,5));
//        setPreferredSize(new Dimension(Integer.MAX_VALUE,80));

//        role.setAlignmentX(Component.LEFT_ALIGNMENT);
//        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        seriesPosterLb.setAlignmentY(Component.TOP_ALIGNMENT);
        container.setAlignmentY(Component.TOP_ALIGNMENT);
//        container.setAlignmentX(LEFT_ALIGNMENT);

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(role);
        container.add(Box.createVerticalStrut(10));
        container.add(title);

        add(seriesPosterLb);
        add(container);
        setMaximumSize(new Dimension(Integer.MAX_VALUE,getPreferredSize().height));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(Color.decode("#F8481C"));
            }

            @Override
            public void mouseReleased(MouseEvent e){
                setBackground(Color.WHITE);
            }
        });

    }

    public static BufferedImage loadImage(String iresPath)
    {
        BufferedImage image = null;
        try { image = ImageIO.read(AccountPanel.class.getResource(iresPath)); }
        catch (IOException e) { e.printStackTrace(); }
        return image;
    }
}
