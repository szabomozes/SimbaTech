package road;

import entity.notmobile.Water;
import map.Coordinate;
import safari.Safari;

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

    public boolean overlapsWaterArea(int newX, int newY) {
        Road newRoad = new Road(startX, startY, newX, newY);
        List<Coordinate> coordinates = newRoad.getMid();

        for (Water water : Safari.Instance.getWaters()) {
            int waterX = water.getX();
            int waterY = water.getY();

            for (Coordinate coord : coordinates) {
                int x = coord.x;
                int y = coord.y;

                if (x >= waterX && x < waterX + water.getWidth() &&
                        y >= waterY && y < waterY + water.getHeight()) {
                    return true;
                }
            }
        }

        return false;
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

    public List<Coordinate> getPathCoordinations() {
        List<Coordinate> coordinates = new ArrayList<>();

        for (Road road : roads) {
            coordinates.addAll(road.getMid());
        }

        return coordinates;
    }
}
