package com.anime.view.customcards;

import com.anime.view.AccountPanel;
import com.anime.view.HomePage;
import com.anime.model.Series;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class SeriesCard extends JPanel {
//    private Episode episode;
    private String SERIES_POSTER = "/imgs/takoroll_logo.png";
    private ImageIcon seriesPosterIcon;
    private final int series_id;

    public SeriesCard(int sid, String title, String SERIES_POSTER){
//        episode = ep;
        this.SERIES_POSTER = SERIES_POSTER;
        this.series_id = sid;

        setOpaque(true);
        setVisible(true);
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(5,5,5,5));
        setPreferredSize(new Dimension(160,230));
        setMaximumSize(new Dimension(160,230));

        JLabel seriesTitle = new JLabel(title);
        JLabel seriesPosterLb = new JLabel();

        BufferedImage biSeriesPoster = loadImage(SERIES_POSTER);
        Image scaled = biSeriesPoster.getScaledInstance(160,200,Image.SCALE_SMOOTH);
        seriesPosterIcon = new ImageIcon(scaled);
        seriesPosterLb.setIcon(seriesPosterIcon);

        add(seriesPosterLb);
        add(seriesTitle);

        addMouseListener(new MouseAdapter() {
//            @Override
//            public vo`id mousePressed(MouseEvent e) {
//                setBackground(Color.decode("#F8481C"));
//            }

//            @Override
//            public void mouseReleased(MouseEvent e){
//                setBackground(Color.WHITE);
//                seriesTitle.setForeground(Color.black);
//            }

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
        try { image = ImageIO.read(SeriesCard.class.getResource(iresPath)); }
        catch (IOException e) { e.printStackTrace(); }
        return image;
    }

    public ImageIcon getSeriesPosterIcon() {
        return seriesPosterIcon;
    }

    public void setSeriesPosterIcon(ImageIcon seriesPosterIcon) {
        this.seriesPosterIcon = seriesPosterIcon;
    }

    public void setSERIES_POSTERLink(String iresPath){
        this.SERIES_POSTER = iresPath;
    }
    public int getSeriesID() {
        return series_id;
    }
}
