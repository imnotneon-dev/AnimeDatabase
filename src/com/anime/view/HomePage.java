package com.anime.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HomePage extends JPanel{
    List<String> watchingList = List.of("Series1", "Series2");
    List<String> favoriteList = List.of("My Hero Academia", "Attack on Titan");
    List<String> catalog = List.of("Haikyuu", "Blue Lock", "Battery Oblivion", "Slam Dunk", "Ace of the Diamond",
            "Inazuma Eleven", "Kuroko no Basket");

    GridBagLayout gb = new GridBagLayout();
    JPanel container = new JPanel();
    public HomePage() {
        init();
    }
    private void init() {
        setOpaque(true);
        setVisible(true);
        setBackground(Color.RED);
        setLayout(gb);

        initComponents();

    }

    private void initComponents(){
        gb.setConstraints(container, new GridBagConstraints());
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        container.setMaximumSize(new Dimension(Integer.MAX_VALUE, container.getPreferredSize().height));
        JPanel watchShelf = createShelf("Watching", watchingList);
        JPanel favoriteShelf = createShelf("Favorite Shows", favoriteList);
        JPanel shelf2 = createCatalog("Catalog", catalog);
        favoriteShelf.setBackground(Color.ORANGE);
        shelf2.setBackground(Color.GRAY);
        container.add(watchShelf);
        container.add(favoriteShelf);
        container.add(shelf2);

        add(container);
    }

    private JPanel createShelf(String title, List<String> seriesList){
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

        for (String s: seriesList){
            JButton series = new JButton();
            series.setText(s);
            series.setAlignmentX(Component.LEFT_ALIGNMENT);
            seriesContentPnl.add(series);
        }
        shelfPnl.add(seriesTitle);
        shelfPnl.add(seriesContentScroller);
        return shelfPnl;
    }

    private JPanel createCatalog(String title, List<String> seriesList){
        JPanel shelfPnl = new JPanel();
        JPanel seriesContentPnl = new JPanel();
        JScrollPane seriesContentScroller = new JScrollPane(seriesContentPnl);
        JLabel seriesTitle = new JLabel(title);

        shelfPnl.setLayout(new BoxLayout(shelfPnl, BoxLayout.Y_AXIS));
        shelfPnl.setAlignmentX(Component.LEFT_ALIGNMENT);

        seriesContentPnl.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
        seriesContentPnl.setAlignmentX(Component.LEFT_ALIGNMENT);

        seriesTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        seriesContentScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        seriesContentScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        seriesContentScroller.setWheelScrollingEnabled(true);
        seriesContentScroller.setAlignmentX(Component.LEFT_ALIGNMENT);

        for (String s: seriesList){
            JButton series = new JButton();
            series.setText(s);
//            series.setActionCommand(ep_id); -> for sql retrieval in controller
            series.setAlignmentX(Component.LEFT_ALIGNMENT);
            seriesContentPnl.add(series);
        }
        shelfPnl.add(seriesTitle);
        shelfPnl.add(seriesContentScroller);
        return shelfPnl;
    }
}
