package com.anime.view;

import com.anime.model.EpisodeReview;
import com.anime.model.dao.EpisodeReviewDAO;
import com.anime.model.dao.EpisodeDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class EpisodeReviewPanel extends JPanel {

    private EpisodeReviewDAO reviewDAO;
    private EpisodeDAO episodeDAO;

    private JComboBox<String> episodeSelector;
    private JButton loadAllBtn;
    private JTable reviewTable;
    private DefaultTableModel tableModel;

    public EpisodeReviewPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Filter
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.WHITE);

        episodeSelector = new JComboBox<>();
        episodeSelector.setPreferredSize(new Dimension(250, 30));

        loadAllBtn = new JButton("Show All Reviews");

        topPanel.add(new JLabel("Filter by Episode: "));
        topPanel.add(episodeSelector);
        topPanel.add(loadAllBtn);

        add(topPanel, BorderLayout.NORTH);

        
        tableModel = new DefaultTableModel(
                new Object[]{"Review ID", "Username", "Episode Title", "Review", "Date Reviewed"}, 0
        );
        reviewTable = new JTable(tableModel);
        reviewTable.setRowHeight(25);

        add(new JScrollPane(reviewTable), BorderLayout.CENTER);

        // Load data
        loadEpisodeList();
        loadAllReviews();

      
        episodeSelector.addActionListener(e -> loadReviewsByEpisode());
        loadAllBtn.addActionListener(e -> loadAllReviews());
    }

    // Load ep titles
    private void loadEpisodeList() {
        try {
            List<String> episodes = episodeDAO.getAllEpisodeTitles();
            episodeSelector.addItem("Select Episode");
            for (String title : episodes) {
                episodeSelector.addItem(title);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading episodes.");
            e.printStackTrace();
        }
    }

    // Load reviews before filtering by ep
    private void loadAllReviews() {
        try {
            List<EpisodeReview> reviews = reviewDAO.getAllReviews();
            populateTable(reviews);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading reviews.");
            e.printStackTrace();
        }
    }

    // Load reviews by ep
    private void loadReviewsByEpisode() {
        String selected = (String) episodeSelector.getSelectedItem();
        if (selected == null || selected.equals("Select Episode")) {
            return;
        }

        try {
            int episodeId = episodeDAO.getEpisodeIdByTitle(selected);
            List<EpisodeReview> reviews = reviewDAO.getReviewsByEpisodeId(episodeId);
            populateTable(reviews);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading episode reviews.");
            e.printStackTrace();
        }
    }


  
    private void populateTable(List<EpisodeReview> reviews) {
        tableModel.setRowCount(0);

        for (EpisodeReview r : reviews) {
            tableModel.addRow(new Object[]{
                    r.getReview_id(),
                    r.getUser_id(),
                    r.getEpisode_id(),
                    r.getReview(),
                    r.getDate_reviewed().toString()
            });
        }
    }
}
