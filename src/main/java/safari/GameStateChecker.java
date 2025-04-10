package safari;

import entity.Entity;
import entity.mobile.animal.*;

import java.util.ArrayList;
import java.util.List;

public class GameStateChecker {
    private DifficultyEnum difficulty;
    private final int EASY = 3;
    private final int MEDIUM = 6;
    private final int HARD = 12;
    private int visitorThreshold;
    private int herbivoreThreshold;
    private int predatorThreshold;
    private int coinThreshold;

    public GameStateChecker(DifficultyEnum difficulty) {
        switch (difficulty) {
            case EASY -> setEasy();
            case MEDIUM -> setMedium();
            case HARD -> setHard();
        }
        System.out.println("TRESHOLD SET IN -----------> " + difficulty);
    }

    public boolean instantLose(int coins, List<Entity> animals) {
        if (coins < 0) {
            System.out.println("Vesztettél: Csődbe mentél!");
            return true;
        } else if (animals.isEmpty()) {
            System.out.println("Vesztettél: Minden állat elpusztult!");
            return true;
        }
        return false;
    }

    public boolean checkWin(DifficultyEnum difficulty, int date, List<Entity> animals, int coin) {
        int cycle = 0;
        switch (difficulty) {
            case EASY -> cycle = EASY * 30;
            case MEDIUM -> cycle = MEDIUM * 30;
            case HARD -> cycle = HARD * 30;
        }
        List<Animal> herbivores = new ArrayList<>(); //Zebra , Giraffe
        for (Entity animal : animals) {
            if (animal instanceof Zebra || animal instanceof Giraffe) {
                herbivores.add((Animal) animal);
            }
        }
        List<Animal> predators = new ArrayList<>(); //Lion Leopard
        for (Entity animal : animals) {
            if (animal instanceof Lion || animal instanceof Leopard) {
                predators.add((Animal) animal);
            }
        }

        if (date == cycle) {
            if (herbivores.size() >= herbivoreThreshold && predators.size() >= predatorThreshold && coin >= coinThreshold) {
                System.out.println("Nyertél: Elérted a célokat!");
                return true;
            } else {
                System.out.println("Vesztettél: Nem teljesítetted a célokat!");
                return true;
            }


        }

        return false;


    }


    private void setEasy() {
        this.visitorThreshold = 50;
        this.herbivoreThreshold = 10;
        this.predatorThreshold = 3;
        this.coinThreshold = 500;
    }

    private void setMedium() {
        this.visitorThreshold = 80;
        this.herbivoreThreshold = 20;
        this.predatorThreshold = 5;
        this.coinThreshold = 1000;
    }

    private void setHard() {
        this.visitorThreshold = 120;
        this.herbivoreThreshold = 30;
        this.predatorThreshold = 8;
        this.coinThreshold = 2000;
    }


}