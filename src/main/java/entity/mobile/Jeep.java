package entity.mobile;

import core.Resources;
import map.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Jeep extends MobileEntity{
    private boolean isAvaliable = true;
    private boolean forward = true;
    private int passenger = 0;
    private int selectedPathIndex = 0;
    private int pathIndex = 0;
    private int MaxPathIndex = 0;
    private List<Coordinate> path = new ArrayList<>();
    private JeepTimerPlus timer;

    public Jeep(int x, int y) {
        super(x, y, Resources.Instance.jeep);
        timer = new JeepTimerPlus(this);
    }

    public int getPassenger() {
        return passenger;
    }

    public void setPassenger(int passenger) {
        this.passenger = passenger;
    }

    public boolean isAvaliable() {
        return isAvaliable;
    }

    public void setAvaliable(boolean avaliable) {
        isAvaliable = avaliable;
    }

    public int getSelectedPathIndex() {
        return selectedPathIndex;
    }

    public void setSelectedPathIndex(int selectedPathIndex) {
        this.selectedPathIndex = selectedPathIndex;
    }

    public boolean isForward() {
        return forward;
    }

    public void setForward(boolean forward) {
        this.forward = forward;
    }

    public int getPathIndex() {
        return pathIndex;
    }

    public void setPathIndex(int pathIndex) {
        this.pathIndex = pathIndex;
    }

    public int getMaxPathIndex() {
        return MaxPathIndex;
    }

    public void setMaxPathIndex(int maxPathIndex) {
        MaxPathIndex = maxPathIndex;
    }

    public List<Coordinate> getPath() {
        return path;
    }

    public void setPath(List<Coordinate> path) {
        this.path = path;
    }
}
