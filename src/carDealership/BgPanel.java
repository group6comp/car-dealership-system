package carDealership;

import javax.swing.*;
import java.awt.*;

public class BgPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Image bgImage;

    public BgPanel(Image image) {
        this.bgImage = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImage != null) {
            // Draw the image scaled to the panel's size
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

