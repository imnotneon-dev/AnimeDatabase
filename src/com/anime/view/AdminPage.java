package com.anime.view;

import com.anime.view.customcards.PlainEpisodeCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class AdminPage extends JPanel implements ItemListener {
    private JPanel addSeriesPnl = new JPanel();
    private JPanel addEpisodePnl = new JPanel();
    private JPanel addActorPnl = new JPanel();
    private JPanel panelContainer = new JPanel();

    private JComboBox<String> cb;
    private CardLayout cl = new CardLayout();



    private List<String> episodeList = List.of(
            "Episode 1","Episode 2","Episode 3","Episode 4","Episode 5",
            "Episode 6","Episode 7","Episode 8","Episode 9","Episode 10",
            "Episode 11","Episode 12");

    public AdminPage(){
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
        setupEpisodePanel();
        revalidate();
        repaint();

    }
    private void initPanels(){
        final String ADD_SERIES = "Add Series";
        final String ADD_EP = "Add Episode";
        final String ADD_ACTOR = "Add Actor";
        JLabel adminTitleLb = new JLabel("Administrator Record Management");
        adminTitleLb.setAlignmentX(CENTER_ALIGNMENT);

        JPanel adminTitlePnl = new JPanel();
        adminTitlePnl.setLayout(new BoxLayout(adminTitlePnl,BoxLayout.X_AXIS));
        adminTitlePnl.setBackground(Color.gray);
        adminTitlePnl.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));
        adminTitlePnl.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        adminTitlePnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminTitlePnl.setBorder(new EmptyBorder(10,10,10,10));

        String comboBoxItems[] = { ADD_SERIES, ADD_EP, ADD_ACTOR };
        cb = new JComboBox<>(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);

        adminTitlePnl.add(adminTitleLb);
        adminTitlePnl.add(cb);
        add(adminTitlePnl,BorderLayout.NORTH);

        panelContainer.setLayout(cl);
//        panelContainer.setBorder(new EmptyBorder(10,10,10,10));
        panelContainer.setAlignmentY(Component.CENTER_ALIGNMENT);
        panelContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContainer.setBackground(Color.red);

        addSeriesPnl.setLayout(new BorderLayout());
        addSeriesPnl.setBackground(Color.black);
        addSeriesPnl.setBorder(new EmptyBorder(10,10,10,10));

        addEpisodePnl.setLayout(new BorderLayout());
        addEpisodePnl.setBackground(Color.yellow);
        addEpisodePnl.setBorder(new EmptyBorder(10,10,10,10));

        addActorPnl.setLayout(new BorderLayout());
        addActorPnl.setBackground(Color.cyan);
        addActorPnl.setBorder(new EmptyBorder(10,10,10,10));

        panelContainer.add(addSeriesPnl,ADD_SERIES);
        panelContainer.add(addEpisodePnl,ADD_EP);
        panelContainer.add(addActorPnl,ADD_ACTOR);

        add(panelContainer, BorderLayout.CENTER);
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
        addEpisodePnl.add(epListScrollPane, BorderLayout.WEST);

        episodeFormPnl.setLayout(new BoxLayout(episodeFormPnl,BoxLayout.Y_AXIS));
        episodeFormPnl.setPreferredSize(new Dimension((int)(1280/2), 720));
        episodeFormPnl.setMaximumSize(new Dimension((int)(1280/2), 720));
        episodeFormPnl.setBorder(new EmptyBorder(10,10,10,10));
        episodeFormPnl.setBackground(Color.orange);


        addEpisodePnl.add(episodeFormPnl,BorderLayout.CENTER);

    }
    @Override
    public void itemStateChanged(ItemEvent evt) {
        // Only switch cards when an item is selected (state change from DESELECTED to SELECTED)
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String selected = (String) evt.getItem();
            cl.show(panelContainer, selected);
        }
    }

}
