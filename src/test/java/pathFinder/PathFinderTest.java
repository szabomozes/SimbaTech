package pathFinder;

import entity.notmobile.Water;
import map.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import core.Resources;
import safari.Safari;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PathFinderTest {

    @BeforeEach
    void setUp() {
        Resources.Instance.map = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);

        Safari.Instance = new Safari() {
            @Override
            public List<entity.notmobile.Water> getWaters() {
                return new ArrayList<>();
            }
        };
    }

    @Test
    void testASearch_straightPath() {
        List<Coordinate> path = PathFinder.ASearch(
                0, 0, 1, 1,
                5, 0, 1, 1
        );

        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertEquals(new Coordinate(0, 0), path.get(path.size() - 1));
        assertTrue(path.contains(new Coordinate(4, 0)));
    }

    @Test
    void testASearch_diagonalPath() {
        List<Coordinate> path = PathFinder.ASearch(
                0, 0, 1, 1,
                3, 3, 1, 1
        );

        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertEquals(new Coordinate(0, 0), path.get(path.size() - 1));
        assertTrue(path.contains(new Coordinate(2, 2)));
    }

    @Test
    void testASearch_goalAlreadyAdjacent() {
        List<Coordinate> path = PathFinder.ASearch(
                4, 4, 1, 1,
                5, 4, 1, 1
        );

        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertEquals(1, path.size());
        assertEquals(new Coordinate(4, 4), path.get(0));
    }


    @Test
    void testASearch_blockedByWater() {
        try {
            Resources.Instance.water = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        } catch (Exception e) {
            System.err.println("Nem sikerült betölteni a víz képét.");
        }

        Safari.Instance = new Safari() {
            @Override
            public List<Water> getWaters() {
                List<Water> waters = new ArrayList<>();
                waters.add(new Water(1, 0));
                return waters;
            }
        };

        List<Coordinate> path = PathFinder.ASearch(
                0, 0, 1, 1,
                200, 200, 1, 1
        );

        assertTrue(path.isEmpty());
    }


    @Test
    void testASearch_goalOutOfBounds() {
        List<Coordinate> path = PathFinder.ASearch(
                0, 0, 1, 1,
                200, 200, 1, 1
        );

        assertNotNull(path);
        assertTrue(path.isEmpty());
    }

}
