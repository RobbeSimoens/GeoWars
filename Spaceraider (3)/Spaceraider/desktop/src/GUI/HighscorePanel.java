package GUI;


//import Player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HighscorePanel extends BackGroundPanel {

    private JFrame frame;
    private double width;
    private double height;
    private JButton playAgain;
    private JButton goToStart;
    private JLabel scoreBoardName;
    private JLabel scoreBoardPoints;
    private JLabel scoreBoardIcon;
    private JLabel winner;
    //private ArrayList<Player> players = new ArrayList<>();
    //private ArrayList<Player> scores = new ArrayList<>();


    public HighscorePanel(JFrame frame /*SpaceRaider game*/) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        setPreferredSize(new Dimension((int) width, (int) height));
        setLayout(null);
        this.frame = frame;
        //this.game = game;
        //players.addAll(game.getPlayers());
        initComponents();
        addComponents();
        actionListener();
        ShowScore();
    }

    private void initComponents() {
        playAgain = new JButton("Play Again");
        playAgain.setBounds((int) (width / 2) - (400 / 2), (int) (height / 2) + 300, 160, 50);
        goToStart = new JButton(("Go To MenuSelector"));
        goToStart.setBounds((int) (width / 2) + (50), (int) (height / 2) + 300, 160, 50);
        winner = new JLabel("", SwingConstants.CENTER);
        winner.setBounds((int) (width / 2) - (350), 50, 700, 100);
        winner.setForeground(Color.WHITE);
        winner.setFont((new Font(winner.getName(), Font.PLAIN, 50)));
        winner.setBackground(new Color(0, 0, 0, 125));
        winner.setOpaque(true);
    }

    private void addComponents() {
        add(winner);
        add(playAgain);
        add(goToStart);
    }

    private void actionListener() {
        goToStart.addActionListener(e -> {
            MenuSelectorPanel menuPanel = new MenuSelectorPanel(frame);
            frame.getContentPane().removeAll();
            frame.getContentPane().invalidate();
            frame.getContentPane().add(menuPanel);
            frame.getContentPane().revalidate();
        });

        playAgain.addActionListener(e -> {

        });
    }

    private void sortScores() {
    //    int highestScoreInLoop = 0;
       // Player playerToRemove = null;

//        for (int i = 0; i < game.getPlayers().size(); i++) {
  //          for (/*Player player : players*/) {
    //            if (/*player.getScore() > highestScoreInLoop*/) {
                   // highestScoreInLoop = player.getScore();
                //    playerToRemove = player;


      //      highestScoreInLoop = 0;
         //   players.remove(playerToRemove);
           // scores.add(playerToRemove);
        //}
    }

    private void ShowScore() {
        sortScores();

        int i = 0;
     //   for (/*Player player : scores*/) {
            //scoreBoardName = new JLabel(player.getName(), SwingConstants.CENTER);
            scoreBoardName.setForeground(Color.WHITE);
            scoreBoardName.setBackground(new Color(0, 0, 0, 125));
            scoreBoardName.setOpaque(true);
            scoreBoardName.setFont((new Font(scoreBoardName.getName(), Font.PLAIN, 40)));
            scoreBoardName.setBounds((int) (width / 2) - (550), 200 + (i * 150), 250, 50);

          //  scoreBoardPoints = new JLabel("" + player.getScore(), SwingConstants.CENTER);
            scoreBoardPoints.setForeground(Color.WHITE);
            scoreBoardPoints.setBackground(new Color(0, 0, 0, 125));
            scoreBoardPoints.setOpaque(true);
            scoreBoardPoints.setFont((new Font(scoreBoardPoints.getName(), Font.PLAIN, 40)));
            scoreBoardPoints.setBounds((int) (width) - ((int) (width / 2) - (300)), 200 + (i * 150), 100, 50);



            add(scoreBoardName);
            add(scoreBoardPoints);
            add(scoreBoardIcon);

            if (i == 0) {
                winner.setText("Game Over");
            }
            i++;
        }
    }

//}
