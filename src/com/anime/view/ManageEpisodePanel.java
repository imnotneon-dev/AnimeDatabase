package com.anime.view;

import com.anime.view.customcards.PlainEpisodeCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ManageEpisodePanel extends JPanel {

    private JTextField titleField = new JTextField();

    private JTextField releaseDateField = new JTextField();
    private JTextArea synopsisTA = new JTextArea();
    private JTextField runtimeField = new JTextField();
    private JComboBox<Object> seriesTitleCb = new JComboBox<Object>();

    private JButton addBtn = new JButton("Add");
    private JButton updateBtn = new JButton("Update");
    private JButton clearBtn = new JButton("Clear");



    private List<String> episodeList = List.of(
            "Episode 1","Episode 2","Episode 3","Episode 4","Episode 5",
            "Episode 6","Episode 7","Episode 8","Episode 9","Episode 10",
            "Episode 11","Episode 12");
    private List<String> seriesList = List.of(
            "Series 1","Series 2","Series 3","Series 4","Series 5",
            "Series 6","Series 7","Series 8","Series 9","Series 10",
            "Series 11","Series 12");

    public ManageEpisodePanel(){
        setLayout(new BorderLayout());
        setBackground(Color.yellow);
        setBorder(new EmptyBorder(10,10,10,10));
        setupEpisodePanel();
    }
    private void setupEpisodePanel(){
        JPanel episodeListPnl = new JPanel();
        JPanel episodeFormPnl = new JPanel();
        JScrollPane epListScrollPane = new JScrollPane(episodeListPnl);

        episodeListPnl.setLayout(new BoxLayout(episodeListPnl, BoxLayout.Y_AXIS));
        episodeListPnl.setPreferredSize(new Dimension((int)(1280/2), 720));
        episodeListPnl.setMaximumSize(new Dimension((int)(1280/2), 720));
        episodeListPnl.setBorder(new EmptyBorder(10,10,10,10));
        episodeListPnl.setBackground(Color.magenta);
        for(String e: episodeList){
            PlainEpisodeCard pec = new PlainEpisodeCard("Seriesname", e);
            episodeListPnl.setAlignmentX(Component.LEFT_ALIGNMENT);
            episodeListPnl.add(pec);
            episodeListPnl.add(Box.createVerticalStrut(5));
        }

        epListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        epListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        epListScrollPane.setWheelScrollingEnabled(true);
        epListScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        epListScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        epListScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(3,Integer.MAX_VALUE));
        add(epListScrollPane, BorderLayout.WEST);

        titleField.setMaximumSize(new Dimension(350,30));
        titleField.setAlignmentX(LEFT_ALIGNMENT);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        releaseDateField = new JFormattedTextField(dateFormat);
        releaseDateField.setMaximumSize(new Dimension(350,30));
        releaseDateField.setAlignmentX(LEFT_ALIGNMENT);

        NumberFormat integerFormat = NumberFormat.getIntegerInstance(Locale.ROOT);
        NumberFormatter formatter = new NumberFormatter(integerFormat);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false);
        runtimeField = new JFormattedTextField(formatter);
        runtimeField.setMaximumSize(new Dimension(350,30));
        runtimeField.setAlignmentX(LEFT_ALIGNMENT);

        synopsisTA.setLineWrap(true);
        synopsisTA.setWrapStyleWord(true);
        synopsisTA.setAlignmentX(Component.LEFT_ALIGNMENT);
//        synopsisTA.setBorder(new MatteBorder(1,1,1,1,Color.BLACK));
        synopsisTA.setBackground(Color.WHITE);
        synopsisTA.setPreferredSize(new Dimension(600,200));
        synopsisTA.setMaximumSize(new Dimension(600,200));

        seriesTitleCb = new JComboBox<Object>(seriesList.toArray());
        seriesTitleCb.setMaximumSize(new Dimension(350,30));
        seriesTitleCb.setAlignmentX(Component.LEFT_ALIGNMENT);

        addBtn.setPreferredSize(new Dimension(200,35));
        addBtn.setMaximumSize(new Dimension(200,35));
        addBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
//        addBtn.setForeground(Color.WHITE);

        updateBtn.setPreferredSize(new Dimension(200,35));
        updateBtn.setMaximumSize(new Dimension(200,35));
        updateBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
//        updateBtn.setForeground(Color.WHITE);
        updateBtn.setEnabled(false);

        episodeFormPnl.add(Box.createVerticalStrut(10));
        episodeFormPnl.add(new JLabel("Enter Episode Title"));
        episodeFormPnl.add(titleField);
        episodeFormPnl.add(Box.createVerticalStrut(10));
        episodeFormPnl.add(new JLabel("Select Series"));
        episodeFormPnl.add(seriesTitleCb);
        episodeFormPnl.add(Box.createVerticalStrut(10));
        episodeFormPnl.add(new JLabel("Enter Synopsis"));
        episodeFormPnl.add(synopsisTA);
        episodeFormPnl.add(Box.createVerticalStrut(10));
        episodeFormPnl.add(new JLabel("Enter Release Date (YYYY-MM-DD)"));
        episodeFormPnl.add(releaseDateField);
        episodeFormPnl.add(Box.createVerticalStrut(10));
        episodeFormPnl.add(new JLabel("Enter Runtime (in mins)"));
        episodeFormPnl.add(runtimeField);
        episodeFormPnl.add(new JLabel("Enter Number of Episodes"));
        episodeFormPnl.add(Box.createVerticalStrut(10));
//        episodeFormPnl.add(Box.createVerticalGlue());
        episodeFormPnl.add(addBtn);
        episodeFormPnl.add(Box.createVerticalStrut(10));
//        episodeFormPnl.add(Box.createVerticalGlue());
        episodeFormPnl.add(updateBtn);
        episodeFormPnl.add(Box.createVerticalStrut(10));
        episodeFormPnl.add(clearBtn);
        episodeFormPnl.add(Box.createVerticalGlue());
        episodeFormPnl.setLayout(new BoxLayout(episodeFormPnl,BoxLayout.Y_AXIS));
        episodeFormPnl.setPreferredSize(new Dimension((int)(1280/2), 720));
        episodeFormPnl.setMaximumSize(new Dimension((int)(1280/2), 720));
        episodeFormPnl.setBorder(new EmptyBorder(10,10,10,10));
        episodeFormPnl.setBackground(Color.orange);
//        episodeFormPnl.add(addEpisodePnl);

        add(episodeFormPnl,BorderLayout.CENTER);

    }
}
