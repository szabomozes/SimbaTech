package panels.game;

import core.Resources;
import entity.Entity;
import entity.mobile.person.Ranger;
import road.Path;
import road.Road;
import map.EntityCreate;
import safari.Safari;
import panels.feedback.BasicFeedBackPanel;
import panels.game.coin.CoinPanel;
import panels.game.minimap.Minimap;
import panels.game.toolbar.ToolBarCardLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.List;

public class EventPanel extends JPanel {
    private final BufferedImage background = Resources.Instance.map;
    private int offsetX = 0, offsetY = 0;
    private int lastX, lastY;
    private boolean dragging = false;
    private final Minimap minimap = new Minimap();
    private CoinPanel coinPanel = new CoinPanel();
    private BasicFeedBackPanel feedback = null;

    public EventPanel() {
        setLayout(null);

        add(new LogoutButton());
        add(coinPanel);
        add(Calendar.Instance);
        add(minimap);
        add(new FeedBackTriggerButton());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();

                if (SwingUtilities.isRightMouseButton(e)) {
                    if (Safari.Instance.getRoadBuilding()) {
                        Safari.Instance.saveARoad(lastX - offsetX, lastY - offsetY);
                    } else if (Safari.Instance.shopping != null) {
                        boolean okay = true;
                        List<Entity> allentities = Safari.Instance.getAllEntities();
                        for (int i = 0; i < allentities.size() && okay; i++) {
                            //if (allentities.get(i).enviromentContains(lastX - offsetX, lastY - offsetY)) {
                            //    okay = false;
                            //}
                        }
                        if (okay) {
                            Safari.Instance.placeSomething(lastX - offsetX, lastY - offsetY);
                            ToolBarCardLayout.Instance.showCard("toolbar");
                            System.out.println("Hozzáadva a következő pozícióval: (" + lastX + ", " + lastY + ")");
                        }
                    } else if (Safari.Instance.getSellingMode()){
                        List<Entity> allentities = Safari.Instance.getAllEntities();
                        for (Entity entity : allentities) {
                            if (entity.contains(lastX - offsetX, lastY - offsetY)) {
                                Safari.Instance.sellSomething(entity.id);
                            }
                        }
                    } else {
                        List<Ranger> rangers = Safari.Instance.getRangers();
                        int maxi = rangers.size();
                        int i = 0;
                        if (Safari.Instance.isSelectedRanger()) {
                            while (i < maxi && !rangers.get(i).isSelected()) {
                                i++;
                            }
                            if (i < maxi) {
                                Ranger ranger = rangers.get(i);
                                // Vagy sajat maga, vagy valami mas
                                if (ranger.contains(lastX - offsetX, lastY - offsetY)) {
                                    ranger.setSelected(false);
                                    Safari.Instance.setSelectedRanger(false);
                                } else {
                                    Entity entity = null;
                                    for (int j = 0; j < Safari.Instance.getAnimalsPoachers().size() && entity == null; j++) {
                                        if (Safari.Instance.getAnimalsPoachers().get(j).contains(lastX - offsetX, lastY - offsetY)) {
                                            entity = Safari.Instance.getAnimalsPoachers().get(j);
                                        }
                                    }
                                    // vagy pozicio, vagy entity
                                    if (entity == null) {
                                        ranger.setNewPosition(true);
                                        ranger.setTarget(null);
                                        ranger.setNewPositionX(lastX - offsetX);
                                        ranger.setNewPositionY(lastY - offsetY);
                                    } else {
                                        ranger.setTarget(entity);
                                        ranger.setNewPosition(false);
                                    }
                                }
                            }
                        } else {
                            while (i < maxi && !rangers.get(i).contains(lastX - offsetX, lastY - offsetY)) {
                                i++;
                            }
                            if (i < maxi) {
                                rangers.get(i).setSelected(true);
                                Safari.Instance.setSelectedRanger(true);
                            }
                        }
                    }
                } else {
                    dragging = true;
                }
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!SwingUtilities.isRightMouseButton(e)) {
                    dragging = false;
                } else {
                    if (Safari.Instance.getExit().contains(e.getX() - offsetX, e.getY() - offsetY)){
                        Safari.Instance.saveARoad(EntityCreate.exitX, EntityCreate.exitY);
                        Safari.Instance.getTempPaths().add(new Path(EntityCreate.entryX, EntityCreate.entryY));
                    }
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                if (SwingUtilities.isRightMouseButton(e) && Safari.Instance.getRoadBuilding()) {
                    Safari.Instance.saveARoad(e.getX() - offsetX, e.getY() - offsetY);
                    // building mode
                } else if (dragging) {
                    int dx = e.getX() - lastX;
                    int dy = e.getY() - lastY;
                    offsetX += dx;
                    offsetY += dy;
                    offsetX = Math.max(Math.min(0, offsetX), getWidth() - background.getWidth());
                    offsetY = Math.max(Math.min(0, offsetY), getHeight() - background.getHeight());
                    lastX = e.getX();
                    lastY = e.getY();
                }
                repaint();
            }
        });
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsets(double scaleX, double scaleY) {
        int backgroundWidth = background.getWidth();
        int backgroundHeight = background.getHeight();

        offsetX = (int) ((backgroundWidth) * scaleX) * -1;
        offsetY = (int) ((backgroundHeight) * scaleY) * -1;

        offsetX += getWidth() / 2;
        offsetY += getHeight() / 2;

        int offsetXMin = -1 * backgroundWidth + getWidth();
        if (offsetX < offsetXMin) offsetX = offsetXMin;
        if (offsetX > 0) offsetX = 0;

        int offsetYMin = -1 * backgroundHeight + getHeight();
        if (offsetY < offsetYMin) offsetY = offsetYMin;
        if (offsetY > 0) offsetY = 0;

        repaint();
    }

    public void setFeedback(BasicFeedBackPanel feedback) {
        if (this.feedback != null) remove(this.feedback);
        this.feedback = feedback;
        if (this.feedback != null) add(feedback);
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // háttér
        g.drawImage(background, offsetX, offsetY, this);

        // utak
        List<Path> paths = Safari.Instance.getPaths();
        for (Path path : paths) {
            List<Road> roads = path.getRoads();
            for (Road road : roads) {
                road.draw(g, offsetX, offsetY);
            }
        }

        // ideglenes ut
        List<Path> tempPaths = Safari.Instance.getTempPaths();
        for (Path path : tempPaths) {
            List<Road> roads = path.getRoads();
            for (Road road : roads) {
                road.draw(g, offsetX, offsetY);
            }
        }

        // bejarat - kijarat
        Safari.Instance.getEntry().draw(g, offsetX, offsetY);
        Safari.Instance.getExit().draw(g, offsetX, offsetY);

        // entityk
        List<Entity> allEntities = Safari.Instance.getAllEntitiesWithSorted();

        for (Entity entity : allEntities) {
            entity.draw(g, offsetX, offsetY);

            if (entity instanceof Ranger && ((Ranger) entity).isSelected()) {
                int x = entity.getX() + offsetX;
                int y = entity.getY() + offsetY;
                int width = entity.getWidth();
                int height = entity.getHeight();

                g.setColor(Color.RED);
                g.drawRect(x, y, width, height);
            }
        }
    }

    @Override
    public void doLayout() {
        super.doLayout();
        minimap.updateMinimapPosition(getHeight());
        if (feedback != null) {
            feedback.updatePosition();
        }
        Calendar.Instance.updatePosition();
        offsetX = Math.max(Math.min(0, offsetX), getWidth() - background.getWidth());
        offsetY = Math.max(Math.min(0, offsetY), getHeight() - background.getHeight());
    }
}
