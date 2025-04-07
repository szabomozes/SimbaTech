package panels.game.minimap;

import core.Resources;
import entity.mobile.animal.Giraffe;
import entity.mobile.animal.Leopard;
import entity.mobile.animal.Lion;
import entity.mobile.animal.Zebra;
import entity.mobile.person.Poacher;
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

        g.setColor(Color.RED);
        for (Lion lion : Safari.Instance.getLions()) {
            int x = (int) (width * ((double) lion.getX() / parentWidth));
            int y = (int) (height * ((double) lion.getY() / parentHeight));
            g.fillOval(x, y, 5, 5);
        }
        for (Leopard leopard : Safari.Instance.getLeopards()) {
            int x = (int) (width * ((double) leopard.getX() / parentWidth));
            int y = (int) (height * ((double) leopard.getY() / parentHeight));
            g.fillOval(x, y, 5, 5);
        }
        for (Zebra zebra : Safari.Instance.getZebras()) {
            int x = (int) (width * ((double) zebra.getX() / parentWidth));
            int y = (int) (height * ((double) zebra.getY() / parentHeight));
            g.fillOval(x, y, 5, 5);
        }
        for (Giraffe giraffe : Safari.Instance.getGiraffes()) {
            int x = (int) (width * ((double) giraffe.getX() / parentWidth));
            int y = (int) (height * ((double) giraffe.getY() / parentHeight));
            g.fillOval(x, y, 5, 5);
        }
        g.setColor(Color.GREEN);
        for (PalmTree palmTree : Safari.Instance.getPalmTrees()) {
            int x = (int) (width * ((double) palmTree.getX() / parentWidth));
            int y = (int) (height * ((double) palmTree.getY() / parentHeight));
            g.fillOval(x, y, 5, 5);
        }
        for (Pancium pancium : Safari.Instance.getPanciums()) {
            int x = (int) (width * ((double) pancium.getX() / parentWidth));
            int y = (int) (height * ((double) pancium.getY() / parentHeight));
            g.fillOval(x, y, 5, 5);
        }
        for (Baobab baobab : Safari.Instance.getBaobabs()) {
            int x = (int) (width * ((double) baobab.getX() / parentWidth));
            int y = (int) (height * ((double) baobab.getY() / parentHeight));
            g.fillOval(x, y, 5, 5);
        }
        g.setColor(Color.CYAN);
        for (Water water : Safari.Instance.getWaters()) {
            int x = (int) (width * ((double) water.getX() / parentWidth));
            int y = (int) (height * ((double) water.getY() / parentHeight));
            g.fillOval(x, y, 5, 5);
        }
        g.setColor(Color.YELLOW);
        for (Ranger ranger : Safari.Instance.getRangers()) {
            int x = (int) (width * ((double) ranger.getX() / parentWidth));
            int y = (int) (height * ((double) ranger.getY() / parentHeight));
            g.fillOval(x, y, 5, 5);
        }
        g.setColor(new Color(255, 95, 31));
        for (Poacher poacher : Safari.Instance.getPoachers()) {
            int x = (int) (width * ((double) poacher.getX() / parentWidth));
            int y = (int) (height * ((double) poacher.getY() / parentHeight));
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

        g.drawRect(startX, startY, rectWidth, rectHeight);;
    }
}
