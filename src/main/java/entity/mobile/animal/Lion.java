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
 * Represents a Lion, a type of carnivorous animal in the safari simulation.
 */
public class Lion extends Animal {

    /**
     * Constructs a Lion at the specified coordinates with the default lion image.
     *
     * @param x The x-coordinate of the lion.
     * @param y The y-coordinate of the lion.
     */
    public Lion(int x, int y) {
        super(x, y, Resources.Instance.lionBody);
    }

    /**
     * Manages the lion's movement and behavior based on its thirst, hunger, and state.
     * Updates thirst and hunger levels, handles movement toward water or prey, and manages
     * random or average range movement when not seeking resources. Removes the lion
     * from the simulation if it is no longer alive.
     */
    public void handleLionMovement() {
        if (isAlive()) {
            updateThirstAndHunger(Speed.Instance.speedEnum.getLionThirst(), Speed.Instance.speedEnum.getLionHunger());
            if (thirst <= thirstLimit) {
                handleThirst();
                movingForEat = false;
                target = null;
            } else if (hunger <= hungerLimit) {
                handleHungerCarnivorous();
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
            }

            if (movingForDrink) {
                moveToDrink(Speed.Instance.speedEnum.getLionSteps());
            } else if (movingForEat) {
                moveToEatCarnivorous(Speed.Instance.speedEnum.getLionSteps());
            } else {
                if (thirst <= thirstLimit2 && hunger <= hungerLimit2) {
                    if (lessAvgRangeLimit()) {
                        justMove(Speed.Instance.speedEnum.getLionSteps());
                    } else {
                        moveToTheAvgRange(Speed.Instance.speedEnum.getLionSteps());
                    }
                }
            }
        } else {
            System.out.println("Halott");
            Safari.Instance.removeEntityById(id);
            if (task != null && !task.isCancelled()) {
                task.cancel(false);
            }
        }
    }
}