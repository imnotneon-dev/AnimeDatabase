package com.anime.view;

import com.anime.model.Series;
import com.anime.view.customcards.PlainSeriesCard;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

public class ManageSeriesPanel extends JPanel {
    private JPanel seriesListPnl = new JPanel();

    private JTextField titleField = new JTextField();
    private JTextField genreField = new JTextField();
    private JTextField releaseYearField = new JTextField();
    private JTextField epCountField = new JTextField();
    private JTextField filepathToPosterField = new JTextField();
    private JComboBox<String> statusCb = new JComboBox<>();

    private JButton addBtn = new JButton("Add");
    private JButton updateBtn = new JButton("Update");
    private JButton clearBtn = new JButton("Clear");

    private List<Series> seriesList = new ArrayList<>();
    private List<PlainSeriesCard> seriesCards = new ArrayList<>();

    /*private List<String> seriesList = List.of(
            "Series 1","Series 2","Series 3","Series 4","Series 5",
            "Series 6","Series 7","Series 8","Series 9","Series 10",
            "Series 11","Series 12");*/

    public ManageSeriesPanel(){
        setLayout(new BorderLayout());
        setBackground(Color.decode("#282828"));
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
        seriesListPnl.setBackground(Color.decode("#282828"));
        loadPSCards();

        seriesListScrollPanel.getViewport().setBackground(Color.decode("#282828"));
        seriesListScrollPanel.setBackground(Color.decode("#282828"));
        seriesListScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        seriesListScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        seriesListScrollPanel.setWheelScrollingEnabled(true);
        seriesListScrollPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        seriesListScrollPanel.getVerticalScrollBar().setUnitIncrement(10);
        seriesListScrollPanel.getVerticalScrollBar().setPreferredSize(new Dimension(3,Integer.MAX_VALUE));
        add(seriesListScrollPanel, BorderLayout.WEST);

        titleField.setMaximumSize(new Dimension(350,30));
        titleField.setAlignmentX(LEFT_ALIGNMENT);
        titleField.setForeground(Color.WHITE);
        genreField.setMaximumSize(new Dimension(350,30));
        genreField.setAlignmentX(LEFT_ALIGNMENT);
        genreField.setForeground(Color.WHITE);

        NumberFormat integerFormat = NumberFormat.getIntegerInstance(Locale.ROOT);
        NumberFormatter formatter = new NumberFormatter(integerFormat);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false);
        releaseYearField = new JFormattedTextField(formatter);
        releaseYearField.setMaximumSize(new Dimension(350,30));
        releaseYearField.setAlignmentX(LEFT_ALIGNMENT);
        releaseYearField.setForeground(Color.WHITE);

        epCountField = new JFormattedTextField(formatter);
        epCountField.setMaximumSize(new Dimension(350,30));
        epCountField.setAlignmentX(LEFT_ALIGNMENT);
        epCountField.setForeground(Color.WHITE);

        filepathToPosterField.setMaximumSize(new Dimension(350,30));
        filepathToPosterField.setAlignmentX(LEFT_ALIGNMENT);
        filepathToPosterField.setForeground(Color.WHITE);

        statusCb = new JComboBox<>(new String[]{ "On-Going", "Complete", "Archived" });
        statusCb.setMaximumSize(new Dimension(350,30));
        statusCb.setAlignmentX(Component.LEFT_ALIGNMENT);
        statusCb.setForeground(Color.WHITE);

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
        seriesFormPnl.add(new JLabel("Filepath to poster"));
        seriesFormPnl.add(filepathToPosterField);
        seriesFormPnl.add(Box.createVerticalStrut(10));
//        seriesFormPnl.add(Box.createVerticalGlue());
        seriesFormPnl.add(addBtn);
        seriesFormPnl.add(Box.createVerticalStrut(10));
//        seriesFormPnl.add(Box.createVerticalGlue());
        seriesFormPnl.add(updateBtn);
        seriesFormPnl.add(Box.createVerticalStrut(10));
        seriesFormPnl.add(clearBtn);
        seriesFormPnl.add(Box.createVerticalGlue());
        seriesFormPnl.setLayout(new BoxLayout(seriesFormPnl,BoxLayout.Y_AXIS));
        seriesFormPnl.setPreferredSize(new Dimension((int)(1280/2), 720));
        seriesFormPnl.setMaximumSize(new Dimension((int)(1280/2), 720));
        seriesFormPnl.setBorder(new EmptyBorder(10,10,10,10));
        seriesFormPnl.setBackground(Color.decode("#282828"));

        add(seriesFormPnl,BorderLayout.CENTER);

    }

    private void loadPSCards(){
        seriesListPnl.removeAll();
        seriesCards.clear();
        for(Series s: seriesList){
            PlainSeriesCard psc = new PlainSeriesCard(s.getTitle());
            psc.putClientProperty("series_id",s.getSeriesId());
            seriesListPnl.setAlignmentX(Component.LEFT_ALIGNMENT);
            seriesCards.add(psc);
            seriesListPnl.add(psc);
            seriesListPnl.add(Box.createVerticalStrut(5));
        }

        seriesListPnl.revalidate();
        seriesListPnl.repaint();
    }

    public JPanel getSeriesListPnl() {
        return seriesListPnl;
    }

    public void setSeriesListPnl(JPanel seriesListPnl) {
        this.seriesListPnl = seriesListPnl;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public void setTitleField(JTextField titleField) {
        this.titleField = titleField;
    }

    public JTextField getGenreField() {
        return genreField;
    }

    public void setGenreField(JTextField genreField) {
        this.genreField = genreField;
    }

    public JTextField getReleaseYearField() {
        return releaseYearField;
    }

    public void setReleaseYearField(JTextField releaseYearField) {
        this.releaseYearField = releaseYearField;
    }

    public JTextField getEpCountField() {
        return epCountField;
    }

    public void setEpCountField(JTextField epCountField) {
        this.epCountField = epCountField;
    }

    public JTextField getFilepathToPosterField() {
        return filepathToPosterField;
    }

    public void setFilepathToPosterField(JTextField filepathToPosterField) {
        this.filepathToPosterField = filepathToPosterField;
    }

    public JComboBox<String> getStatusCb() {
        return statusCb;
    }

    public void setStatusCb(JComboBox<String> statusCb) {
        this.statusCb = statusCb;
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

    public List<Series> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<Series> seriesList) {
        this.seriesList = seriesList;
        loadPSCards();
    }

    public List<PlainSeriesCard> getSeriesCards() {
        return seriesCards;
    }

    public void setSeriesCards(List<PlainSeriesCard> seriesCards) {
        this.seriesCards = seriesCards;
    }

}
