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


public class Zebra extends Animal {

    public Zebra(int x, int y) {
        super(x, y, Resources.Instance.zebraBody);
    }

    public void handleZebraMovement() {
        if (isAlive()) {
            updateThirstAndHunger(Speed.Instance.speedEnum.getZebraThirst(), Speed.Instance.speedEnum.getZebraHunger());
            if (hunger == 0 || thirst == 0) {
                alive = false;
                return;
            }
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
            }else if (scheduledFutureCoordinatesForEat != null && scheduledFutureCoordinatesForEat.state() == Future.State.SUCCESS) {
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
                moveToDrink(Speed.Instance.speedEnum.getZebraSteps());
            } else if (movingForEat) {
                moveToEatHerbivorous(Speed.Instance.speedEnum.getZebraSteps());
            } else {
                if (thirst <= thirstLimit2 && hunger <= hungerLimit2) {
                    if (lessAvgRangeLimit()) {
                        justMove(Speed.Instance.speedEnum.getZebraSteps());
                    } else {
                        moveToTheAvgRange(Speed.Instance.speedEnum.getZebraSteps());
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
