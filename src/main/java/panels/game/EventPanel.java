package panels.game;

import core.Resources;
import entity.Entity;
import entity.mobile.person.Poacher;
import entity.mobile.person.Ranger;
import map.Coordinate;
import panels.feedback.GameStatePanel;
import road.Path;
import map.EntityCreate;
import road.Road;
import safari.Safari;
import panels.feedback.BasicFeedBackPanel;
import panels.game.coin.CoinPanel;
import panels.game.minimap.Minimap;
import panels.game.toolbar.ToolBarCardLayout;
import safari.Speed;
import safari.SpeedEnum;
import timer.WinOrLoseTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * The {@code EventPanel} class represents the main interactive game panel
 * where user inputs such as dragging, clicking, shopping, road building,
 * and ranger movement are handled.
 * It also initializes and adds various UI components like the minimap,
 * coin panel, calendar, and logout button.
 */
public class EventPanel extends JPanel {
    private final BufferedImage background = Resources.Instance.map;
    private int offsetX = 0, offsetY = 0;
    private int lastX, lastY;
    private boolean dragging = false;
    private final Minimap minimap = new Minimap();
    private final CoinPanel coinPanel = new CoinPanel();
    private BasicFeedBackPanel feedback;
    private GameStatePanel statePanel;

    /**
     * Constructs the event panel, sets layout, initializes components,
     * and adds the necessary mouse event listeners.
     */
    public EventPanel() {
        setLayout(null);
        initializeComponents();
        addEventListeners();
    }

    /**
     * Initializes UI components, resets toolbar, sets game speed,
     * and starts the win/lose timer.
     */
    private void initializeComponents() {
        Speed.Instance.speedEnum = SpeedEnum.SNAIL;
        ToolBarCardLayout.Instance.resetToToolbar();
        add(new LogoutButton());
        add(coinPanel);
        add(Calendar.Instance);
        add(minimap);
        add(GameStateTriggerButton.Instance);
        WinOrLoseTimer.getInstance().setEventPanel(this);
        WinOrLoseTimer.getInstance().startTimer();
    }

    /**
     * Adds mouse listener and motion listener to handle user input
     * such as dragging, clicking, and building.
     */
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

