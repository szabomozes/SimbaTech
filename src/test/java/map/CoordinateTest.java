package map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void testCoordinateConstructor() {
        Coordinate coordinate = new Coordinate(5, 10);

        assertEquals(5, coordinate.x);
        assertEquals(10, coordinate.y);
    }

    @Test
    void testToString() {
        Coordinate coordinate = new Coordinate(3, 7);

        assertEquals("{x = 3, y = 7}", coordinate.toString());
    }

    @Test
    void testEquals() {
        Coordinate coordinate1 = new Coordinate(2, 4);
        Coordinate coordinate2 = new Coordinate(2, 4);
        Coordinate coordinate3 = new Coordinate(5, 8);

        assertTrue(coordinate1.equals(coordinate2));

        assertFalse(coordinate1.equals(coordinate3));

        assertFalse(coordinate1.equals("string"));
    }

    @Test
    void testHashCode() {
        Coordinate coordinate1 = new Coordinate(3, 6);
        Coordinate coordinate2 = new Coordinate(3, 6);

        assertEquals(coordinate1.hashCode(), coordinate2.hashCode());

        Coordinate coordinate3 = new Coordinate(4, 7);
        assertNotEquals(coordinate1.hashCode(), coordinate3.hashCode());
    }
}
