package com.anime.view;

import java.awt.*;
import java.util.List;
import javax.swing.*;

public class SeriesPage extends JPanel{
    private JButton button = new JButton("SeriesPage");
    private JPanel seriesInfoPnl = new JPanel();
    private JPanel episodesPnl = new JPanel();
    private JPanel contentPnl = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(contentPnl);
    private JButton faveBtn = new JButton("fave", new ImageIcon());
    private JLabel titleLb = new JLabel("My Hero Aca");
    private JLabel genreLb = new JLabel("Action");
    private JLabel releaseYearLb = new JLabel("2025-09-08");
    private JLabel epCountLb = new JLabel("5");
    private JLabel statusLb = new JLabel("Ongoing");
//    private List<Episode> episodeList;
    private List<String> episodeList = List.of(
            "Episode 1","Episode 2","Episode 3","Episode 4","Episode 5",
        "Episode 6","Episode 7","Episode 8","Episode 9","Episode 10",
        "Episode 11","Episode 12");
    private GridBagLayout gb = new GridBagLayout();
    /*public SeriesPage (Series series, List<Episodes> epList){
        // take the info from the series passed then use that to display stuff
        titleLb.setText();
        genreLb.setText();
        releaseYearLb.setText();
        epCountLb.setText();
        statusLb.setText();
    }*/

    public SeriesPage() {
        init();
    }
    private void init() {
        setOpaque(true);
        setVisible(true);
        setBackground(Color.BLUE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        initComponents();
    }

    private void initComponents(){
        seriesInfoPnl.setLayout(gb);
        seriesInfoPnl.setBackground(Color.yellow);
        seriesInfoPnl.setPreferredSize(new Dimension(1280,700));
//        seriesInfoPnl.setMaximumSize(new Dimension(1280,600));
//        seriesInfoPnl.setPreferredSize(new Dimension(420,720));
//        seriesInfoPnl.setMaximumSize(new Dimension(500, 720));
        seriesInfoPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        seriesInfoPnl.add(titleLb);
        seriesInfoPnl.add(genreLb);
        seriesInfoPnl.add(releaseYearLb);
        seriesInfoPnl.add(epCountLb);
        seriesInfoPnl.add(statusLb);
        seriesInfoPnl.add(faveBtn);

        episodesPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        episodesPnl.setBackground(Color.black);
        seriesInfoPnl.setPreferredSize(new Dimension(1280,720));
//        seriesInfoPnl.setMaximumSize(new Dimension(1280,300));
//        episodesPnl.setPreferredSize(new Dimension(100,100));
        episodesPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        for(String s: episodeList){
            JButton series = new JButton();
            series.setText(s);
            series.setAlignmentX(Component.LEFT_ALIGNMENT);
            episodesPnl.add(series);
        }

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        contentPnl.setLayout(new BoxLayout(contentPnl,BoxLayout.Y_AXIS));
        contentPnl.add(seriesInfoPnl);
        contentPnl.add(episodesPnl);
        add(scrollPane);
        
    }
    public JButton getButton(){
        return button;
    }
}
