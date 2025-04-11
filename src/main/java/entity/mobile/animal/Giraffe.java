package entity.mobile.animal;

import core.Resources;
import entity.Entity;
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
 * Represents a Giraffe, a type of herbivorous animal in the safari simulation.
 */
public class Giraffe extends Animal {

    /**
     * Constructs a Giraffe at the specified coordinates with the default giraffe image.
     *
     * @param x The x-coordinate of the giraffe.
     * @param y The y-coordinate of the giraffe.
     */
    public Giraffe(int x, int y) {
        super(x, y, Resources.Instance.giraffeBody);
    }

    /**
     * Manages the giraffe's movement and behavior based on its thirst, hunger, and state.
     * Updates thirst and hunger levels, handles movement toward water or food, and manages
     * random or average range movement when not seeking resources. Removes the giraffe
     * from the simulation if it is no longer alive.
     */
    public void handleGiraffeMovement() {
        if (isAlive()) {
            updateThirstAndHunger(Speed.Instance.speedEnum.getGiraffeThirst(), Speed.Instance.speedEnum.getGiraffeHunger());
            if (thirst <= thirstLimit) {
                handleThirst();
            } else if (hunger <= hungerLimit) {
                handleHungerHerbivorous();
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
            if (scheduledFutureCoordinatesForEat != null && scheduledFutureCoordinatesForEat.state() == Future.State.SUCCESS) {
                if (coordinatesForEat.isEmpty()) {
                    try {
                        movingForEat = true;
                        coordinatesForEat = scheduledFutureCoordinatesForEat.get();
                        scheduledFutureCoordinatesForEat = null;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            if (movingForDrink) {
                moveToDrink(Speed.Instance.speedEnum.getGiraffeSteps());
            } else if (movingForEat) {
                moveToEatHerbivorous(Speed.Instance.speedEnum.getGiraffeSteps());
            } else {
                if (thirst <= thirstLimit2 && hunger <= hungerLimit2 &&
                        thirst > thirstLimit && hunger > hungerLimit) {
                    if (lessAvgRangeLimit()) {
                        justMove(Speed.Instance.speedEnum.getZebraSteps());
                    } else {
                        moveToTheAvgRange(Speed.Instance.speedEnum.getGiraffeSteps());
                    }
                }
            }
        } else {
            Safari.Instance.removeEntityById(id);
            if (task != null && !task.isCancelled()) {
                task.cancel(false);
            }
        }
    }
}