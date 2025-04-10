package panels.game;

import core.Resources;
import entity.Entity;
import entity.mobile.person.Poacher;
import entity.mobile.person.Ranger;
import road.Path;
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
    private final CoinPanel coinPanel = new CoinPanel();
    private BasicFeedBackPanel feedback;


    public EventPanel() {
        setLayout(null);
        initializeComponents();
        addEventListeners();
        Safari.Instance.placePoachers(1);
    }

    private void initializeComponents() {
        add(new LogoutButton());
        add(coinPanel);
        add(Calendar.Instance);
        add(minimap);
        add(new FeedBackTriggerButton());
    }

    private void addEventListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();
                handleMousePress(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handleMouseRelease(e);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouseDrag(e);
            }
        });
    }

    private void handleMousePress(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            if (Safari.Instance.getRoadBuilding()) {
                Safari.Instance.saveARoad(lastX - offsetX, lastY - offsetY);
            } else if (Safari.Instance.shopping != null) {
                handleShopping(lastX, lastY);
            } else if (Safari.Instance.getSellingMode()) {
                handleSelling(lastX, lastY);
            } else {
                handleRangerSelectionOrMovement(lastX, lastY);
            }
        } else {
            dragging = true;
        }
        repaint();
    }

    private void handleMouseRelease(MouseEvent e) {
        if (!SwingUtilities.isRightMouseButton(e)) {
            dragging = false;
        } else {
            handleExitClick(e);
        }
    }

    private void handleMouseDrag(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e) && Safari.Instance.getRoadBuilding()) {
            Safari.Instance.saveARoad(e.getX() - offsetX, e.getY() - offsetY);
        } else if (dragging) {
            int dx = e.getX() - lastX;
            int dy = e.getY() - lastY;
            offsetX += dx;
            offsetY += dy;
            limitOffsets();
            lastX = e.getX();
            lastY = e.getY();
        }
        repaint();
    }

    private void handleShopping(int lastX, int lastY) {
        List<Entity> allEntities = Safari.Instance.getAllEntities();
        boolean isPositionAvailable = true;
        //boolean isPositionAvailable = allEntities.stream().noneMatch(entity -> entity.enviromentContains(lastX - offsetX, lastY - offsetY));

        if (isPositionAvailable) {
            Safari.Instance.placeSomething(lastX - offsetX, lastY - offsetY);
            ToolBarCardLayout.Instance.showCard("toolbar");
            System.out.println("Hozzáadva a következő pozícióval: (" + lastX + ", " + lastY + ")");
        }
    }

    private void handleSelling(int lastX, int lastY) {
        Safari.Instance.getAllEntities().stream()
                .filter(entity -> entity.contains(lastX - offsetX, lastY - offsetY))
                .findFirst()
                .ifPresent(entity -> Safari.Instance.sellSomething(entity.id));
    }

    private void handleExitClick(MouseEvent e) {
        if (Safari.Instance.getExit().contains(e.getX() - offsetX, e.getY() - offsetY)) {
            Safari.Instance.saveARoad(EntityCreate.exitX, EntityCreate.exitY);
            Safari.Instance.getTempPaths().add(new Path(EntityCreate.entryX, EntityCreate.entryY));
        }
    }

    private void handleRangerSelectionOrMovement(int lastX, int lastY) {
        List<Ranger> rangers = Safari.Instance.getRangers();

        if (Safari.Instance.isSelectedRanger()) {
            rangers.stream()
                    .filter(ranger -> ranger.isSelected() && ranger.contains(lastX - offsetX, lastY - offsetY))
                    .findFirst()
                    .ifPresent(ranger -> {
                        ranger.setSelected(false);
                        Safari.Instance.setSelectedRanger(false);
                        ToolBarCardLayout.Instance.showCard("toolbar");
                    });

            rangers.stream()
                    .filter(ranger -> ranger.isSelected() && !ranger.contains(lastX - offsetX, lastY - offsetY))
                    .findFirst()
                    .ifPresent(ranger -> MoveOrHunt(lastX, lastY, ranger));

        } else {
            selectRangerIfNeeded(rangers, lastX, lastY);
        }
    }

    public void MoveOrHunt(int lastX, int lastY, Ranger ranger) {
        // Ellenőrizzük, hogy egy Entity-re kattintottunk-e
        Safari.Instance.getAnimalsPoachers().stream()
                .filter(entity -> entity.contains(lastX - offsetX, lastY - offsetY))
                .findFirst()
                .ifPresent(entity -> {
                    huntEntity(ranger, entity);
                });

        if (!ranger.isTarget()) {
            moveRangerToNewPosition(lastX, lastY, ranger);
        }
    }

    private void moveRangerToNewPosition(int lastX, int lastY, Ranger ranger) {
        ranger.setNewPosition(true);
        ranger.setTarget(false);
        ranger.setMovingToTarget(false);
        System.out.println("no hunting");
        ranger.setNewPositionX(lastX - offsetX - Resources.Instance.ranger.getWidth() / 2);
        ranger.setNewPositionY(lastY - offsetY - Resources.Instance.ranger.getHeight() / 2);
    }

    public void huntEntity(Ranger ranger, Entity entity) {
        ranger.setTargetEntity(entity);
        ranger.setTarget(true);
        ranger.setNewPosition(false);
        ranger.setMovingNewPosition(false);
        System.out.println("no hunting");
    }

    private void selectRangerIfNeeded(List<Ranger> rangers, int lastX, int lastY) {
        rangers.stream()
                .filter(ranger -> ranger.contains(lastX - offsetX, lastY - offsetY))
                .findFirst()
                .ifPresent(ranger -> {
                    ranger.setSelected(true);
                    Safari.Instance.setSelectedRanger(true);
                    ToolBarCardLayout.Instance.showCard("void");
                });
    }

    private void limitOffsets() {
        offsetX = Math.max(Math.min(0, offsetX), getWidth() - background.getWidth());
        offsetY = Math.max(Math.min(0, offsetY), getHeight() - background.getHeight());
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

        limitOffsets();
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
        g.drawImage(background, offsetX, offsetY, this);
        drawPaths(g);
        drawEntities(g);
    }

    private void drawPaths(Graphics g) {
        List<Path> paths = Safari.Instance.getPaths();
        for (Path path : paths) {
            path.getRoads().forEach(road -> road.draw(g, offsetX, offsetY));
        }

        List<Path> tempPaths = Safari.Instance.getTempPaths();
        for (Path path : tempPaths) {
            path.getRoads().forEach(road -> road.draw(g, offsetX, offsetY));
        }

        Safari.Instance.getEntry().draw(g, offsetX, offsetY);
        Safari.Instance.getExit().draw(g, offsetX, offsetY);
    }

    private void drawEntities(Graphics g) {
        List<Entity> allEntities = Safari.Instance.getAllEntitiesWithSorted();
        for (Entity entity : allEntities) {
            if (entity instanceof Poacher) {
                if (((Poacher) entity).isVisible()) entity.draw(g, offsetX, offsetY);
            } else {
                entity.draw(g, offsetX, offsetY);
                if (entity instanceof Ranger && ((Ranger) entity).isSelected()) {
                    drawSelectedRanger(g, entity);
                }
            }
        }
    }

    private void drawSelectedRanger(Graphics g, Entity entity) {
        int x = entity.getX() + offsetX;
        int y = entity.getY() + offsetY;
        int width = entity.getWidth();
        int height = entity.getHeight();

        g.setColor(Color.RED);
        g.drawRect(x, y, width, height);
    }

    @Override
    public void doLayout() {
        super.doLayout();
        minimap.updateMinimapPosition(getHeight());
        if (feedback != null) {
            feedback.updatePosition();
        }
        Calendar.Instance.updatePosition();
        limitOffsets();
    }
}
