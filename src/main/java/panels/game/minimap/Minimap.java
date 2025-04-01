package panels.game.minimap;

import core.Resources;
import entity.Entity;
import entity.mobile.animal.Giraffe;
import entity.mobile.animal.Leopard;
import entity.mobile.animal.Lion;
import entity.mobile.animal.Zebra;
import entity.mobile.person.Ranger;
import entity.notmobile.Water;
import entity.notmobile.plant.Baobab;
import entity.notmobile.plant.PalmTree;
import entity.notmobile.plant.Pancium;
import safari.Safari;
import panels.game.EventPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.List;

public class Minimap extends JPanel {
    private BufferedImage backgroundImage = Resources.Instance.minimap;

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

        int parentWidth = Resources.Instance.map.getWidth();
        int parentHeight = Resources.Instance.map.getHeight();
        int width = getWidth();
        int height = getHeight();


        List<Entity> entities = Safari.Instance.getAllEntities();

        for (Entity entity : entities) {
            String className = entity.getClass().getSimpleName();
            switch (className) {
                case "Lion", "Leopard", "Zebra", "Giraffe" -> g.setColor(Color.RED);
                case "PalmTree", "Pancium", "Baobab" -> g.setColor(Color.GREEN);
                case "Water" -> g.setColor(Color.CYAN);
                case "Ranger" -> g.setColor(Color.YELLOW);
            }
            int x = (int) (width * ((double) entity.getX() / parentWidth));
            int y = (int) (height * ((double) entity.getY() / parentHeight));
            g.fillOval(x, y, 5, 5);
        }

        int offsetX = ((EventPanel) getParent()).getOffsetX();
        int offsetY = ((EventPanel) getParent()).getOffsetY();
        g.setColor(Color.RED);
        int startX = (int) (width * ((double) (offsetX * -1) / parentWidth));
        int startY = (int) (height * ((double) (offsetY * -1) / parentHeight));
        int endX = (int) (width * ((double) (offsetX * -1 + getParent().getWidth()) / parentWidth));
        int endY = (int) (height * ((double) (offsetY * -1 + getParent().getHeight()) / parentHeight));

        int rectWidth = endX - startX - 1;
        int rectHeight = endY - startY - 1;

        g.drawRect(startX, startY, rectWidth, rectHeight);
    }
}
