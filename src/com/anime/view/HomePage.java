package com.anime.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HomePage extends JPanel{
    List<String> watchingList = List.of("Series1", "Series2");
    List<String> sportsList = List.of("Haikyuu", "Blue Lock", "Battery Oblivion", "Slam Dunk");
    List<String> actionList = List.of("My Hero Academia", "Attack on Titan");

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
        JPanel shelf1 = createShelf("Action", actionList);
        JPanel shelf2 = createShelf("Sports", sportsList);
        shelf1.setBackground(Color.ORANGE);
        shelf2.setBackground(Color.GRAY);
        container.add(watchShelf);
        container.add(shelf1);
        container.add(shelf2);

        add(container);
    }

    private JPanel createShelf(String title, List<String> seriesList){
        JPanel shelfPnl = new JPanel();
        JPanel seriesContentPnl = new JPanel();

        shelfPnl.setLayout(new BoxLayout(shelfPnl, BoxLayout.Y_AXIS));
        shelfPnl.setAlignmentX(Component.LEFT_ALIGNMENT);

        seriesContentPnl.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
        seriesContentPnl.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel seriesTitle = new JLabel(title);
        seriesTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        for (String s: seriesList){
            JButton series = new JButton();
            series.setText(s);
            series.setAlignmentX(Component.LEFT_ALIGNMENT);
            seriesContentPnl.add(series);
        }

        shelfPnl.add(seriesTitle);
        shelfPnl.add(seriesContentPnl);
        return shelfPnl;
    }
}
