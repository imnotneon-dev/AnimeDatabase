package com.anime.view;

import com.anime.model.Series;
import com.anime.model.TopGenreOfTheWeek;
import com.anime.model.dao.SeriesDAO;
import com.anime.model.dao.TopGenreOfTheWeekDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TopGenreOfTheWeekPanel extends JPanel{
    private JPanel historyPnl = new JPanel();
    private LocalDate dateToday = LocalDate.now();
    private LocalDate date1WeekAgo = dateToday.minusWeeks(1);
    private List<JLabel> allSeriesLb = new ArrayList<>();
    private List<Series> seriesListUnderTheGenre = new ArrayList<>();
    private List<TopGenreOfTheWeek> top5GenreOfTheWeekList = new ArrayList<>();

    List<Series> genre1List = new ArrayList<Series>();
    List<Series> genre2List = new ArrayList<Series>();
    List<Series> genre3List = new ArrayList<Series>();
    List<Series> genre4List = new ArrayList<Series>();
    List<Series> genre5List = new ArrayList<Series>();
    private String heading = "TOP GENRES FROM " + dateToday + " TO " + date1WeekAgo;
    private JLabel top5GenreLb = new JLabel(heading);
    private TopGenreOfTheWeekDAO tgDAO = new TopGenreOfTheWeekDAO();

    public TopGenreOfTheWeekPanel() {
        init();
    }
    private void init() {
        setOpaque(true);
        setVisible(true);
        setBackground(Color.ORANGE);
//        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents(){
//        String heading = "TOP GENRES FROM " + dateToday + " TO " + date1WeekAgo;
//        JLabel top5GenreLb = new JLabel(heading);
        JPanel container = new JPanel();
        JScrollPane scrollPane = new JScrollPane(container);
        JPanel upperPnl = new JPanel();
        JPanel lowerPnl = new JPanel();
        JPanel genre1Pnl = new JPanel();
        JPanel genre2Pnl = new JPanel();
        JPanel genre3Pnl = new JPanel();
        JPanel genre4Pnl = new JPanel();
        JPanel genre5Pnl = new JPanel();

        loadLists();
        if(top5GenreOfTheWeekList.size()>0)
            loadSeriesUnderGenreLabels(genre1Pnl, top5GenreOfTheWeekList.get(0).getGenre(), genre1List);
        if(top5GenreOfTheWeekList.size()>1)
            loadSeriesUnderGenreLabels(genre2Pnl, top5GenreOfTheWeekList.get(1).getGenre(), genre2List);
        if(top5GenreOfTheWeekList.size()>2)
            loadSeriesUnderGenreLabels(genre3Pnl, top5GenreOfTheWeekList.get(2).getGenre(), genre3List);
        if(top5GenreOfTheWeekList.size()>3)
            loadSeriesUnderGenreLabels(genre4Pnl, top5GenreOfTheWeekList.get(3).getGenre(), genre4List);
        if(top5GenreOfTheWeekList.size()>4)
            loadSeriesUnderGenreLabels(genre5Pnl, top5GenreOfTheWeekList.get(4).getGenre(), genre5List);

        top5GenreLb.setAlignmentX(Component.CENTER_ALIGNMENT);
//        top5GenreLb.setAlignmentY(Component.CENTER_ALIGNMENT);
        // height 300
        // width 300

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        upperPnl.setLayout(new BoxLayout(upperPnl, BoxLayout.X_AXIS));
        upperPnl.setBackground(Color.black);
        upperPnl.setPreferredSize(new Dimension(1280, 400));
        upperPnl.setMaximumSize(new Dimension(1280, 400));
        upperPnl.setAlignmentY(Component.CENTER_ALIGNMENT);
//        upperPnl.setMinimumSize(new Dimension(1280/2,720));
        upperPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        upperPnl.setBorder(new EmptyBorder(10,10,10,10));

        lowerPnl.setLayout(new BoxLayout(lowerPnl, BoxLayout.X_AXIS));
        lowerPnl.setBackground(Color.black);
        lowerPnl.setPreferredSize(new Dimension(1280, 400));
        lowerPnl.setMaximumSize(new Dimension(1280, 400));
        lowerPnl.setAlignmentY(Component.CENTER_ALIGNMENT);
//        lowerPnl.setMinimumSize(new Dimension(1280/2,720));
        lowerPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        lowerPnl.setBorder(new EmptyBorder(10,10,10,10));

        genre1Pnl.setLayout(new BoxLayout(genre1Pnl, BoxLayout.Y_AXIS));
        genre1Pnl.setBackground(Color.white);
        genre1Pnl.setPreferredSize(new Dimension(300, 300));
        genre1Pnl.setMaximumSize(new Dimension(300, 300));
        genre1Pnl.setAlignmentY(Component.CENTER_ALIGNMENT);
//        genre1Pnl.setMinimumSize(new Dimension(1280/2,720));
        genre1Pnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        genre1Pnl.setBorder(new EmptyBorder(10,10,10,10));

        genre2Pnl.setLayout(new BoxLayout(genre2Pnl, BoxLayout.Y_AXIS));
        genre2Pnl.setBackground(Color.white);
        genre2Pnl.setPreferredSize(new Dimension(300, 300));
        genre2Pnl.setMaximumSize(new Dimension(300, 300));
        genre2Pnl.setAlignmentY(Component.CENTER_ALIGNMENT);
//        genre2Pnl.setMinimumSize(new Dimension(1280/2,720));
        genre2Pnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        genre2Pnl.setBorder(new EmptyBorder(10,10,10,10));

        genre3Pnl.setLayout(new BoxLayout(genre3Pnl, BoxLayout.Y_AXIS));
        genre3Pnl.setBackground(Color.white);
        genre3Pnl.setPreferredSize(new Dimension(300, 300));
        genre3Pnl.setMaximumSize(new Dimension(300, 300));
        genre3Pnl.setAlignmentY(Component.CENTER_ALIGNMENT);
//        genre3Pnl.setMinimumSize(new Dimension(1280/2,720));
        genre3Pnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        genre3Pnl.setBorder(new EmptyBorder(10,10,10,10));

        genre4Pnl.setLayout(new BoxLayout(genre4Pnl, BoxLayout.Y_AXIS));
        genre4Pnl.setBackground(Color.white);
        genre4Pnl.setPreferredSize(new Dimension(300, 300));
        genre4Pnl.setMaximumSize(new Dimension(300, 300));
        genre4Pnl.setAlignmentY(Component.CENTER_ALIGNMENT);
//        genre4Pnl.setMinimumSize(new Dimension(1280/2,720));
        genre4Pnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        genre4Pnl.setBorder(new EmptyBorder(10,10,10,10));

        genre5Pnl.setLayout(new BoxLayout(genre5Pnl, BoxLayout.Y_AXIS));
        genre5Pnl.setBackground(Color.white);
        genre5Pnl.setPreferredSize(new Dimension(300, 300));
        genre5Pnl.setMaximumSize(new Dimension(300, 300));
        genre5Pnl.setAlignmentY(Component.CENTER_ALIGNMENT);
//        genre5Pnl.setMinimumSize(new Dimension(1280/2,720));
        genre5Pnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        genre5Pnl.setBorder(new EmptyBorder(10,10,10,10));

        upperPnl.add(genre1Pnl);
        upperPnl.add(Box.createHorizontalStrut(10));
        upperPnl.add(genre2Pnl);
        upperPnl.add(Box.createHorizontalStrut(10));
        upperPnl.add(genre3Pnl);
        upperPnl.add(Box.createHorizontalStrut(10));
        lowerPnl.add(genre4Pnl);
        upperPnl.add(Box.createHorizontalStrut(10));
        lowerPnl.add(genre5Pnl);
        upperPnl.add(Box.createHorizontalStrut(10));
        container.add(upperPnl);
        upperPnl.add(Box.createHorizontalStrut(10));
//        container.add(Box.createVerticalStrut(10));
        container.add(lowerPnl);
//        loadSeriesUnderGenreLabels(genre1Pnl,top5GenreOfTheWeekList.get(0).getGenre(),genre1List);
//        loadSeriesUnderGenreLabels(genre2Pnl,top5GenreOfTheWeekList.get(1).getGenre(),genre2List);
//        loadSeriesUnderGenreLabels(genre3Pnl,top5GenreOfTheWeekList.get(2).getGenre(),genre3List);
//        loadSeriesUnderGenreLabels(genre4Pnl,top5GenreOfTheWeekList.get(3).getGenre(),genre4List);
//        loadSeriesUnderGenreLabels(genre5Pnl,top5GenreOfTheWeekList.get(4).getGenre(),genre5List);

        /*scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());*/
//        historyPnl.setBorder(new EmptyBorder(10,10,10,10));

        add(container, BorderLayout.CENTER);
    }
    private void loadLists(){
        try {
            top5GenreOfTheWeekList = tgDAO.getTop5GenreSeriesOfTheWeek(date1WeekAgo, dateToday);
            if(top5GenreOfTheWeekList==null || top5GenreOfTheWeekList.isEmpty()){
                top5GenreOfTheWeekList = new ArrayList<>();

                genre1List = new ArrayList<Series>();
                genre2List = new ArrayList<Series>();
                genre3List = new ArrayList<Series>();
                genre4List = new ArrayList<Series>();
                genre5List = new ArrayList<Series>();

                if(top5GenreLb!=null) top5GenreLb.setText("No genre data available.");
                return;
            }
//            if(top5GenreOfTheWeekList.size()>0)
                genre1List = tgDAO.getSeriesByGenre(String.valueOf(top5GenreOfTheWeekList.get(0).getGenre()));
//            if(top5GenreOfTheWeekList.size()>1)
                genre2List = tgDAO.getSeriesByGenre(String.valueOf(top5GenreOfTheWeekList.get(1).getGenre()));
//            if(top5GenreOfTheWeekList.size()>2)
                genre3List = tgDAO.getSeriesByGenre(String.valueOf(top5GenreOfTheWeekList.get(2).getGenre()));
//            if(top5GenreOfTheWeekList.size()>3)
                genre4List = tgDAO.getSeriesByGenre(String.valueOf(top5GenreOfTheWeekList.get(3).getGenre()));
//            if(top5GenreOfTheWeekList.size()>4)
                genre5List = tgDAO.getSeriesByGenre(String.valueOf(top5GenreOfTheWeekList.get(4).getGenre()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadSeriesUnderGenreLabels(JPanel panel, String genreName, List<Series> list) {
        panel.removeAll();
        list.clear();
        JLabel genreTitle = new JLabel(genreName);
        genreTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
//        genreTitle.setAlignmentY(Component.CENTER_ALIGNMENT);
        if(list!=null){
            for(Series e: list){

                String series_title = e.getTitle();
                JLabel seriesTitleLb = new JLabel(series_title);

                seriesTitleLb.putClientProperty("series_id", e.getSeriesId());

                seriesTitleLb.setAlignmentX(Component.CENTER_ALIGNMENT);
                allSeriesLb.add(seriesTitleLb);
                panel.add(seriesTitleLb);
            }
        }
        else {
            panel.add(new JLabel("No series yet..."));
        }
        panel.revalidate();
        panel.repaint();
    }

    private List<JLabel> getAllSeriesLb(){
        return allSeriesLb;
    }
}
