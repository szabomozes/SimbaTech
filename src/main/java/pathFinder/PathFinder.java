package pathFinder;

import core.Resources;
import entity.notmobile.Water;
import map.Coordinate;
import safari.Safari;

import java.util.*;

/**
 * This class provides pathfinding functionality, including an A* search algorithm to find the shortest path
 * from a start coordinate to a goal coordinate while avoiding obstacles such as water.
 */
public class PathFinder {

    /**
     * Heuristic function to estimate the distance between two coordinates.
     * Uses the Chebyshev distance (maximum of horizontal and vertical distance).
     *
     * @param a the starting coordinate
     * @param b the goal coordinate
     * @return the heuristic value representing the estimated distance
     */
    private static int heuristic(Coordinate a, Coordinate b) {
        int dx = Math.abs(a.x - b.x);
        int dy = Math.abs(a.y - b.y);
        return Math.max(dx, dy);
    }

    /**
     * Performs an A* search to find the shortest path from a starting coordinate to a goal area.
     * The path avoids water areas and considers both horizontal, vertical, and diagonal movement.
     *
     * @param startX      the starting X coordinate
     * @param startY      the starting Y coordinate
     * @param startWidth  the width of the starting area
     * @param startHeight the height of the starting area
     * @param goalX       the goal X coordinate
     * @param goalY       the goal Y coordinate
     * @param goalWidth   the width of the goal area
     * @param goalHeight  the height of the goal area
     * @return a list of coordinates representing the shortest path from start to goal
     */
    public static List<Coordinate> ASearch(int startX, int startY, int startWidth, int startHeight,
                                           int goalX, int goalY, int goalWidth, int goalHeight) {
        int maxWidth = Resources.Instance.map.getWidth();
        int maxHeight = Resources.Instance.map.getHeight();
        List<Coordinate> path = new ArrayList<>();

        Coordinate start = new Coordinate(startX, startY);

        boolean[][] visited = new boolean[maxWidth][maxHeight];
        Coordinate[][] cameFrom = new Coordinate[maxWidth][maxHeight];
        int[][] gScore = new int[maxWidth][maxHeight];

        for (int i = 0; i < maxWidth; i++) {
            for (int j = 0; j < maxHeight; j++) {
                gScore[i][j] = Integer.MAX_VALUE;
            }
        }

        gScore[start.x][start.y] = 0;

        class Node {
            Coordinate coord;
            int fScore;

            Node(Coordinate coord, int fScore) {
                this.coord = coord;
                this.fScore = fScore;
            }
        }

        List<Node> openSet = new ArrayList<>();
        openSet.add(new Node(start, heuristic(start, new Coordinate(goalX, goalY))));

        int[][] directions = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1},
                {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
        };

        while (!openSet.isEmpty()) {
            if (Thread.currentThread().isInterrupted()) {
                return null;
            }
            Node currentNode = openSet.stream().min(Comparator.comparingInt(n -> n.fScore)).orElse(null);
            Coordinate current = currentNode.coord;
            openSet.remove(currentNode);

            if (isNextToGoalArea(current, goalX, goalY, goalWidth, goalHeight, startWidth, startHeight)) {
                Coordinate step = current;
                while (step != null && !step.equals(start)) {
                    path.add(step);
                    step = cameFrom[step.x][step.y];
                }
                path.add(start);
                return path;
            }

            visited[current.x][current.y] = true;

            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];
                Coordinate neighbor = new Coordinate(newX, newY);

                if (newX < 0 || newY < 0 || newX >= maxWidth || newY >= maxHeight) continue;
                if (visited[newX][newY]) continue;
                if (overlapsWaterArea(newX, newY, startWidth, startHeight)) continue;

                int tentativeGScore = gScore[current.x][current.y] + 1;

                if (tentativeGScore < gScore[newX][newY]) {
                    cameFrom[newX][newY] = current;
                    gScore[newX][newY] = tentativeGScore;
                    int fScore = tentativeGScore + heuristic(neighbor, new Coordinate(goalX, goalY));

                    boolean inOpenSet = openSet.stream().anyMatch(n -> n.coord.equals(neighbor));
                    if (!inOpenSet) {
                        openSet.add(new Node(neighbor, fScore));
                    }
                }
            }
        }

        return path;
    }

    /**
     * Checks if a given coordinate overlaps with any water area.
     *
     * @param x the X coordinate to check
     * @param y the Y coordinate to check
     * @param width the width of the area to check
     * @param height the height of the area to check
     * @return true if the area overlaps with any water, false otherwise
     */
    private static boolean overlapsWaterArea(int x, int y, int width, int height) {
        for (Water water : Safari.Instance.getWaters()) {
            int waterX = water.getX();
            int waterY = water.getY();
            int waterWidth = water.getWidth();
            int waterHeight = water.getHeight();

            if (x + width > waterX && x < waterX + waterWidth &&
                    y + height > waterY && y < waterY + waterHeight) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if a given coordinate is next to the goal area.
     *
     * @param coord the coordinate to check
     * @param goalX the goal X coordinate
     * @param goalY the goal Y coordinate
     * @param goalWidth the width of the goal area
     * @param goalHeight the height of the goal area
     * @param entityWidth the width of the entity
     * @param entityHeight the height of the entity
     * @return true if the coordinate is adjacent to the goal area, false otherwise
     */
    private static boolean isNextToGoalArea(Coordinate coord, int goalX, int goalY, int goalWidth, int goalHeight,
                                            int entityWidth, int entityHeight) {
        int right = coord.x + entityWidth - 1;
        int bottom = coord.y + entityHeight - 1;

        if (right + 1 == goalX && bottom >= goalY && coord.y <= goalY + goalHeight - 1) return true;

        if (coord.x == goalX + goalWidth && bottom >= goalY && coord.y <= goalY + goalHeight - 1) return true;

        if (bottom + 1 == goalY && right >= goalX && coord.x <= goalX + goalWidth - 1) return true;

        if (coord.y == goalY + goalHeight && right >= goalX && coord.x <= goalX + goalWidth - 1) return true;

        return false;
    }
}
