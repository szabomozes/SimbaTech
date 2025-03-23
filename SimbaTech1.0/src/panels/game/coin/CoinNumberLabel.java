package panels.game.coin;

import core.Resources;
import logic.Logic;
import panels.feedback.FeedBackCardLayout;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class CoinNumberLabel extends JLabel {
    private int number;
    private int height;
    //feedback teszteléshez
    private final FeedBackCardLayout feedback = FeedBackCardLayout.Instance;
    private final Random random = new Random();

    public CoinNumberLabel(CoinStackLabel image) {
        height = image.getHeight();
        number = Logic.Instance.coin;
        setFont(Resources.Instance.menu_font.deriveFont(50f));
        updateText();

        //feedback teszteléshez
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                feedback.setVisible(true);
                callRandomFeedbackFunction();
            }
        });
    }

    public void updateText() {
        number = Logic.Instance.coin;
        String text = number + " $";
        setText(text);

        FontMetrics fm = getFontMetrics(getFont());
        int textWidth = fm.stringWidth(text);

        setBounds(60, 0, textWidth, height);
    }

    //feedback teszteléshez
    private void callRandomFeedbackFunction() {
        int choice = random.nextInt(6);
        switch (choice) {
            case 0:
                feedback.notEnoughMoney();
                break;
            case 1:
                feedback.lose();
                break;
            case 2:
                feedback.loseByMoney();
                break;
            case 3:
                feedback.loseByAnimals();
                break;
            case 4:
                feedback.loseByLimit();
                break;
            case 5:
                feedback.win();
                break;
        }}
}
