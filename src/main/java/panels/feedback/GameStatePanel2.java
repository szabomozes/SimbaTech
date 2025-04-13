package panels.feedback;

import core.Resources;
import entity.mobile.animal.Giraffe;
import entity.mobile.animal.Leopard;
import entity.mobile.animal.Lion;
import entity.mobile.animal.Zebra;
import panels.game.EventPanel;
import panels.game.GameStateTriggerButton;
import panels.game.toolbar.ToolBarCardLayout;
import safari.GameStateChecker;
import safari.Safari;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;

public class GameStatePanel2 extends JPanel {

    private final BufferedImage image = Resources.Instance.info;
    protected GridBagConstraints gbc = new GridBagConstraints();

    public GameStatePanel2(String toolBarCardLayout) {
        ToolBarCardLayout.Instance.showCard("void");

        setSize(new Dimension(300, 200));
        setLayout(new GridBagLayout());

        JLabel imageLabel = new JLabel(new ImageIcon(image));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(imageLabel, gbc);

        HashMap<String, Integer[]> text = new HashMap<>();

    }

    public void updatePosition() {
        setBounds(getParent().getWidth()/2 - getWidth() / 2, getParent().getHeight()/2 - getHeight() / 2, getWidth(), getHeight());
    }

    public HashMap<String, Integer[]> getText() {
        HashMap<String, Integer[]> hasmap = new HashMap<>();

        GameStateChecker checker = Safari.Instance.getGameStateChecker();
        int herbivoreThreshold = checker.getHerbivoreThreshold();
        int predatorThreshold = checker.getPredatorThreshold();
        int visitorThreshold = checker.getVisitorThreshold();
        int coinThreshold = checker.getCoinThreshold();

        int herbivoreCount = (int) Safari.Instance.getAnimals().stream()
                .filter(a -> a instanceof Zebra || a instanceof Giraffe)
                .count();

        int carnivoreCount = (int) Safari.Instance.getAnimals().stream()
                .filter(a -> a instanceof Lion || a instanceof Leopard)
                .count();

        int visitorCount = Safari.Instance.getPassengers();

        int balance = Safari.Instance.coin;

        hasmap.put("növényevő", new Integer[]{herbivoreCount, herbivoreThreshold});
        hasmap.put("húsevő", new Integer[]{carnivoreCount, predatorThreshold});
        hasmap.put("látogató", new Integer[]{visitorCount, visitorThreshold});
        hasmap.put("egyenleg", new Integer[]{balance, coinThreshold});
        return hasmap;
    }
}