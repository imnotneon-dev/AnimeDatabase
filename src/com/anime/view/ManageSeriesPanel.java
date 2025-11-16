package com.anime.view;

import com.anime.view.customcards.PlainEpisodeCard;
import com.anime.view.customcards.PlainSeriesCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ManageSeriesPanel extends JPanel {

    private JTextField titleField = new JTextField();
    private JTextField genreField = new JTextField();
    private JTextField releaseYearField = new JTextField();
    private JTextField epCountField = new JTextField();
    private JComboBox<String> statusCb = new JComboBox<>();

    private JButton addBtn = new JButton("Add");
    private JButton updateBtn = new JButton("Update");

    private List<String> seriesList = List.of(
            "Series 1","Series 2","Series 3","Series 4","Series 5",
            "Series 6","Series 7","Series 8","Series 9","Series 10",
            "Series 11","Series 12");

    public ManageSeriesPanel(){
        setLayout(new BorderLayout());
        setBackground(Color.yellow);
        setBorder(new EmptyBorder(10,10,10,10));
        setupSeriesPanel();
    }
    private void setupSeriesPanel(){
        JPanel seriesListPnl = new JPanel();
        JPanel seriesFormPnl = new JPanel();
        JScrollPane seriesListScrollPanel = new JScrollPane(seriesListPnl);

        seriesListPnl.setLayout(new BoxLayout(seriesListPnl, BoxLayout.Y_AXIS));
        seriesListPnl.setPreferredSize(new Dimension((int)(1280/2), 720));
        seriesListPnl.setMaximumSize(new Dimension((int)(1280/2), 720));
        seriesListPnl.setBorder(new EmptyBorder(10,10,10,10));
        seriesListPnl.setBackground(Color.magenta);
        for(String s: seriesList){
            PlainSeriesCard psc = new PlainSeriesCard(s);
            seriesListPnl.setAlignmentX(Component.LEFT_ALIGNMENT);
            seriesListPnl.add(psc);
            seriesListPnl.add(Box.createVerticalStrut(5));
        }

        seriesListScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        seriesListScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        seriesListScrollPanel.setWheelScrollingEnabled(true);
        seriesListScrollPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        seriesListScrollPanel.getVerticalScrollBar().setUnitIncrement(10);
        seriesListScrollPanel.getVerticalScrollBar().setPreferredSize(new Dimension(3,Integer.MAX_VALUE));
        add(seriesListScrollPanel, BorderLayout.WEST);

        titleField.setMaximumSize(new Dimension(350,30));
        titleField.setAlignmentX(LEFT_ALIGNMENT);
        genreField.setMaximumSize(new Dimension(350,30));
        genreField.setAlignmentX(LEFT_ALIGNMENT);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        releaseYearField = new JFormattedTextField(dateFormat);
        releaseYearField.setMaximumSize(new Dimension(350,30));
        releaseYearField.setAlignmentX(LEFT_ALIGNMENT);

        NumberFormat integerFormat = NumberFormat.getIntegerInstance(Locale.ROOT);
        NumberFormatter formatter = new NumberFormatter(integerFormat);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false);

        epCountField = new JFormattedTextField(formatter);
        epCountField.setMaximumSize(new Dimension(350,30));
        epCountField.setAlignmentX(LEFT_ALIGNMENT);

        statusCb = new JComboBox<>(new String[]{ "On-Going", "Complete", "Archived" });
        statusCb.setMaximumSize(new Dimension(350,30));
        statusCb.setAlignmentX(Component.LEFT_ALIGNMENT);

        addBtn.setPreferredSize(new Dimension(200,35));
        addBtn.setMaximumSize(new Dimension(200,35));
        addBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
//        addBtn.setForeground(Color.WHITE);

        updateBtn.setPreferredSize(new Dimension(200,35));
        updateBtn.setMaximumSize(new Dimension(200,35));
        updateBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
//        updateBtn.setForeground(Color.WHITE);
        updateBtn.setEnabled(false);

        seriesFormPnl.add(Box.createVerticalStrut(10));
        seriesFormPnl.add(new JLabel("Enter Series Title"));
        seriesFormPnl.add(titleField);
        seriesFormPnl.add(Box.createVerticalStrut(10));
        seriesFormPnl.add(new JLabel("Enter Genre"));
        seriesFormPnl.add(genreField);
        seriesFormPnl.add(Box.createVerticalStrut(10));
        seriesFormPnl.add(new JLabel("Enter Number of Episodes"));
        seriesFormPnl.add(epCountField);
        seriesFormPnl.add(Box.createVerticalStrut(10));
        seriesFormPnl.add(new JLabel("Enter Release Date (YYYY-MM-DD)"));
        seriesFormPnl.add(releaseYearField);
        seriesFormPnl.add(Box.createVerticalStrut(10));
        seriesFormPnl.add(new JLabel("Set Status"));
        seriesFormPnl.add(statusCb);
        seriesFormPnl.add(Box.createVerticalStrut(10));
//        seriesFormPnl.add(Box.createVerticalGlue());
        seriesFormPnl.add(addBtn);
        seriesFormPnl.add(Box.createVerticalStrut(10));
//        seriesFormPnl.add(Box.createVerticalGlue());
        seriesFormPnl.add(updateBtn);
        seriesFormPnl.add(Box.createVerticalGlue());
        seriesFormPnl.setLayout(new BoxLayout(seriesFormPnl,BoxLayout.Y_AXIS));
        seriesFormPnl.setPreferredSize(new Dimension((int)(1280/2), 720));
        seriesFormPnl.setMaximumSize(new Dimension((int)(1280/2), 720));
        seriesFormPnl.setBorder(new EmptyBorder(10,10,10,10));
        seriesFormPnl.setBackground(Color.orange);

        add(seriesFormPnl,BorderLayout.CENTER);

    }
}
