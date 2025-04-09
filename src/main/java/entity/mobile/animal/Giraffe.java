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


public class Giraffe extends Animal {

    public Giraffe(int x, int y) {
        super(x, y, Resources.Instance.giraffeBody);
    }

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
                moveToEatherbivorous(Speed.Instance.speedEnum.getGiraffeSteps());
            } else {
                if (thirst <= thirstLimit2 && hunger <= hungerLimit2) {
                    justMove(Speed.Instance.speedEnum.getZebraSteps());
                    // TODO: csapatban mozogjon
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
