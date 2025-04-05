package entity.mobile.person;

import core.Resources;
import entity.mobile.animal.Animal;

public class Poacher extends Person {
    public Poacher(int x, int y) {
        super(x, y, Resources.Instance.ranger);
        isVisible = false;
    }

    private boolean isVisible;

    public void hide() {
        isVisible = false;
    }

    public void reveal() {
        isVisible = true;
    }

    public void move() {

    }

    public void shoot(Animal animal) {

    }
}
