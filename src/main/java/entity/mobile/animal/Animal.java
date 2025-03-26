package entity.mobile.animal;

import entity.mobile.MobileEntity;

import java.awt.image.BufferedImage;

public abstract class Animal extends MobileEntity {
    private int age;
    private int hunger;
    private int thirst;

    public Animal(int x, int y, BufferedImage image) {
        super(x, y, image);
    }
}
