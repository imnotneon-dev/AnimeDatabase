package com.anime.view;

import com.anime.view.customcards.SeriesCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class HomePage extends JPanel{
    private List<SeriesCard> watchingList = null;
            //List.of(
            //new SeriesCard("Haikyuu"), new SeriesCard("Blue Lock"), new SeriesCard("Battery Oblivion"));
    private List<SeriesCard> favoriteList = List.of(
            new SeriesCard("Haikyuu"), new SeriesCard("Blue Lock"), new SeriesCard("Battery Oblivion"),
            new SeriesCard("Slam Dunk"), new SeriesCard("Ace of the Diamond"),
            new SeriesCard("Inazuma Eleven"),new SeriesCard("Haikyuu"), new SeriesCard("Blue Lock"), new SeriesCard("Battery Oblivion"),
            new SeriesCard("Slam Dunk"), new SeriesCard("Ace of the Diamond"),
            new SeriesCard("Inazuma Eleven"),new SeriesCard("Haikyuu"), new SeriesCard("Blue Lock"), new SeriesCard("Battery Oblivion"),
            new SeriesCard("Slam Dunk"), new SeriesCard("Ace of the Diamond"),
            new SeriesCard("Inazuma Eleven"),new SeriesCard("Haikyuu"), new SeriesCard("Blue Lock"), new SeriesCard("Battery Oblivion"),
            new SeriesCard("Slam Dunk"), new SeriesCard("Ace of the Diamond"),
            new SeriesCard("Inazuma Eleven"));
    private List<SeriesCard> catalog = List.of(
            new SeriesCard("Haikyuu"), new SeriesCard("Blue Lock"), new SeriesCard("Battery Oblivion"),
            new SeriesCard("Slam Dunk"), new SeriesCard("Ace of the Diamond"),
            new SeriesCard("Inazuma Eleven"), new SeriesCard("Kuroko no Basket"),
        new SeriesCard("Haikyuu"), new SeriesCard("Blue Lock"), new SeriesCard("Battery Oblivion"),
        new SeriesCard("Slam Dunk"), new SeriesCard("Ace of the Diamond"),
        new SeriesCard("Inazuma Eleven"), new SeriesCard("Kuroko no Basket"),
        new SeriesCard("Haikyuu"), new SeriesCard("Blue Lock"), new SeriesCard("Battery Oblivion"),
        new SeriesCard("Slam Dunk"), new SeriesCard("Ace of the Diamond"),
        new SeriesCard("Inazuma Eleven"), new SeriesCard("Kuroko no Basket"),
            new SeriesCard("Haikyuu"), new SeriesCard("Blue Lock"), new SeriesCard("Battery Oblivion"),
            new SeriesCard("Slam Dunk"), new SeriesCard("Ace of the Diamond"),
            new SeriesCard("Inazuma Eleven"),
            new SeriesCard("Haikyuu"), new SeriesCard("Blue Lock"), new SeriesCard("Battery Oblivion"),
            new SeriesCard("Slam Dunk"), new SeriesCard("Ace of the Diamond"),
            new SeriesCard("Inazuma Eleven"),
            new SeriesCard("Haikyuu"), new SeriesCard("Blue Lock"), new SeriesCard("Battery Oblivion"),
            new SeriesCard("Slam Dunk"), new SeriesCard("Ace of the Diamond"),
            new SeriesCard("Inazuma Eleven"),
            new SeriesCard("Haikyuu"), new SeriesCard("Blue Lock"), new SeriesCard("Battery Oblivion"),
            new SeriesCard("Slam Dunk"), new SeriesCard("Ace of the Diamond"),
            new SeriesCard("Inazuma Eleven"),
            new SeriesCard("Haikyuu"), new SeriesCard("Blue Lock"), new SeriesCard("Battery Oblivion"),
            new SeriesCard("Slam Dunk"), new SeriesCard("Ace of the Diamond"),
            new SeriesCard("Inazuma Eleven"),
            new SeriesCard("Haikyuu"), new SeriesCard("Blue Lock"), new SeriesCard("Battery Oblivion"),
            new SeriesCard("Slam Dunk"), new SeriesCard("Ace of the Diamond"),
            new SeriesCard("Inazuma Eleven"),
            new SeriesCard("Haikyuu"), new SeriesCard("Blue Lock"), new SeriesCard("Battery Oblivion"),
            new SeriesCard("Slam Dunk"), new SeriesCard("Ace of the Diamond"),
            new SeriesCard("Inazuma Eleven"));

    private GridBagLayout gb = new GridBagLayout();
    private JPanel container = new JPanel();
//    private JPanel catalogWrapperPanel = new JPanel();
    private JScrollPane homePageScrollPane = new JScrollPane(container);

    public HomePage() {
        init();
    }
    private void init() {
        setOpaque(true);
        setVisible(true);
        setBackground(Color.RED);
//        setLayout(new BorderLayout());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        setBorder(new EmptyBorder(10,10,10,10));

        initComponents();

    }

    private void initComponents(){
        JPanel watchShelf = createShelf("Watching",
                "Not watching anything? Check out the catalog!", watchingList);
        JPanel favoriteShelf = createShelf("Favorite Shows",
                "Add your favorite shows by clicking the heart on a series",favoriteList);
        watchShelf.setBackground(Color.MAGENTA);
        favoriteShelf.setBackground(Color.ORANGE);

//        gb.setConstraints(container, new GridBagConstraints());
        container.setBackground(Color.black);
        container.setBorder(new EmptyBorder(20,20,20,20));
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
//        container.setAlignmentY(Component.CENTER_ALIGNMENT);
//        container.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        container.add(Box.createVerticalGlue());
        container.add(watchShelf);
        container.add(favoriteShelf);
        container.add(Box.createVerticalGlue());
//        container.add(shelf2);

//        homePageScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        homePageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        homePageScrollPane.getVerticalScrollBar().setUnitIncrement(10);
//        homePageScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10,Integer.MAX_VALUE));
//        homePageScrollPane.getVerticalScrollBar().setVisible(false);
//        homePageScrollPane.setWheelScrollingEnabled(true);
//        homePageScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
//        homePageScrollPane.setBorder(new EmptyBorder(20,20,20,20));

        add(container);
//        add(homePageScrollPane, BorderLayout.CENTER);
    }

    private JPanel createShelf(String title, String msg, List<SeriesCard> seriesList){
        JLabel seriesTitle = new JLabel(title);
        JLabel message = new JLabel(msg);
        JPanel shelfPnl = new JPanel();
        JPanel seriesContentPnl = new JPanel();
        JScrollPane seriesContentScroller = new JScrollPane(seriesContentPnl);

        seriesTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        shelfPnl.setLayout(new BoxLayout(shelfPnl, BoxLayout.Y_AXIS));
        shelfPnl.setAlignmentX(Component.LEFT_ALIGNMENT);

        seriesContentPnl.setLayout(new FlowLayout(FlowLayout.LEFT,15,10));
        seriesContentPnl.setAlignmentX(Component.LEFT_ALIGNMENT);

        seriesContentScroller.setPreferredSize(new Dimension(Integer.MAX_VALUE, 250));
        seriesContentScroller.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        seriesContentScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        seriesContentScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        seriesContentScroller.setWheelScrollingEnabled(true);
        seriesContentScroller.setAlignmentX(Component.LEFT_ALIGNMENT);
        seriesContentScroller.getVerticalScrollBar().setUnitIncrement(15);
        seriesContentScroller.getVerticalScrollBar().setPreferredSize(new Dimension(3,Integer.MAX_VALUE));

        shelfPnl.setBorder(new EmptyBorder(10,10,15,10));
        if(seriesList !=null) {
            for (SeriesCard s : seriesList) {
//            JButton series = new JButton();
//            series.setText(s);
                s.setAlignmentX(Component.LEFT_ALIGNMENT);
                seriesContentPnl.add(s);
            }
        } else {
            seriesContentPnl.add(message);
        }
//        seriesContentPnl.setMaximumSize(new Dimension(Integer.MAX_VALUE, seriesContentPnl.getPreferredSize().height));
        shelfPnl.add(seriesTitle);
        shelfPnl.add(seriesContentScroller);
        return shelfPnl;
    }

    private JPanel createCatalog(String title, List<SeriesCard> seriesList){
        JLabel seriesTitle = new JLabel(title);
        JPanel shelfPnl = new JPanel();
        JPanel seriesContentPnl = new JPanel();
        JPanel catalogWrapperPanel = new JPanel();
        JScrollPane seriesContentScroller = new JScrollPane(catalogWrapperPanel);

        seriesTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        shelfPnl.setLayout(new BoxLayout(shelfPnl, BoxLayout.Y_AXIS));
        shelfPnl.setAlignmentX(Component.LEFT_ALIGNMENT);

        seriesContentPnl.setLayout(new GridLayout(0,7,15,15));
        seriesContentPnl.setAlignmentX(Component.LEFT_ALIGNMENT);

        catalogWrapperPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        catalogWrapperPanel.setBackground(Color.CYAN);
        catalogWrapperPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        catalogWrapperPanel.add(seriesContentPnl);

        seriesContentScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        seriesContentScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        seriesContentScroller.setWheelScrollingEnabled(true);
        seriesContentScroller.setAlignmentX(Component.LEFT_ALIGNMENT);
        seriesContentScroller.getVerticalScrollBar().setUnitIncrement(10);
        seriesContentScroller.getVerticalScrollBar().setPreferredSize(new Dimension(3,Integer.MAX_VALUE));


        shelfPnl.setBorder(new EmptyBorder(10,10,15,10));

        for (SeriesCard s: seriesList){
            SeriesCard series = s;
//            series.setText(s);
            series.setAlignmentX(Component.LEFT_ALIGNMENT);
            seriesContentPnl.add(series);
        }
        shelfPnl.add(seriesTitle);
        shelfPnl.add(seriesContentScroller);
        return shelfPnl;
    }
}
