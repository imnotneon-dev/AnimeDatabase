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
        JLabel user = new JLabel(username);
        JTextArea review = new JTextArea(userReview);

//        int lines = review.getLineCount();
//        review.setRows(Math.max(lines,3));
//        review.setText(userReview);
        review.setAlignmentX(Component.LEFT_ALIGNMENT);
        review.setLineWrap(true);
        review.setWrapStyleWord(true);
        review.setEditable(false);
        review.setOpaque(true);
        review.setAlignmentX(Component.LEFT_ALIGNMENT);
        review.setBorder(BorderFactory.createEmptyBorder());

        user.setAlignmentX(Component.LEFT_ALIGNMENT);
        review.setAlignmentX(Component.LEFT_ALIGNMENT);

        setOpaque(true);
        setVisible(true);
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(5,10,5,5));
//        setPreferredSize(new Dimension(200,160));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Short.MAX_VALUE));

        add(user);
        add(review);

        revalidate();
        repaint();

    }
}
