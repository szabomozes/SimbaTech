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
import entity.notmobile.Water;
import entity.notmobile.plants.*;
import logic.Logic;
import panels.feedback.FeedBackCardLayout;
import panels.game.coin.CoinPanel;
import panels.game.minimap.Minimap;
import panels.game.toolbar.ToolBarCardLayout;

import java.util.Comparator;
import java.util.List;

public class EventPanel extends JPanel {
    public static final EventPanel Instance = new EventPanel();


    private final BufferedImage background = Resources.Instance.map;
    private int offsetX = 0, offsetY = 0;
    private int lastX, lastY;
    private boolean dragging = false;
    private final Minimap minimap = new Minimap();
    private CoinPanel coinPanel = new CoinPanel();
    private final FeedBackCardLayout feedback = FeedBackCardLayout.Instance;
    private final List<PalmTree> palmTrees=new ArrayList<>();
    private boolean placingPalmTreeMode = false;
    private final List<Pancium> panciums=new ArrayList<>();
    private boolean placingPanciumMode = false;
    private final List<Baobab> baobabs=new ArrayList<>();
    private boolean placingBaobabMode = false;
    private final List<Water> waters=new ArrayList<>();
    private boolean placingWaterMode = false;


    public EventPanel() {
        setLayout(null);

        add(new LogoutButton());
        add(coinPanel);
        add(Calendar.Instance);
        add(minimap);

        //feedback megjelenítése
        add(feedback);
        //feedback.notEnoughMoney();

        //feedback.setVisible(false);
        feedback.setSize(feedback.getPreferredSize());
        feedback.setLocation((getWidth() - feedback.getWidth()) / 2,
                (getHeight() - feedback.getHeight()) / 2);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                double x = e.getX() - offsetX;
                double y = e.getY() - offsetY;

                //ha a feedback nem látható
                if (!feedback.isVisible()) {
                    if(placingPalmTreeMode && isNotWater(x,y))
                    {
                        palmTrees.add(new PalmTree(x, y));
                        repaint();
                    }
                    else if(placingPanciumMode && isNotWater(x,y))
                    {
                        panciums.add(new Pancium(x, y));
                        repaint();
                    }
                    else if(placingBaobabMode && isNotWater(x,y))
                    {

                        baobabs.add(new Baobab(x, y));
                        repaint();
                    }
                    else if(placingWaterMode && isPositonFreeWaterToWater(x,y))
                    {
                        waters.add(new Water(x, y));
                        repaint();
                    }
                    else{
                        lastX = e.getX();
                        lastY = e.getY();
                        dragging = true;
                    }


                }


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


    public boolean isPlacingPalmTreeMode() {
        return placingPalmTreeMode;
    }

    public void setPlacingPalmTreeMode(boolean mode) {
        turnOffPlacingModes();
        this.placingPalmTreeMode = mode;
    }

    public boolean isPlacingBaobabMode() {
        return placingBaobabMode;
    }

    public void setPlacingBaobabMode(boolean mode) {
        turnOffPlacingModes();
        this.placingBaobabMode = mode;
    }

    public boolean isPlacingPanciumMode() {
        return placingPanciumMode;
    }

    public void setPlacingPanciumMode(boolean mode) {
        turnOffPlacingModes();
        this.placingPanciumMode = mode;
    }

    public boolean isPlacingWaterMode() {
        return placingWaterMode;
    }

    public void setPlacingWaterMode(boolean mode) {
        turnOffPlacingModes();
        this.placingWaterMode = mode;
    }

    public void setClearArrays()
    {
        palmTrees.clear();
        panciums.clear();
        baobabs.clear();
        waters.clear();
    }

    public void turnOffPlacingModes()
    {
        placingPalmTreeMode = false;
        placingBaobabMode = false;
        placingPanciumMode = false;
        placingWaterMode = false;
    }

    private boolean isPositonFreeWaterToWater(double x, double y){
        double tolerance = 90.0;

        for (Water water : waters) {
            if (Math.abs(water.getX() - x) < tolerance && Math.abs(water.getY() - y) < tolerance) {
                System.out.println("Ütközés vízzel: " + x + ", " + y);
                return false;
            }
        }

        for (PalmTree palm : palmTrees) {
            if (Math.abs(palm.getX() - x) < tolerance && Math.abs(palm.getY() - y) < tolerance) {
                System.out.println("Ütközés pálmafával: " + x + ", " + y);
                return false;
            }
        }

        for (Pancium pancium : panciums) {
            if (Math.abs(pancium.getX() - x) < tolerance && Math.abs(pancium.getY() - y) < tolerance) {
                System.out.println("Ütközés panciummal: " + x + ", " + y);
                return false;
            }
        }

        for (Baobab baobab : baobabs) {
            if (Math.abs(baobab.getX() - x) < tolerance && Math.abs(baobab.getY() - y) < tolerance) {
                System.out.println("Ütközés baobabbal: " + x + ", " + y);
                return false;
            }
        }
        return true;
    }
    private boolean isNotWater(double x, double y){
        double tolerance = 70.0;

        for (Water water : waters) {
            if (Math.abs(water.getX() - x) < tolerance && Math.abs(water.getY() - y) < tolerance) {
                System.out.println("Ütközés vízzel: " + x + ", " + y);
                return false;
            }
        }
        return true;
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, offsetX, offsetY, this);

        for(Water water:waters)
        {
            ImageIcon icon=water.getImage();
            int width = icon.getIconWidth();
            int height = icon.getIconHeight();
            int drawX = (int) (water.getX() + offsetX - width / 2);
            int drawY = (int) (water.getY() + offsetY - height / 2);
            g.drawImage(icon.getImage(), drawX, drawY, this);
        }

        for(Pancium pancium:panciums)
        {
            ImageIcon icon=pancium.getImage();
            int width = icon.getIconWidth();
            int height = icon.getIconHeight();
            int drawX = (int) (pancium.getX() + offsetX - width / 2);
            int drawY = (int) (pancium.getY() + offsetY - height / 2);
            g.drawImage(icon.getImage(), drawX, drawY, this);
        }

        for(PalmTree palm:palmTrees)
        {
            ImageIcon icon=palm.getImage();
            //int drawX=(int)(palm.getX()+offsetX);
            //int drawY=(int)(palm.getY()+offsetY);
            int width = icon.getIconWidth();
            int height = icon.getIconHeight();
            int drawX = (int) (palm.getX() + offsetX - width / 2);
            int drawY = (int) (palm.getY() + offsetY - height / 2);
            g.drawImage(icon.getImage(), drawX, drawY, this);
        }



        for(Baobab baobab:baobabs)
        {
            ImageIcon icon=baobab.getImage();
            int width = icon.getIconWidth();
            int height = icon.getIconHeight();
            int drawX = (int) (baobab.getX() + offsetX - width / 2);
            int drawY = (int) (baobab.getY() + offsetY - height / 2);
            g.drawImage(icon.getImage(), drawX, drawY, this);
        }

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


        //feedback poziója
        feedback.setLocation((getWidth() - feedback.getWidth()) / 2,
                (getHeight() - feedback.getHeight()) / 2);
    }
}
