package com.anime.view;

import com.anime.model.Actor;
import com.anime.model.Episode;
import com.anime.model.Series;
import com.anime.view.customcards.SeriesEpisodeCard;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SeriesPage extends JPanel{
    private Series series;
    private GradientPanel seriesInfoPnl;
    private JPanel episodesPnl = new JPanel();
    private JPanel contentPnl = new JPanel();
    private JPanel actorsContainer = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(contentPnl);

    private JButton faveBtn = new JButton("❤️", new ImageIcon());
    private JLabel titleLb = new JLabel("My Hero Acaedmia Boku No Hero Academia some gibberish to cehck");
    private JLabel genreLb = new JLabel("Action");
    private JLabel releaseYearLb = new JLabel("2025");
    private JLabel epCountLb = new JLabel("Episodes: 5");
    private JLabel statusLb = new JLabel("Ongoing");

    private List<Episode> episodeList = new ArrayList<>();
    private List<Actor> actorsList = new ArrayList<>();

    private List<SeriesEpisodeCard> episodeCards = new ArrayList<>();
    private List<JLabel> actorLabelCards = new ArrayList<>();
    /*
    private List<String> episodeList = List.of(
            "Episode 1","Episode 2","Episode 3","Episode 4","Episode 5",
        "Episode 6","Episode 7","Episode 8","Episode 9","Episode 10",
        "Episode 11","Episode 12");
    private List<String> actorsList = List.of("Actor 1", "Actor 2", "Actor 3");
    */
  private GridBagLayout gb = new GridBagLayout();
    /*public SeriesPage (Series series, List<Episodes> epList){
        // take the info from the series passed then use that to display stuff
        titleLb.setText();
        genreLb.setText();
        releaseYearLb.setText();
        epCountLb.setText();
        statusLb.setText();
    }*/

    public SeriesPage() {
        init();
        reloadSeriesPage();
    }
    private void init() {
        setOpaque(true);
        setVisible(true);
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        initComponents();

    }

    private void initComponents(){
        String TAKOROLL_LOGO = "/imgs/takoroll_logo.png";
        BufferedImage biTakorollIcon = loadImage(TAKOROLL_LOGO);

        JPanel epInfoHSpacer = new JPanel();
        epInfoHSpacer.setOpaque(false);
        JPanel epInfoVSpacer = new JPanel();
        epInfoVSpacer.setOpaque(false);

        JLabel actorsListLb = new JLabel("Notable Actors:     ");
        actorsContainer.add(actorsListLb);
        actorsContainer.setLayout(new BoxLayout(actorsContainer, BoxLayout.Y_AXIS));
        actorsContainer.setOpaque(false);

        seriesInfoPnl = new GradientPanel(biTakorollIcon);
        seriesInfoPnl.setLayout(gb);
        seriesInfoPnl.setBorder(new EmptyBorder(0,70,100,70));
        seriesInfoPnl.setBackground(Color.WHITE);
        seriesInfoPnl.setPreferredSize(new Dimension(1280,700));
        seriesInfoPnl.setMaximumSize(new Dimension(1280,700));
//        seriesInfoPnl.setPreferredSize(new Dimension(420,720));
//        seriesInfoPnl.setMaximumSize(new Dimension(500, 720));
        seriesInfoPnl.setAlignmentX(Component.LEFT_ALIGNMENT);
        gb.setConstraints(epInfoVSpacer, new GridBagConstraints(
                0,0,3,1,
                0.0,0.7,
                GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL,
                new Insets(0,0,20,0),0,0));
        gb.setConstraints(titleLb, new GridBagConstraints(0,1,1,1,0.0,0.0,GridBagConstraints.LINE_START,GridBagConstraints.HORIZONTAL,new Insets(0,10,0,10),0,0));
        gb.setConstraints(statusLb, new GridBagConstraints(0,2,1,1,0.0,0.0,GridBagConstraints.LINE_START,GridBagConstraints.HORIZONTAL,new Insets(0,10,0,10),0,0));
        gb.setConstraints(genreLb, new GridBagConstraints(0,3,1,1,0.0,0.0,GridBagConstraints.LINE_START,GridBagConstraints.HORIZONTAL,new Insets(0,10,0,10),0,0));
        gb.setConstraints(releaseYearLb, new GridBagConstraints(0,4,1,1,0.0,0.0,GridBagConstraints.LINE_START,GridBagConstraints.HORIZONTAL,new Insets(0,10,0,10),0,0));
        gb.setConstraints(epCountLb, new GridBagConstraints(0,5,1,1,0.0,0.0,GridBagConstraints.LINE_START,GridBagConstraints.HORIZONTAL,new Insets(0,10,0,10),0,0));
        gb.setConstraints(epInfoHSpacer, new GridBagConstraints(
                1,1,1,1,
                1.0,0.0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                new Insets(0,0,0,0),0,0));
        gb.setConstraints(faveBtn, new GridBagConstraints(
                2,1,1,1,
                0.0,0.0,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                new Insets(0,10,0,10),0,0));
        gb.setConstraints(actorsContainer, new GridBagConstraints(
                0,6,
                1,1,
                0.0,0.0,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                new Insets(0,10,0,10),0,0));

        faveBtn.setPreferredSize(new Dimension(40,40));
        faveBtn.setMaximumSize(new Dimension(40,40));
        faveBtn.setBorder(new EmptyBorder(0,0,0,0));
        faveBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        seriesInfoPnl.add(epInfoVSpacer);
        seriesInfoPnl.add(titleLb);
        seriesInfoPnl.add(statusLb);
        seriesInfoPnl.add(genreLb);
        seriesInfoPnl.add(releaseYearLb);
        seriesInfoPnl.add(epCountLb);
        seriesInfoPnl.add(epInfoHSpacer);
        seriesInfoPnl.add(faveBtn);
        actorsContainer.add(actorsListLb);
        seriesInfoPnl.add(actorsContainer);

        episodesPnl.setLayout(new GridLayout(0,4,5,5));
        episodesPnl.setBorder(new EmptyBorder(0,25,35,40));
        episodesPnl.setBackground(Color.black);
//        episodesPnl.setPreferredSize(new Dimension(1280,600));
        episodesPnl.setMaximumSize(new Dimension(1280,Integer.MAX_VALUE));
        episodesPnl.setAlignmentX(Component.LEFT_ALIGNMENT);
        episodesPnl.setPreferredSize(new Dimension(1200, (int) (Math.ceil(episodeList.size() / 5.0) * 150)));

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(1,Integer.MAX_VALUE));
        scrollPane.getVerticalScrollBar().setVisible(false);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        contentPnl.setLayout(new BoxLayout(contentPnl,BoxLayout.Y_AXIS));
        contentPnl.add(seriesInfoPnl);
        contentPnl.add(episodesPnl);
        add(scrollPane);
        
    }
    public static BufferedImage loadImage(String iresPath)
    {
        BufferedImage image = null;
        try { image = ImageIO.read(SeriesPage.class.getResource(iresPath)); }
        catch (IOException e) { e.printStackTrace(); }
        return image;
    }

    private void defaultLabels() {
        titleLb.setText("No Series Selected");
        genreLb.setText("");
        releaseYearLb.setText("");
        epCountLb.setText("");
        statusLb.setText("");
    }

    private void reloadSeriesPage(){
        if(this.series==null){
            defaultLabels();
            return;
        }

        titleLb.setText(this.series.getTitle());
        genreLb.setText(this.series.getGenre());
        releaseYearLb.setText("Release Year: " + this.series.getReleaseYear());
        epCountLb.setText("Total Number of Episodes: " + this.series.getTotalEpisodes());
        statusLb.setText("Status: " + this.series.getStatus());

        loadEpisodeCards();
        loadActorLabelCards();

        contentPnl.revalidate();
        contentPnl.repaint();

    }
    private void loadEpisodeCards(){
        episodesPnl.removeAll();
        episodeCards.clear();
        if(episodeList!=null){
            for(Episode e: episodeList){
                SeriesEpisodeCard epCard = new SeriesEpisodeCard(e.getEpisodeId(),
                        e.getTitle(),e.getSypnosis(),e.getRuntime(),
                        e.getReleaseDate());
                epCard.setAlignmentX(Component.LEFT_ALIGNMENT);
                episodeCards.add(epCard);
                episodesPnl.add(epCard);
            }
            episodesPnl.setPreferredSize(new Dimension(1200, (int) (Math.ceil(episodeList.size() / 5.0) * 150)));
        }
        else {
            episodesPnl.add(new JLabel("No episodes yet..."));
        }
        episodesPnl.revalidate();
        episodesPnl.repaint();
    }

    private void loadActorLabelCards(){
        actorsContainer.removeAll();
        actorLabelCards.clear();
        JLabel actorsListLb = new JLabel("Notable Actors:     ");
        actorsContainer.add(actorsListLb);
        if(actorsList!=null){
            for(Actor a: actorsList){
                JLabel actor = new JLabel(a.getLastName() + ", " + a.getFirstName());
                actor.putClientProperty("actor_id",a.getId());
                actorLabelCards.add(actor);
                actorsContainer.add(actor);
                actorsContainer.add(Box.createVerticalStrut(5));
            }
        }
        actorsContainer.revalidate();
        actorsContainer.repaint();
    }
    public Series getSeries() { return series; }

    public void setSeries(Series series) {
        this.series = series;
        reloadSeriesPage();
    }

    public List<SeriesEpisodeCard> getEpisodeCards(){
        return episodeCards;
    }
    public List<JLabel> getActorLabelCards(){
        return actorLabelCards;
    }
    public List<Episode> getEpisodeList() { return episodeList; }

    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
        loadEpisodeCards();
    }

    public List<Actor> getActorsList() { return actorsList; }

    public void setActorsList(List<Actor> actorsList) {
        this.actorsList = actorsList;
        loadActorLabelCards();
//        reloadSeriesPage();

    }

}
