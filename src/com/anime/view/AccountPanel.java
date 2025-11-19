package com.anime.view;

import com.anime.model.Account;
import com.anime.model.ActorSeries;
import com.anime.model.Series;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountPanel extends JPanel{
    private final String TAKOROLL_LOGO = "/imgs/takoroll_logo.png";
    private ImageIcon takorollIcon = new ImageIcon();


    private JButton loginBtn = new JButton();
    private JButton signupBtn = new JButton();

    private JTextField loginNameField = new JTextField(25);
    private JTextField loginPasswordField = new JTextField(25);
    private JPanel loginContainer = new JPanel();


    private JTextField signNameField = new JTextField(25);
    private JTextField signPasswordField = new JTextField(25);
    private JTextField signConfirmField = new JTextField(25);
    private JButton submitSignUpBtn = new JButton();
    private JButton alreadyHasAccountBtn = new JButton();
    private JFormattedTextField signDobField;
    private JComboBox<Object> countrySelector = new JComboBox<Object>();
    private JPanel signupContainer = new JPanel();
    private GridBagLayout logLayout = new GridBagLayout();
    private GridBagLayout signLayout = new GridBagLayout();
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
        setBackground(Color.decode("#282828"));
        setLayout(new GridBagLayout());

        setupSignupPanel();
        setupLoginPanel();
        setupRenderers();
        revalidate();
        repaint();
    }


    private void setupSignupPanel(){
        JLabel logoIconLb = new JLabel();
        JLabel register = new JLabel("Register Account");
        register.setForeground(Color.WHITE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        signDobField = new JFormattedTextField(dateFormat);
        String[] countryChoices = { "--- Select a Country ---", "Philippines", "Singapore", "Malaysia" };
        countrySelector = new JComboBox<>(countryChoices);

        register.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // GridBagLayout centers the signupContainer panel
        signLayout.setConstraints(signupContainer, new GridBagConstraints());

        // set layout of component signupContainer so components are centered
        signupContainer.setVisible(false);
        // signupContainer.setBackground(Color.WHITE);
        signupContainer.setLayout(new BoxLayout(signupContainer,BoxLayout.Y_AXIS));
        signupContainer.setPreferredSize(new Dimension(400,600));
        signupContainer.setMaximumSize(new Dimension(400,600));
        
        // Set text and component attributes
//        signNameField.setText();
        setupGhostText(signNameField,"Name");
        signNameField.setPreferredSize(new Dimension(350,30));
        signNameField.setMaximumSize(new Dimension(350,30));
        signNameField.setBorder(new MatteBorder(0,0,1,0,Color.black));
        signNameField.setForeground(Color.WHITE);
        setupGhostText(signPasswordField,"Enter Password");
        signPasswordField.setPreferredSize(new Dimension(350,30));
        signPasswordField.setMaximumSize(new Dimension(350,30));
        signPasswordField.setBorder(new MatteBorder(0,0,1,0,Color.black));
        signPasswordField.setForeground(Color.WHITE);
        setupGhostText(signConfirmField,"Confirm Password");
        signConfirmField.setPreferredSize(new Dimension(350,30));
        signConfirmField.setMaximumSize(new Dimension(350,30));
        signConfirmField.setBorder(new MatteBorder(0,0,1,0,Color.black));
        signConfirmField.setForeground(Color.WHITE);
        signDobField.setPreferredSize(new Dimension(350,30));
        signDobField.setMaximumSize(new Dimension(350,30));
        signDobField.setBorder(new MatteBorder(0,0,1,0,Color.black));
        signDobField.setForeground(Color.WHITE);
        countrySelector.setPreferredSize(new Dimension(350,30));
        countrySelector.setMaximumSize(new Dimension(350,30));
        countrySelector.setBorder(new MatteBorder(0,0,1,0,Color.black));
        submitSignUpBtn.setText("Submit Registration");
        submitSignUpBtn.setFocusable(true);
        alreadyHasAccountBtn.setText("Already Have Account? Login Here");
        alreadyHasAccountBtn.setFocusable(true);

        BufferedImage biTakorollIcon = loadImage(TAKOROLL_LOGO);
        Image scaled = biTakorollIcon.getScaledInstance(250,250,Image.SCALE_AREA_AVERAGING);
        takorollIcon = new ImageIcon(scaled);
        logoIconLb.setIcon(takorollIcon);

        // Set component alignments
        register.setAlignmentX(Component.CENTER_ALIGNMENT);
        register.setAlignmentY(Component.TOP_ALIGNMENT);
        signNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        signNameField.setAlignmentY(Component.TOP_ALIGNMENT);
        signPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        signPasswordField.setAlignmentY(Component.TOP_ALIGNMENT);
        signConfirmField.setAlignmentX(Component.CENTER_ALIGNMENT);
        signConfirmField.setAlignmentY(Component.TOP_ALIGNMENT);
        signDobField.setAlignmentX(Component.CENTER_ALIGNMENT);
        signDobField.setAlignmentY(Component.TOP_ALIGNMENT);
        countrySelector.setAlignmentX(Component.CENTER_ALIGNMENT);
        countrySelector.setAlignmentY(Component.TOP_ALIGNMENT);
        countrySelector.setForeground(Color.WHITE); 
        submitSignUpBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        alreadyHasAccountBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoIconLb.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Setup visual appearance of signupContainer panel
        signupContainer.add(TOP_GLUE);
//        signupContainer.add(LEFT_GLUE);
        signupContainer.add(Box.createVerticalStrut(5));
        signupContainer.add(logoIconLb);
        signupContainer.add(register);
        signupContainer.add(Box.createVerticalStrut(5));
        signupContainer.add(signNameField);
        signupContainer.add(Box.createVerticalStrut(10));
        signupContainer.add(signPasswordField);
        signupContainer.add(Box.createVerticalStrut(10));
        signupContainer.add(signConfirmField);
        signupContainer.add(Box.createVerticalStrut(10));
        signupContainer.add(signDobField);
        signupContainer.add(Box.createVerticalStrut(10));
        signupContainer.add(countrySelector);
        signupContainer.add(Box.createVerticalStrut(10));
        signupContainer.add(submitSignUpBtn);
        signupContainer.add(Box.createVerticalStrut(5));
        signupContainer.add(alreadyHasAccountBtn);
        signupContainer.add(Box.createVerticalStrut(5));
//        signupContainer.setBackground(Color.decode("#282828"));

//        signupContainer.add(RIGHT_GLUE);
        signupContainer.add(BOT_GLUE);

        // Add to main panel
        add(signupContainer);
        signupContainer.setVisible(false);
        revalidate();
        repaint();
    }
    private void setupLoginPanel(){
        loginContainer.setVisible(true);
        JLabel logoIconLb = new JLabel();
        // GridBagLayout centers the loginContainer panel
        logLayout.setConstraints(loginContainer, new GridBagConstraints());
        // set layout of component loginContainer so components are centered
        loginContainer.setLayout(new BoxLayout(loginContainer,BoxLayout.Y_AXIS));
        loginContainer.setAlignmentY(Component.CENTER_ALIGNMENT);
        loginContainer.setPreferredSize(new Dimension(400,600));
        loginContainer.setMaximumSize(new Dimension(400,600));
        // Set text and component attributes
//        loginNameField.setText();
        setupGhostText(loginNameField,"Name");
        loginNameField.setPreferredSize(new Dimension(350,30));
        loginNameField.setMaximumSize(new Dimension(350,30));
        loginNameField.setBorder(new MatteBorder(0,0,1,0,Color.black));
        loginNameField.setForeground(Color.WHITE);
        setupGhostText(loginPasswordField,"Password");
        loginPasswordField.setPreferredSize(new Dimension(350,30));
        loginPasswordField.setMaximumSize(new Dimension(350,30));
        loginPasswordField.setBorder(new MatteBorder(0,0,1,0,Color.black));
        loginPasswordField.setForeground(Color.WHITE);
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

        // loginContainer.setBackground(Color.WHITE);

        // Add to main panel
        add(loginContainer);
        revalidate();
        repaint();
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
                    textField.setForeground(Color.WHITE);
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

    private void setupRenderers() {
        // Apply the custom renderer using a lambda expression
        countrySelector.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected,
                                                          boolean cellHasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof Account account) {
                    ((JLabel) component).setText(account.getCountry());
                } else if (value != null) {
                    ((JLabel) component).setText(value.toString());
                } else {
                    ((JLabel) component).setText("Select a Country");
                }
                return component;
            }
        });
    }
    public JButton getLoginBtn() {
        return loginBtn;
    }

    public JButton getSignupBtn() {
        return signupBtn;
    }
    public String getLoginName() {
        return loginNameField.getText();
    }
    public String getLoginPassword() {
        return loginPasswordField.getText();
    }

    public JPanel getLoginContainer() {
        return loginContainer;
    }

    public String getSignName() {
        return signNameField.getText();
    }

    public String getSignPassword() {
        return signPasswordField.getText();
    }

    public String getSignConfirm() {
        return signConfirmField.getText();
    }

    public String getSignDob() {
        return signDobField.getText();
    }

    public JFormattedTextField getSignDobField() {
        return signDobField;
    }

    public String getCountry() {
        return String.valueOf(countrySelector);
    }

    public JButton getSubmitSignUpBtn() {
        return submitSignUpBtn;
    }

    public JButton getAlreadyHasAccountBtn() {
        return alreadyHasAccountBtn;
    }

    public JPanel getSignupContainer() {
        return signupContainer;
    }

    public ImageIcon getTakorollIcon() {
        return takorollIcon;
    }
}

