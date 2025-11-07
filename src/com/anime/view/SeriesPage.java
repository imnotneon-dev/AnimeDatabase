package com.anime.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SeriesPage extends JPanel{
    private JButton button = new JButton("SeriesPage");
    private JPanel seriesInfoPnl = new JPanel();
    private JPanel episodesPnl = new JPanel();
    private JLabel titleLb = new JLabel("My Hero Aca");
    private JLabel genreLb = new JLabel("Action");
    private JLabel releaseYearLb = new JLabel("2025-09-08");
    private JLabel epCountLb = new JLabel("5");
    private JLabel statusLb = new JLabel("Ongoing");
//    private List<Episode> episodeList;
    private List<String> episodeList = List.of("1","2","3","4","5");
    private GridBagLayout gb = new GridBagLayout();
    /*public SeriesPage (Series series, List<Episodes> epList){
        // take the info from the series passed then use that to display stuff
        this.seriesTitle = series.title;
        this.seriesGenre = series.genre;
        this.releaseYear = series.rel_year;
        this.episodeCount = series.ep_count;
        this.seriesStatus = series.status;
        this.episodeList = epList;
    }*/

    public SeriesPage() {
        init();
    }
    private void init() {
        setOpaque(true);
        setVisible(true);
        setBackground(Color.BLUE);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        initComponents();
    }

    private void initComponents(){
        seriesInfoPnl.setLayout(gb);
        seriesInfoPnl.setBackground(Color.yellow);
        seriesInfoPnl.setPreferredSize(new Dimension(1280,450));
        seriesInfoPnl.setMaximumSize(new Dimension(1280,600));
//        seriesInfoPnl.setPreferredSize(new Dimension(420,720));
//        seriesInfoPnl.setMaximumSize(new Dimension(500, 720));
        seriesInfoPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        seriesInfoPnl.add(titleLb);
        seriesInfoPnl.add(genreLb);
        seriesInfoPnl.add(releaseYearLb);
        seriesInfoPnl.add(epCountLb);
        seriesInfoPnl.add(statusLb);


        episodesPnl.setLayout(new FlowLayout(FlowLayout.LEFT));
        episodesPnl.setBackground(Color.black);
        seriesInfoPnl.setPreferredSize(new Dimension(1280,270));
        seriesInfoPnl.setMaximumSize(new Dimension(1280,300));
//        episodesPnl.setPreferredSize(new Dimension(100,100));
        episodesPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        for(String s: episodeList){
            JButton series = new JButton();
            series.setText(s);
            series.setAlignmentX(Component.LEFT_ALIGNMENT);
            episodesPnl.add(series);
        }


        add(seriesInfoPnl);
        add(episodesPnl);
        
    }
    public JButton getButton(){
        return button;
    }
}
