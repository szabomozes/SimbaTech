package map;

import core.Resources;
import entity.mobile.animal.Lion;
import entity.notmobile.Entry;
import entity.notmobile.Exit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityCreate {
    public static final int entryX = 0 + Resources.Instance.entry.getWidth() / 2;
    public static final int entryY = 0 + Resources.Instance.entry.getHeight() / 2;
    public static final int exitX = Resources.Instance.map.getWidth() - 0 - Resources.Instance.exit.getWidth() / 2;
    public static final int exitY = Resources.Instance.map.getHeight() - 0 - Resources.Instance.exit.getHeight() / 2;

    public static Entry getEntry() {
        return new Entry(entryX, entryY);
    }

    public static Exit getExit() {
        return new Exit(exitX, exitY);
    }

    public static List<Lion> getLions() {
        int maxWidth = Resources.Instance.map.getWidth() - Resources.Instance.lionBody.getWidth()/2;
        int maxHeight = Resources.Instance.map.getHeight() - Resources.Instance.lionBody.getHeight()/2;
        List<Lion> lions = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < rnd.nextInt(5, 10); i++) {
            lions.add(new Lion(rnd.nextInt(Resources.Instance.lionBody.getWidth()/2, maxWidth), rnd.nextInt(Resources.Instance.lionBody.getHeight()/2, maxHeight)));
        }
        return lions;
    }
}
