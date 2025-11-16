package com.anime.view;

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
    private JPanel updateSeriesPnl = new JPanel();
    private JPanel updateEpisodePnl = new JPanel();
    private JPanel updateActorPnl = new JPanel();
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

        String comboBoxItems[] = { "add series", "add episode" };
        cb = new JComboBox<>(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);

        adminTitlePnl.add(adminTitleLb);
        adminTitlePnl.add(cb);
        add(adminTitlePnl,BorderLayout.NORTH);

        panelContainer.setLayout(cl);
        panelContainer.setBorder(new EmptyBorder(10,10,10,10));
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
        addActorPnl.setBackground(Color.yellow);
        addActorPnl.setBorder(new EmptyBorder(10,10,10,10));

        panelContainer.add(addSeriesPnl,ADD_SERIES);
        panelContainer.add(addEpisodePnl,ADD_ACTOR);
        panelContainer.add(addActorPnl,ADD_ACTOR);

        add(panelContainer, BorderLayout.CENTER);
    }

    private void setupEpisodePanel(){
        JPanel episodeList = new JPanel();
        JPanel episodeForm = new JPanel();

        episodeList.setLayout(new GridLayout(0,4,5,5));
        episodeList.setPreferredSize(new Dimension((int)(1280/2.5), 720));
        episodeList.setMaximumSize(new Dimension((int)(1280/2.5), 720));
        for()
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
