package entity.mobile.person;

import entity.mobile.MobileEntity;

import java.awt.image.BufferedImage;

public abstract class Person extends MobileEntity {
    public Person(int x, int y, BufferedImage image) {
        super(x, y, image);
    }
}


