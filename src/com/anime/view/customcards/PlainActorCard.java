package com.anime.view.customcards;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PlainActorCard extends JPanel {
    //    private Review review;
    private JPanel container = new JPanel();

    public PlainActorCard(String seriesTitle){
        JLabel series = new JLabel(seriesTitle);

        setOpaque(true);
        setVisible(true);
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(new EmptyBorder(15,15,15,15));
//        setPreferredSize(new Dimension(Integer.MAX_VALUE,80));

//        role.setAlignmentX(Component.LEFT_ALIGNMENT);
//        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.setAlignmentY(Component.TOP_ALIGNMENT);
//        container.setAlignmentX(LEFT_ALIGNMENT);
        container.setOpaque(false);

        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.add(Box.createHorizontalStrut(10));
        container.add(series);
        container.add(Box.createHorizontalGlue());

//        add(da);
        add(container);
        setMaximumSize(new Dimension(Integer.MAX_VALUE,getPreferredSize().height));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(Color.decode("#F8481C"));
            }

            @Override
            public void mouseReleased(MouseEvent e){
                setBackground(Color.WHITE);
            }
        });

    }

}
