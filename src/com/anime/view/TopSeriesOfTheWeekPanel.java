package com.anime.view;

import com.anime.model.TopSeriesOfTheWeek;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TopSeriesOfTheWeekPanel extends JPanel {

    private JPanel mainContainer = new JPanel();
    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JScrollPane scrollPane;

    public TopSeriesOfTheWeekPanel() {
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.decode("#212121"));

        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setOpaque(false);
        mainContainer.setBorder(new EmptyBorder(20, 40, 20, 40));

        initHeader();
        initContent();

        add(mainContainer, BorderLayout.CENTER);
    }

    private void initHeader() {
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel title = new JLabel("Top Anime Series of the Week");
        title.setFont(new Font("SansSerif", Font.BOLD, 32));
        title.setForeground(Color.decode("#FFD700"));
        title.setBorder(new EmptyBorder(10, 0, 20, 0));
        title.setHorizontalAlignment(SwingConstants.LEFT);

        headerPanel.add(title, BorderLayout.WEST);
        mainContainer.add(headerPanel);
    }

    private void initContent() {
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.decode("#121212"));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        mainContainer.add(scrollPane);
    }

    public void loadTopSeries(List<TopSeriesOfTheWeek> seriesList) {
        contentPanel.removeAll();

        if (seriesList == null || seriesList.isEmpty()) {
            JLabel noData = new JLabel("No series data available");
            noData.setForeground(Color.decode("#808080"));
            noData.setFont(new Font("SansSerif", Font.ITALIC, 16));
            contentPanel.add(noData);
        } else {
            int rank = 1;
            for (TopSeriesOfTheWeek series : seriesList) {
                JPanel card = createSeriesCard(rank, series);
                contentPanel.add(card);
                contentPanel.add(Box.createVerticalStrut(15));
                rank++;
            }
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JPanel createSeriesCard(int rank, TopSeriesOfTheWeek series) {

        JPanel card = new JPanel(new BorderLayout(15, 0));
        card.setBorder(new EmptyBorder(15, 15, 15, 15));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        card.setBackground(Color.decode("#282828"));
        card.setOpaque(true);

        JLabel rankLabel = new JLabel("#" + rank);
        rankLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        rankLabel.setForeground(getRankColor(rank));
        rankLabel.setPreferredSize(new Dimension(60, 50));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        JLabel titleLabel = new JLabel(series.getTitle());
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(Color.decode("#FFFFFF"));

        JLabel detailLabel = new JLabel(series.getTotalViewers() + " viewers • "
                + String.format("%.2f", series.getPercentage()) + "% of users");
        detailLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        detailLabel.setForeground(Color.decode("#C8C8C8"));

        infoPanel.add(titleLabel);
        infoPanel.add(detailLabel);

        card.add(rankLabel, BorderLayout.WEST);
        card.add(infoPanel, BorderLayout.CENTER);

        return card;
    }

    private Color getRankColor(int rank) {
        switch (rank) {
            case 1: return Color.decode("#FFD700");
            case 2: return Color.decode("#C0C0C0");
            case 3: return Color.decode("#CD7F32");
            default: return Color.decode("#B4B4B4");
        }
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}
