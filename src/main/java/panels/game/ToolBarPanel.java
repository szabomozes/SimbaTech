package panels.game;

import javax.swing.*;
import java.awt.*;

public class ToolBarPanel extends JPanel {

    private JPanel shopPanel, rightPanel;
    private JButton lionButton, leopardButton, zebraButton, giraffeButton;
    private JButton palmTreeButton, panicumButton, baobabButton;
    private JButton waterAreaButton, jeepButton, rangerButton;
    private JButton sellButton, buildRoadButton, speedButton, speedOptions;
    private final int IMG_WIDTH = 30;
    private final int IMG_HEIGHT = 30;

    public ToolBarPanel() {
        setLayout(new BorderLayout());

        initComponents();
        setupLayout();
    }

    private void initComponents() {
        shopPanel = new JPanel();
        rightPanel = new JPanel();

        // Gombok létrehozása
        lionButton = createButton("res/icons/toolkit/shop/animal/face/lion-face.png");
        leopardButton = createButton("res/icons/toolkit/shop/animal/face/leopard-face.png");
        zebraButton = createButton("res/icons/toolkit/shop/animal/face/zebra-face.png");
        giraffeButton = createButton("res/icons/toolkit/shop/animal/face/giraffe-face.png");

        palmTreeButton = createButton("res/icons/toolkit/shop/plant/palm-tree.png");
        panicumButton = createButton("res/icons/toolkit/shop/plant/pancium.png");
        baobabButton = createButton("res/icons/toolkit/shop/plant/baobab.png");

        waterAreaButton = createButton("res/icons/toolkit/shop/object/water.png");
        jeepButton = createButton("res/icons/toolkit/shop/object/jeep.png");
        rangerButton = createButton("res/icons/toolkit/shop/person/ranger.png");

        sellButton = createButton("res/icons/toolkit/shop/other/sell.png");
        buildRoadButton = createButton("res/icons/toolkit/road/road-table.png");
        speedButton = createButton("res/icons/toolkit/speed/snail.png");
        speedOptions = createButton("res/icons/toolkit/speed/hippopotamus.png");
    }

    private void setupLayout() {
        shopPanel.setLayout(new GridLayout(2, 5));
        rightPanel.setLayout(new GridLayout(1, 3));

        // Gombok hozzáadása a panelekhez
        shopPanel.add(lionButton);
        shopPanel.add(leopardButton);
        shopPanel.add(palmTreeButton);
        shopPanel.add(panicumButton);
        shopPanel.add(baobabButton);

        shopPanel.add(zebraButton);
        shopPanel.add(giraffeButton);
        shopPanel.add(waterAreaButton);
        shopPanel.add(jeepButton);
        shopPanel.add(rangerButton);

        rightPanel.add(sellButton);
        rightPanel.add(buildRoadButton);
        rightPanel.add(speedButton);
        //rightPanel.add(speedOptions);

        // Panelek hozzáadása a frame-hez
        add(shopPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        setVisible(true);
    }

    private JButton createButton(String iconPath) {
        JButton button = new JButton();
        ImageIcon icon = new ImageIcon(iconPath);
        Image image = icon.getImage().getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(image));
        button.setMinimumSize(new Dimension(32, 32));
        button.setMaximumSize(new Dimension(32, 32));
        return button;
    }

    public void addEventListeners() {

    }

    public void updateSpeed() {

    }

    public void updateToolBar() {

    }

}
