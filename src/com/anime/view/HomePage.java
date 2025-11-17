package com.anime.view;

import com.anime.view.customcards.SeriesCard;
import com.anime.model.Series;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends JPanel{
    private List<SeriesCard> watchingListCard = new ArrayList<>();
    private List<SeriesCard> favoriteListCard = new ArrayList<>();

    private List<Series> watchingList = new ArrayList<>();
    private List<Series> favoriteList = new ArrayList<>();
            //List.of(
            //new SeriesCard("Haikyuu"), new SeriesCard("Blue Lock"), new SeriesCard("Battery Oblivion"));
    /*
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
*/
    private JPanel container = new JPanel();
    private JPanel watchContainer = new JPanel();
    private JPanel favoriteContainer = new JPanel();

    private final String WATCH_MSG = "Not watching anything? Check out the catalog!";
    private final String FAVE_MSG = "Add your favorite shows by clicking the heart on a series";

//    private JPanel catalogWrapperPanel = new JPanel();

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
                WATCH_MSG, watchContainer, watchingListCard );
          JPanel favoriteShelf = createShelf("Favorite Shows",
                FAVE_MSG,favoriteContainer, favoriteListCard);
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
/*

        homePageScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        homePageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        homePageScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        homePageScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10,Integer.MAX_VALUE));
        homePageScrollPane.getVerticalScrollBar().setVisible(false);
        homePageScrollPane.setWheelScrollingEnabled(true);
        homePageScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        homePageScrollPane.setBorder(new EmptyBorder(20,20,20,20));
*/

        add(container);
//        add(homePageScrollPane, BorderLayout.CENTER);
    }

    private JPanel createShelf(String title, String msg, JPanel container, List<SeriesCard> cards){
        JLabel seriesTitle = new JLabel(title);
        JPanel shelfPnl = new JPanel();
        JPanel seriesContentPnl = container;
//        JLabel message = new JLabel(msg);
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
        loadShelf(watchingList, cards, seriesContentPnl,msg);
        loadShelf(favoriteList, cards, seriesContentPnl,msg);
//        seriesContentPnl.setMaximumSize(new Dimension(Integer.MAX_VALUE, seriesContentPnl.getPreferredSize().height));
        shelfPnl.add(seriesTitle);
        shelfPnl.add(seriesContentScroller);
        return shelfPnl;
    }

    private void loadShelf(List<Series> sList, List<SeriesCard> sCards, JPanel container, String msg){
        container.removeAll();
        sCards.clear();
        JLabel message = new JLabel(msg);
        if(sList !=null) {
            for (Series s : sList) {
                SeriesCard sc = new SeriesCard(s.getSeriesId(),s.getTitle());
                sc.setAlignmentX(Component.LEFT_ALIGNMENT);
                sCards.add(sc);
                container.add(sc);
            }
        } else {
            container.add(message);
        }
        container.revalidate();
        container.repaint();
    }
    public JPanel getWatchContentPanel() {
        return watchContainer;
    }
    public JPanel getFavoriteContentPanel() {
        return favoriteContainer;
    }
    public List<SeriesCard> getWatchingListCard() {
        return watchingListCard;
    }

    public List<SeriesCard> getFavoriteListCard() {
        return favoriteListCard;
    }

    public List<Series> getWatchingList() {
        return watchingList;
    }

    public void setWatchingList(List<Series> seriesList) {
        this.watchingList = seriesList;
        loadShelf(this.watchingList, this.watchingListCard, this.watchContainer, WATCH_MSG);
    }

    public List<Series> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(List<Series> favoriteList) {
        this.favoriteList = favoriteList;
        loadShelf(this.favoriteList, this.favoriteListCard, this.favoriteContainer, FAVE_MSG);
    }
}

