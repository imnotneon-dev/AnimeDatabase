package com.anime.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
//import com.anime.model.Review;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

public class ReviewCard extends JPanel {
    //    private Review review;
    public ReviewCard(String username, String userReview){
        setOpaque(true);
        setVisible(true);
        setBackground(Color.RED);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(5,5,10,5));
//        setPreferredSize(new Dimension(1280,IN));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, this.getPreferredSize().height));

        JLabel user = new JLabel(username);
        JTextArea review = new JTextArea(3,40);

        int lines = review.getLineCount();
        review.setRows(Math.max(lines,3));
        review.setText(userReview);
        review.setAlignmentX(Component.LEFT_ALIGNMENT);
        review.setLineWrap(true);
        review.setWrapStyleWord(true);
        review.setEditable(false);
        review.setOpaque(false);
        review.setBorder(BorderFactory.createEmptyBorder());

        user.setAlignmentX(LEFT_ALIGNMENT);
        review.setAlignmentX(LEFT_ALIGNMENT);

        add(user);
        add(review);

        revalidate();
        repaint();

    }
}
