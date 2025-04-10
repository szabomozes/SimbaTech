package map;

import core.Resources;
import entity.mobile.animal.Giraffe;
import entity.mobile.animal.Leopard;
import entity.mobile.animal.Lion;
import entity.mobile.animal.Zebra;
import safari.DifficultyEnum;
import safari.Safari;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnimalCreate {
    private static final Random rnd = new Random();

    public static List<Lion> getLions(DifficultyEnum difficulty) {
        int maxWidth = Resources.Instance.map.getWidth() - Resources.Instance.lionBody.getWidth() / 2;
        int maxHeight = Resources.Instance.map.getHeight() - Resources.Instance.lionBody.getHeight() / 2;
        List<Lion> lions = new ArrayList<>();

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
            lions.add(new Lion(
                    rnd.nextInt(Resources.Instance.lionBody.getWidth() / 2, maxWidth),
                    rnd.nextInt(Resources.Instance.lionBody.getHeight() / 2, maxHeight)
            ));
        }

        return lions;
    }

    public static List<Leopard> getLeopards(DifficultyEnum difficulty) {
        int maxWidth = Resources.Instance.map.getWidth() - Resources.Instance.leopardBody.getWidth() / 2;
        int maxHeight = Resources.Instance.map.getHeight() - Resources.Instance.leopardBody.getHeight() / 2;
        List<Leopard> leopards = new ArrayList<>();

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
            leopards.add(new Leopard(
                    rnd.nextInt(Resources.Instance.leopardBody.getWidth() / 2, maxWidth),
                    rnd.nextInt(Resources.Instance.leopardBody.getHeight() / 2, maxHeight)
            ));
        }

        return leopards;
    }

    public static List<Zebra> getZebras(DifficultyEnum difficulty) {
        Safari.Instance.createAnEntityForFree(Zebra.class, 200, 200);



        int maxWidth = Resources.Instance.map.getWidth() - Resources.Instance.zebraBody.getWidth() / 2;
        int maxHeight = Resources.Instance.map.getHeight() - Resources.Instance.zebraBody.getHeight() / 2;
        List<Zebra> zebras = new ArrayList<>();

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
            zebras.add(new Zebra(
                    rnd.nextInt(Resources.Instance.zebraBody.getWidth() / 2, maxWidth),
                    rnd.nextInt(Resources.Instance.zebraBody.getHeight() / 2, maxHeight)
            ));
        }

        return zebras;
    }

    public static List<Giraffe> getGiraffes(DifficultyEnum difficulty) {
        int maxWidth = Resources.Instance.map.getWidth() - Resources.Instance.giraffeBody.getWidth() / 2;
        int maxHeight = Resources.Instance.map.getHeight() - Resources.Instance.giraffeBody.getHeight() / 2;
        List<Giraffe> giraffes = new ArrayList<>();

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
            giraffes.add(new Giraffe(
                    rnd.nextInt(Resources.Instance.giraffeBody.getWidth() / 2, maxWidth),
                    rnd.nextInt(Resources.Instance.giraffeBody.getHeight() / 2, maxHeight)
            ));
        }

        return giraffes;
    }
}