package com.anime.view;

import com.anime.view.customcards.PlainActorCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class ManageActorPanel extends JPanel {

    private JTextField nameField = new JTextField();
    private JComboBox<String> sexCb = new JComboBox<>();
    private JTextField dobField = new JTextField();
    private JTextField pobField = new JTextField();
    private JTextField agencyField = new JTextField();

    private JButton addBtn = new JButton("Add");
    private JButton updateBtn = new JButton("Update");
    private JButton deleteBtn = new JButton("Delete");
    private JButton clearBtn = new JButton("Clear");


    private List<String> actorList = List.of(
            "Series 1","Series 2","Series 3","Series 4","Series 5",
            "Series 6","Series 7","Series 8","Series 9","Series 10",
            "Series 11","Series 12");

    public ManageActorPanel(){
        setLayout(new BorderLayout());
        setBackground(Color.yellow);
        setBorder(new EmptyBorder(10,10,10,10));
        setupActorPanel();
    }
    private void setupActorPanel(){
        JPanel actorListPnl = new JPanel();
        JPanel actorFormPnl = new JPanel();
        JScrollPane actorListScrollPanel = new JScrollPane(actorListPnl);

        actorListPnl.setLayout(new BoxLayout(actorListPnl, BoxLayout.Y_AXIS));
        actorListPnl.setPreferredSize(new Dimension((int)(1280/2), 720));
        actorListPnl.setMaximumSize(new Dimension((int)(1280/2), 720));
        actorListPnl.setBorder(new EmptyBorder(10,10,10,10));
        actorListPnl.setBackground(Color.magenta);
        for(String e: actorList){
            PlainActorCard pac = new PlainActorCard(e);
            actorListPnl.setAlignmentX(Component.LEFT_ALIGNMENT);
            actorListPnl.add(pac);
            actorListPnl.add(Box.createVerticalStrut(5));
        }

        actorListScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        actorListScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        actorListScrollPanel.setWheelScrollingEnabled(true);
        actorListScrollPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        actorListScrollPanel.getVerticalScrollBar().setUnitIncrement(10);
        actorListScrollPanel.getVerticalScrollBar().setPreferredSize(new Dimension(3,Integer.MAX_VALUE));
        add(actorListScrollPanel, BorderLayout.WEST);

        nameField.setMaximumSize(new Dimension(350,30));
        nameField.setAlignmentX(LEFT_ALIGNMENT);

        sexCb = new JComboBox<>(new String[] {"Male", "Female", "Other"});
        sexCb.setMaximumSize(new Dimension(350,30));
        sexCb.setAlignmentX(Component.LEFT_ALIGNMENT);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        dobField = new JFormattedTextField(dateFormat);
        dobField.setMaximumSize(new Dimension(350,30));
        dobField.setAlignmentX(LEFT_ALIGNMENT);

        pobField.setMaximumSize(new Dimension(350,30));
        pobField.setAlignmentX(LEFT_ALIGNMENT);

        agencyField.setMaximumSize(new Dimension(350,30));
        agencyField.setAlignmentX(LEFT_ALIGNMENT);

        addBtn.setPreferredSize(new Dimension(200,35));
        addBtn.setMaximumSize(new Dimension(200,35));
        addBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        updateBtn.setPreferredSize(new Dimension(200,35));
        updateBtn.setMaximumSize(new Dimension(200,35));
        updateBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        updateBtn.setEnabled(false);

        deleteBtn.setPreferredSize(new Dimension(200,35));
        deleteBtn.setMaximumSize(new Dimension(200,35));
        deleteBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        deleteBtn.setEnabled(false);

        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(new JLabel("Enter Series Title"));
        actorFormPnl.add(nameField);
        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(new JLabel("Select Sex"));
        actorFormPnl.add(sexCb);
        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(new JLabel("Enter Date of Birth"));
        actorFormPnl.add(dobField);
        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(new JLabel("Enter Place of Birth"));
        actorFormPnl.add(pobField);
        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(new JLabel("Enter Agency"));
        actorFormPnl.add(agencyField);
        actorFormPnl.add(Box.createVerticalStrut(10));
//        actorFormPnl.add(Box.createVerticalGlue());
        actorFormPnl.add(addBtn);
        actorFormPnl.add(Box.createVerticalStrut(10));
//        actorFormPnl.add(Box.createVerticalGlue());
        actorFormPnl.add(updateBtn);
        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(deleteBtn);
        actorFormPnl.add(Box.createVerticalStrut(10));
        actorFormPnl.add(clearBtn);
        actorFormPnl.add(Box.createVerticalGlue());
        actorFormPnl.setLayout(new BoxLayout(actorFormPnl,BoxLayout.Y_AXIS));
        actorFormPnl.setPreferredSize(new Dimension((int)(1280/2), 720));
        actorFormPnl.setMaximumSize(new Dimension((int)(1280/2), 720));
        actorFormPnl.setBorder(new EmptyBorder(10,10,10,10));
        actorFormPnl.setBackground(Color.orange);

        add(actorFormPnl,BorderLayout.CENTER);

    }
}
