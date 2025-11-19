package com.anime.view;

import com.anime.model.Actor;
import com.anime.view.customcards.PlainActorCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ManageActorPanel extends JPanel {

    private JTextField firstNameField = new JTextField();
    private JTextField lastNameField = new JTextField();
    private JComboBox<String> sexCb = new JComboBox<>();
    private JTextField dobField = new JTextField();
    private JTextField pobField = new JTextField();
    private JTextField agencyField = new JTextField();

    private JButton addBtn = new JButton("Add");
    private JButton updateBtn = new JButton("Update");
    private JButton clearBtn = new JButton("Clear");

    private List<Actor> actorList = new ArrayList<>();
    private List<PlainActorCard> actorCards = new ArrayList<>();

    private JPanel actorListPnl = new JPanel();

    public ManageActorPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#121212")); // main background
        setBorder(new EmptyBorder(10,10,10,10));
        setupActorPanel();
    }

    private void setupActorPanel() {
        JPanel actorListPnl = new JPanel();
        JPanel actorFormPnl = new JPanel();
        JScrollPane actorListScrollPanel = new JScrollPane(actorListPnl);


        actorListPnl.setLayout(new BoxLayout(actorListPnl, BoxLayout.Y_AXIS));
        actorListPnl.setPreferredSize(new Dimension(640, 720));
        actorListPnl.setBackground(Color.decode("#282828")); // card background color
        actorListPnl.setBorder(new EmptyBorder(10,10,10,10));
        loadPACards();

        actorListScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        actorListScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        actorListScrollPanel.setWheelScrollingEnabled(true);
        actorListScrollPanel.setBorder(null);
        add(actorListScrollPanel, BorderLayout.WEST);


        applyFieldStyle(firstNameField);
        applyFieldStyle(lastNameField);
        applyFieldStyle(dobField);
        applyFieldStyle(pobField);
        applyFieldStyle(agencyField);

        sexCb.setBackground(Color.decode("#121212"));
        sexCb.setForeground(Color.decode("#FFFFFF"));
        sexCb.setMaximumSize(new Dimension(350,30));
        sexCb.setBorder(BorderFactory.createLineBorder(Color.decode("#C8C8C8")));


        styleButton(addBtn);
        styleButton(updateBtn);
        styleButton(clearBtn);

        updateBtn.setEnabled(false);


        actorFormPnl.setLayout(new BoxLayout(actorFormPnl, BoxLayout.Y_AXIS));
        actorFormPnl.setPreferredSize(new Dimension(640, 720));
        actorFormPnl.setBackground(Color.decode("#282828")); // form background
        actorFormPnl.setBorder(new EmptyBorder(10,10,10,10));


        actorFormPnl.add(label("Enter First Name"));
        actorFormPnl.add(firstNameField);

        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(label("Enter Last Name"));
        actorFormPnl.add(lastNameField);

        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(label("Select Sex"));
        actorFormPnl.add(sexCb);

        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(label("Enter Date of Birth"));
        actorFormPnl.add(dobField);

        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(label("Enter Place of Birth"));
        actorFormPnl.add(pobField);

        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(label("Enter Agency"));
        actorFormPnl.add(agencyField);

        actorFormPnl.add(Box.createVerticalStrut(20));
        actorFormPnl.add(addBtn);

        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(updateBtn);

        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(clearBtn);

        actorFormPnl.add(Box.createVerticalGlue());
        add(actorFormPnl, BorderLayout.CENTER);
    }

    private JLabel label(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.decode("#FFFFFF"));
        return lbl;
    }

    private void applyFieldStyle(JTextField f) {
        f.setMaximumSize(new Dimension(350,30));
        f.setBackground(Color.decode("#121212"));
        f.setForeground(Color.decode("#FFFFFF"));
        f.setCaretColor(Color.decode("#FFFFFF"));
        f.setBorder(BorderFactory.createLineBorder(Color.decode("#C8C8C8")));
        f.setAlignmentX(LEFT_ALIGNMENT);
    }

    private void styleButton(JButton b) {
        b.setForeground(Color.decode("#FFFFFF"));
        b.setBackground(Color.decode("#121212"));
        b.setBorder(BorderFactory.createLineBorder(Color.decode("#C8C8C8")));
        b.setFocusPainted(false);
        b.setMaximumSize(new Dimension(200,35));
        b.setAlignmentX(LEFT_ALIGNMENT);
    }

    private void loadPACards() {
        actorListPnl.removeAll();
        actorCards.clear();
        for (Actor e : actorList) {
            PlainActorCard pac = new PlainActorCard(e.getLastName() + ", " + e.getFirstName());
            pac.putClientProperty("actor_id", e.getId());
            actorCards.add(pac);
            actorListPnl.add(pac);
            actorListPnl.add(Box.createVerticalStrut(5));
        }
        actorListPnl.revalidate();
        actorListPnl.repaint();
    }
}
