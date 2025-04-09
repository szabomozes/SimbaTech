package entity.mobile.animal;

import core.Resources;
import entity.notmobile.Water;
import map.Coordinate;
import pathFinder.PathFinder;
import safari.Safari;
import safari.Speed;
import timer.EntitiesExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class Leopard extends Animal {

    public Leopard(int x, int y) {
        super(x, y, Resources.Instance.leopardBody);
    }

    public void handleLeopardMovement() {
        if (isAlive()) {
            updateThirstAndHunger(Speed.Instance.speedEnum.getLeopardThirst(), Speed.Instance.speedEnum.getLeopardHunger());
            if (hunger == 0 || thirst == 0) {
                alive = false;
                return;
            }
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
                moveToDrink(Speed.Instance.speedEnum.getLeopardSteps());
            } else if (movingForEat) {
                moveToEatCarnivorous(Speed.Instance.speedEnum.getLeopardSteps());
            } else {
                if (thirst <= thirstLimit2 && hunger <= hungerLimit2) {
                    if (lessAvgRangeLimit()) {
                        justMove(Speed.Instance.speedEnum.getLeopardSteps());
                    } else {
                        moveToTheAvgRange(Speed.Instance.speedEnum.getLeopardSteps());
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
