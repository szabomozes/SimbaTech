package entity.mobile.animal;

import entity.mobile.MobileEntity;

import java.awt.image.BufferedImage;

public abstract class Animal extends MobileEntity {
    protected int age;
    protected double hunger;
    protected double thirst;
    protected boolean movingForDrink = false;
    protected boolean movingForEat = false;
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
}
