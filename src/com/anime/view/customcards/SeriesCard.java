package com.anime.view.customcards;

import com.anime.view.AccountPanel;

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
        setPreferredSize(new Dimension(160,200));
        setMaximumSize(new Dimension(160,200));

        JLabel seriesTitle = new JLabel(title);
        JLabel seriesPosterLb = new JLabel();
        String TAKOROLL_LOGO = "/imgs/takoroll_logo.png";
        BufferedImage biSeriesPoster = loadImage(TAKOROLL_LOGO);
        Image scaled = biSeriesPoster.getScaledInstance(80,80,Image.SCALE_SMOOTH);
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
