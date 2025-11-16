package com.anime.view.customcards;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
//import com.anime.model.Review;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;


public class PlainEpisodeCard extends JPanel {
    //    private Review review;
    private JPanel container = new JPanel();

    public PlainEpisodeCard(String seriesTitle, String episodeTitle, LocalDate watchDate){
        String episodeInfo = seriesTitle + ": \"" +  episodeTitle + "\"";
        JLabel epTitle = new JLabel(episodeInfo);
        JLabel date = new JLabel(String.valueOf(watchDate));

        setOpaque(true);
        setVisible(true);
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(new EmptyBorder(15,15,15,15));
//        setPreferredSize(new Dimension(Integer.MAX_VALUE,80));

//        role.setAlignmentX(Component.LEFT_ALIGNMENT);
//        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.setAlignmentY(Component.TOP_ALIGNMENT);
//        container.setAlignmentX(LEFT_ALIGNMENT);
        container.setOpaque(false);

        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.add(date);
        container.add(Box.createHorizontalStrut(10));
        container.add(epTitle);
        container.add(Box.createHorizontalGlue());

//        add(da);
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

    public PlainEpisodeCard(String seriesTitle, String episodeTitle){
        String episodeInfo = seriesTitle + ": \"" +  episodeTitle + "\"";
        JLabel epTitle = new JLabel(episodeInfo);

        setOpaque(true);
        setVisible(true);
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(new EmptyBorder(15,15,15,15));
//        setPreferredSize(new Dimension(Integer.MAX_VALUE,80));

//        role.setAlignmentX(Component.LEFT_ALIGNMENT);
//        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.setAlignmentY(Component.TOP_ALIGNMENT);
//        container.setAlignmentX(LEFT_ALIGNMENT);
        container.setOpaque(false);

        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.add(Box.createHorizontalStrut(10));
        container.add(epTitle);
        container.add(Box.createHorizontalGlue());

//        add(da);
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
        try { image = ImageIO.read(RoleCard.class.getResource(iresPath)); }
        catch (IOException e) { e.printStackTrace(); }
        return image;
    }
}
