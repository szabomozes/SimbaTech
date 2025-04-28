package entity.mobile.animal;

import core.Resources;
import safari.Safari;
import safari.Speed;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Represents a Leopard, a type of carnivorous animal in the safari simulation.
 */
public class Leopard extends Animal {

    /**
     * Constructs a Leopard at the specified coordinates with the default leopard image.
     *
     * @param x The x-coordinate of the leopard.
     * @param y The y-coordinate of the leopard.
     */
    public Leopard(int x, int y) {
        super(x, y, Resources.Instance.leopardBody);
    }

    /**
     * Manages the leopard's movement and behavior based on its thirst, hunger, and state.
     * Updates thirst and hunger levels, handles movement toward water or prey, and manages
     * random or average range movement when not seeking resources. Removes the leopard
     * from the simulation if it is no longer alive.
     */
    public void handleLeopardMovement() {
        if (isAlive()) {
            updateThirstAndHunger(Speed.Instance.speedEnum.getLeopardThirst(), Speed.Instance.speedEnum.getLeopardHunger());
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
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            if (movingForDrink) {
                moveToDrink(Speed.Instance.speedEnum.getLeopardSteps());
            } else if (movingForEat) {
                moveToEatCarnivorous(Speed.Instance.speedEnum.getLeopardSteps());
            } else {
                if (thirst <= thirstLimit2 && hunger <= hungerLimit2 &&
                        thirst > thirstLimit && hunger > hungerLimit) {
                    if (lessAvgRangeLimit()) {
                        justMove(Speed.Instance.speedEnum.getLeopardSteps());
                    } else {
                        moveToTheAvgRange(Speed.Instance.speedEnum.getLeopardSteps());
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
        }
    }
}