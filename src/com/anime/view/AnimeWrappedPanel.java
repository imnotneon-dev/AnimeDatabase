package com.anime.view;

import com.anime.model.AnimeWrapped;
import com.anime.model.AnimeWrapped.*;
import java.time.Year;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class AnimeWrappedPanel extends JPanel {
    
    private AnimeWrapped wrappedData;
    
    private JLabel yearLabel = new JLabel(String.valueOf(Year.now().getValue()));
    private JLabel usernameLabel = new JLabel("Username");
    private JLabel totalEpisodesLabel = new JLabel("0");
    private JLabel topGenreLabel = new JLabel("N/A");
    
    private JPanel top5SeriesPanel = new JPanel();
    private JPanel actorsPanel = new JPanel(); 
    
    private JScrollPane scrollPane;
    private JPanel contentPanel = new JPanel();
    
    public AnimeWrappedPanel() {
        init();
    }
    
    private void init() {
        setOpaque(true);
        setVisible(true);
        setBackground(Color.decode("#1a1a1a"));
        setLayout(new BorderLayout());
        
        initComponents();
    }
    
    private void initComponents() {
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.decode("#1a1a1a"));
        contentPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
        
        JPanel headerPanel = createHeaderSection();
        JPanel statsPanel = createStatsSection();
        JPanel seriesSection = createTop5SeriesSection();
        JPanel actorSection = createTop3ActorsSection();
        
        contentPanel.add(headerPanel);
        contentPanel.add(Box.createVerticalStrut(30));
        contentPanel.add(statsPanel);
        contentPanel.add(Box.createVerticalStrut(30));
        contentPanel.add(seriesSection);
        contentPanel.add(Box.createVerticalStrut(30));
        contentPanel.add(actorSection);
        contentPanel.add(Box.createVerticalGlue());
        
        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private JPanel createHeaderSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel title = new JLabel("Your Anime Wrapped");
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        yearLabel.setFont(new Font("Arial", Font.BOLD, 36));
        yearLabel.setForeground(Color.decode("#1DB954")); 
        yearLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameLabel.setForeground(Color.LIGHT_GRAY);
        usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(yearLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(usernameLabel);
        
        return panel;
    }
    
    private JPanel createStatsSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2, 20, 0));
        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        
        JPanel episodesCard = createStatCard("Episodes Watched", totalEpisodesLabel, Color.decode("#E91E63"));
        JPanel genreCard = createStatCard("Top Genre", topGenreLabel, Color.decode("#9C27B0"));
        
        panel.add(episodesCard);
        panel.add(genreCard);
        
        return panel;
    }
    
    private JPanel createStatCard(String title, JLabel valueLabel, Color accentColor) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.decode("#282828"));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(accentColor, 2),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        titleLabel.setForeground(Color.LIGHT_GRAY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        valueLabel.setFont(new Font("Arial", Font.BOLD, 32));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(valueLabel);
        
        return card;
    }
    
    private JPanel createTop5SeriesSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel sectionTitle = new JLabel("Your Top 5 Anime Series");
        sectionTitle.setFont(new Font("Arial", Font.BOLD, 28));
        sectionTitle.setForeground(Color.WHITE);
        sectionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        top5SeriesPanel.setLayout(new BoxLayout(top5SeriesPanel, BoxLayout.Y_AXIS));
        top5SeriesPanel.setOpaque(false);
        top5SeriesPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panel.add(sectionTitle);
        panel.add(Box.createVerticalStrut(20));
        panel.add(top5SeriesPanel);
        
        return panel;
    }
    
    private JPanel createTop3ActorsSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel sectionTitle = new JLabel("Top 3 Most Watched Voice Actors");
        sectionTitle.setFont(new Font("Arial", Font.BOLD, 28));
        sectionTitle.setForeground(Color.WHITE);
        sectionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        actorsPanel.setLayout(new BoxLayout(actorsPanel, BoxLayout.Y_AXIS));
        actorsPanel.setOpaque(false);
        actorsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panel.add(sectionTitle);
        panel.add(Box.createVerticalStrut(20));
        panel.add(actorsPanel);
        
        return panel;
    }

    public void loadWrappedData(AnimeWrapped wrapped) {
        this.wrappedData = wrapped;
        
        yearLabel.setText(String.valueOf(wrapped.getYear()));
        usernameLabel.setText("@" + wrapped.getUsername());
        
        totalEpisodesLabel.setText(String.valueOf(wrapped.getTotalEpisodesWatched()));
        topGenreLabel.setText(wrapped.getTopGenre());
        
        loadTop5Series(wrapped.getTop5Series());
        loadTop3Actors(wrapped.getTop3VoiceActors(), wrapped.getActorRoles());
        
        revalidate();
        repaint();
    }
    
    private void loadTop5Series(List<SeriesStats> seriesList) {
        top5SeriesPanel.removeAll();
        
        if (seriesList == null || seriesList.isEmpty()) {
            JLabel noData = new JLabel("No series data available");
            noData.setForeground(Color.GRAY);
            top5SeriesPanel.add(noData);
            return;
        }
        
        int rank = 1;
        for (SeriesStats series : seriesList) {
            JPanel seriesCard = createSeriesCard(rank, series);
            top5SeriesPanel.add(seriesCard);
            top5SeriesPanel.add(Box.createVerticalStrut(10));
            rank++;
        }
        
        top5SeriesPanel.revalidate();
        top5SeriesPanel.repaint();
    }
    
    private JPanel createSeriesCard(int rank, SeriesStats series) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(15, 0));
        card.setBackground(Color.decode("#282828"));
        card.setBorder(new EmptyBorder(15, 15, 15, 15));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        
        JLabel rankLabel = new JLabel("#" + rank);
        rankLabel.setFont(new Font("Arial", Font.BOLD, 24));
        rankLabel.setForeground(Color.decode("#1DB954"));
        rankLabel.setPreferredSize(new Dimension(50, 50));
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        
        JLabel titleLabel = new JLabel(series.getTitle());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel detailLabel = new JLabel(series.getGenre() + " • " + 
                                       series.getEpisodesWatched() + " episodes");
        detailLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        detailLabel.setForeground(Color.LIGHT_GRAY);
        
        infoPanel.add(titleLabel);
        infoPanel.add(detailLabel);
        
        card.add(rankLabel, BorderLayout.WEST);
        card.add(infoPanel, BorderLayout.CENTER);
        
        return card;
    }
    
    private void loadTop3Actors(List<ActorStats> actors, List<ActorRole> allRoles) {
        actorsPanel.removeAll();
        
        if (actors == null || actors.isEmpty()) {
            JLabel noData = new JLabel("No voice actor data available");
            noData.setForeground(Color.GRAY);
            actorsPanel.add(noData);
            actorsPanel.revalidate();
            actorsPanel.repaint();
            return;
        }
        
        int rank = 1;
        for (ActorStats actor : actors) {
            List<ActorRole> actorRoles = allRoles.stream()
                .filter(role -> role.getActorId() == actor.getActorId())
                .collect(Collectors.toList());
            
            JPanel actorCard = createActorCard(rank, actor, actorRoles);
            actorsPanel.add(actorCard);
            actorsPanel.add(Box.createVerticalStrut(20));
            rank++;
        }
        
        actorsPanel.revalidate();
        actorsPanel.repaint();
    }
    

    private JPanel createActorCard(int rank, ActorStats actor, List<ActorRole> roles) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.decode("#282828"));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#1DB954"), 2),
            new EmptyBorder(20, 20, 20, 20)
        ));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel rankLabel = new JLabel("#" + rank);
        rankLabel.setFont(new Font("Arial", Font.BOLD, 32));
        rankLabel.setForeground(Color.decode("#1DB954"));
        
        JPanel actorInfoPanel = new JPanel();
        actorInfoPanel.setLayout(new BoxLayout(actorInfoPanel, BoxLayout.Y_AXIS));
        actorInfoPanel.setOpaque(false);
        
        JLabel actorName = new JLabel(actor.getFullName());
        actorName.setFont(new Font("Arial", Font.BOLD, 24));
        actorName.setForeground(Color.WHITE);
        actorName.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel actorAgency = new JLabel(actor.getAgency());
        actorAgency.setFont(new Font("Arial", Font.PLAIN, 14));
        actorAgency.setForeground(Color.LIGHT_GRAY);
        actorAgency.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel episodesCount = new JLabel("Appeared in " + actor.getEpisodesAppearedIn() + 
                                         " episodes you watched");
        episodesCount.setFont(new Font("Arial", Font.BOLD, 12));
        episodesCount.setForeground(Color.decode("#1DB954"));
        episodesCount.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        actorInfoPanel.add(actorName);
        actorInfoPanel.add(Box.createVerticalStrut(3));
        actorInfoPanel.add(actorAgency);
        actorInfoPanel.add(Box.createVerticalStrut(8));
        actorInfoPanel.add(episodesCount);
        
        headerPanel.add(rankLabel, BorderLayout.WEST);
        headerPanel.add(Box.createHorizontalStrut(15), BorderLayout.CENTER);
        headerPanel.add(actorInfoPanel, BorderLayout.EAST);
        
        card.add(headerPanel);
        card.add(Box.createVerticalStrut(15));
        
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.decode("#404040"));
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        card.add(separator);
        card.add(Box.createVerticalStrut(15));
        
        if (roles != null && !roles.isEmpty()) {
            JLabel rolesTitle = new JLabel("Top Roles:");
            rolesTitle.setFont(new Font("Arial", Font.BOLD, 16));
            rolesTitle.setForeground(Color.WHITE);
            rolesTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
            card.add(rolesTitle);
            card.add(Box.createVerticalStrut(10));
            
            int roleNum = 1;
            for (ActorRole role : roles) {
                JPanel rolePanel = new JPanel();
                rolePanel.setLayout(new BoxLayout(rolePanel, BoxLayout.Y_AXIS));
                rolePanel.setOpaque(false);
                rolePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                
                JLabel roleLabel = new JLabel(roleNum + ". " + role.getCharacterName());
                roleLabel.setFont(new Font("Arial", Font.BOLD, 14));
                roleLabel.setForeground(Color.WHITE);
                roleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                
                JLabel seriesLabel = new JLabel("   from " + role.getSeriesTitle() + 
                                               " (" + role.getEpisodesWatched() + " episodes)");
                seriesLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                seriesLabel.setForeground(Color.LIGHT_GRAY);
                seriesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                
                rolePanel.add(roleLabel);
                rolePanel.add(seriesLabel);
                
                card.add(rolePanel);
                card.add(Box.createVerticalStrut(8));
                roleNum++;
            }
        } else {
            JLabel noRoles = new JLabel("No role data available");
            noRoles.setFont(new Font("Arial", Font.ITALIC, 12));
            noRoles.setForeground(Color.GRAY);
            noRoles.setAlignmentX(Component.LEFT_ALIGNMENT);
            card.add(noRoles);
        }
        
        return card;
    }
    
    public AnimeWrapped getWrappedData() {
        return wrappedData;
    }
}