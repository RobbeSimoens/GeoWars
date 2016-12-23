package com.spaceraider.GUI;

import javax.swing.*;
import java.awt.*;

public class BackGroundPanel extends JPanel {

    private Image image = new ImageIcon("core/src/com/spaceraider/desktop/resources/background.jpg").getImage();

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }


}
