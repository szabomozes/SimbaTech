package entity.mobile.animal;

import core.Resources;
import entity.notmobile.Water;
import entity.notmobile.plant.Plant;
import map.Coordinate;
import pathFinder.PathFinder;
import safari.Safari;
import safari.Speed;
import timer.EntitiesExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Represents a Zebra, a type of herbivorous animal in the safari simulation.
 */
public class Zebra extends Animal {

    /**
     * Constructs a Zebra at the specified coordinates with the default zebra image.
     *
     * @param x The x-coordinate of the zebra.
     * @param y The y-coordinate of the zebra.
     */
    public Zebra(int x, int y) {
        super(x, y, Resources.Instance.zebraBody);
    }

    /**
     * Manages the zebra's movement and behavior based on its thirst, hunger, and state.
     * Updates thirst and hunger levels, handles movement toward water or plants, and manages
     * random or average range movement when not seeking resources. Removes the zebra
     * from the simulation if it is no longer alive.
     */
    public void handleZebraMovement() {
        if (isAlive()) {
            updateThirstAndHunger(Speed.Instance.speedEnum.getZebraThirst(), Speed.Instance.speedEnum.getZebraHunger());
            if (thirst <= thirstLimit) {
                handleThirst();
                movingForEat = false;
                scheduledFutureCoordinatesForEat = null;
            } else if (hunger <= hungerLimit) {
                handleHungerHerbivorous();
                movingForDrink = false;
                scheduledFutureCoordinatesForDrink = null;
            } else {
                movingForEat = false;
                movingForDrink = false;
            }

            if (scheduledFutureCoordinatesForDrink != null && scheduledFutureCoordinatesForDrink.state() == Future.State.SUCCESS) {
                if (coordinatesForDrink.isEmpty()) {
                    try {
                        movingForDrink = true;
                        coordinatesForDrink = scheduledFutureCoordinatesForDrink.get();
                        scheduledFutureCoordinatesForDrink = null;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else if (scheduledFutureCoordinatesForEat != null && scheduledFutureCoordinatesForEat.state() == Future.State.SUCCESS) {
                if (coordinatesForEat.isEmpty()) {
                    try {
                        movingForEat = true;
                        coordinatesForEat = scheduledFutureCoordinatesForEat.get();
                        scheduledFutureCoordinatesForEat = null;
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            if (movingForDrink) {
                moveToDrink(Speed.Instance.speedEnum.getZebraSteps());
            } else if (movingForEat) {
                moveToEatHerbivorous(Speed.Instance.speedEnum.getZebraSteps());
            } else {
                if (thirst <= thirstLimit2 && hunger <= hungerLimit2 &&
                        thirst > thirstLimit && hunger > hungerLimit) {
                    if (lessAvgRangeLimit()) {
                        justMove(Speed.Instance.speedEnum.getZebraSteps());
                    } else {
                        moveToTheAvgRange(Speed.Instance.speedEnum.getZebraSteps());
                    }
                }
            }
        } else {
            Safari.Instance.removeEntityById(id);
            if (task != null && !task.isCancelled()) {
                task.cancel(false);
            }
            if (scheduledFutureCoordinatesForDrink != null && !scheduledFutureCoordinatesForDrink.isCancelled()) {
                scheduledFutureCoordinatesForDrink.cancel(true);
            }
            if (scheduledFutureCoordinatesForEat != null && !scheduledFutureCoordinatesForEat.isCancelled()) {
                scheduledFutureCoordinatesForEat.cancel(true);
            }
        }
    }
}