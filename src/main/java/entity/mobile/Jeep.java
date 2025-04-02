package entity.mobile;

import core.Resources;

import javax.swing.*;

public class Jeep extends MobileEntity{
    private boolean running = false;
    private int passenger = 0;

    public Jeep(int x, int y) {
        super(x, y, Resources.Instance.jeep);
        Timer timer = new JeepTimer();
        timer.start();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getPassenger() {
        return passenger;
    }

    public void setPassenger(int passenger) {
        this.passenger = passenger;
    }
}
