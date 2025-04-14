package panels.game.minimap;

import core.Resources;
import entity.Entity;
import entity.notmobile.Entry;
import entity.notmobile.Exit;
import map.EntityCreate;
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

/**
 * The Minimap class represents a minimap that displays a scaled-down version of the game map.
 * The minimap includes paths, entry and exit points, entities, and a view of the currently visible area.
 */
public class Minimap extends JPanel {

    /**
     * The background image for the minimap.
     */
    private BufferedImage backgroundImage = Resources.Instance.minimap;

    /**
     * Constructor for the Minimap class. It initializes the minimap size and sets up mouse interaction handlers.
     */
    public Minimap() {
        setMinimapSize();
        setupMouseClickHandler();
        setupMouseDragHandler();
    }

    /**
     * Sets the size of the minimap based on the background image dimensions.
     */
    private void setMinimapSize() {
        setSize(new Dimension(backgroundImage.getWidth(), backgroundImage.getHeight()));
    }

    /**
     * Sets up the mouse click handler that updates the offsets when the user clicks on the minimap.
     */
    private void setupMouseClickHandler() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updateOffsetsFromMouseEvent(e);
            }
        });
    }

    /**
     * Sets up the mouse drag handler that updates the offsets as the user drags the mouse over the minimap.
     */
    private void setupMouseDragHandler() {
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateOffsetsFromMouseEvent(e);
            }
        });
    }

    /**
     * Updates the offsets based on the mouse event.
     * This is used to adjust the visible portion of the map in the main game window.
     *
     * @param e The MouseEvent containing the mouse position.
     */
    private void updateOffsetsFromMouseEvent(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        double scaleX = (double) mouseX / getWidth();
        double scaleY = (double) mouseY / getHeight();

        ((EventPanel) getParent()).setOffsets(scaleX, scaleY);
    }

    /**
     * Updates the minimap's position on the screen based on the provided height.
     * This ensures the minimap is correctly placed relative to the game window.
     *
     * @param height The height used to calculate the y-position of the minimap.
     */
    public void updateMinimapPosition(int height) {
        int yPosition = height - getHeight() - 10;
        setBounds(10, yPosition, getWidth(), getHeight());
    }

    /**
     * Paints the minimap on the screen, including paths, entities, entry/exit points, and the current visible area.
     *
     * @param g The Graphics object used for rendering the minimap.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g; // Graphics2D for advanced drawing features

        int parentWidth = Resources.Instance.map.getWidth();
        int parentHeight = Resources.Instance.map.getHeight();
        int width = getWidth();
        int height = getHeight();

        // Draw background
        g.drawImage(backgroundImage, 0, 0, this);

        // Draw paths
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

        // Draw temporary paths
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

        // Draw entry and exit points
        g2d.setColor(new Color(101, 67, 33));
        Entry entry = Safari.Instance.getEntry();
        Exit exit = Safari.Instance.getExit();


        int tempEntryExitWidth = (int) (width * ((double) (entry.getWidth()) / parentWidth));
        int tempEntryExitHeight = (int) (height * ((double) (entry.getHeight()) / parentHeight));

        g2d.fillRect((int) (width * ((double) entry.getX() / parentWidth)),
                (int) (height * ((double) entry.getY() / parentHeight)), tempEntryExitWidth, tempEntryExitHeight);

        tempEntryExitWidth = (int) (width * ((double) (exit.getWidth()) / parentWidth));
        tempEntryExitHeight = (int) (height * ((double) (exit.getHeight()) / parentHeight));

        g2d.fillRect((int) (width * ((double) exit.getX() / parentWidth)),
                (int) (height * ((double) exit.getY() / parentHeight)), tempEntryExitWidth, tempEntryExitHeight);


        // Draw entities
        List<Entity> entities = Safari.Instance.getAllEntities();
        for (Entity entity : entities) {
            String className = entity.getClass().getSimpleName();
            switch (className) {
                case "Lion", "Leopard", "Zebra", "Giraffe" -> g2d.setColor(Color.RED);
                case "PalmTree", "Pancium", "Baobab" -> g2d.setColor(new Color(34, 139, 34));
                case "Water" -> g2d.setColor(new Color(30, 60, 160));
                case "Ranger" -> g2d.setColor(Color.YELLOW);
                case "Jeep" -> g2d.setColor(Color.WHITE);
                case "Poacher" -> g2d.setColor(new Color(57, 255, 20));
            }
            int x = (int) (width * ((double) (entity.getX()) / parentWidth));
            int y = (int) (height * ((double) (entity.getY()) / parentHeight));
            int tempWidth = (int) (width * ((double) (entity.getWidth()) / parentWidth));
            int tempHeight = (int) (height * ((double) (entity.getHeight()) / parentHeight));
            g2d.fillRect(x, y, tempWidth, tempHeight);
        }

        // Draw border
        g2d.setColor(Color.BLACK);
        g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        // Draw view frame
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

        // Reset stroke to default
        g2d.setStroke(new BasicStroke());
    }
}
