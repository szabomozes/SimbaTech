package entity.notmobile.plants;

import entity.NotMobileEntity;

import javax.swing.*;

public class Plant extends NotMobileEntity {
    protected double x;
    protected double y;
    protected ImageIcon image;

    public Plant(double x,double y, ImageIcon image)
    {
        this.x=x;
        this.y=y;
        this.image=image;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public ImageIcon getImage()  {
        return image;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }


}
