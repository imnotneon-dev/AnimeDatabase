package com.anime.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GradientPanel extends JPanel {
    private final BufferedImage image;

    public GradientPanel(BufferedImage img){
        this.image = img;
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
//        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        int w = getWidth(), h = getHeight();
        if(image != null){
            Image scaled = image.getScaledInstance(w,h,Image.SCALE_SMOOTH);
            g2d.drawImage(scaled,0,0,null);
        }
        Color color1 = new Color(0,0,0,0);
        Color color2 = new Color(0,0,0,255);
//        Color color1 = Color.yellow;
//        Color color2 = Color.orange;
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h-120, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}
