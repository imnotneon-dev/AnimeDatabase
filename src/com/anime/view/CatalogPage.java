package com.anime.view;

import com.anime.view.customcards.SeriesCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class CatalogPage extends JPanel{
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
//    private JScrollPane homePageScrollPane = new JScrollPane(container);

    public CatalogPage() {
        init();
    }
    private void init() {
        setOpaque(true);
        setVisible(true);
        setBackground(Color.RED);
        setLayout(new BorderLayout());
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        setBorder(new EmptyBorder(10,10,10,10));

        initComponents();

    }

    private void initComponents(){

//        gb.setConstraints(container, new GridBagConstraints());
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        container.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        JPanel catalogShelf = createCatalog("Catalog", catalog);
        catalogShelf.setBackground(Color.BLACK);
        container.add(catalogShelf);
//        add(container);
        add(container, BorderLayout.CENTER);
    }

    private JPanel createCatalog(String title, List<SeriesCard> seriesList){
        JLabel seriesTitle = new JLabel(title);
        JPanel shelfPnl = new JPanel();
        JPanel seriesContentPnl = new JPanel();
        // JPanel catalogWrapperPanel = new JPanel();
        JScrollPane seriesContentScroller = new JScrollPane(seriesContentPnl);

        seriesTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        shelfPnl.setLayout(new BoxLayout(shelfPnl, BoxLayout.Y_AXIS));
        shelfPnl.setAlignmentX(Component.LEFT_ALIGNMENT);

        seriesContentPnl.setLayout(new GridLayout(0,7,15,15));
        seriesContentPnl.setAlignmentX(Component.LEFT_ALIGNMENT);

        /*
        catalogWrapperPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        catalogWrapperPanel.setBackground(Color.CYAN);
        catalogWrapperPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        catalogWrapperPanel.add(seriesContentPnl);
        */

        seriesContentScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        seriesContentScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        seriesContentScroller.setWheelScrollingEnabled(true);
        seriesContentScroller.setAlignmentX(Component.LEFT_ALIGNMENT);
        seriesContentScroller.getVerticalScrollBar().setUnitIncrement(10);
        seriesContentScroller.getVerticalScrollBar().setPreferredSize(new Dimension(3,Integer.MAX_VALUE));


        shelfPnl.setBorder(new EmptyBorder(20,20,20,20));

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
