package road;

import core.Resources;
import map.Coordinate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PathTest {

    @BeforeAll
    public static void setup() {
        Resources.Instance.map = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
    }

    @Test
    public void testAddNewRoad() {
        Path path = new Path(0, 0);
        path.setEndX(3);
        path.setEndY(0);
        path.addANewRoad();

        List<Road> roads = path.getRoads();
        assertEquals(1, roads.size());

        Road road = roads.get(0);
        assertEquals(0, road.startX);
        assertEquals(0, road.startY);
        assertEquals(3, road.endX);
        assertEquals(0, road.endY);
    }

    @Test
    public void testEndCoordinateCopyToStartCoordinate() {
        Path path = new Path(1, 1);
        path.setEndX(4);
        path.setEndY(4);
        path.endCoorinateCopyToStartCoordinate();
        path.setEndX(7);
        path.setEndY(4);
        path.addANewRoad();

        Road road = path.getRoads().get(0);
        assertEquals(4, road.startX);
        assertEquals(4, road.startY);
        assertEquals(7, road.endX);
        assertEquals(4, road.endY);
    }

    @Test
    public void testGetPixelCount() {
        Path path = new Path(0, 0);
        path.setEndX(2);
        path.setEndY(0);
        path.addANewRoad();

        path.endCoorinateCopyToStartCoordinate();
        path.setEndX(2);
        path.setEndY(2);
        path.addANewRoad();

        assertEquals(6, path.getPixelCount()); // (2-0)+1 + (2-0)+1 = 3 + 3
    }

    @Test
    public void testGetPathCoordinations() {
        Path path = new Path(0, 0);
        path.setEndX(2);
        path.setEndY(0);
        path.addANewRoad();

        List<Coordinate> coords = path.getPathCoordinations();
        assertEquals(3, coords.size());
        assertEquals(new Coordinate(0, 0), coords.get(0));
        assertEquals(new Coordinate(1, 0), coords.get(1));
        assertEquals(new Coordinate(2, 0), coords.get(2));
    }
}