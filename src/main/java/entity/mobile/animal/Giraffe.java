package entity.mobile.animal;

import core.Resources;
import entity.Entity;
import entity.notmobile.Water;
import entity.notmobile.plant.Plant;
import map.Coordinate;
import pathFinder.PathFinder;
import safari.Safari;
import safari.Speed;
import safari.SpeedEnum;
import timer.EntitiesExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.Random;


public class Giraffe extends Animal {

    private ScheduledFuture<List<Coordinate>> scheduledFutureCoordinatesForDrink = null;
    private ScheduledFuture<List<Coordinate>> scheduledFutureCoordinatesForEat = null;
    private List<Coordinate> coordinatesForDrink = new ArrayList<>();
    private List<Coordinate> coordinatesForEat = new ArrayList<>();

    public Giraffe(int x, int y) {
        super(x, y, Resources.Instance.giraffeBody);
    }

    public void handleGiraffeMovement() {
        if (isAlive()) {
            updateThirstAndHunger();
            if (thirst <= thirstLimit) {
                handleThirst();
                movingForEat = false;
                scheduledFutureCoordinatesForEat = null;
            } else if (hunger <= hungerLimit) {
                handleHunger();
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
                moveToDrink();
            } else if (movingForEat) {
                moveToEat();
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

    private void updateThirstAndHunger() {
        thirst = (Math.max(thirst - Speed.Instance.speedEnum.getGiraffeThirst(), 0));
        hunger = (Math.max(hunger - Speed.Instance.speedEnum.getGiraffeHunger(), 0));
    }


    private void handleThirst() {
        if (scheduledFutureCoordinatesForDrink == null && !movingForDrink) {
            Water closestWater = searchClosestWater();

            if (closestWater == null) {
                closestWater = getClosestWater(Safari.Instance.getWaters());
                if (closestWater != null) {
                    watersID.add(closestWater.id);
                }
            }
            if (closestWater != null) {
                int waterX = closestWater.getX();
                int waterY = closestWater.getY();
                int waterWidth = closestWater.getWidth();
                int waterHeight = closestWater.getHeight();
                scheduledFutureCoordinatesForDrink = EntitiesExecutor.Instance.addSchedule(() -> PathFinder.ASearch(x, y, width, height, waterX, waterY, waterWidth, waterHeight));
            }
        }
    }

    private void moveToDrink() {
        if (coordinatesForDrink.isEmpty()) {
            movingForDrink = false;
            thirst = 100;
            System.out.println("Stop drinking");
        } else {
            int limit = Math.min(coordinatesForDrink.size(), Speed.Instance.speedEnum.getGiraffeSteps()) - 1;
            for (int i = 0; i < limit; i++) {
                coordinatesForDrink.removeLast();
            }

            Coordinate coordinate = coordinatesForDrink.removeLast();
            x = coordinate.x;
            y = coordinate.y;
        }
    }


    private void handleHunger() {
        if (scheduledFutureCoordinatesForEat == null && !movingForEat) {
            Plant closestPlant = searchClosestPlant();

            if (closestPlant == null) {
                closestPlant = getClosestPlant(Safari.Instance.getPlants());
                if (closestPlant != null) {
                    plantsID.add(closestPlant.id);
                }
            }
            if (closestPlant != null) {
                int plantX = closestPlant.getX();
                int plantY = closestPlant.getY();
                int plantWidth = closestPlant.getWidth();
                int plantHeight = closestPlant.getHeight();
                scheduledFutureCoordinatesForEat = EntitiesExecutor.Instance.addSchedule(() -> PathFinder.ASearch(x, y, width, height, plantX, plantY, plantWidth, plantHeight));
            }
        }
    }


    private void moveToEat() {
        if (coordinatesForEat.isEmpty()) {
            movingForEat = false;
            hunger = 100;
            System.out.println("Stop eating");
        } else {
            int limit = Math.min(coordinatesForEat.size(), Speed.Instance.speedEnum.getGiraffeSteps()) - 1;
            for (int i = 0; i < limit; i++) {
                coordinatesForEat.removeLast();
            }

            Coordinate coordinate = coordinatesForEat.removeLast();
            x = coordinate.x;
            y = coordinate.y;
        }
    }



}
