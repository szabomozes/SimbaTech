package map;


import java.util.*;

public class Create1 {
    static private final int clearArea = 0;
    static private final Random rnd = new Random();

    static public Integer[][] getAMap(int width, int height) {
        Integer[][] map = new Integer[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = 0;
            }
        }
        List<Coordinate> grass = randomCoordinates(rnd.nextInt(30, 50), clearArea, width-clearArea, clearArea, height-clearArea);
        grassGrownings(map, grass, width, height);
        System.out.println("ASD");
        return map;
    }

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

    static private void grassGrownings(Integer[][] map, List<Coordinate> grass, int width, int height) {
        Set<Coordinate> visited = new HashSet<>();

        for (Coordinate c : grass) {
            if (!visited.contains(c) && map[c.y][c.x] == 0) {
                int endChance = 0;
                List<Coordinate> queue = new ArrayList<>();
                queue.add(c);
                visited.add(c);
// 100000000
                while (!queue.isEmpty() && rnd.nextInt(100000000) >= endChance) {
                    int randomIndex = rnd.nextInt(queue.size());
                    Coordinate f = queue.remove(randomIndex);

                    map[f.y][f.x] = rnd.nextInt(2) + 2;

                    int[][] directions = {
                            {0, -1}, // Fel
                            {-1, 0}, // Balra
                            {0, 1},  // Le
                            {1, 0}   // Jobbra
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
