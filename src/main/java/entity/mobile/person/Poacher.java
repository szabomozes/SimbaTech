package entity.mobile.person;

import core.Resources;
import entity.mobile.animal.Animal;
import safari.Speed;
import java.util.Random;

public class Poacher extends Person {
    private double targetX, targetY;
    private double currentX, currentY;
    //private Speed speed = Speed.Instance;
    private double speed;
    public final static int rifleRange = 300;

    public Poacher(int x, int y) {
        super(x, y, Resources.Instance.poacher);
        this.currentX = x;
        this.currentY = y;
        this.targetX = x;
        this.targetY = y;
        isVisible = false;
        // !!!
        speed = 20;
    }

    private boolean isVisible;

    public void hide() {
        isVisible = false;
    }

    public void reveal() {
        isVisible = true;
    }

    public void move() {

        // Ha már elérte a célpontot, új célt sorsolunk
        if (Math.abs(currentX - targetX) < 1 && Math.abs(currentY - targetY) < 1) {
            Random rand = new Random();
            targetX = rand.nextInt(Resources.Instance.map.getWidth());
            targetY = rand.nextInt(Resources.Instance.map.getHeight());
        }

        // Kiszámoljuk az irányt
        double dx = targetX - currentX;
        double dy = targetY - currentY;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance > 0) {
            currentX += (dx / distance) * speed;
            currentY += (dy / distance) * speed;
        }

    }


    public void shoot(Animal animal) {

    }
}
