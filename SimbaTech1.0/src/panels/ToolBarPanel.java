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
        lionButton = createButton("res/icons/toolkit/shop/animal/face/lion-face.png", 50, 50);
        leopardButton = createButton("res/icons/toolkit/shop/animal/face/leopard-face.png", 50, 50);
        zebraButton = createButton("res/icons/toolkit/shop/animal/face/zebra-face.png", 50, 50);
        giraffeButton = createButton("res/icons/toolkit/shop/animal/face/giraffe-face.png", 50, 50);

        palmTreeButton = createButton("res/icons/toolkit/shop/plant/palm-tree.png", 50, 50);
        panicumButton = createButton("res/icons/toolkit/shop/plant/pancium.png", 50, 50);
        baobabButton = createButton("res/icons/toolkit/shop/plant/baobab.png", 50, 50);

        waterAreaButton = createButton("res/icons/toolkit/shop/object/water.png", 50, 50);
        jeepButton = createButton("res/icons/toolkit/shop/object/jeep.png", 50, 5);
        rangerButton = createButton("res/icons/toolkit/shop/person/ranger.png", 50, 50);

        sellButton = createButton("res/icons/toolkit/shop/other/sell.png", 50, 50);
        buildRoadButton = createButton("res/icons/toolkit/road/road-table.png", 50, 50);
        speedButton = createButton("res/icons/toolkit/speed/snail.png", 50, 50);
        speedOptions = createButton("res/icons/toolkit/speed/hippopotamus.png", 50, 50);
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
        //rightPanel.add(speedOptions);

        // Panelek hozzáadása a frame-hez
        frame.add(shopPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private JButton createButton(String iconPath, int width, int height) {
        JButton button = new JButton();
        ImageIcon icon = new ImageIcon(iconPath);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(image));
        return button;
    }

    public void addEventListeners() {

    }

    public void updateSpeed() {

    }

    public void updateToolBar() {

    }

}
