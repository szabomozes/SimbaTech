package entity;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<Road> roads = new ArrayList<>();
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public Path(int x, int y) {
        startX = x;
        startY = y;
    }

    public List<Road> getRoads() {
        return roads;
    }

    public void setEndX (int x) {
        endX = x;
    }

    public void setEndY (int y) {
        endY = y;
    }

    public void addANewRoad() {
        roads.add(new Road(startX, startY, endX, endY));
    }

    public void endCoorinateCopyToStartCoordinate () {
        startX = endX;
        startY = endY;
    }

    public int getPixelCount() {
        int sum = 0;
        for (Road road : roads) {
            sum += road.getPixel();
        }
        return sum;
    }
}
