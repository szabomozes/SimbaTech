package entity.mobile.animal;

import core.Resources;
import safari.Safari;
import safari.Speed;


public class Giraffe extends Animal {

    public static final double hungerLimit = 30;
    public static final double thirstLimit = 30;

    public Giraffe(int x, int y) {
        super(x, y, Resources.Instance.giraffeBody);
    }

    public void handleGiraffeMovement() {
        updateThirstAndHunger();
        if (isAlive()) {
            try {
                Thread.sleep(5000);
                setAlive(false);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("halott");
            Safari.Instance.removeEntityById(id);
            if (task != null && !task.isCancelled()) {
                task.cancel(false);
            }

        }

        /*
        if (thirst <= thirstLimit && !Safari.Instance.getWaters().isEmpty()) {
            Safari.Instance.handleThirst(giraffe);
            giraffe.setMovingForEat(false);
            giraffe.setMovingInTheArea(false);
        } else if (hunger <= hungerLimit) {
            Safari.Instance.handleHunger(giraffe);
            giraffe.setMovingForDrink(false);
            giraffe.setMovingInTheArea(false);
        } else {
            //Safari.Instance.handleMovingInTheArea(giraffe);
        }

        if (giraffe.isMovingForDrink()) {
            moveToDrink();
        } else if (giraffe.isMovingForEat()) {
            moveToEat();
        } else {
            Safari.Instance.directionInTheArea(giraffe);
            Safari.Instance.moveInTheArea(giraffe, Speed.Instance.speedEnum.getGiraffeSteps());
        }
        */
    }

    private void updateThirstAndHunger() {
        setThirst(Math.max(getThirst() - Speed.Instance.speedEnum.getGiraffeThirst(), 0));
        setHunger(Math.max(getHunger() - Speed.Instance.speedEnum.getGiraffeHunger(), 0));
    }
}
