package GUI;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.spaceraider.game.Spaceraider;
import com.spaceraider.game.desktop.DesktopLauncher;

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

    public MenuSelectorPanel(JFrame frame) {
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
            new LwjglApplication(new Spaceraider(), config);

        });

        buttonMultiplayer.addActionListener(e -> {
            frame.dispose();
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
            config.title = "Space Raider";
            config.width = 1920;
            config.height = 1080;
            config.resizable = false;
            new LwjglApplication(new Spaceraider(), config);

        });

        buttonArcade.addActionListener(e -> {
           frame.dispose();
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
            config.title = "Space Raider";
            config.width = 1920;
            config.height = 1080;
            config.resizable = false;
            new LwjglApplication(new Spaceraider(), config);
        });

        buttonHighscores.addActionListener(e -> {
         //   HighscoresPanel highscoresPanel = new HighScoresPanel(frame);
            frame.getContentPane().removeAll();
            frame.getContentPane().invalidate();
           // frame.getContentPane().add(highscoresPanel);
            frame.getContentPane().revalidate();

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
        buttonMultiplayer = new JButton("Multiplayer");
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