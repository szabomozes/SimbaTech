package panels;

import javax.swing.*;
import java.awt.*;

public class ToolBarPanel {

    private JPanel shopPanel, rightPanel;
    private JButton lionButton, leopardButton, zebraButton, giraffeButton;
    private JButton palmTreeButton, panicumButton, baobabButton;
    private JButton waterAreaButton, jeepButton, rangerButton;
    private JButton sellButton, buildRoadButton, speedButton, speedOptions;
    private JFrame frame;

    public ToolBarPanel() {
        frame = new JFrame("Toolbar Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout()); // Fontos beállítani a Layout-ot!

        initComponents();
        setupLayout();
    }

    private void initComponents() {
        shopPanel = new JPanel();
        rightPanel = new JPanel();

        // Gombok létrehozása
        lionButton = createButton("Lion", "pictures/icons/toolkit/shop/animal/face/lion-face.png");
        leopardButton = createButton("Leopard", "pictures/icons/toolkit/shop/animal/face/leopard-face.png");
        zebraButton = createButton("Zebra", "pictures/icons/toolkit/shop/animal/face/zebra-face.png");
        giraffeButton = createButton("Giraffe", "pictures/icons/toolkit/shop/animal/face/giraffe-face.png");

        palmTreeButton = createButton("PalmTree", "pictures/icons/toolkit/shop/plant/palm-tree.png");
        panicumButton = createButton("Panicum", "pictures/icons/toolkit/shop/plant/panicum.png");
        baobabButton = createButton("Baobab", "pictures/icons/toolkit/shop/plant/baobab.png");

        waterAreaButton = createButton("Water", "pictures/icons/toolkit/shop/object/water.png");
        jeepButton = createButton("Jeep", "pictures/icons/toolkit/shop/object/jeep.png");
        rangerButton = createButton("Ranger", "pictures/icons/toolkit/shop/person/ranger.png");

        sellButton = createButton("Sell", "pictures/icons/toolkit/shop/other/sell.png");
        buildRoadButton = createButton("BuildRoad", "pictures/icons/toolkit/road/road-table.png");
        speedButton = createButton("Speed", "pictures/icons/toolkit/speed/snail.png");
        speedOptions = createButton("SpeedOptions", "pictures/icons/toolkit/speed/hippopotamus.png");
    }

    private void setupLayout() {
        shopPanel.setLayout(new GridLayout(4, 3)); 
        rightPanel.setLayout(new GridLayout(4, 1));

        // Gombok hozzáadása a panelekhez
        shopPanel.add(lionButton);
        shopPanel.add(leopardButton);
        shopPanel.add(zebraButton);
        shopPanel.add(giraffeButton);
        shopPanel.add(palmTreeButton);
        shopPanel.add(panicumButton);
        shopPanel.add(baobabButton);
        shopPanel.add(waterAreaButton);
        shopPanel.add(jeepButton);
        shopPanel.add(rangerButton);

        rightPanel.add(sellButton);
        rightPanel.add(buildRoadButton);
        rightPanel.add(speedButton);
        rightPanel.add(speedOptions);

        // Panelek hozzáadása a frame-hez
        frame.add(shopPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setIcon(new ImageIcon(iconPath));
        return button;
    }

    public void addEventListeners() {
        
    }

    public void updateSpeed() {
      
    }

    public void updateToolBar() {
        
    }

}
