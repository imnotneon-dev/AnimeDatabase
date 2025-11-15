package com.anime.view;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountPanel extends JPanel{
    private final String TAKOROLL_LOGO = "/imgs/takoroll_logo.png";
    private ImageIcon takorollIcon = new ImageIcon();
    private JLabel logoIconLb = new JLabel();

    private JButton loginBtn = new JButton();
    private JButton signupBtn = new JButton();

    private JTextField loginNameField = new JTextField(25);
    private JTextField loginPasswordField = new JTextField(25);
    private JPanel loginContainer = new JPanel();


    private JTextField signNameField = new JTextField(25);
    private JTextField signPasswordField = new JTextField(25);
//    private
    private JFormattedTextField signDobField;
    private JPanel signupContainer = new JPanel();
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


        setupLoginPanel();
        initComponents();
    }


    private void initComponents(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        signDobField = new JFormattedTextField(dateFormat);

        // GridBagLayout centers the signupContainer panel
        layout.setConstraints(signupContainer, new GridBagConstraints());

        // set layout of component signupContainer so components are centered
        signupContainer.setLayout(new BoxLayout(signupContainer,BoxLayout.Y_AXIS));
        signupContainer.setPreferredSize(new Dimension(400,600));
        signupContainer.setMaximumSize(new Dimension(400,600));
        // Set text and component attributes
//        signNameField.setText();
        setupGhostText(signNameField,"test");
        signNameField.setPreferredSize(new Dimension(350,30));
        signNameField.setMaximumSize(new Dimension(350,30));
        signNameField.setBorder(new MatteBorder(0,0,1,0,Color.black));
        setupGhostText(loginPasswordField,"Password");
        loginPasswordField.setPreferredSize(new Dimension(350,30));
        loginPasswordField.setMaximumSize(new Dimension(350,30));
        loginPasswordField.setBorder(new MatteBorder(0,0,1,0,Color.black));
        loginBtn.setText("Login");
        loginBtn.setFocusable(true);
        signupBtn.setText("Signup");

        BufferedImage biTakorollIcon = loadImage(TAKOROLL_LOGO);
        Image scaled = biTakorollIcon.getScaledInstance(250,250,Image.SCALE_AREA_AVERAGING);
        takorollIcon = new ImageIcon(scaled);
        logoIconLb.setIcon(takorollIcon);

        // Set component alignments
        signNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        signNameField.setAlignmentY(Component.TOP_ALIGNMENT);
        loginPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPasswordField.setAlignmentY(Component.TOP_ALIGNMENT);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoIconLb.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Setup visual appearance of signupContainer panel
        signupContainer.add(TOP_GLUE);
        signupContainer.add(LEFT_GLUE);
        signupContainer.add(Box.createVerticalStrut(5));
        signupContainer.add(logoIconLb);
        signupContainer.add(signNameField);
        signupContainer.add(Box.createVerticalStrut(10));
        signupContainer.add(loginPasswordField);
        signupContainer.add(Box.createVerticalStrut(10));
        signupContainer.add(loginBtn);
        signupContainer.add(Box.createVerticalStrut(5));
        signupContainer.add(signupBtn);
        signupContainer.add(Box.createVerticalStrut(5));

        signupContainer.add(RIGHT_GLUE);
        signupContainer.add(BOT_GLUE);

        // Add to main panel
        add(signupContainer);
    }
    private void setupLoginPanel(){
        // GridBagLayout centers the loginContainer panel
        layout.setConstraints(loginContainer, new GridBagConstraints());

        // set layout of component loginContainer so components are centered
        loginContainer.setLayout(new BoxLayout(loginContainer,BoxLayout.Y_AXIS));
        loginContainer.setPreferredSize(new Dimension(400,600));
        loginContainer.setMaximumSize(new Dimension(400,600));
        // Set text and component attributes
//        loginNameField.setText();
        setupGhostText(loginNameField,"Name");
        loginNameField.setPreferredSize(new Dimension(350,30));
        loginNameField.setMaximumSize(new Dimension(350,30));
        loginNameField.setBorder(new MatteBorder(0,0,1,0,Color.black));
        setupGhostText(loginPasswordField,"Password");
        loginPasswordField.setPreferredSize(new Dimension(350,30));
        loginPasswordField.setMaximumSize(new Dimension(350,30));
        loginPasswordField.setBorder(new MatteBorder(0,0,1,0,Color.black));
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
        loginPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPasswordField.setAlignmentY(Component.TOP_ALIGNMENT);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoIconLb.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Setup visual appearance of loginContainer panel
        loginContainer.add(TOP_GLUE);
        loginContainer.add(LEFT_GLUE);
        loginContainer.add(Box.createVerticalStrut(5));
        loginContainer.add(logoIconLb);
        loginContainer.add(loginNameField);
        loginContainer.add(Box.createVerticalStrut(10));
        loginContainer.add(loginPasswordField);
        loginContainer.add(Box.createVerticalStrut(10));
        loginContainer.add(loginBtn);
        loginContainer.add(Box.createVerticalStrut(5));
        loginContainer.add(signupBtn);
        loginContainer.add(Box.createVerticalStrut(5));

        loginContainer.add(RIGHT_GLUE);
        loginContainer.add(BOT_GLUE);

        // Add to main panel
        add(loginContainer);
    }

    public static BufferedImage loadImage(String iresPath)
    {
        BufferedImage image = null;
        try { image = ImageIO.read(AccountPanel.class.getResource(iresPath)); }
        catch (IOException e) { e.printStackTrace(); }
        return image;
    }

    private void setupGhostText(JTextField textField, String placeholder) {

        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);

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
