package com.anime.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminPage extends JPanel {
    private JPanel addSeriesPnl = new JPanel();
    private JPanel addEpisodePnl = new JPanel();
    private JPanel addActorPnl = new JPanel();
    private JPanel updateSeriesPnl = new JPanel();
    private JPanel updateEpisodePnl = new JPanel();
    private JPanel updateActorPnl = new JPanel();
    private JPanel buttonsPnl = new JPanel();
    private JPanel panelContainer = new JPanel();

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
        JLabel adminTitleLb = new JLabel("Administrator Record Management");
        JPanel adminTitlePnl = new JPanel();

        adminTitleLb.setAlignmentX(CENTER_ALIGNMENT);
        adminTitlePnl.setLayout(new BoxLayout(adminTitlePnl,BoxLayout.X_AXIS));
        adminTitlePnl.setBackground(Color.gray);
        adminTitlePnl.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));
        adminTitlePnl.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        adminTitlePnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminTitlePnl.setBorder(new EmptyBorder(10,10,10,10));

        buttonsPnl.setLayout(new BoxLayout(buttonsPnl,BoxLayout.Y_AXIS));
        buttonsPnl.setBackground(Color.gray);
        buttonsPnl.setPreferredSize(new Dimension((int)(1280/3), 720));
        buttonsPnl.setMaximumSize(new Dimension((int)(1280/3), 720));
        buttonsPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPnl.setBorder(new EmptyBorder(10,10,10,10));
        buttonsPnl.setBackground(Color.yellow);

        panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));
        panelContainer.setBackground(Color.black);
        panelContainer.setAlignmentY(Component.CENTER_ALIGNMENT);
        panelContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContainer.setBackground(Color.red);
        panelContainer.setBorder(new EmptyBorder(10,10,10,10));

        adminTitlePnl.add(adminTitleLb);
        add(adminTitlePnl,BorderLayout.NORTH);
        add(buttonsPnl,BorderLayout.WEST);
        add(panelContainer, BorderLayout.CENTER);
    }

}
