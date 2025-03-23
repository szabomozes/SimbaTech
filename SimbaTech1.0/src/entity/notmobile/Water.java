package entity.notmobile;

import core.Resources;
import entity.NotMobileEntity;

import javax.swing.*;

public class Water extends NotMobileEntity {
    protected double x;
    protected double y;
    protected ImageIcon image;

    public Water(double x,double y )
    {
        this.x=x;
        this.y=y;
        this.image=new ImageIcon(Resources.Instance.water);
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
