package pathFinder;

import map.Coordinate;

import java.util.*;

public class PathFinder {
    public static List<Coordinate> ASearch(int startX, int startY, int goalX, int goalY, int maxWidth, int maxHeight, List<Coordinate> wrongCoordinates) {
        List<Coordinate> path = new ArrayList<>();

        Coordinate start = new Coordinate(startX, startY);
        Coordinate goal = new Coordinate(goalX, goalY);

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
        openSet.add(new Node(start, heuristic(start, goal)));

        int[][] directions = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1},
                {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
        };

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.stream().min((a, b) -> Integer.compare(a.fScore, b.fScore)).orElse(null);
            Coordinate current = currentNode.coord;
            openSet.remove(currentNode);

            if (current.equals(goal)) {
                Coordinate step = goal;
                while (step != null && !step.equals(start)) {
                    path.add(step);
                    step = cameFrom[step.x][step.y];
                }
                path.add(start);
                System.out.println("Good path");
                return path;
            }

            visited[current.x][current.y] = true;

            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];
                Coordinate neighbor = new Coordinate(newX, newY);

                if (newX < 0 || newY < 0 || newX >= maxWidth || newY >= maxHeight) continue;
                if (visited[newX][newY]) continue;
                if (wrongCoordinates.contains(neighbor)) continue;

                int tentativeGScore = gScore[current.x][current.y] + 1;

                if (tentativeGScore < gScore[newX][newY]) {
                    cameFrom[newX][newY] = current;
                    gScore[newX][newY] = tentativeGScore;
                    int fScore = tentativeGScore + heuristic(neighbor, goal);

                    boolean inOpenSet = openSet.stream().anyMatch(n -> n.coord.equals(neighbor));
                    if (!inOpenSet) {
                        openSet.add(new Node(neighbor, fScore));
                    }
                }
            }
        }

        System.out.println("Bad path");
        return path;
    }


    // Octile heuristics (egyenlő mozgási költség minden irányban)
    private static int heuristic(Coordinate a, Coordinate b) {
        int dx = Math.abs(a.x - b.x);
        int dy = Math.abs(a.y - b.y);
        return Math.max(dx, dy);
    }

}