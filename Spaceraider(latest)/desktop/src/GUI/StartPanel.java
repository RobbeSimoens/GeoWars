package GUI;

import Database.Database;

import javax.swing.*;
import java.awt.*;


public class StartPanel extends BackGroundPanel {
    private JButton buttonLogin;
    private JButton buttonRegister;
    private JLabel logo;
    private JFrame frame;
    private double width;
    private double height;
    private Database database = new Database();

    public StartPanel(JFrame frame) {
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
        buttonLogin.addActionListener(e -> {

                    //echte code
                    LoginPanel loginPanel = new LoginPanel(frame, database);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().invalidate();
                    frame.getContentPane().add(loginPanel);
                    frame.getContentPane().revalidate();
                });
          //test
        /*
           frame.dispose();
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
            config.title = "Space Raider";
            config.width = 1920;
            config.height = 1080;
            config.resizable = false;
            new LwjglApplication(new Spaceraider("singleplayer"), config);
        });*/

        buttonRegister.addActionListener(e -> {
            RegisterPanel registerPanel = new RegisterPanel(frame,database);
            frame.getContentPane().removeAll();
            frame.getContentPane().invalidate();
            frame.getContentPane().add(registerPanel);
            frame.getContentPane().revalidate();

        });

    }

    private void addComponents() {
        add(buttonLogin);
        add(buttonRegister);
        add(logo);
    }

    private void initComponents() {
        buttonLogin = new JButton("Login");
        buttonLogin.setBounds((int) (width / 2) - (160 / 2), (int) (height / 2) - 150, 160, 50);
        buttonRegister = new JButton("Register");
        buttonRegister.setBounds((int) (width / 2) - (160 / 2), (int) (height / 2) + 125, 160, 50);
        logo = new JLabel();
        logo.setBounds((int) (width / 2) - (400 / 2), 25, 600, 200);
        logo.setIcon(new ImageIcon("src/resources/arcade-font-writer.png"));
    }

}
