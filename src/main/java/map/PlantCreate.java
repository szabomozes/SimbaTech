package map;

import core.Resources;
import entity.notmobile.plant.Baobab;
import entity.notmobile.plant.PalmTree;
import entity.notmobile.plant.Pancium;
import safari.DifficultyEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlantCreate {
    private static final Random rnd = new Random();

    public static List<Baobab> getBaobabs(DifficultyEnum difficulty) {
        int maxWidth = Resources.Instance.map.getWidth() - Resources.Instance.baobab.getWidth() / 2;
        int maxHeight = Resources.Instance.map.getHeight() - Resources.Instance.baobab.getHeight() / 2;
        List<Baobab> baobabs = new ArrayList<>();

        int baobabCount = rnd.nextInt(
                switch (difficulty) {
                    case EASY -> 2;
                    case MEDIUM -> 0;
                    case HARD -> 1;
                },
                switch (difficulty) {
                    case EASY -> 4;
                    case MEDIUM -> 3;
                    case HARD -> 3;
                }
        );

        for (int i = 0; i < baobabCount; i++) {
            baobabs.add(new Baobab(
                    rnd.nextInt(Resources.Instance.baobab.getWidth() / 2, maxWidth),
                    rnd.nextInt(Resources.Instance.baobab.getHeight() / 2, maxHeight)
            ));
        }

        return baobabs;
    }

    public static List<Pancium> getPanciums(DifficultyEnum difficulty) {
        int maxWidth = Resources.Instance.map.getWidth() - Resources.Instance.pancium.getWidth() / 2;
        int maxHeight = Resources.Instance.map.getHeight() - Resources.Instance.pancium.getHeight() / 2;
        List<Pancium> panciums = new ArrayList<>();

        int panciumCount = rnd.nextInt(
                switch (difficulty) {
                    case EASY -> 1;
                    case MEDIUM -> 0;
                    case HARD -> 0;
                },
                switch (difficulty) {
                    case EASY -> 3;
                    case MEDIUM -> 2;
                    case HARD -> 1;
                }
        );

        for (int i = 0; i < panciumCount; i++) {
            panciums.add(new Pancium(
                    rnd.nextInt(Resources.Instance.pancium.getWidth() / 2, maxWidth),
                    rnd.nextInt(Resources.Instance.pancium.getHeight() / 2, maxHeight)
            ));
        }

        return panciums;
    }

    public static List<PalmTree> getPalmTrees(DifficultyEnum difficulty) {
        int maxWidth = Resources.Instance.map.getWidth() - Resources.Instance.palmTree.getWidth() / 2;
        int maxHeight = Resources.Instance.map.getHeight() - Resources.Instance.palmTree.getHeight() / 2;
        List<PalmTree> palmTrees = new ArrayList<>();

        int palmTreeCount = rnd.nextInt(
                switch (difficulty) {
                    case EASY -> 2;
                    case MEDIUM -> 1;
                    case HARD -> 0;
                },
                switch (difficulty) {
                    case EASY -> 4;
                    case MEDIUM -> 3;
                    case HARD -> 4;
                }
        );

        for (int i = 0; i < palmTreeCount; i++) {
            palmTrees.add(new PalmTree(
                    rnd.nextInt(Resources.Instance.palmTree.getWidth() / 2, maxWidth),
                    rnd.nextInt(Resources.Instance.palmTree.getHeight() / 2, maxHeight)
            ));
        }

        return palmTrees;
    }
}