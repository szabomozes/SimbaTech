package map;

import core.Resources;
import entity.mobile.animal.Giraffe;
import entity.mobile.animal.Leopard;
import entity.mobile.animal.Lion;
import entity.mobile.animal.Zebra;
import entity.mobile.person.Ranger;
import entity.notmobile.Entry;
import entity.notmobile.Exit;
import entity.notmobile.Water;
import entity.notmobile.plant.Baobab;
import entity.notmobile.plant.PalmTree;
import entity.notmobile.plant.Pancium;
import safari.DifficultyEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Utility class for creating non-animal entities in the safari simulation based on difficulty levels and map constraints.
 */
public class EntityCreate {
    private static final Random rnd = new Random();

    /**
     * Creates an Entry entity positioned at the top-left corner of the map.
     *
     * @return A new Entry instance.
     */
    public static Entry getEntry() {
        int entryX = Resources.Instance.entry.getWidth() / 2;
        int entryY = Resources.Instance.entry.getHeight() / 2;
        return new Entry(entryX, entryY);
    }

    public static int getEntryX() {
        return Resources.Instance.entry.getWidth() / 2;
    }

    public static int getEntryY() {
        return Resources.Instance.entry.getHeight() / 2;
    }

    /**
     * Creates an Exit entity positioned at the bottom-right corner of the map.
     *
     * @return A new Exit instance.
     */
    public static Exit getExit() {
        int exitX = Resources.Instance.map.getWidth() - Resources.Instance.exit.getWidth() / 2;
        int exitY = Resources.Instance.map.getHeight() - Resources.Instance.exit.getHeight() / 2;
        return new Exit(exitX, exitY);
    }

    public static int getExitX() {
        return Resources.Instance.map.getWidth() - Resources.Instance.exit.getWidth() / 2;
    }
    public static int getExitY() {
        return Resources.Instance.map.getHeight() - Resources.Instance.exit.getHeight() / 2;
    }

    /**
     * Creates a random number of Ranger entities based on the specified difficulty level.
     *
     * @param difficulty The difficulty level determining the range of ranger counts (EASY: 1-2, MEDIUM: 0-1, HARD: 0).
     * @return A list of Ranger instances.
     */
    public static List<Ranger> getRangers(DifficultyEnum difficulty) {
        int maxWidth = Resources.Instance.map.getWidth() - Resources.Instance.ranger.getWidth() / 2;
        int maxHeight = Resources.Instance.map.getHeight() - Resources.Instance.ranger.getHeight() / 2;
        List<Ranger> rangers = new ArrayList<>();

        int rangerCount = rnd.nextInt(
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

        for (int i = 0; i < rangerCount; i++) {
            rangers.add(new Ranger(
                    rnd.nextInt(Resources.Instance.ranger.getWidth() / 2, maxWidth),
                    rnd.nextInt(Resources.Instance.ranger.getHeight() / 2, maxHeight)
            ));
        }

        return rangers;
    }

    /**
     * Creates a random number of Water entities based on the specified difficulty level, positioned within padded map boundaries.
     *
     * @param difficulty The difficulty level determining the range of water counts (EASY: 3-5, MEDIUM: 2-4, HARD: 1-3).
     * @return A list of Water instances.
     */
    public static List<Water> getWaters(DifficultyEnum difficulty) {
        int waterPadding = 1000;
        int maxWidth = Resources.Instance.map.getWidth() - Resources.Instance.water.getWidth() / 2 - waterPadding;
        int maxHeight = Resources.Instance.map.getHeight() - Resources.Instance.water.getHeight() / 2 - waterPadding;
        int minWidth = Resources.Instance.water.getWidth() / 2 + waterPadding;
        int minHeight = Resources.Instance.water.getHeight() / 2 + waterPadding;
        List<Water> waters = new ArrayList<>();

        int waterCount = rnd.nextInt(
                switch (difficulty) {
                    case EASY -> 3;
                    case MEDIUM -> 2;
                    case HARD -> 1;
                },
                switch (difficulty) {
                    case EASY -> 6;
                    case MEDIUM -> 5;
                    case HARD -> 4;
                }
        );

        for (int i = 0; i < waterCount; i++) {
            waters.add(new Water(
                    rnd.nextInt(minWidth, maxWidth),
                    rnd.nextInt(minHeight, maxHeight)
            ));
        }

        return waters;
    }
}