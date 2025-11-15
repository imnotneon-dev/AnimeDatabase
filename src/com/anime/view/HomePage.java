package com.anime.view;

import com.anime.view.customcards.SeriesCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class HomePage extends JPanel{
//    List<String> watchingList = List.of("Series1", "Series2");
//    List<String> favoriteList = List.of("My Hero Academia", "Attack on Titan");
    private List<SeriesCard> catalog = List.of(
            new SeriesCard("Haikyuu"), new SeriesCard("Blue Lock"), new SeriesCard("Battery Oblivion"),
            new SeriesCard("Slam Dunk"), new SeriesCard("Ace of the Diamond"),
            new SeriesCard("Inazuma Eleven"), new SeriesCard("Kuroko no Basket"),
        new SeriesCard("Haikyuu"), new SeriesCard("Blue Lock"), new SeriesCard("Battery Oblivion"),
        new SeriesCard("Slam Dunk"), new SeriesCard("Ace of the Diamond"),
        new SeriesCard("Inazuma Eleven"), new SeriesCard("Kuroko no Basket"),
        new SeriesCard("Haikyuu"), new SeriesCard("Blue Lock"), new SeriesCard("Battery Oblivion"),
        new SeriesCard("Slam Dunk"), new SeriesCard("Ace of the Diamond"),
        new SeriesCard("Inazuma Eleven"), new SeriesCard("Kuroko no Basket"));

    private GridBagLayout gb = new GridBagLayout();
    private JPanel headerPnl = new JPanel();
    private JPanel container = new JPanel();

    public HomePage() {
        init();
    }
    private void init() {
        setOpaque(true);
        setVisible(true);
        setBackground(Color.RED);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10,10,10,10));

        initComponents();

    }

    private void initComponents(){

//        gb.setConstraints(container, new GridBagConstraints());
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        container.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
//        JPanel watchShelf = createShelf("Watching", watchingList);
//        JPanel favoriteShelf = createShelf("Favorite Shows", favoriteList);
        JPanel shelf2 = createCatalog("Catalog", catalog);
//        favoriteShelf.setBackground(Color.ORANGE);
        shelf2.setBackground(Color.GRAY);
//        container.add(watchShelf);
//        container.add(favoriteShelf);
        container.add(shelf2);

        add(container);
    }

    private JPanel createShelf(String title, List<SeriesCard> seriesList){
        JPanel shelfPnl = new JPanel();
        JPanel seriesContentPnl = new JPanel();
        JScrollPane seriesContentScroller = new JScrollPane(seriesContentPnl);
        JLabel seriesTitle = new JLabel(title);

        shelfPnl.setLayout(new BoxLayout(shelfPnl, BoxLayout.Y_AXIS));
        shelfPnl.setAlignmentX(Component.LEFT_ALIGNMENT);

        seriesContentPnl.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
        seriesContentPnl.setAlignmentX(Component.LEFT_ALIGNMENT);

        seriesTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        seriesContentScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        seriesContentScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        seriesContentScroller.setWheelScrollingEnabled(true);
        seriesContentScroller.setAlignmentX(Component.LEFT_ALIGNMENT);

        for (SeriesCard s: seriesList){
//            JButton series = new JButton();
//            series.setText(s);
            s.setAlignmentX(Component.LEFT_ALIGNMENT);
            seriesContentPnl.add(s);
        }
        shelfPnl.add(seriesTitle);
        shelfPnl.add(seriesContentScroller);
        return shelfPnl;
    }

    private JPanel createCatalog(String title, List<SeriesCard> seriesList){
        JLabel seriesTitle = new JLabel(title);
        JPanel shelfPnl = new JPanel();
        JPanel seriesContentPnl = new JPanel();
        JScrollPane seriesContentScroller = new JScrollPane(seriesContentPnl);

        seriesTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        shelfPnl.setLayout(new BoxLayout(shelfPnl, BoxLayout.Y_AXIS));
        shelfPnl.setAlignmentX(Component.LEFT_ALIGNMENT);

        seriesContentPnl.setLayout(new GridLayout(0,7,15,15));
        seriesContentPnl.setAlignmentX(Component.LEFT_ALIGNMENT);


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
