package panels.game.minimap;

import core.Resources;
import entity.Entity;
import road.Path;
import road.Road;
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

        Graphics2D g2d = (Graphics2D) g; // Graphics2D, hogy tudjunk Stroke-ot állítani

        // Háttér
        g.drawImage(backgroundImage, 0, 0, this);

        // Keret
        g2d.setColor(Color.BLACK);
        g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        int parentWidth = Resources.Instance.map.getWidth();
        int parentHeight = Resources.Instance.map.getHeight();
        int width = getWidth();
        int height = getHeight();

        // Path
        g2d.setColor(Color.BLACK);
        float roadStrokeWidth = 1.0f;
        g2d.setStroke(new BasicStroke(roadStrokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        List<Path> paths = Safari.Instance.getPaths();
        for (Path path : paths) {
            List<Road> roads = path.getRoads();
            for (Road road : roads) {
                int startX = (int) (width * ((double) road.startX / parentWidth));
                int startY = (int) (height * ((double) road.startY / parentHeight));
                int endX = (int) (width * ((double) road.endX / parentWidth));
                int endY = (int) (height * ((double) road.endY / parentHeight));

                g2d.drawLine(startX, startY, endX, endY);
            }
        }
        // TempPath
        g2d.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke(roadStrokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        List<Path> tempPath = Safari.Instance.getTempPaths();
        for (Path path : tempPath) {
            List<Road> roads = path.getRoads();
            for (Road road : roads) {
                int startX = (int) (width * ((double) road.startX / parentWidth));
                int startY = (int) (height * ((double) road.startY / parentHeight));
                int endX = (int) (width * ((double) road.endX / parentWidth));
                int endY = (int) (height * ((double) road.endY / parentHeight));

                g2d.drawLine(startX, startY, endX, endY);
            }
        }

        // Entity
        List<Entity> entities = Safari.Instance.getAllEntities();
        for (Entity entity : entities) {
            String className = entity.getClass().getSimpleName();
            switch (className) {
                case "Lion", "Leopard", "Zebra", "Giraffe" -> g2d.setColor(Color.RED);
                case "PalmTree", "Pancium", "Baobab" -> g2d.setColor(Color.GREEN);
                case "Water" -> g2d.setColor(Color.CYAN);
                case "Ranger" -> g2d.setColor(Color.YELLOW);
            }
            int x = (int) (width * ((double) entity.getX() / parentWidth));
            int y = (int) (height * ((double) entity.getY() / parentHeight));
            g2d.fillOval(x, y, 5, 5);
        }

        // Nézet keret
        int offsetX = ((EventPanel) getParent()).getOffsetX();
        int offsetY = ((EventPanel) getParent()).getOffsetY();
        g2d.setColor(Color.RED);
        int startX = (int) (width * ((double) (offsetX * -1) / parentWidth));
        int startY = (int) (height * ((double) (offsetY * -1) / parentHeight));
        int endX = (int) (width * ((double) (offsetX * -1 + getParent().getWidth()) / parentWidth));
        int endY = (int) (height * ((double) (offsetY * -1 + getParent().getHeight()) / parentHeight));

        int rectWidth = endX - startX - 1;
        int rectHeight = endY - startY - 1;

        g2d.drawRect(startX, startY, rectWidth, rectHeight);

        // Stroke visszaállítása az alapértelmezettre
        g2d.setStroke(new BasicStroke());











    }
}
