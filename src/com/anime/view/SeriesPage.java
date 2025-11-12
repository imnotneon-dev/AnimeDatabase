package com.anime.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SeriesPage extends JPanel{
    private JButton button = new JButton("SeriesPage");
    private GradientPanel seriesInfoPnl;
    private JPanel episodesPnl = new JPanel();
    private JPanel contentPnl = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(contentPnl);
    private JButton faveBtn = new JButton("❤️", new ImageIcon());
    private JLabel titleLb = new JLabel("My Hero Acaedmia Boku No Hero Academia some gibberish to cehck");
    private JLabel genreLb = new JLabel("Action");
    private JLabel releaseYearLb = new JLabel("2025-09-08");
    private JLabel epCountLb = new JLabel("Episodes: 5");
    private JLabel statusLb = new JLabel("Ongoing");
//    private List<Episode> episodeList;
    private List<String> episodeList = List.of(
            "Episode 1","Episode 2","Episode 3","Episode 4","Episode 5",
        "Episode 6","Episode 7","Episode 8","Episode 9","Episode 10",
        "Episode 11","Episode 12");
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

        seriesInfoPnl = new GradientPanel(biTakorollIcon);
        seriesInfoPnl.setLayout(gb);
        seriesInfoPnl.setBorder(new EmptyBorder(0,70,100,70));
        seriesInfoPnl.setBackground(Color.WHITE);
        seriesInfoPnl.setPreferredSize(new Dimension(1280,700));
//        seriesInfoPnl.setMaximumSize(new Dimension(1280,600));
//        seriesInfoPnl.setPreferredSize(new Dimension(420,720));
//        seriesInfoPnl.setMaximumSize(new Dimension(500, 720));
        seriesInfoPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
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
                1,1,1,5,
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

        faveBtn.setPreferredSize(new Dimension(30,30));
        faveBtn.setMaximumSize(new Dimension(30,30));
        seriesInfoPnl.add(epInfoVSpacer);
        seriesInfoPnl.add(titleLb);
        seriesInfoPnl.add(statusLb);
        seriesInfoPnl.add(genreLb);
        seriesInfoPnl.add(releaseYearLb);
        seriesInfoPnl.add(epCountLb);
        seriesInfoPnl.add(epInfoHSpacer);
        seriesInfoPnl.add(faveBtn);

        episodesPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        episodesPnl.setBackground(Color.black);
        seriesInfoPnl.setPreferredSize(new Dimension(1280,600));
        seriesInfoPnl.setMaximumSize(new Dimension(1280,600));
//        episodesPnl.setPreferredSize(new Dimension(100,100));
        episodesPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        for(String s: episodeList){
            JButton series = new JButton();
            series.setText(s);
            series.setAlignmentX(Component.LEFT_ALIGNMENT);
            episodesPnl.add(series);
        }

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        contentPnl.setLayout(new BoxLayout(contentPnl,BoxLayout.Y_AXIS));
        contentPnl.add(seriesInfoPnl);
        contentPnl.add(episodesPnl);
        add(scrollPane);
        
    }
    public static BufferedImage loadImage(String iresPath)
    {
        BufferedImage image = null;
        try { image = ImageIO.read(AccountPanel.class.getResource(iresPath)); }
        catch (IOException e) { e.printStackTrace(); }
        return image;
    }
    public JButton getButton(){
        return button;
    }
}
