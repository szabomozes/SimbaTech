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


public class Lion extends Animal {
    public Lion(int x, int y) {
        super(x, y, Resources.Instance.lionBody);
    }

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
