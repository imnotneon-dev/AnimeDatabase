package com.anime.view;

import javax.swing.*;
import java.awt.*;

public class AccountPanel extends JPanel{
    private JButton button = new JButton("Account");

    public AccountPanel() {
        init();
    }
    private void init() {
        setOpaque(true);
        setVisible(true);
        setBackground(Color.CYAN);
        setLayout(new FlowLayout());
        add(button);
    }
    public JButton getButton(){
        return button;
    }
}
