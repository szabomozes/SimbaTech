package pathFinder;

import core.Resources;
import entity.notmobile.Water;
import map.Coordinate;
import safari.Safari;

import java.util.*;

public class PathFinder {

    private static int heuristic(Coordinate a, Coordinate b) {
        int dx = Math.abs(a.x - b.x);
        int dy = Math.abs(a.y - b.y);
        return Math.max(dx, dy);
    }

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