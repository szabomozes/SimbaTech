package panels.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import core.Resources;
import entity.mobile.*;
import entity.mobile.animal.Giraffe;
import entity.mobile.animal.Leopard;
import entity.mobile.animal.Lion;
import entity.mobile.animal.Zebra;
import logic.Logic;
import panels.game.coin.CoinPanel;
import panels.game.minimap.Minimap;
import panels.game.toolbar.ToolBarCardLayout;

import java.util.Comparator;
import java.util.List;

public class EventPanel extends JPanel {
    private final BufferedImage background = Resources.Instance.map;
    private int offsetX = 0, offsetY = 0;
    private int lastX, lastY;
    private boolean dragging = false;
    private final Minimap minimap = new Minimap();
    private CoinPanel coinPanel = new CoinPanel();

    public EventPanel() {
        setLayout(null);

        add(new LogoutButton());
        add(coinPanel);
        add(Calendar.Instance);
        add(minimap);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();

                if (SwingUtilities.isRightMouseButton(e)) {
                    if (Logic.Instance.shopping != null) {
                        switch (Logic.Instance.shopping) {
                            case "lion":
                                Logic.Instance.lions.add(new Lion(lastX - offsetX, lastY - offsetY));
                                Logic.Instance.coin -= Logic.Instance.lionPrice;
                                break;
                            case "leopard":
                                Logic.Instance.leopards.add(new Leopard(lastX - offsetX, lastY - offsetY));
                                Logic.Instance.coin -= Logic.Instance.leopardPrice;
                                break;
                            case "zebra":
                                Logic.Instance.zebras.add(new Zebra(lastX - offsetX, lastY - offsetY));
                                Logic.Instance.coin -= Logic.Instance.zebraPrice;
                                break;
                            case "giraffe":
                                Logic.Instance.giraffes.add(new Giraffe(lastX - offsetX, lastY - offsetY));
                                Logic.Instance.coin -= Logic.Instance.giraffePrice;
                                break;
                        }
                        Logic.Instance.shopping = null;
                        ToolBarCardLayout.Instance.showCard("toolbar");
                        System.out.println("Hozzáadva a következő pozícióval: (" + lastX + ", " + lastY + ")");
                        repaint();
                    }
                } else {
                    dragging = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!SwingUtilities.isRightMouseButton(e)) {
                    dragging = false;
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragging) {
                    int dx = e.getX() - lastX;
                    int dy = e.getY() - lastY;
                    offsetX += dx;
                    offsetY += dy;
                    offsetX = Math.max(Math.min(0, offsetX), getWidth() - background.getWidth());
                    offsetY = Math.max(Math.min(0, offsetY), getHeight() - background.getHeight());
                    lastX = e.getX();
                    lastY = e.getY();
                    repaint();
                }
            }
        });
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, offsetX, offsetY, this);

        List<MobileEntity> allEntities = new ArrayList<>();
        allEntities.addAll(Logic.Instance.giraffes);
        allEntities.addAll(Logic.Instance.zebras);
        allEntities.addAll(Logic.Instance.leopards);
        allEntities.addAll(Logic.Instance.lions);

        allEntities.sort(Comparator.comparingInt(entity -> entity.getY()));

        for (MobileEntity entity : allEntities) {
            entity.draw(g, offsetX, offsetY);
        }
    }

    @Override
    public void doLayout() {
        super.doLayout();
        minimap.updateMinimapPosition(getHeight());
        Calendar.Instance.updatePosition();
    }
}
