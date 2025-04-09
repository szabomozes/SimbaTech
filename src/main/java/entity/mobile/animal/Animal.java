package entity.mobile.animal;

import entity.mobile.MobileEntity;
import entity.notmobile.Water;
import entity.notmobile.plant.Plant;
import pathFinder.PathFinder;
import safari.Safari;
import safari.Speed;
import timer.EntitiesExecutor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Animal extends MobileEntity {
    protected int age;
    protected double hunger = 100;
    protected double thirst = 100;
    public static final double hungerLimit = 30;
    public static final double thirstLimit = 30;
    public static final double hungerLimit2 = 70;
    public static final double thirstLimit2 = 70;
    protected boolean movingForDrink = false;
    protected boolean movingForEat = false;
    protected List<Integer> watersID = new ArrayList<>();
    protected List<Integer> plantsID = new ArrayList<>();
    protected static final int visualRangeByPixel = 600;
    protected Random rnd = new Random();
    protected Animal target;
    public Animal(int x, int y, BufferedImage image) {
        super(x, y, image);
    }

    public int getAge() {
        return age;
    }

    public double getHunger() {
        return hunger;
    }

    public double getThirst() {
        return thirst;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHunger(double hunger) {
        this.hunger = hunger;
    }

    public void setThirst(double thirst) {
        this.thirst = thirst;
    }

    public boolean isMovingForDrink() {
        return movingForDrink;
    }

    public void setMovingForDrink(boolean movingForDrink) {
        this.movingForDrink = movingForDrink;
    }

    public boolean isMovingForEat() {
        return movingForEat;
    }

    public void setMovingForEat(boolean movingForEat) {
        this.movingForEat = movingForEat;
    }

    //

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


    protected double distanceTo(int entity1X, int entity1Y, int entity2X, int entity2Y) {
        int dx = entity1X - entity2X;
        int dy = entity1Y - entity2Y;
        return Math.sqrt(dx * dx + dy * dy);
    }


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
    /// ///////

    protected void justMove(int steps) {
        Random rnd = new Random();
        int directionX = rnd.nextBoolean() ? -1 : 1;
        int directionY = rnd.nextBoolean() ? -1 : 1;

        int futureX = x + directionX * steps;
        int futureY = y + directionY * steps;

        if (!overlapsWaterArea(futureX, futureY)) {
            x = futureX;
            y = futureY;
        }
        // Ha vízre menne, nem lép
    }

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
}
