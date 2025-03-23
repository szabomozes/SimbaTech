package logic;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DateTimer extends Timer {

    private int tick = 0;

    public DateTimer() {
        super(1000, null);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tick++;
                System.out.println(tick);
                if (tick >= Speed.Instance.speedEnum.getDateTick()){
                    Logic.Instance.updateDate();
                    tick = 0;
                }
            }
        });
    }

    public void stopTimer() {
        stop();  // Swing Timer beépített stop() metódusa
        tick = 0; // Reseteljük a számlálót, ha újraindulna
    }


}
