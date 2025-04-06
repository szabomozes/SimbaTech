package road;

import map.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoadTest {

    private Road road;

    @BeforeEach
    public void setUp() {
        road = new Road(0, 0, 100, 100);
    }

    @Test
    public void testStartX() {
        assertEquals(0, road.startX, "startX should be 0");
    }

    @Test
    public void testStartY() {
        assertEquals(0, road.startY, "startY should be 0");
    }

    @Test
    public void testEndX() {
        assertEquals(100, road.endX, "endX should be 100");
    }

    @Test
    public void testEndY() {
        assertEquals(100, road.endY, "endY should be 100");
    }

    @Test
    public void testGetPixel() {
        assertEquals(101, road.getPixel(), "getPixel should return the number of mid points (101)");
    }

    @Test
    public void testGetMidNotNull() {
        List<Coordinate> midPoints = road.getMid();
        assertNotNull(midPoints, "mid points list should not be null");
    }

    @Test
    public void testGetMidSize() {
        List<Coordinate> midPoints = road.getMid();
        assertEquals(101, midPoints.size(), "mid points list should contain 101 points");
    }
}