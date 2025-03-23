package panels.game.minimap;

import core.Resources;
import panels.feedback.FeedBackCardLayout;
import panels.game.EventPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class Minimap extends JPanel {
    private BufferedImage backgroundImage = Resources.Instance.minimap;

    //minimap letíltása
    private final FeedBackCardLayout feedback = FeedBackCardLayout.Instance;

    public Minimap() {
        setSize(new Dimension(backgroundImage.getWidth(), backgroundImage.getHeight()));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                double scaleX = (double) mouseX / getWidth();
                double scaleY = (double) mouseY / getHeight();

                ((EventPanel) getParent()).setOffsets(scaleX, scaleY);

            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                double scaleX = (double) mouseX / getWidth();
                double scaleY = (double) mouseY / getHeight();

                ((EventPanel) getParent()).setOffsets(scaleX, scaleY);
            }
        });
    }

    public void updateMinimapPosition(int height) {
        int yPosition = height - getHeight() - 10;
        setBounds(10, yPosition, getWidth(), getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, this);

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
