package com.anime.view;

import com.anime.model.dao.TopSeriesOfTheWeek;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ReportPage extends JPanel{
    private TopGenreOfTheWeekPanel tgPnl = new TopGenreOfTheWeekPanel();
//    private TopSeriesOfTheWeekPanel tsPnl = new TopSeriesOfTheWeekPanel();
    private EpisodeReviewPanel reviewLogPnl = new EpisodeReviewPanel();

    private JPanel panelContainer = new JPanel();


    public ReportPage(){
        init();
    }

    private void init() {
        setOpaque(true);
        setVisible(true);
        setBackground(Color.decode("#212121"));
        setLayout(new BorderLayout());

        initComponents();
    }
    private void initComponents(){
        initPanels();
        revalidate();
        repaint();

    }
    private void initPanels(){
        panelContainer.setLayout(new BoxLayout(panelContainer,BoxLayout.Y_AXIS));
        panelContainer.setAlignmentY(Component.CENTER_ALIGNMENT);
        panelContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContainer.setBackground(Color.red);

        JScrollPane scrollPane = new JScrollPane(panelContainer);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        panelContainer.add(tgPnl);
//        panelContainer.add(tsPnl);
        panelContainer.add(reviewLogPnl);

        add(scrollPane, BorderLayout.CENTER);
    }


}
