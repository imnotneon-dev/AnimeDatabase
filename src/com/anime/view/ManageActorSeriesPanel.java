package com.anime.view;

import com.anime.model.Actor;
import com.anime.model.ActorSeries;
import com.anime.model.Series;
import com.anime.view.customcards.PlainActorCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ManageActorSeriesPanel extends JPanel {
    private JPanel actorListPnl = new JPanel();

    private JTextField actorNameField = new JTextField();
    private JTextField actorRole = new JTextField();

    private JComboBox<Object> seriesTitleCb = new JComboBox<Object>();
    private JComboBox<Object> actorSeriesCb = new JComboBox<Object>();
    private List<PlainActorCard> actorCards = new ArrayList<>();

    private JButton addBtn = new JButton("Add");
    private JButton deleteBtn = new JButton("Delete");
    private JButton clearBtn = new JButton("Clear");


    private List<Actor> actorList = new ArrayList<>();
    private List<Series> allSeriesList = new ArrayList<>();
    private List<ActorSeries> actorSeriesList = new ArrayList<>();

    public ManageActorSeriesPanel(){
        setLayout(new BorderLayout());
        setBackground(Color.yellow);
        setBorder(new EmptyBorder(10,10,10,10));
        setupEpisodePanel();
    }
    private void setupEpisodePanel(){
        JPanel actorListPnl = new JPanel();
        JPanel actorFormPnl = new JPanel();
        JScrollPane actorListScrollPanel = new JScrollPane(actorListPnl);

        actorListPnl.setLayout(new BoxLayout(actorListPnl, BoxLayout.Y_AXIS));
        actorListPnl.setPreferredSize(new Dimension((int)(1280/2), 720));
        actorListPnl.setMaximumSize(new Dimension((int)(1280/2), 720));
        actorListPnl.setBorder(new EmptyBorder(10,10,10,10));
        actorListPnl.setBackground(Color.magenta);
        loadPACards();

        actorListScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        actorListScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        actorListScrollPanel.setWheelScrollingEnabled(true);
        actorListScrollPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        actorListScrollPanel.getVerticalScrollBar().setUnitIncrement(10);
        actorListScrollPanel.getVerticalScrollBar().setPreferredSize(new Dimension(3,Integer.MAX_VALUE));
        add(actorListScrollPanel, BorderLayout.WEST);

        seriesTitleCb = new JComboBox<Object>(allSeriesList.toArray());
        seriesTitleCb.setMaximumSize(new Dimension(350,30));
        seriesTitleCb.setAlignmentX(Component.LEFT_ALIGNMENT);

        actorSeriesCb = new JComboBox<Object>(allSeriesList.toArray());
        actorSeriesCb.setMaximumSize(new Dimension(350,30));
        actorSeriesCb.setAlignmentX(Component.LEFT_ALIGNMENT);
        actorSeriesCb.setEditable(false);

        addBtn.setPreferredSize(new Dimension(200,35));
        addBtn.setMaximumSize(new Dimension(200,35));
        addBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
//        addBtn.setForeground(Color.WHITE);

        deleteBtn.setPreferredSize(new Dimension(200,35));
        deleteBtn.setMaximumSize(new Dimension(200,35));
        deleteBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
//        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setEnabled(false);

        actorNameField.setEditable(false);

        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(new JLabel("Actor Name"));
        actorFormPnl.add(actorNameField);
        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(new JLabel("Select Series"));
        actorFormPnl.add(seriesTitleCb);
        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(new JLabel("Existing Series For Deleting Actor"));
        actorFormPnl.add(actorSeriesCb);
        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(new JLabel("Enter Role"));
        actorFormPnl.add(actorRole);
        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(addBtn);
        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(deleteBtn);
        actorFormPnl.add(Box.createVerticalGlue());

        actorFormPnl.setLayout(new BoxLayout(actorFormPnl,BoxLayout.Y_AXIS));
        actorFormPnl.setPreferredSize(new Dimension((int)(1280/2), 720));
        actorFormPnl.setMaximumSize(new Dimension((int)(1280/2), 720));
        actorFormPnl.setBorder(new EmptyBorder(10,10,10,10));
        actorFormPnl.setBackground(Color.orange);
//        actorFormPnl.add(addEpisodePnl);

        add(actorFormPnl,BorderLayout.CENTER);

    }

    private void loadPACards(){
        actorListPnl.removeAll();
        actorCards.clear();
        for(Actor e: actorList){
            PlainActorCard pac = new PlainActorCard(e.getLastName() + ", " + e.getFirstName());
            pac.putClientProperty("actor_id", e.getId());
            actorListPnl.setAlignmentX(Component.LEFT_ALIGNMENT);
            actorCards.add(pac);
            actorListPnl.add(pac);
            actorListPnl.add(Box.createVerticalStrut(5));
        }

        actorListPnl.revalidate();
        actorListPnl.repaint();
    }

    public JTextField getActorNameField() {
        return actorNameField;
    }

    public void setActorNameField(JTextField actorNameField) {
        this.actorNameField = actorNameField;
    }

    public JTextField getActorRole() {
        return actorRole;
    }

    public void setActorRole(JTextField actorRole) {
        this.actorRole = actorRole;
    }

    public JComboBox<Object> getSeriesTitleCb() {
        return seriesTitleCb;
    }

    public void setSeriesTitleCb(JComboBox<Object> seriesTitleCb) {
        this.seriesTitleCb = seriesTitleCb;
    }

    public List<PlainActorCard> getActorSeriesCards(){
        return actorCards;
    }
    public JButton getAddBtn() {
        return addBtn;
    }

    public JButton getDeleteBtn() {
        return deleteBtn;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
        loadPACards();
    }

    public List<Series> getSeriesList() {
        return allSeriesList;
    }

    public void setAllSeriesList(List<Series> allSeriesList) {
        this.allSeriesList = allSeriesList;
    }

    public List<ActorSeries> getActorSeriesList() {
        return actorSeriesList;
    }

    public void setActorSeriesList(List<ActorSeries> actorSeriesList) {
        this.actorSeriesList = actorSeriesList;
    }
}
