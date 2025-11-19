package com.anime.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HeaderPanel extends JPanel {
    private JMenuItem logoutItem = new JMenuItem();
    private JMenuItem watchHistoryItem = new JMenuItem();
    private JMenuItem likesItem = new JMenuItem();
    private JMenuItem accStatsItem = new JMenuItem();
    private JMenuItem wrappedItem = new JMenuItem();
    private JLabel homeIcon = new JLabel();
    private JLabel accountName = new JLabel();
    private JLabel catalogLb = new JLabel();

    public HeaderPanel(){
        setLayout(new BorderLayout());
        setBackground(Color.yellow);
        setBorder(new EmptyBorder(0,10,0,10));
        setPreferredSize(new Dimension(Integer.MAX_VALUE, 60));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        init();
//        revalidate();
//        repaint();
    }

    private void init(){
        JPanel filler = new JPanel();
        JPanel leftPnl = new JPanel();
        JPanel rightPnl = new JPanel();
        String TAKOROLL_LOGO = "/imgs/tako_hori.png";
        BufferedImage biHomeIcon = loadImage(TAKOROLL_LOGO);
        Image scaled = biHomeIcon.getScaledInstance(160,50,Image.SCALE_SMOOTH);
        ImageIcon homeIconIcon = new ImageIcon(scaled);

        homeIcon.setIcon(homeIconIcon);
        homeIcon.setMaximumSize(new Dimension(160,50));
        homeIcon.setBackground(Color.WHITE);
        homeIcon.setOpaque(true);

        catalogLb.setText("CATALOG");
//        catalogLb.setFont(new Font("SansSerif", Font.BOLD, 14)); // Make it visible and clear

        accountName.setForeground(Color.black);
        leftPnl.add(homeIcon);
        leftPnl.add(catalogLb);
        rightPnl.add(accountName);

        JPopupMenu menu = new JPopupMenu();
        logoutItem = new JMenuItem("Log Out");
        watchHistoryItem = new JMenuItem("Watch History");
        likesItem = new JMenuItem("Liked Episodes");
        accStatsItem = new JMenuItem("Statistics and Review Log");
        wrappedItem = new JMenuItem("Anime Wrapped!");
        menu.add(logoutItem);
        menu.add(watchHistoryItem);
        menu.add(likesItem);
        menu.add(accStatsItem);
        menu.add(wrappedItem);

        add(leftPnl, BorderLayout.WEST);
        add(filler, BorderLayout.CENTER);
        add(rightPnl, BorderLayout.EAST);

        accountName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                accountName.setForeground(Color.red);
                menu.show(accountName,0,accountName.getHeight());
            }

        });

//        revalidate();
//        repaint();
    }

    public static BufferedImage loadImage(String iresPath)
    {
        BufferedImage image = null;
        try { image = ImageIO.read(HeaderPanel.class.getResource(iresPath)); }
        catch (IOException e) { e.printStackTrace(); }
        return image;
    }

    public JMenuItem getLogoutItem() { return logoutItem; }
    public JMenuItem getWatchHistoryItem() { return watchHistoryItem; }
    public JMenuItem getLikesItem() { return likesItem; }
    public JMenuItem getAccStatsItem() { return accStatsItem; }
    public JMenuItem getWrappedItem() { return wrappedItem; }
    public JLabel getHomeIcon() { return homeIcon; }
    public JLabel getAccountName() { return accountName; }
    public JLabel getCatalogLb() { return catalogLb; }
    public void setAccountName(String name) { accountName.setText(name);}
}
