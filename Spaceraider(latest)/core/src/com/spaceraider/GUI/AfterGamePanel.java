package com.spaceraider.GUI;

import com.spaceraider.Database.Database;

import javax.swing.*;
import java.awt.*;


public class AfterGamePanel extends BackGroundPanel {
    private JButton buttonMenu;
    private JButton buttonPlayAgain;
    private JLabel logo;
    private Frame frame;
    private double width;
    private double height;
    private Database database = new Database();

    private String username;
    private int id;
    private int finalScore;

    public AfterGamePanel(Frame frame, String username, int id, int score) {
        this.username = username;
        this.id = id;
        this.finalScore = score;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        setPreferredSize(new Dimension((int) width, (int) height));
        setLayout(null);
        this.frame=frame;
        initComponents();
        addComponents();
        actionListener();
    }

    private void actionListener() {
        buttonMenu.addActionListener(e -> {

                    //echte code
                    MenuSelectorPanel panel = new MenuSelectorPanel(frame,username,id);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().invalidate();
                    frame.getContentPane().add(panel);
                    frame.getContentPane().revalidate();
                });

        buttonPlayAgain.addActionListener(e -> {
            RegisterPanel registerPanel = new RegisterPanel(frame,database);
            frame.getContentPane().removeAll();
            frame.getContentPane().invalidate();
            frame.getContentPane().add(registerPanel);
            frame.getContentPane().revalidate();

        });

    }

    private void addComponents() {
        add(buttonMenu);
        add(buttonPlayAgain);
        add(logo);
    }

    private void initComponents() {
        buttonMenu = new JButton("Go to Menu");
        buttonMenu.setBounds((int) (width / 2) - (160 / 2), (int) (height / 2) - 150, 160, 50);
        buttonPlayAgain = new JButton("Play Again");
        buttonPlayAgain.setBounds((int) (width / 2) - (160 / 2), (int) (height / 2) + 125, 160, 50);
        logo = new JLabel();
        logo.setBounds((int) (width / 2) - (400 / 2), 25, 600, 200);
        logo.setText(String.valueOf(finalScore));
    }

    public void setFrame(Frame frame){
        this.frame = frame;
    }

}
