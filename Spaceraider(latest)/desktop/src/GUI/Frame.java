package GUI;

import javax.swing.*;

public class Frame extends JFrame {

    public Frame() {
        super();
        setFocusable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Space Raider");
        getContentPane().add(new StartPanel(this));


        setVisible(true);
        pack();
    }

    public Frame(int highscore, String username, int id)
    {
        super();
        setFocusable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Space Raider");
        getContentPane().add(new AfterGamePanel(this, username, id, highscore));
        setVisible(true);
        pack();
    }

}

