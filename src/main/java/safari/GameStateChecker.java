package safari;

import entity.Entity;
import entity.mobile.animal.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for checking the game state in the safari simulation, determining win or loss conditions based on difficulty.
 */
public class GameStateChecker {
    private DifficultyEnum difficulty;
    private final int EASY = 3;
    private final int MEDIUM = 6;
    private final int HARD = 12;
    private int visitorThreshold;
    private int herbivoreThreshold;
    private int predatorThreshold;
    private int coinThreshold;

    /**
     * Constructs a GameStateChecker with thresholds set based on the specified difficulty level.
     *
     * @param difficulty The difficulty level of the game (EASY, MEDIUM, HARD).
     */
    public GameStateChecker(DifficultyEnum difficulty) {
        this.difficulty = difficulty;
        switch (difficulty) {
            case EASY -> setEasy();
            case MEDIUM -> setMedium();
            case HARD -> setHard();
        }
        System.out.println("TRESHOLD SET IN -----------> " + difficulty);
    }

    /**
     * Checks for instant loss conditions such as running out of coins or having no animals left.
     *
     * @param coins   The current amount of coins.
     * @param animals The list of animal entities in the game.
     * @return True if an instant loss condition is met, false otherwise.
     */
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

    /**
     * Checks if the player has won the game based on the date, animal counts, coins, and visitors.
     *
     * @param difficulty The current difficulty level.
     * @param date       The current game date (in days).
     * @param animals    The list of animal entities in the game.
     * @param coin       The current amount of coins.
     * @param visitors   The total number of visitors.
     * @return True if the game is won or lost at the cycle end, false if the game continues.
     */
    public boolean checkWin(DifficultyEnum difficulty, int date, List<Entity> animals, int coin, int visitors) {
        int cycle = 0;
        switch (difficulty) {
            case EASY -> cycle = EASY * 30;
            case MEDIUM -> cycle = MEDIUM * 30;
            case HARD -> cycle = HARD * 30;
        }
        List<Animal> herbivores = new ArrayList<>(); // Zebra, Giraffe
        for (Entity animal : animals) {
            if (animal instanceof Zebra || animal instanceof Giraffe) {
                herbivores.add((Animal) animal);
            }
        }
        List<Animal> predators = new ArrayList<>(); // Lion, Leopard
        for (Entity animal : animals) {
            if (animal instanceof Lion || animal instanceof Leopard) {
                predators.add((Animal) animal);
            }
        }

        if (date == cycle) {
            if (herbivores.size() >= herbivoreThreshold && predators.size() >= predatorThreshold && coin >= coinThreshold && visitors >= visitorThreshold) {
                System.out.println("Nyertél: Elérted a célokat!");
                return true;
            } else {
                System.out.println("Vesztettél: Nem teljesítetted a célokat!");
                return true;
            }
        }

        return false;
    }

    /**
     * Sets thresholds for EASY difficulty: 50 visitors, 10 herbivores, 3 predators, 500 coins.
     */
    private void setEasy() {
        this.visitorThreshold = 50;
        this.herbivoreThreshold = 10;
        this.predatorThreshold = 3;
        this.coinThreshold = 500;
    }

    /**
     * Sets thresholds for MEDIUM difficulty: 80 visitors, 20 herbivores, 5 predators, 1000 coins.
     */
    private void setMedium() {
        this.visitorThreshold = 80;
        this.herbivoreThreshold = 20;
        this.predatorThreshold = 5;
        this.coinThreshold = 1000;
    }

    /**
     * Sets thresholds for HARD difficulty: 120 visitors, 30 herbivores, 8 predators, 2000 coins.
     */
    private void setHard() {
        this.visitorThreshold = 120;
        this.herbivoreThreshold = 30;
        this.predatorThreshold = 8;
        this.coinThreshold = 2000;
    }

    /**
     * Gets the visitor threshold for the current difficulty.
     *
     * @return The visitor threshold.
     */
    public int getVisitorThreshold() {
        return visitorThreshold;
    }

    /**
     * Gets the herbivore threshold for the current difficulty.
     *
     * @return The herbivore threshold.
     */
    public int getHerbivoreThreshold() {
        return herbivoreThreshold;
    }

    /**
     * Gets the predator threshold for the current difficulty.
     *
     * @return The predator threshold.
     */
    public int getPredatorThreshold() {
        return predatorThreshold;
    }

    /**
     * Gets the coin threshold for the current difficulty.
     *
     * @return The coin threshold.
     */
    public int getCoinThreshold() {
        return coinThreshold;
    }
}