package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by q on 19/10/2016.
 */
public class CampaignGamePanel extends JPanel{
    private JFrame frame;
    private double width;
    private double height;

    public CampaignGamePanel(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        setPreferredSize(new Dimension((int) width, (int) height));
        setLayout(null);
        this.frame = frame;

    }
}
