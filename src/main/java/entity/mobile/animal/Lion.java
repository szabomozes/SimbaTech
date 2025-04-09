package entity.mobile.animal;

import core.Resources;
import entity.notmobile.Water;
import entity.notmobile.plant.Plant;
import map.Coordinate;
import pathFinder.PathFinder;
import safari.Safari;
import safari.Speed;
import timer.EntitiesExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;


public class Lion extends Animal {

    private ScheduledFuture<List<Coordinate>> scheduledFutureCoordinatesForDrink = null;
    private List<Coordinate> coordinatesForDrink = new ArrayList<>();
    private String drinkOrEat;

    public Lion(int x, int y) {
        super(x, y, Resources.Instance.lionBody);
    }
    public void handleLionMovement() {
        if (isAlive()) {
            updateThirstAndHunger();
            if (thirst <= thirstLimit) {
                handleThirst();
                movingForEat = false;
                target = null;
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

            if (movingForDrink) {
                moveToDrink();
            } else if (movingForEat) {
                moveToEat();
            } else {
                if (thirst <= thirstLimit2 && hunger <= hungerLimit2) {
                    justMove(Speed.Instance.speedEnum.getLionSteps());
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
        thirst = (Math.max(thirst - Speed.Instance.speedEnum.getLionThirst(), 0));
        hunger = (Math.max(hunger - Speed.Instance.speedEnum.getLionHunger(), 0));
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
        if (target == null || !target.isAlive()) {
            target = getClosestHerbivorous();
            if (target != null) {
                movingForEat = true;
            }
        }
    }


    private void moveToEat() {
        if (target == null || !target.isAlive()) {
            return;
        }

        int targetX = target.getX();
        int targetY = target.getY();
        int targetWidth = target.getWidth();
        int targetHeight = target.getHeight();

        int directionX = 0;
        int directionY = 0;

        boolean overlapX = overlaps(x, x + width, targetX, targetX + targetWidth);
        boolean overlapY = overlaps(y, y + height, targetY, targetY + targetHeight);

        if (!overlapX) {
            if (x + width < targetX) {
                directionX = 1;
            } else if (targetX + targetWidth < x) {
                directionX = -1;
            }
        }

        if (!overlapY) {
            if (y + height < targetY) {
                directionY = 1;
            } else if (targetY + targetHeight < y) {
                directionY = -1;
            }
        }

        int step = Speed.Instance.speedEnum.getLionSteps();
        int futureX = x + directionX * step;
        int futureY = y + directionY * step;

        if (!overlapsWaterArea(futureX, futureY)) {
            x = futureX;
            y = futureY;

            // Ellenőrizze újra az átfedést a mozgás után
            boolean nowOverlapX = overlaps(x, x + width, targetX, targetX + targetWidth);
            boolean nowOverlapY = overlaps(y, y + height, targetY, targetY + targetHeight);

            if (nowOverlapX && nowOverlapY) {
                target.setAlive(false);
                target = null;
                movingForEat = false;
                hunger = 100;
            }
        }
    }

    private boolean overlaps(int aStart, int aEnd, int bStart, int bEnd) {
        int margin = 5; // Ekkora távolságon belül már átfedésnek számít
        return aStart < bEnd + margin && bStart < aEnd + margin;
    }
}
