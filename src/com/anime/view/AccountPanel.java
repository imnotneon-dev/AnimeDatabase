package com.anime.view;

import javax.swing.*;
import java.awt.*;

public class AccountPanel extends JPanel{
    private JButton loginBtn = new JButton();
    private JButton signupBtn = new JButton();
    private JTextField loginNameField = new JTextField(25);
    private JPanel container = new JPanel();
    private GridBagLayout layout = new GridBagLayout();
    private final Component LEFT_GLUE = Box.createHorizontalGlue();
    private final Component RIGHT_GLUE = Box.createHorizontalGlue();
    private final Component TOP_GLUE = Box.createVerticalGlue();
    private final Component BOT_GLUE = Box.createVerticalGlue();

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
        // GridBagLayout centers the container panel
        layout.setConstraints(container, new GridBagConstraints());

        // set layout of component container so components are centered
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));

        // Set text and component attributes
        loginNameField.setText("Enter your account");
        loginBtn.setText("Login");
        loginBtn.setFocusable(true);
        signupBtn.setText("Signup");

        // Set component alignments
        loginNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Setup visual appearance of container panel
        container.add(TOP_GLUE);

        container.add(Box.createVerticalStrut(5));
        container.add(loginNameField);
        container.add(Box.createVerticalStrut(10));
        container.add(loginBtn);
        container.add(Box.createVerticalStrut(5));
        container.add(signupBtn);
        container.add(Box.createVerticalStrut(5));

        container.add(BOT_GLUE);

        // Add to main panel
        add(container);
    }

    public String getLoginName() {
        return loginNameField.getText();
    }

    public JButton getLoginBtn(){
        return loginBtn;
    }

    public JButton getSignupBtn(){
        return signupBtn;
    }
}
