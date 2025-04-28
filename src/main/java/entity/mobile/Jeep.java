package entity.mobile;

import core.Resources;
import entity.Entity;
import map.Coordinate;
import safari.Prices;
import safari.Safari;
import safari.Speed;

import java.util.*;

/**
 * Represents a Jeep, a mobile entity in the safari simulation that transports passengers along predefined paths,
 * collects payments, and returns to its starting point.
 */
public class Jeep extends MobileEntity {
    private final Random rnd = new Random();
    private boolean isAvaliable = true;
    private boolean forward = true;
    private int passenger = 0;
    private int selectedPathIndex = 0;
    private int pathIndex = 0;
    private int MaxPathIndex = 0;
    private List<Coordinate> path = new ArrayList<>();
    private final Set<Integer> visitedIDs = new HashSet<>();
    private int maxVisitedIDs = 0;
    protected static final int visualRangeByPixel = 700;

    /**
     * Constructs a Jeep at the specified coordinates with the default jeep image.
     *
     * @param x The x-coordinate of the jeep.
     * @param y The y-coordinate of the jeep.
     */
    public Jeep(int x, int y) {
        super(x, y, Resources.Instance.jeep);
    }

    /**
     * Gets the number of passengers in the jeep.
     *
     * @return The number of passengers.
     */
    public int getPassenger() {
        return passenger;
    }

    /**
     * Sets the number of passengers in the jeep.
     *
     * @param passenger The number of passengers to set.
     */
    public void setPassenger(int passenger) {
        this.passenger = passenger;
    }

    /**
     * Checks if the jeep is available to start a new trip.
     *
     * @return True if the jeep is available, false otherwise.
     */
    public boolean isAvaliable() {
        return isAvaliable;
    }

    /**
     * Sets whether the jeep is available to start a new trip.
     *
     * @param avaliable True if the jeep is available, false otherwise.
     */
    public void setAvaliable(boolean avaliable) {
        isAvaliable = avaliable;
    }

    /**
     * Gets the index of the selected path the jeep is following.
     *
     * @return The selected path index.
     */
    public int getSelectedPathIndex() {
        return selectedPathIndex;
    }

    /**
     * Sets the index of the selected path for the jeep to follow.
     *
     * @param selectedPathIndex The path index to set.
     */
    public void setSelectedPathIndex(int selectedPathIndex) {
        this.selectedPathIndex = selectedPathIndex;
    }

    /**
     * Checks if the jeep is moving forward along its path.
     *
     * @return True if moving forward, false otherwise.
     */
    public boolean isForward() {
        return forward;
    }

    /**
     * Sets whether the jeep is moving forward along its path.
     *
     * @param forward True if moving forward, false otherwise.
     */
    public void setForward(boolean forward) {
        this.forward = forward;
    }

    /**
     * Gets the current index in the path the jeep is following.
     *
     * @return The current path index.
     */
    public int getPathIndex() {
        return pathIndex;
    }

    /**
     * Sets the current index in the path the jeep is following.
     *
     * @param pathIndex The path index to set.
     */
    public void setPathIndex(int pathIndex) {
        this.pathIndex = pathIndex;
    }

    /**
     * Gets the maximum index of the path the jeep is following.
     *
     * @return The maximum path index.
     */
    public int getMaxPathIndex() {
        return MaxPathIndex;
    }

    /**
     * Sets the maximum index of the path the jeep is following.
     *
     * @param maxPathIndex The maximum path index to set.
     */
    public void setMaxPathIndex(int maxPathIndex) {
        MaxPathIndex = maxPathIndex;
    }

    /**
     * Gets the list of coordinates representing the path the jeep is following.
     *
     * @return The path coordinates.
     */
    public List<Coordinate> getPath() {
        return path;
    }

    /**
     * Sets the list of coordinates representing the path the jeep will follow.
     *
     * @param path The path coordinates to set.
     */
    public void setPath(List<Coordinate> path) {
        this.path = path;
    }

    /**
     * Manages the jeep's movement and behavior based on its state.
     * Initializes the jeep if available, moves forward or backward along the path,
     * and collects passenger payments when returning.
     */
    public void handleJeepMovement() {
        if (alive) {
            if (isAvaliable) {
                initializeJeep();
            } else if (forward) {
                moveJeepForward();
            } else if (passenger > 0) {
                collectPassengerPayment();
            } else if (passenger == 0) {
                moveJeepBackward();
            }
        } else {
            Safari.Instance.removeEntityById(id);
            if (task != null && !task.isCancelled()) {
                task.cancel(false);
            }
        }
    }

    /**
     * Initializes the jeep for a new trip by setting a random number of passengers,
     * selecting a random path, and preparing to move forward.
     */
    private void initializeJeep() {
        if (Safari.Instance.getPaths().isEmpty()) {
            return;
        }
        visitedIDs.clear();
        maxVisitedIDs = Safari.Instance.getAnimals().size();
        image = Resources.Instance.jeep;
        isAvaliable = false;
        passenger = rnd.nextInt(4) + 1;
        selectedPathIndex = rnd.nextInt(Safari.Instance.getPaths().size());
        forward = true;
        pathIndex = 0;
        path = Safari.Instance.getPaths().get(selectedPathIndex).getPathCoordinations();
        MaxPathIndex = path.size();
    }

    /**
     * Moves the jeep forward along its path, switching to backward when the end is reached.
     */
    private void moveJeepForward() {
        pathIndex += Speed.Instance.speedEnum.getJeepSteps();
        if (pathIndex >= MaxPathIndex) {
            pathIndex = MaxPathIndex - 1;
            forward = false;
        }
        updateJeepPosition();
        checkAnimals();
    }

    /**
     * Collects payment from passengers and updates safari records when the jeep begins its return trip.
     */
    private void collectPassengerPayment() {
        image = Resources.Instance.jeep_back;
        double visitRatio = maxVisitedIDs > 0 ? (double) visitedIDs.size() / maxVisitedIDs : 0.0;
        int basePayment = (int) (Prices.getPriceByEnum(Prices.PASSENGER) * passenger);
        int bonusPayment = (int) (Prices.getPriceByEnum(Prices.PASSENGER) * passenger * visitRatio);
        Safari.Instance.coin += basePayment + bonusPayment;
        Safari.Instance.addPassengers(passenger);
        System.out.println("visitors: " + Safari.Instance.getPassengers());
        passenger = 0;
    }

    /**
     * Moves the jeep backward along its path, becoming available again when it reaches the start.
     */
    private void moveJeepBackward() {
        pathIndex -= Speed.Instance.speedEnum.getJeepSteps();
        if (pathIndex < 0) {
            pathIndex = 0;
            isAvaliable = true;
        }
        updateJeepPosition();
    }

    /**
     * Updates the jeep's position based on the current path index, centering the jeep on the path coordinate.
     */
    private void updateJeepPosition() {
        setX(path.get(pathIndex).x - getWidth() / 2);
        setY(path.get(pathIndex).y - getHeight() / 2);
    }

    /**
     * Adds animals to {@code visitedIDs} if they are outside the visual range.
     */
    private void checkAnimals() {
        List<Entity> animals = new ArrayList<>(Safari.Instance.getAnimals());
        for (Entity animal : animals) {
            if (visualRangeByPixel < distanceTo(animal)) {
                visitedIDs.add(animal.id);
            }
        }
    }

    /**
     * Calculates the Euclidean distance from this entity to another specified entity.
     *
     * @param entity the target entity to which the distance is measured
     * @return the distance between this entity and the specified entity
     */
    protected double distanceTo(Entity entity) {
        int dx = x - entity.getX();
        int dy = y - entity.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

}