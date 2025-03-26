package entity.notmobile.plant;

import entity.notmobile.NotMobileEntity;

import java.awt.image.BufferedImage;

public abstract class Plant extends NotMobileEntity {
    private int age;
    private int hunger;
    private int thirst;

    public Plant(int x, int y, BufferedImage image) {
        super(x, y, image);
    }
}
