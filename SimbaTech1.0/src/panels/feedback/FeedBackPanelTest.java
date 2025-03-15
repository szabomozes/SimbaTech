package panels.feedback;

import core.Resources;

import javax.swing.*;
import java.awt.*;

public class FeedBackPanelTest {
    public static void main(String[] args) {
        Resources.Instance.load();


        JFrame mainFrame = new JFrame("Test");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);

        FeedBackPanel feedbackPanel = new FeedBackPanel();
        mainFrame.add(feedbackPanel, BorderLayout.CENTER);

        mainFrame.setVisible(true);
        //feedbackPanel.notEnoughMoney();
        //feedbackPanel.lose();
        //feedbackPanel.loseByMoney();
        //feedbackPanel.loseByAnimals();
        feedbackPanel.loseByLimit();
        //feedbackPanel.win();
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}
