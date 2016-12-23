package com.spaceraider.GUI;


//import SinglePlayer.SinglePlayer;

import com.spaceraider.Database.Database;
import com.spaceraider.Database.UserScore;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HighscorePanel extends BackGroundPanel {

    private JFrame frame;
    private double width;
    private double height;
    private JButton goToStart;
    private Database dbInstance;
    private List<UserScore> highscores;
    private String username;
    private int id;

    private JLabel[] lblNames = new JLabel[10];     // FIXME: constante van die 10 maken
    private JLabel[] lblScores = new JLabel[10];

    public HighscorePanel(JFrame frame, String username, int id) {
        this.id = id;
        this.username = username;
        dbInstance = new Database();
        highscores = dbInstance.getHighscores();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        setPreferredSize(new Dimension((int) width, (int) height));
        setLayout(null);
        this.frame = frame;
        initComponents();
        addComponents();
        actionListener();
        ShowScore();
    }

    private void initComponents() {
        goToStart = new JButton(("Go To MenuSelector"));
        goToStart.setBounds((int) (width / 2) + (50), (int) (height / 2) + 300, 160, 50);

        for(int i = 0; i < lblNames.length; i++)
        {
            lblNames[i] = new JLabel();
            lblScores[i] = new JLabel();

            lblNames[i].setBounds(750, 50 * (i+1), 320, 50);
            lblNames[i].setFont(new Font("Serif",Font.BOLD,50));
            lblNames[i].setForeground(Color.WHITE);
            lblScores[i].setBounds(1050, 50 * (i+1), 320, 50);
            lblScores[i].setFont(new Font("Serif",Font.BOLD,50));
            lblScores[i].setForeground(Color.WHITE);
        }
    }

    private void addComponents() {
        add(goToStart);

        for(int i = 0; i < lblNames.length; i++)
        {
            add(lblNames[i]);
            add(lblScores[i]);
        }
    }

    private void actionListener() {
        goToStart.addActionListener(e -> {
            MenuSelectorPanel menuPanel = new MenuSelectorPanel(frame, username , id);
            frame.getContentPane().removeAll();
            frame.getContentPane().invalidate();
            frame.getContentPane().add(menuPanel);
            frame.getContentPane().revalidate();
        });
    }

    private void ShowScore()
    {
        int guiIdx = 0;

        for(UserScore userScore : highscores) {
            lblNames[guiIdx].setText(userScore.getUserName());
            lblScores[guiIdx].setText(Integer.toString(userScore.getScore()));

            guiIdx ++;
        }
    }

}
