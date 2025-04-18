package road;

import core.Resources;
import map.Coordinate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoadTest {

    @BeforeAll
    public static void setup() {
        Resources.Instance.map = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
    }

    @Test
    public void testHorizontalRoadMidPoints() {
        Road road = new Road(0, 0, 4, 0);
        List<Coordinate> midPoints = road.getMid();

        assertEquals(5, midPoints.size());
        assertEquals(new Coordinate(0, 0), midPoints.get(0));
        assertEquals(new Coordinate(4, 0), midPoints.get(4));
    }

    @Test
    public void testDiagonalRoadMidPoints() {
        Road road = new Road(0, 0, 3, 3);
        List<Coordinate> midPoints = road.getMid();

        assertEquals(4, midPoints.size());
        assertEquals(new Coordinate(1, 1), midPoints.get(1));
        assertEquals(new Coordinate(3, 3), midPoints.get(3));
    }

    @Test
    public void testSinglePointRoadMidPoints() {
        Road road = new Road(2, 2, 2, 2);
        List<Coordinate> midPoints = road.getMid();

        assertEquals(1, midPoints.size());
        assertEquals(new Coordinate(2, 2), midPoints.get(0));
    }

    @Test
    public void testPixelCount() {
        Road road = new Road(0, 0, 4, 0);
        assertEquals(5, road.getPixel());
    }
}