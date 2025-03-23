package panels.feedback;

import javax.swing.*;
import java.awt.*;

public class FeedBackCardLayout extends JPanel {

    public static final FeedBackCardLayout Instance = new FeedBackCardLayout();

    private FeedBackCardLayout() {
        super(new CardLayout());

        FeedBackPanel feedback = new FeedBackPanel();
        feedback.setSize(300, 200);
        add(feedback, "default");

        setPreferredSize(new Dimension(300, 200));
        setVisible(false);
    }

    public void showCard(String name) {
        ((CardLayout) getLayout()).show(this, name);
    }

    public void notEnoughMoney() {
        FeedBackPanel panel = (FeedBackPanel) getComponent(0);
        panel.notEnoughMoney();
        setVisible(true);
    }

    public void lose() {
        FeedBackPanel panel = (FeedBackPanel) getComponent(0);
        panel.lose();
        setVisible(true);
    }

    public void loseByMoney() {
        FeedBackPanel panel = (FeedBackPanel) getComponent(0);
        panel.loseByMoney();
        setVisible(true);
    }

    public void loseByAnimals() {
        FeedBackPanel panel = (FeedBackPanel) getComponent(0);
        panel.loseByAnimals();
        setVisible(true);
    }

    public void loseByLimit() {
        FeedBackPanel panel = (FeedBackPanel) getComponent(0);
        panel.loseByLimit();
        setVisible(true);
    }

    public void win() {
        FeedBackPanel panel = (FeedBackPanel) getComponent(0);
        panel.win();
        setVisible(true);
    }
}