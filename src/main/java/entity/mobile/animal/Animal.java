package entity.mobile.animal;

import core.Resources;
import entity.mobile.MobileEntity;
import entity.notmobile.Water;
import entity.notmobile.plant.Plant;
import map.Coordinate;
import pathFinder.PathFinder;
import safari.Safari;
import safari.Speed;
import timer.EntitiesExecutor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledFuture;

public abstract class Animal extends MobileEntity {
    protected int age;
    protected double hunger = 200;
    protected double thirst = 200;
    public static final double hungerLimit = 100;
    public static final double thirstLimit = 100;
    public static final double hungerLimit2 = 170;
    public static final double thirstLimit2 = 170;
    protected boolean movingForDrink = false;
    protected boolean movingForEat = false;
    protected List<Integer> watersID = new ArrayList<>();
    protected List<Integer> plantsID = new ArrayList<>();
    protected static final int visualRangeByPixel = 600;
    protected static final int avgRangeLimitByPixel = 1000;
    protected Random rnd = new Random();
    protected Animal target;
    protected Plant targetPlant;
    protected ScheduledFuture<List<Coordinate>> scheduledFutureCoordinatesForDrink = null;
    protected List<Coordinate> coordinatesForDrink = new ArrayList<>();
    protected ScheduledFuture<List<Coordinate>> scheduledFutureCoordinatesForEat = null;
    protected List<Coordinate> coordinatesForEat = new ArrayList<>();
    private int bornDate = Safari.Instance.getDate();

    /**
     * Constructs an Animal with specified coordinates and image.
     *
     * @param x     The x-coordinate of the animal.
     * @param y     The y-coordinate of the animal.
     * @param image The image representing the animal.
     */
    public Animal(int x, int y, BufferedImage image) {
        super(x, y, image);
    }

    /**
     * Gets the age of the animal.
     *
     * @return The age of the animal.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the hunger level of the animal.
     *
     * @return The hunger level of the animal.
     */
    public double getHunger() {
        return hunger;
    }

    /**
     * Gets the thirst level of the animal.
     *
     * @return The thirst level of the animal.
     */
    public double getThirst() {
        return thirst;
    }

    /**
     * Sets the age of the animal.
     *
     * @param age The age to set.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets the hunger level of the animal.
     *
     * @param hunger The hunger level to set.
     */
    public void setHunger(double hunger) {
        this.hunger = hunger;
    }

    /**
     * Sets the thirst level of the animal.
     *
     * @param thirst The thirst level to set.
     */
    public void setThirst(double thirst) {
        this.thirst = thirst;
    }

    /**
     * Checks if the animal is moving to drink.
     *
     * @return True if the animal is moving to drink, false otherwise.
     */
    public boolean isMovingForDrink() {
        return movingForDrink;
    }

    /**
     * Sets whether the animal is moving to drink.
     *
     * @param movingForDrink True if moving to drink, false otherwise.
     */
    public void setMovingForDrink(boolean movingForDrink) {
        this.movingForDrink = movingForDrink;
    }

    /**
     * Checks if the animal is moving to eat.
     *
     * @return True if the animal is moving to eat, false otherwise.
     */
    public boolean isMovingForEat() {
        return movingForEat;
    }

    /**
     * Sets whether the animal is moving to eat.
     *
     * @param movingForEat True if moving to eat, false otherwise.
     */
    public void setMovingForEat(boolean movingForEat) {
        this.movingForEat = movingForEat;
    }

    /**
     * Searches for the closest water source within visual range.
     *
     * @return The closest Water object if found, null otherwise.
     */
    protected Water searchClosestWater() {
        List<Water> waters = Safari.Instance.getWatersByInteger(watersID);

        boolean temp = false;
        for (Water water : waters) {
            double distance = distanceTo(x, y, water.getX(), water.getY());
            if (distance <= visualRangeByPixel) {
                temp = true;
                break;
            }
        }

        if (temp) {
            return getClosestWater(waters);
        }
        return null;
    }

    /**
     * Gets the closest water source from a list of waters.
     *
     * @param waters The list of Water objects to search from.
     * @return The closest Water object, or null if the list is empty.
     */
    protected Water getClosestWater(List<Water> waters) {
        Water closestWater = null;
        double minDistance = 0;
        for (Water water : waters) {
            double distance = distanceTo(x, y, water.getX(), water.getY());
            if (closestWater == null || distance < minDistance) {
                minDistance = distance;
                closestWater = water;
            }
        }
        return closestWater;
    }

