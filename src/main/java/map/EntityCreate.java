package map;

import core.Resources;
import entity.mobile.animal.Lion;
import entity.notmobile.Entry;
import entity.notmobile.Exit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityCreate {
    public static final int entryX = 0;
    public static final int entryY = 0;
    public static final int exitX = Resources.Instance.map.getWidth();
    public static final int exitY = 0;

    public static Entry getEntry() {
        return new Entry(entryX + Resources.Instance.entry.getWidth() / 2, entryY + Resources.Instance.entry.getHeight() / 2);
    }

    public static Exit getExit() {
        return new Exit(exitX - Resources.Instance.exit.getWidth() / 2, exitY - Resources.Instance.exit.getHeight() / 2);
    }

    public static List<Lion> getLions() {
        int maxWidth = Resources.Instance.map.getWidth();
        int maxHeight = Resources.Instance.map.getHeight();
        List<Lion> lions = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < rnd.nextInt(0, 10); i++) {
            lions.add(new Lion(rnd.nextInt(0, maxWidth), rnd.nextInt(0, maxHeight)));
        }
        return lions;
    }
}
