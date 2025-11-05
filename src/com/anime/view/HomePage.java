package com.anime.view;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel{
    private JButton button = new JButton("HomePage");
    public HomePage() {
        init();
    }
    private void init() {
        setOpaque(true);
        setVisible(true);
        setBackground(Color.RED);
        setLayout(new FlowLayout());
        add(button);
    }

    public JButton getButton(){
        return button;
    }
}
