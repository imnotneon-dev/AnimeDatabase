package com.anime.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AdminPage extends JPanel implements ItemListener {
    private ManageSeriesPanel mngSeriesPnl = new ManageSeriesPanel();
    private ManageEpisodePanel mngEpisodePnl = new ManageEpisodePanel();
    private ManageActorPanel mngActorPnl = new ManageActorPanel();
    private JPanel panelContainer = new JPanel();

    private JComboBox<String> cb;
    private CardLayout cl = new CardLayout();

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
//        JLabel adminTitleLb = new JLabel("Administrator Record Management");
//        adminTitleLb.setAlignmentX(CENTER_ALIGNMENT);

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

//        adminTitlePnl.add(adminTitleLb);
        adminTitlePnl.add(cb);
        add(adminTitlePnl,BorderLayout.NORTH);

        panelContainer.setLayout(cl);
//        panelContainer.setBorder(new EmptyBorder(10,10,10,10));
        panelContainer.setAlignmentY(Component.CENTER_ALIGNMENT);
        panelContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContainer.setBackground(Color.red);

//        mngSeriesPnl.setLayout(new BorderLayout());
//        mngSeriesPnl.setBackground(Color.black);
//        mngSeriesPnl.setBorder(new EmptyBorder(10,10,10,10));

//        mngActorPnl.setLayout(new BorderLayout());
//        mngActorPnl.setBackground(Color.cyan);
//        mngActorPnl.setBorder(new EmptyBorder(10,10,10,10));

        panelContainer.add(mngSeriesPnl,ADD_SERIES);
        panelContainer.add(mngEpisodePnl,ADD_EP);
        panelContainer.add(mngActorPnl,ADD_ACTOR);

        add(panelContainer, BorderLayout.CENTER);
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
