package entity.mobile;

import entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class MobileEntity extends Entity {
    public MobileEntity(int x, int y, BufferedImage image) {
        super(x, y, image);
    }
}
