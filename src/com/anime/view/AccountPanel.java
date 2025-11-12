package com.anime.view;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;

public class AccountPanel extends JPanel{
    private final String TAKOROLL_LOGO = "/imgs/takoroll_logo.png";

    private JButton loginBtn = new JButton();
    private JButton signupBtn = new JButton();
    private JTextField loginNameField = new JTextField(25);
    private JTextField passwordField = new JTextField(25);
    private ImageIcon takorollIcon = new ImageIcon();
    private JLabel logoIconLb = new JLabel();
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
        setBackground(Color.decode("#212121"));
        setLayout(layout);


        initComponents();
    }

    private void initComponents(){
        // GridBagLayout centers the container panel
        layout.setConstraints(container, new GridBagConstraints());

        // set layout of component container so components are centered
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        container.setPreferredSize(new Dimension(400,600));
        container.setMaximumSize(new Dimension(400,600));
        // Set text and component attributes
//        loginNameField.setText();
        setupGhostText(loginNameField,"Name");
        loginNameField.setPreferredSize(new Dimension(350,30));
        loginNameField.setMaximumSize(new Dimension(350,30));
        loginNameField.setBorder(new MatteBorder(0,0,1,0,Color.black));
        setupGhostText(passwordField,"Password");
        passwordField.setPreferredSize(new Dimension(350,30));
        passwordField.setMaximumSize(new Dimension(350,30));
        passwordField.setBorder(new MatteBorder(0,0,1,0,Color.black));
        loginBtn.setText("Login");
        loginBtn.setFocusable(true);
        signupBtn.setText("Signup");

        BufferedImage biTakorollIcon = loadImage(TAKOROLL_LOGO);
        Image scaled = biTakorollIcon.getScaledInstance(250,250,Image.SCALE_AREA_AVERAGING);
        takorollIcon = new ImageIcon(scaled);
        logoIconLb.setIcon(takorollIcon);

        // Set component alignments
        loginNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginNameField.setAlignmentY(Component.TOP_ALIGNMENT);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setAlignmentY(Component.TOP_ALIGNMENT);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoIconLb.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Setup visual appearance of container panel
        container.add(TOP_GLUE);
        container.add(LEFT_GLUE);
        container.add(Box.createVerticalStrut(5));
        container.add(logoIconLb);
        container.add(loginNameField);
        container.add(Box.createVerticalStrut(10));
        container.add(passwordField);
        container.add(Box.createVerticalStrut(10));
        container.add(loginBtn);
        container.add(Box.createVerticalStrut(5));
        container.add(signupBtn);
        container.add(Box.createVerticalStrut(5));

        container.add(RIGHT_GLUE);
        container.add(BOT_GLUE);

        // Add to main panel
        add(container);
    }

    public static BufferedImage loadImage(String iresPath)
    {
        BufferedImage image = null;
        try { image = ImageIO.read(AccountPanel.class.getResource(iresPath)); }
        catch (IOException e) { e.printStackTrace(); }
        return image;
    }

    private void setupGhostText(final JTextField textField, final String placeholder) {

        // 1. Initial State: Set the placeholder text and gray color
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);

        // 2. Add FocusListener to handle gaining/losing focus
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // When the user clicks away:
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
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
