package map;

import core.Resources;
import entity.mobile.animal.Giraffe;
import entity.mobile.animal.Leopard;
import entity.mobile.animal.Lion;
import entity.mobile.animal.Zebra;
import safari.DifficultyEnum;
import safari.Safari;

import java.util.Random;

/**
 * Utility class for creating animal entities in the safari simulation based on difficulty levels.
 */
public class AnimalCreate {
    private static final Random rnd = new Random();

    /**
     * Creates a random number of Lion entities based on the specified difficulty level and adds them to the safari.
     *
     * @param difficulty The difficulty level determining the range of lion counts (EASY: 0-1, MEDIUM: 2-3, HARD: 2-3).
     */
    public static void getLions(DifficultyEnum difficulty) {
        int maxWidth = Resources.Instance.map.getWidth() - Resources.Instance.lionBody.getWidth() / 2;
        int maxHeight = Resources.Instance.map.getHeight() - Resources.Instance.lionBody.getHeight() / 2;
        //List<Lion> lions = new ArrayList<>();

        int lionCount = rnd.nextInt(
                switch (difficulty) {
                    case EASY -> 0;
                    case MEDIUM -> 2;
                    case HARD -> 2;
                },
                switch (difficulty) {
                    case EASY -> 2;
                    case MEDIUM -> 4;
                    case HARD -> 4;
                }
        );

        for (int i = 0; i < lionCount; i++) {
            Safari.Instance.createAnEntityForFree(Lion.class, rnd.nextInt(Resources.Instance.zebraBody.getWidth() / 2, maxWidth), rnd.nextInt(Resources.Instance.zebraBody.getHeight() / 2, maxHeight));
        }
    }

    /**
     * Creates a random number of Leopard entities based on the specified difficulty level and adds them to the safari.
     *
     * @param difficulty The difficulty level determining the range of leopard counts (EASY: 0-1, MEDIUM: 1-2, HARD: 2-3).
     */
    public static void getLeopards(DifficultyEnum difficulty) {
        int maxWidth = Resources.Instance.map.getWidth() - Resources.Instance.leopardBody.getWidth() / 2;
        int maxHeight = Resources.Instance.map.getHeight() - Resources.Instance.leopardBody.getHeight() / 2;
        //List<Leopard> leopards = new ArrayList<>();

        int leopardCount = rnd.nextInt(
                switch (difficulty) {
                    case EASY -> 0;
                    case MEDIUM -> 1;
                    case HARD -> 2;
                },
                switch (difficulty) {
                    case EASY -> 2;
                    case MEDIUM -> 3;
                    case HARD -> 4;
                }
        );

        for (int i = 0; i < leopardCount; i++) {
            Safari.Instance.createAnEntityForFree(Leopard.class, rnd.nextInt(Resources.Instance.zebraBody.getWidth() / 2, maxWidth), rnd.nextInt(Resources.Instance.zebraBody.getHeight() / 2, maxHeight));
        }
    }

    /**
     * Creates a random number of Zebra entities based on the specified difficulty level and adds them to the safari.
     *
     * @param difficulty The difficulty level determining the range of zebra counts (EASY: 2-3, MEDIUM: 2-4, HARD: 2-3).
     */
    public static void getZebras(DifficultyEnum difficulty) {
        int maxWidth = Resources.Instance.map.getWidth() - Resources.Instance.zebraBody.getWidth() / 2;
        int maxHeight = Resources.Instance.map.getHeight() - Resources.Instance.zebraBody.getHeight() / 2;
        //List<Zebra> zebras = new ArrayList<>();

        int zebraCount = rnd.nextInt(
                switch (difficulty) {
                    case EASY -> 2;
                    case MEDIUM -> 2;
                    case HARD -> 2;
                },
                switch (difficulty) {
                    case EASY -> 4;
                    case MEDIUM -> 5;
                    case HARD -> 4;
                }
        );

        for (int i = 0; i < zebraCount; i++) {
            Safari.Instance.createAnEntityForFree(Zebra.class, rnd.nextInt(Resources.Instance.zebraBody.getWidth() / 2, maxWidth), rnd.nextInt(Resources.Instance.zebraBody.getHeight() / 2, maxHeight));
        }
    }

    /**
     * Creates a random number of Giraffe entities based on the specified difficulty level and adds them to the safari.
     *
     * @param difficulty The difficulty level determining the range of giraffe counts (EASY: 2-4, MEDIUM: 1-3, HARD: 1-2).
     */
    public static void getGiraffes(DifficultyEnum difficulty) {
        int maxWidth = Resources.Instance.map.getWidth() - Resources.Instance.giraffeBody.getWidth() / 2;
        int maxHeight = Resources.Instance.map.getHeight() - Resources.Instance.giraffeBody.getHeight() / 2;
        //List<Giraffe> giraffes = new ArrayList<>();

        int giraffeCount = rnd.nextInt(
                switch (difficulty) {
                    case EASY -> 2;
                    case MEDIUM -> 1;
                    case HARD -> 1;
                },
                switch (difficulty) {
                    case EASY -> 5;
                    case MEDIUM -> 4;
                    case HARD -> 3;
                }
        );

        for (int i = 0; i < giraffeCount; i++) {
            Safari.Instance.createAnEntityForFree(Giraffe.class, rnd.nextInt(Resources.Instance.zebraBody.getWidth() / 2, maxWidth), rnd.nextInt(Resources.Instance.zebraBody.getHeight() / 2, maxHeight));
        }
    }
}