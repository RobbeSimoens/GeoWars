package com.spaceraider.GUI;



import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.spaceraider.game.Spaceraider;


import javax.swing.*;
import java.awt.*;


public class MenuSelectorPanel extends BackGroundPanel {
    private JButton buttonCampaign;
    private JButton buttonMultiplayer;
    private JButton buttonArcade;
    private JButton buttonHighscores;
    private JLabel logo;
    private JFrame frame;
    private double width;
    private double height;
    private String username;
    private int id;

    public MenuSelectorPanel(JFrame frame, String username , int id) {
        this.id = id;
        this.username = username;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        setPreferredSize(new Dimension((int) width, (int) height));
        setLayout(null);
        this.frame = frame;
        initComponents();
        addComponents();
        actionListener();
    }

    private void actionListener() {
        buttonCampaign.addActionListener(e -> {
            frame.dispose();
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
            config.title = "Space Raider";
            config.width = 1920;
            config.height = 1080;
            config.resizable = false;
            new LwjglApplication(new Spaceraider("singleplayer", username, id), config);
            frame = new Frame(100,"fuck",-1);

        });

        buttonMultiplayer.addActionListener(e -> {
            frame.dispose();
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
            config.title = "Space Raider";
            config.width = 1920;
            config.height = 1080;
            config.resizable = false;
            Spaceraider sp = new Spaceraider("multiplayer", username, id);
            LwjglApplication game =new LwjglApplication(sp, config);



        });

        buttonArcade.addActionListener(e -> {
           frame.dispose();
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
            config.title = "Space Raider";
            config.width = 1920;
            config.height = 1080;
            config.resizable = false;
            new LwjglApplication(new Spaceraider("multiplayer", username, id), config);
        });

        buttonHighscores.addActionListener(e -> {
            HighscorePanel highscoresPanel = new HighscorePanel(frame, username , id);
            frame.getContentPane().removeAll();
            frame.getContentPane().invalidate();
            frame.getContentPane().add(highscoresPanel);
            frame.getContentPane().revalidate();
            /*Database db = new Database();
            //db.updateHighscore(1,500);
            //var scores = db.getHighscores();*/

        });

    }

    private void addComponents() {
        add(buttonCampaign);
        add(buttonMultiplayer);
        add(buttonHighscores);
        add(buttonArcade);
        add(logo);
    }

    private void initComponents() {
        buttonCampaign = new JButton("Campaign");
        buttonCampaign.setBounds((int) (width / 2) - (160 / 2), (int) (height / 2) - 150, 160, 50);
        buttonMultiplayer = new JButton("MultiPlayer");
        buttonMultiplayer.setBounds((int) (width / 2) - (160 / 2), (int) (height / 2) -75, 160, 50);
        buttonHighscores = new JButton("Highscores");
        buttonHighscores.setBounds((int) (width / 2) - (160 / 2), (int) (height / 2) +75, 160, 50);
        buttonArcade = new JButton("Arcade");
        buttonArcade.setBounds((int) (width / 2) - (160 / 2), (int) (height / 2) , 160, 50);
        logo = new JLabel();
        logo.setBounds((int) (width / 2) - (400 / 2), 25, 600, 200);
        logo.setIcon(new ImageIcon("src/resources/arcade-font-writer.png"));
    }

}