    /**
     * Handles the logic when mouse is pressed, such as initiating
     * road building, shopping, selling, or selecting/moving rangers.
     *
     * @param e the mouse event
     */
    private void handleMousePress(MouseEvent e) {
        if (Safari.Instance.getWinOrLose().isEmpty() && SwingUtilities.isRightMouseButton(e)) {
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

    /**
     * Handles actions when the mouse button is released, such as
     * finalizing dragging or triggering exit logic.
     *
     * @param e the mouse event
     */
    private void handleMouseRelease(MouseEvent e) {
        if (!SwingUtilities.isRightMouseButton(e)) {
            dragging = false;
        } else if (Safari.Instance.getWinOrLose().isEmpty()) {
            handleExitClick(e);
        }
    }

    /**
     * Handles dragging of the map background or road building when dragging with the mouse.
     *
     * @param e the mouse event
     */
    private void handleMouseDrag(MouseEvent e) {
        if (Safari.Instance.getWinOrLose().isEmpty() && SwingUtilities.isRightMouseButton(e) && Safari.Instance.getRoadBuilding()) {
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

    /**
     * Handles placing an item from the shop, if the selected area is suitable.
     *
     * @param lastX the x-coordinate of the mouse
     * @param lastY the y-coordinate of the mouse
     */
    private void handleShopping(int lastX, int lastY) {
        boolean isPositionAvailable = isEnoughSpace(lastX, lastY);
        boolean isThereAnyFreePlaceForWater = isEnoughSpaceForWater(lastX, lastY) && !waterOnTheRoad(lastX, lastY);

        if (isPositionAvailable && isThereAnyFreePlaceForWater) {
            Safari.Instance.placeSomething(lastX - offsetX, lastY - offsetY);
            //ToolBarCardLayout.Instance.showCard("toolbar");
        }
    }

    /**
     * Handles selling an entity if one exists under the cursor.
     *
     * @param lastX the x-coordinate of the mouse
     * @param lastY the y-coordinate of the mouse
     */
    private void handleSelling(int lastX, int lastY) {
        Safari.Instance.getAllEntities().stream()
                .filter(entity -> entity.contains(lastX - offsetX, lastY - offsetY))
                .findFirst()
                .ifPresent(entity -> Safari.Instance.sellSomething(entity.id));
    }

    /**
     * Handles clicking the exit area to validate and save a road connection.
     *
     * @param e the mouse event
     */
    private void handleExitClick(MouseEvent e) {
        if (Safari.Instance.getRoadBuilding() && Safari.Instance.getExit().contains(e.getX() - offsetX, e.getY() - offsetY)) {
            if (Safari.Instance.saveARoad(EntityCreate.getExitX(), EntityCreate.getExitY())) {
                Safari.Instance.getTempPaths().add(new Path(EntityCreate.getEntryX(), EntityCreate.getEntryY()));
            }
        }
    }

    /**
     * Handles ranger selection or movement based on mouse position.
     *
     * @param lastX the x-coordinate of the mouse
     * @param lastY the y-coordinate of the mouse
     */
    private void handleRangerSelectionOrMovement(int lastX, int lastY) {
        List<Ranger> rangers = Safari.Instance.getRangers();
        if (statePanel != null) return;

        if (Safari.Instance.isSelectedRanger()) {
            rangers.stream()
                    .filter(ranger -> ranger.isSelected() && ranger.contains(lastX - offsetX, lastY - offsetY))
                    .findFirst()
                    .ifPresent(ranger -> {
                        ranger.setSelected(false);
                        Safari.Instance.setSelectedRanger(false);
                        ToolBarCardLayout.Instance.showCard("toolbar");
                        GameStateTriggerButton.Instance.updateButtonState();
                    });

            rangers.stream()
                    .filter(ranger -> ranger.isSelected() && !ranger.contains(lastX - offsetX, lastY - offsetY))
                    .findFirst()
                    .ifPresent(ranger -> MoveOrHunt(lastX, lastY, ranger));

        } else {
            selectRangerIfNeeded(rangers, lastX, lastY);
        }
    }

    /**
     * Determines whether the ranger should move or hunt an entity based on click location.
     *
     * @param lastX  the x-coordinate of the mouse
     * @param lastY  the y-coordinate of the mouse
     * @param ranger the selected ranger
     */
    private void MoveOrHunt(int lastX, int lastY, Ranger ranger) {
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

    /**
     * Moves the given ranger to a new position on the map.
     *
     * @param lastX  the x-coordinate of the mouse
     * @param lastY  the y-coordinate of the mouse
     * @param ranger the ranger to be moved
     */
    private void moveRangerToNewPosition(int lastX, int lastY, Ranger ranger) {
        ranger.setNewPosition(true);
        ranger.setTarget(false);
        ranger.setMovingToTarget(false);
        ranger.setNewPositionX(lastX - offsetX - Resources.Instance.ranger.getWidth() / 2);
        ranger.setNewPositionY(lastY - offsetY - Resources.Instance.ranger.getHeight() / 2);
    }

    /**
     * Sets the ranger’s state to target a given entity (e.g., poacher or animal).
     *
     * @param ranger the ranger
     * @param entity the target entity
     */
    private void huntEntity(Ranger ranger, Entity entity) {
        ranger.setTargetEntity(entity);
        ranger.setTarget(true);
        ranger.setNewPosition(false);
        ranger.setMovingNewPosition(false);
    }
    /**
     * Selects a ranger if one exists at the given coordinates.
     *
     * @param rangers The list of rangers.
     * @param lastX   The X coordinate of the mouse click.
     * @param lastY   The Y coordinate of the mouse click.
     */
    private void selectRangerIfNeeded(List<Ranger> rangers, int lastX, int lastY) {
        rangers.stream()
                .filter(ranger -> ranger.contains(lastX - offsetX, lastY - offsetY))
                .findFirst()
                .ifPresent(ranger -> {
                    ranger.setSelected(true);
                    Safari.Instance.setSelectedRanger(true);
                    ToolBarCardLayout.Instance.showCard("void");
                    GameStateTriggerButton.Instance.updateButtonState(); // Disable info button
                });
    }

    /**
     * Limits the offset values to ensure the background doesn't move outside the visible area.
     */
    private void limitOffsets() {
        offsetX = Math.max(Math.min(0, offsetX), getWidth() - background.getWidth());
        offsetY = Math.max(Math.min(0, offsetY), getHeight() - background.getHeight());
    }

    /**
     * Returns the current X offset.
     *
     * @return The X offset.
     */
    public int getOffsetX() {
        return offsetX;
    }

    /**
     * Returns the current Y offset.
     *
     * @return The Y offset.
     */
    public int getOffsetY() {
        return offsetY;
    }

    /**
     * Sets the offsets based on scaling values and recenters the view.
     * Then repaints the panel.
     *
     * @param scaleX The horizontal scale.
     * @param scaleY The vertical scale.
     */
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

    /**
     * Sets the feedback panel. Removes the existing one if present.
     *
     * @param feedback The new feedback panel to display.
     */
    public void setFeedback(BasicFeedBackPanel feedback) {
        if (this.feedback != null) remove(this.feedback);
        this.feedback = feedback;
        if (this.feedback != null) {
            if (statePanel != null) {
                remove(statePanel);
                statePanel = null;
            }
            add(this.feedback);
        }
        repaint();
    }

    /**
     * Sets the game state panel. Removes the existing one if present.
     *
     * @param statePanel The new game state panel to display.
     */
    public void setStatePanel(GameStatePanel statePanel) {
        if (this.statePanel != null) remove(this.statePanel);
        this.statePanel = statePanel;
        if (this.statePanel != null) add(this.statePanel);
        repaint();
    }

    /**
     * Determines whether there's enough space to place an entity
     * at the clicked location, checking for overlap with water areas.
     *
     * @param clickX The X coordinate of the click.
     * @param clickY The Y coordinate of the click.
     * @return true if there's enough space and no overlap with water.
     */
    private boolean isEnoughSpace(int clickX, int clickY) {
        int middleX = clickX - offsetX;
        int middleY = clickY - offsetY;
        int differenceX = 0;
        int differenceY = 0;

        switch (Safari.Instance.shopping) {
            case "lion":
                differenceX = Resources.Instance.lionBody.getWidth() / 2;
                differenceY = Resources.Instance.lionBody.getHeight() / 2;
                break;
            case "leopard":
                differenceX = Resources.Instance.leopardBody.getWidth() / 2;
                differenceY = Resources.Instance.leopardBody.getHeight() / 2;
                break;
            case "zebra":
                differenceX = Resources.Instance.zebraBody.getWidth() / 2;
                differenceY = Resources.Instance.zebraBody.getHeight() / 2;
                break;
            case "giraffe":
                differenceX = Resources.Instance.giraffeBody.getWidth() / 2;
                differenceY = Resources.Instance.giraffeBody.getHeight() / 2;
                break;
            case "water":
                differenceX = Resources.Instance.water.getWidth() / 2;
                differenceY = Resources.Instance.water.getHeight() / 2;
                break;
            case "ranger":
                differenceX = Resources.Instance.ranger.getWidth() / 2;
                differenceY = Resources.Instance.ranger.getHeight() / 2;
                break;
            case "baobab":
                differenceX = Resources.Instance.baobab.getWidth() / 2;
                differenceY = Resources.Instance.baobab.getHeight() / 2;
                break;
            case "pancium":
                differenceX = Resources.Instance.pancium.getWidth() / 2;
                differenceY = Resources.Instance.pancium.getHeight() / 2;
                break;
            case "palmtree":
                differenceX = Resources.Instance.palmTree.getWidth() / 2;
                differenceY = Resources.Instance.palmTree.getHeight() / 2;
                break;
        }

        int x = middleX - differenceX;
        int y = middleY - differenceY;
        int width = differenceX * 2;
        int height = differenceY * 2;

        return !overlapsWaterArea(x, y, width, height, Safari.Instance.getWaters());
    }
    /**
     * Checks if the given rectangular area overlaps with any water entities.
     *
     * @param x        The X coordinate of the area.
     * @param y        The Y coordinate of the area.
     * @param width    The width of the area.
     * @param height   The height of the area.
     * @param entities The list of entities to check for overlap.
     * @return true if the area overlaps with any entity, false otherwise.
     */
    private boolean overlapsWaterArea(int x, int y, int width, int height, List<? extends Entity> entities) {
        for (Entity entity : entities) {
            int entityX = entity.getX();
            int entityY = entity.getY();
            int entityWidth = entity.getWidth();
            int entityHeight = entity.getHeight();

            if (x + width > entityX && x < entityX + entityWidth &&
                    y + height > entityY && y < entityY + entityHeight) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines whether there is enough space to place a water object at the clicked location.
     * Only applies if the current shopping item is "water".
     *
     * @param clickX The X coordinate of the mouse click.
     * @param clickY The Y coordinate of the mouse click.
     * @return true if there is no overlap with existing entities, false otherwise.
     */
    private boolean isEnoughSpaceForWater(int clickX, int clickY) {
        if (Safari.Instance.shopping.equals("water")) {
            int middleX = clickX - offsetX;
            int middleY = clickY - offsetY;
            int differenceX = Resources.Instance.water.getWidth() / 2;
            int differenceY = Resources.Instance.water.getHeight() / 2;
            int x = middleX - differenceX;
            int y = middleY - differenceY;
            int width = differenceX * 2;
            int height = differenceY * 2;
            return !overlapsWaterArea(x, y, width, height, Safari.Instance.getAllEntities());
        }
        return true;
    }

    /**
     * Checks whether a water object placed at the clicked location would overlap with any road.
     * Only applies if the current shopping item is "water".
     *
     * @param clickX The X coordinate of the mouse click.
     * @param clickY The Y coordinate of the mouse click.
     * @return true if the water would overlap with a road, false otherwise.
     */
    private boolean waterOnTheRoad(int clickX, int clickY) {
        if (!Safari.Instance.shopping.equals("water")) return false;

        int middleX = clickX - offsetX;
        int middleY = clickY - offsetY;
        int differenceX = Resources.Instance.water.getWidth() / 2;
        int differenceY = Resources.Instance.water.getHeight() / 2;
        int x = middleX - differenceX;
        int y = middleY - differenceY;
        int width = differenceX * 2;
        int height = differenceY * 2;

        for (Path path : Safari.Instance.getPaths()) {
            for (Road road : path.getRoads()) {
                for (Coordinate coord : road.getMid()) {
                    int coordX = coord.x;
                    int coordY = coord.y;

                    if (coordX >= x && coordX <= x + width &&
                            coordY >= y && coordY <= y + height) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Custom painting logic for the panel.
     * Draws the background image, paths, and all entities.
     *
     * @param g The graphics context used for painting.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, offsetX, offsetY, this);
        drawPaths(g);
        drawEntities(g);
    }
    /**
     * Draws all paths on the panel including permanent and temporary paths,
     * as well as the entry and exit points.
     *
     * @param g The graphics context used for drawing.
     */
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

    /**
     * Draws all entities on the map, sorted in proper order.
     * If a poacher is invisible, it is not drawn.
     * If a ranger is selected, a red border is drawn around it.
     *
     * @param g The graphics context used for drawing.
     */
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

    /**
     * Draws a red rectangle around a selected ranger to highlight it.
     *
     * @param g      The graphics context used for drawing.
     * @param entity The ranger entity to be highlighted.
     */
    private void drawSelectedRanger(Graphics g, Entity entity) {
        int x = entity.getX() + offsetX;
        int y = entity.getY() + offsetY;
        int width = entity.getWidth();
        int height = entity.getHeight();

        g.setColor(Color.RED);
        g.drawRect(x, y, width, height);
    }

    /**
     * Custom layout method. Updates positions of UI components such as the minimap,
     * feedback panel, state panel, calendar, and game state trigger button.
     * Also enforces offset limits based on current window size.
     */
    @Override
    public void doLayout() {
        super.doLayout();
        minimap.updateMinimapPosition(getHeight());
        if (feedback != null) {
            feedback.updatePosition();
        } else if (statePanel != null) {
            statePanel.updatePosition();
        }
        Calendar.Instance.updatePosition();
        GameStateTriggerButton.Instance.updatePosition();
        limitOffsets();
    }
}
