package map;

import java.util.*;

/**
 * Utility class for generating a map with grass growth patterns in the safari simulation.
 */
public class Create1 {
    static private final int clearArea = 0;
    static private final Random rnd = new Random();

    /**
     * Generates a 2D map array with grass grown in random patterns.
     *
     * @param width  The width of the map.
     * @param height The height of the map.
     * @return A 2D Integer array representing the map with grass values.
     */
    static public Integer[][] getAMap(int width, int height) {
        Integer[][] map = new Integer[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = 0;
            }
        }
        List<Coordinate> grass = randomCoordinates(rnd.nextInt(30, 50), clearArea, width - clearArea, clearArea, height - clearArea);
        grassGrownings(map, grass, width, height);
        System.out.println("ASD");
        return map;
    }

    /**
     * Generates a list of random coordinates for grass starting points, ensuring no duplicates along x or y axes.
     *
     * @param piece     The number of coordinates to generate.
     * @param minWidth  The minimum x-coordinate value.
     * @param maxWidth  The maximum x-coordinate value.
     * @param minHeight The minimum y-coordinate value.
     * @param maxHeight The maximum y-coordinate value.
     * @return A list of unique Coordinate objects.
     */
    static private List<Coordinate> randomCoordinates(int piece, int minWidth, int maxWidth, int minHeight, int maxHeight) {
        List<Coordinate> coordinates = new ArrayList<>();
        while (coordinates.size() < piece) {
            Coordinate c = new Coordinate(rnd.nextInt(minWidth, maxWidth), rnd.nextInt(minHeight, maxHeight));
            boolean bool = true;
            for (int i = 0; i < coordinates.size() && bool; i++) {
                if (coordinates.get(i).x == c.x || coordinates.get(i).y == c.y) bool = false;
            }
            if (bool) {
                coordinates.add(c);
            }
        }
        return coordinates;
    }

    /**
     * Simulates grass growth on the map starting from given coordinates, spreading randomly in four directions.
     *
     * @param map    The 2D map array to modify.
     * @param grass  The list of starting coordinates for grass growth.
     * @param width  The width of the map.
     * @param height The height of the map.
     */
    static private void grassGrownings(Integer[][] map, List<Coordinate> grass, int width, int height) {
        Set<Coordinate> visited = new HashSet<>();

        for (Coordinate c : grass) {
            if (!visited.contains(c) && map[c.y][c.x] == 0) {
                int endChance = 0;
                List<Coordinate> queue = new ArrayList<>();
                queue.add(c);
                visited.add(c);

                while (!queue.isEmpty() && rnd.nextInt(100000000) >= endChance) {
                    int randomIndex = rnd.nextInt(queue.size());
                    Coordinate f = queue.remove(randomIndex);

                    map[f.y][f.x] = rnd.nextInt(2) + 2;

                    int[][] directions = {
                            {0, -1}, // Up
                            {-1, 0}, // Left
                            {0, 1},  // Down
                            {1, 0}   // Right
                    };

                    for (int[] d : directions) {
                        int nx = f.x + d[0];
                        int ny = f.y + d[1];
                        Coordinate next = new Coordinate(nx, ny);

                        if (nx >= clearArea && ny >= clearArea && nx < width - clearArea && ny < height - clearArea) {
                            if (!visited.contains(next) && map[ny][nx] == 0) {
                                queue.add(next);
                                visited.add(next);
                            }
                        }
                    }
                    endChance++;
                }
            }
        }
    }
}