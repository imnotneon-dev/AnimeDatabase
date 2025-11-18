package com.anime.view;

import com.anime.model.ActorSeries;
import com.anime.model.Episode;
import com.anime.model.Series;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ManageEpisodePanel extends JPanel {
    private JPanel episodeListPnl = new JPanel();

    private JTextField titleField = new JTextField();

    private JTextField releaseDateField = new JTextField();
    private JTextArea synopsisTA = new JTextArea();
    private JTextField runtimeField = new JTextField();
    private JComboBox<Object> seriesTitleCb = new JComboBox<Object>();

    private JButton addBtn = new JButton("Add");
    private JButton updateBtn = new JButton("Update");
    private JButton clearBtn = new JButton("Clear");

    private List<Episode> episodeList = new ArrayList<>();
    private List<Series> seriesList = new ArrayList<>();
    private List<PlainEpisodeCard> episodeCards = new ArrayList<>();


    /*private List<String> episodeList = List.of(
            "Episode 1","Episode 2","Episode 3","Episode 4","Episode 5",
            "Episode 6","Episode 7","Episode 8","Episode 9","Episode 10",
            "Episode 11","Episode 12");
    private List<String> seriesList = List.of(
            "Series 1","Series 2","Series 3","Series 4","Series 5",
            "Series 6","Series 7","Series 8","Series 9","Series 10",
            "Series 11","Series 12");*/

    public ManageEpisodePanel(){
        setLayout(new BorderLayout());
        setBackground(Color.yellow);
        setBorder(new EmptyBorder(10,10,10,10));
        setupEpisodePanel();
        setupRenderers();
    }
    private void setupEpisodePanel(){
//        JPanel episodeListPnl = new JPanel();
        JPanel episodeFormPnl = new JPanel();
        JScrollPane epListScrollPane = new JScrollPane(episodeListPnl);

        episodeListPnl.setLayout(new BoxLayout(episodeListPnl, BoxLayout.Y_AXIS));
        episodeListPnl.setPreferredSize(new Dimension((int)(1280/2), 720));
        episodeListPnl.setMaximumSize(new Dimension((int)(1280/2), 720));
        episodeListPnl.setBorder(new EmptyBorder(10,10,10,10));
        episodeListPnl.setBackground(Color.magenta);
        loadPECards();

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
//        synopsisTA.setPreferredSize(new Dimension(600,200));
//        synopsisTA.setMaximumSize(new Dimension(600,200));

        JScrollPane synopsisScrollPane = new JScrollPane(synopsisTA);
        synopsisScrollPane.setPreferredSize(new Dimension(600, 200));
        synopsisScrollPane.setMaximumSize(new Dimension(600, 200));
        synopsisScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        synopsisScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

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
        episodeFormPnl.add(new JLabel("Enter Synopsis (Max 200 characters)"));
        episodeFormPnl.add(synopsisScrollPane);
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

    private void setupRenderers() {
        seriesTitleCb.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected,
                                                          boolean cellHasFocus) {

                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof Series series) {
                    ((JLabel) component).setText(series.getTitle());
                } else if (value != null) {
                    ((JLabel) component).setText(value.toString());
                } else {
                    ((JLabel) component).setText("Select a Series");
                }
                return component;
            }
        });
    }

    private void loadPECards(){
        episodeListPnl.removeAll();
        episodeCards.clear();
        for(Episode e: episodeList){
            PlainEpisodeCard pec = new PlainEpisodeCard(e.getTitle());
            pec.putClientProperty("episode_id", e.getEpisodeId());
            pec.putClientProperty("series_id", e.getSeriesId());
            episodeListPnl.setAlignmentX(Component.LEFT_ALIGNMENT);
            episodeCards.add(pec);
            episodeListPnl.add(pec);
            episodeListPnl.add(Box.createVerticalStrut(5));
        }

        episodeListPnl.validate();
        episodeListPnl.repaint();
    }

    public JPanel getEpisodeListPnl() {
        return episodeListPnl;
    }

    public void setEpisodeListPnl(JPanel episodeListPnl) {
        this.episodeListPnl = episodeListPnl;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public void setTitleField(JTextField titleField) {
        this.titleField = titleField;
    }

    public JTextField getReleaseDateField() {
        return releaseDateField;
    }

    public void setReleaseDateField(JTextField releaseDateField) {
        this.releaseDateField = releaseDateField;
    }

    public JTextArea getSynopsisTA() {
        return synopsisTA;
    }

    public void setSynopsisTA(JTextArea synopsisTA) {
        this.synopsisTA = synopsisTA;
    }

    public JTextField getRuntimeField() {
        return runtimeField;
    }

    public void setRuntimeField(JTextField runtimeField) {
        this.runtimeField = runtimeField;
    }

    public JComboBox<Object> getSeriesTitleCb() {
        return seriesTitleCb;
    }

    public void setSeriesTitleCb(JComboBox<Object> seriesTitleCb) {
        this.seriesTitleCb = seriesTitleCb;
    }

    public JButton getAddBtn() {
        return addBtn;
    }

    public void setAddBtn(JButton addBtn) {
        this.addBtn = addBtn;
    }

    public JButton getUpdateBtn() {
        return updateBtn;
    }

    public void setUpdateBtn(JButton updateBtn) {
        this.updateBtn = updateBtn;
    }

    public JButton getClearBtn() {
        return clearBtn;
    }

    public void setClearBtn(JButton clearBtn) {
        this.clearBtn = clearBtn;
    }

    public List<Episode> getEpisodeList() {
        return episodeList;
    }

    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList != null ? episodeList : new ArrayList<>();
        loadPECards();
    }

    public List<Series> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<Series> seriesList) {
        this.seriesList = seriesList;
        seriesTitleCb.removeAll();
        for(Series s: this.seriesList){
            seriesTitleCb.addItem(s);
        }
    }

    public List<PlainEpisodeCard> getEpisodeCards() {
        return episodeCards;
    }

    public void setEpisodeCards(List<PlainEpisodeCard> episodeCards) {
        this.episodeCards = episodeCards != null ? episodeCards : new ArrayList<>();
    }
}
