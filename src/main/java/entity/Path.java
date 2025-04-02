package entity;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<Road> roads = new ArrayList<>();
    private int lastX;
    private int lastY;

    public Path(int x, int y) {
        lastX = x;
        lastY = y;
    }
}
