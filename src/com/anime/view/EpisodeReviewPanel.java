package com.anime.view;

import com.anime.model.EpisodeReview;
import com.anime.model.dao.EpisodeDAO;
import com.anime.model.dao.EpisodeReviewDAO;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class EpisodeReviewPanel extends JPanel {

    private EpisodeReviewDAO reviewDAO = new EpisodeReviewDAO();
    private EpisodeDAO episodeDAO = new EpisodeDAO();

    private JComboBox<String> episodeSelector;
    private JButton loadAllBtn;
    private JTable reviewTable;
    private DefaultTableModel tableModel;

    // palette
    private final Color BG_MAIN = Color.decode("#121212");
    private final Color BG_BOX = Color.decode("#282828");
    private final Color TXT_TITLE = Color.decode("#FFD700");
    private final Color TXT_NORMAL = Color.decode("#FFFFFF");
    private final Color TXT_SECONDARY = Color.decode("#C8C8C8");

    public EpisodeReviewPanel() {

        setLayout(new BorderLayout());
        setBackground(BG_MAIN);


        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(BG_BOX);

        JLabel filterLabel = new JLabel("Filter by Episode: ");
        filterLabel.setForeground(TXT_TITLE);

        episodeSelector = new JComboBox<>();
        episodeSelector.setPreferredSize(new Dimension(250, 30));
        episodeSelector.setBackground(BG_MAIN);
        episodeSelector.setForeground(TXT_NORMAL);

        loadAllBtn = new JButton("Show All Reviews");
        loadAllBtn.setBackground(BG_MAIN);
        loadAllBtn.setForeground(TXT_TITLE);

        topPanel.add(filterLabel);
        topPanel.add(episodeSelector);
        topPanel.add(loadAllBtn);

        add(topPanel, BorderLayout.NORTH);


        tableModel = new DefaultTableModel(
                new Object[]{"Review ID", "Username", "Episode Title", "Review", "Date Reviewed"}, 0
        );

        reviewTable = new JTable(tableModel);
        reviewTable.setRowHeight(25);
        reviewTable.setBackground(BG_BOX);
        reviewTable.setForeground(TXT_NORMAL);
        reviewTable.setGridColor(Color.decode("#1A1A1A"));


        reviewTable.getTableHeader().setBackground(Color.decode("#000000"));
        reviewTable.getTableHeader().setForeground(TXT_TITLE);


        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setForeground(TXT_SECONDARY);
        renderer.setBackground(BG_BOX);
        reviewTable.setDefaultRenderer(Object.class, renderer);

        JScrollPane scrollPane = new JScrollPane(reviewTable);
        scrollPane.getViewport().setBackground(BG_MAIN);
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);


        loadEpisodeList();
        loadAllReviews();

        episodeSelector.addActionListener(e -> loadReviewsByEpisode());
        loadAllBtn.addActionListener(e -> loadAllReviews());
    }

    private void loadEpisodeList() {
        try {
            List<String> episodes = episodeDAO.getAllEpisodeTitles();
            episodeSelector.addItem("Select Episode");
            for (String title : episodes) {
                episodeSelector.addItem(title);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading episodes.");
        }
    }

    private void loadAllReviews() {
        try {
            List<EpisodeReview> reviews = reviewDAO.getAllReviews();
            populateTable(reviews);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading reviews.");
        }
    }

    private void loadReviewsByEpisode() {
        String selected = (String) episodeSelector.getSelectedItem();
        if (selected == null || selected.equals("Select Episode")) return;

        try {
            int episodeId = episodeDAO.getEpisodeIdByTitle(selected);
            List<EpisodeReview> reviews = reviewDAO.getReviewsByEpisodeId(episodeId);
            populateTable(reviews);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading episode reviews.");
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