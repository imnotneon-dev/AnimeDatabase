package com.anime.view.customcards;

import com.anime.view.AccountPanel;
import com.anime.view.HomePage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;

public class SeriesCard extends JPanel {
//    private Episode episode;
    private ImageIcon seriesPosterIcon;
    public SeriesCard(String title){
//        episode = ep;
        setOpaque(true);
        setVisible(true);
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(5,5,5,5));
        setPreferredSize(new Dimension(160,220));
        setMaximumSize(new Dimension(160,220));

        JLabel seriesTitle = new JLabel(title);
        JLabel seriesPosterLb = new JLabel();
        String TAKOROLL_LOGO = "/imgs/takoroll_logo.png";
        BufferedImage biSeriesPoster = loadImage(TAKOROLL_LOGO);
        Image scaled = biSeriesPoster.getScaledInstance(160,200,Image.SCALE_SMOOTH);
        seriesPosterIcon = new ImageIcon(scaled);
        seriesPosterLb.setIcon(seriesPosterIcon);

        add(seriesPosterLb);
        add(seriesTitle);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(Color.decode("#F8481C"));
            }

            @Override
            public void mouseReleased(MouseEvent e){
                setBackground(Color.WHITE);
                seriesTitle.setForeground(Color.black);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.DARK_GRAY);
                seriesTitle.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.white);
                seriesTitle.setForeground(Color.black);
            }
        });
    }
    public static BufferedImage loadImage(String iresPath)
    {
        BufferedImage image = null;
        try { image = ImageIO.read(HomePage.class.getResource(iresPath)); }
        catch (IOException e) { e.printStackTrace(); }
        return image;
    }
}
