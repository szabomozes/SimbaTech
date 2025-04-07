package entity.mobile;

import core.Resources;
import map.Coordinate;
import safari.Prices;
import safari.Safari;
import safari.Speed;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jeep extends MobileEntity{
    private Random rnd = new Random();
    private boolean isAvaliable = true;
    private boolean forward = true;
    private int passenger = 0;
    private int selectedPathIndex = 0;
    private int pathIndex = 0;
    private int MaxPathIndex = 0;
    private List<Coordinate> path = new ArrayList<>();

    public Jeep(int x, int y) {
        super(x, y, Resources.Instance.jeep);
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


    public void handleJeepMovement() {
        if (isAvaliable) {
            initializeJeep();
        } else if (forward) {
            moveJeepForward();
        } else if (!forward && passenger > 0) {
            collectPassengerPayment();
        } else if (!forward && passenger == 0) {
            moveJeepBackward();
        }
    }

    private void initializeJeep() {
        isAvaliable = false;
        passenger = rnd.nextInt(4) + 1;
        selectedPathIndex = rnd.nextInt(Safari.Instance.getPaths().size());
        forward = true;
        pathIndex = 0;
        path = Safari.Instance.getPaths().get(selectedPathIndex).getPathCoordinations();
        MaxPathIndex = path.size();
    }

    private void moveJeepForward() {
        pathIndex += Speed.Instance.speedEnum.getJeepSteps();
        if (pathIndex >= MaxPathIndex) {
            pathIndex = MaxPathIndex - 1;
            forward = false;
        }
        updateJeepPosition();
    }

    private void collectPassengerPayment() {
        Safari.Instance.coin += (int) (Prices.getPriceByEnum(Prices.PASSENGER) * passenger);
        passenger = 0;
    }

    private void moveJeepBackward() {
        pathIndex -= Speed.Instance.speedEnum.getJeepSteps();
        if (pathIndex < 0) {
            pathIndex = 0;
            isAvaliable = true;
        }
        updateJeepPosition();
    }

    private void updateJeepPosition() {
        setX(path.get(pathIndex).x - getWidth() / 2);
        setY(path.get(pathIndex).y - getHeight() / 2);
    }

}