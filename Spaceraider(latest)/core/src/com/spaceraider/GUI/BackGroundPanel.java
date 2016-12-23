package com.spaceraider.GUI;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class BackGroundPanel extends JPanel {

    private Image image = new ImageIcon("desktop/src/resources/background.jpg").getImage();

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }


}
