package map;

import core.Resources;
import entity.notmobile.Entry;
import entity.notmobile.Exit;

public class EntityCreate {
    public static Entry getEntry() {
        return new Entry(0, 0);
    }

    public static Exit getExit() {
        return new Exit(Resources.Instance.map.getWidth(), Resources.Instance.map.getHeight());
    }
}
