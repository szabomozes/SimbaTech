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
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class GameStatePanel extends JPanel {

    private final BufferedImage image = Resources.Instance.info;
    protected GridBagConstraints gbc = new GridBagConstraints();

    public GameStatePanel(String toolBarCardLayout) {
        ToolBarCardLayout.Instance.showCard("void");

        setSize(new Dimension(300, 300));

        setLayout(new GridBagLayout());

        JLabel imageLabel = new JLabel(new ImageIcon(image));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(imageLabel, gbc);
        gbc.gridy = 1;
        JPanel textPanel = getTextPanel();
        add(textPanel, gbc);

        gbc.gridy = 2;
        JButton button = new JButton("Rendben");
        button.setFocusPainted(false);
        button.setFont(Resources.Instance.menu_font.deriveFont(20f));
        button.addActionListener(e-> {
            ToolBarCardLayout.Instance.showCard(toolBarCardLayout);
            ((EventPanel) getParent()).setStatePanel(null);
            GameStateTriggerButton.Instance.setClicked(true);
        });

        add(button, gbc);

    }

    public void updatePosition() {
        setBounds(getParent().getWidth()/2 - getWidth() / 2, getParent().getHeight()/2 - getHeight() / 2, getWidth(), getHeight());
    }


    private JPanel getTextPanel() {
        HashMap<String, Integer[]> text = getText();
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        int row = 0;
        for (Entry<String, Integer[]> entry : text.entrySet()) {
            String key = entry.getKey();
            Integer[] values = entry.getValue();

            gbc.gridy = row;

            // Kulcs (bal oldal)
            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(0, 0, 0, 10);
            JLabel label1 = new JLabel(key);
            label1.setFont(Resources.Instance.menu_font.deriveFont(30f));
            panel.add(label1, gbc);

            // Első érték (bal közép)
            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.EAST;
            JLabel label2 = new JLabel(String.valueOf(values[0]));
            label2.setFont(Resources.Instance.menu_font.deriveFont(30f));
            label2.setForeground(values[0] < values[1] ? Color.RED : new Color(0, 100, 0));
            panel.add(label2, gbc);

            // Elválasztó
            gbc.gridx = 2;
            JLabel label3 = new JLabel(" | ");
            label3.setFont(Resources.Instance.menu_font.deriveFont(30f));
            panel.add(label3, gbc);

            // Második érték (jobb közép)
            gbc.gridx = 3;
            gbc.anchor = GridBagConstraints.WEST;
            JLabel label4 = new JLabel(String.valueOf(values[1]));
            label4.setFont(Resources.Instance.menu_font.deriveFont(30f));
            panel.add(label4, gbc);

            row++;
        }

        return panel;
    }


    private LinkedHashMap<String, Integer[]> getText() {
        LinkedHashMap<String, Integer[]> hasmap = new LinkedHashMap<>();


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