    /**
     * Calculates the Euclidean distance between two points.
     *
     * @param entity1X The x-coordinate of the first entity.
     * @param entity1Y The y-coordinate of the first entity.
     * @param entity2X The x-coordinate of the second entity.
     * @param entity2Y The y-coordinate of the second entity.
     * @return The distance between the two points.
     */
    protected double distanceTo(int entity1X, int entity1Y, int entity2X, int entity2Y) {
        int dx = entity1X - entity2X;
        int dy = entity1Y - entity2Y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Searches for the closest plant within visual range.
     *
     * @return The closest Plant object if found, null otherwise.
     */
    protected Plant searchClosestPlant() {
        List<Plant> plants = Safari.Instance.getPlantsByInteger(plantsID);

        boolean temp = false;
        for (Plant plant : plants) {
            double distance = distanceTo(x, y, plant.getX(), plant.getY());
            if (distance <= visualRangeByPixel) {
                temp = true;
                break;
            }
        }

        if (temp) {
            return getClosestPlant(plants);
        }
        return null;
    }

    /**
     * Gets the closest plant from a list of plants.
     *
     * @param plants The list of Plant objects to search from.
     * @return The closest Plant object, or null if the list is empty.
     */
    protected Plant getClosestPlant(List<Plant> plants) {
        Plant closestPlant = null;
        double minDistance = 0;
        for (Plant plant : plants) {
            double distance = distanceTo(x, y, plant.getX(), plant.getY());
            if (closestPlant == null || distance < minDistance) {
                minDistance = distance;
                closestPlant = plant;
            }
        }
        return closestPlant;
    }

    /**
     * Moves the animal randomly by a specified number of steps.
     *
     * @param steps The number of steps to move.
     */
    protected void justMove(int steps) {
        Random rnd = new Random();
        int directionX = rnd.nextBoolean() ? -1 : 1;
        int directionY = rnd.nextBoolean() ? -1 : 1;

        int futureX = x + directionX * steps;
        int futureY = y + directionY * steps;

        if (!overlapsWaterArea(futureX, futureY)) {
            setCoordinate(futureX, futureY);
        }
    }

    /**
     * Handles the animal's thirst by scheduling a path to the closest water source.
     */
    protected void handleThirst() {
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
        movingForEat = false;
        scheduledFutureCoordinatesForEat = null;
    }

    /**
     * Moves the animal toward a water source along a precomputed path.
     *
     * @param steps The number of steps to move.
     */
    protected void moveToDrink(int steps) {
        if (coordinatesForDrink.isEmpty()) {
            movingForDrink = false;
            thirst = 200;
        } else {
            int limit = Math.min(coordinatesForDrink.size(), steps) - 1;
            for (int i = 0; i < limit; i++) {
                coordinatesForDrink.removeLast();
            }

            Coordinate coordinate = coordinatesForDrink.removeLast();
            setCoordinate(coordinate.x, coordinate.y);
        }
    }

    /**
     * Updates the animal's thirst and hunger levels, potentially killing it if either reaches zero.
     *
     * @param thirst The thirst decrement.
     * @param hunger The hunger decrement.
     */
    protected void updateThirstAndHunger(double thirst, double hunger) {
        double older = (Safari.Instance.getDate() - bornDate) * 0.01;
        this.thirst -= (thirst + older);
        this.hunger -= (hunger + older);
        if (this.thirst < 0) this.thirst = 0;
        if (this.hunger < 0) this.hunger = 0;
        if (this.thirst == 0 || this.hunger == 0) {
            alive = false;
        }
    }

    /**
     * Checks if the animal's future position overlaps with any water area.
     *
     * @param futureX The future x-coordinate.
     * @param futureY The future y-coordinate.
     * @return True if the position overlaps with water, false otherwise.
     */
    protected boolean overlapsWaterArea(int futureX, int futureY) {
        for (Water water : Safari.Instance.getWaters()) {
            if (futureX + width > water.getX() &&
                    futureX < water.getX() + water.getWidth() &&
                    futureY + height > water.getY() &&
                    futureY < water.getY() + water.getHeight()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the closest herbivorous animal.
     *
     * @return The closest herbivorous Animal, or null if none exist.
     */
    protected Animal getClosestHerbivorous() {
        Animal closestHerbivorous = null;
        double minDistance = 0;
        List<Animal> animals = Safari.Instance.getHerbivorous();
        for (Animal animal : animals) {
            double distance = distanceTo(x, y, animal.getX(), animal.getY());
            if (closestHerbivorous == null || distance < minDistance) {
                minDistance = distance;
                closestHerbivorous = animal;
            }
        }
        return closestHerbivorous;
    }

    /**
     * Handles hunger for herbivorous animals by scheduling a path to the closest plant.
     */
    protected void handleHungerHerbivorous() {
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
                targetPlant = closestPlant;
                scheduledFutureCoordinatesForEat = EntitiesExecutor.Instance.addSchedule(() -> PathFinder.ASearch(x, y, width, height, plantX, plantY, plantWidth, plantHeight));
            }
        }
        movingForDrink = false;
        scheduledFutureCoordinatesForDrink = null;
    }

    /**
     * Moves the herbivorous animal toward a plant along a precomputed path.
     *
     * @param steps The number of steps to move.
     */
    protected void moveToEatHerbivorous(int steps) {
        if (coordinatesForEat.isEmpty()) {
            movingForEat = false;
            hunger = 200;
            if (targetPlant != null) {
                synchronized (targetPlant) {
                    targetPlant.foodDecrement();
                }
            }
        } else {
            int limit = Math.min(coordinatesForEat.size(), steps) - 1;
            for (int i = 0; i < limit; i++) {
                coordinatesForEat.removeLast();
            }

            Coordinate coordinate = coordinatesForEat.removeLast();
            setCoordinate(coordinate.x, coordinate.y);
        }
    }

    /**
     * Checks if two ranges overlap with a specified margin.
     *
     * @param aStart The start of the first range.
     * @param aEnd   The end of the first range.
     * @param bStart The start of the second range.
     * @param bEnd   The end of the second range.
     * @return True if the ranges overlap, false otherwise.
     */
    protected boolean overlaps(int aStart, int aEnd, int bStart, int bEnd) {
        int margin = 5;
        return aStart < bEnd + margin && bStart < aEnd + margin;
    }

    /**
     * Sets the animal's coordinates, ensuring they stay within map boundaries.
     *
     * @param x The new x-coordinate.
     * @param y The new y-coordinate.
     */
    protected void setCoordinate(int x, int y) {
        this.x = Math.max(0, x);
        this.y = Math.max(0, y);
        this.x = Math.min(Resources.Instance.map.getWidth() - width, this.x);
        this.y = Math.min(Resources.Instance.map.getHeight() - height, this.y);
    }

    /**
     * Checks if the animal is within the average range limit of its species.
     *
     * @return True if within range, false otherwise.
     */
    protected boolean lessAvgRangeLimit() {
        Coordinate avg = Safari.Instance.avgCoordinateOf(this.getClass());
        return distanceTo(x, y, avg.x, avg.y) <= avgRangeLimitByPixel;
    }

    /**
     * Moves the animal toward the average coordinate of its species.
     *
     * @param steps The number of steps to move.
     */
    protected void moveToTheAvgRange(int steps) {
        Coordinate avg = Safari.Instance.avgCoordinateOf(this.getClass());

        int avgX = avg.x;
        int avgY = avg.y;

        int futureX = x;
        int futureY = y;

        if (avgX < x) {
            futureX -= steps;
        } else if (avgX > x) {
            futureX += steps;
        }

        if (avgY < y) {
            futureY -= steps;
        } else if (avgY > y) {
            futureY += steps;
        }

        if (!overlapsWaterArea(futureX, futureY)) {
            setCoordinate(futureX, futureY);
        }
    }

    /**
     * Handles hunger for carnivorous animals by selecting a target herbivore.
     */
    protected void handleHungerCarnivorous() {
        if (target == null || !target.isAlive()) {
            target = getClosestHerbivorous();
            if (target != null) {
                movingForEat = true;
            }
        }
    }

    /**
     * Moves the carnivorous animal toward its target herbivore.
     *
     * @param steps The number of steps to move.
     */
    protected void moveToEatCarnivorous(int steps) {
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

        int futureX = x + directionX * steps;
        int futureY = y + directionY * steps;

        if (!overlapsWaterArea(futureX, futureY)) {
            x = futureX;
            y = futureY;

            boolean nowOverlapX = overlaps(x, x + width, targetX, targetX + targetWidth);
            boolean nowOverlapY = overlaps(y, y + height, targetY, targetY + targetHeight);

            if (nowOverlapX && nowOverlapY) {
                target.setAlive(false);
                target = null;
                movingForEat = false;
                hunger = 200;
            }
        }
    }
}
