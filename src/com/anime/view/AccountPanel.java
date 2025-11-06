package com.anime.view;

import javax.swing.*;
import java.awt.*;

public class AccountPanel extends JPanel{
    private JButton loginBtn = new JButton();
    private JButton signupBtn = new JButton();
    private JTextField loginNameField = new JTextField();
    private JPanel container = new JPanel();
    private GridBagLayout layout = new GridBagLayout();
    private final Component LEFT_GLUE = Box.createHorizontalGlue();
    private final Component RIGHT_GLUE = Box.createHorizontalGlue();

    public AccountPanel() {
        init();
    }
    private void init() {
        setOpaque(true);
        setVisible(true);
        setBackground(Color.CYAN);
        setLayout(layout);

        initComponents();
    }

    private void initComponents(){
//        container.setPreferredSize(new Dimension(100,100));
//        container.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        container.setLayout(new BoxLayout(container,BoxLayout.PAGE_AXIS));

        layout.setConstraints(container, new GridBagConstraints());

        loginNameField.setText("Enter your account");
        loginBtn.setText("Login");
        loginBtn.setFocusable(true);

        signupBtn.setText("Signup");

        container.add(loginNameField);
        container.add(loginBtn);
        container.add(signupBtn);

        add(container);
    }
}
