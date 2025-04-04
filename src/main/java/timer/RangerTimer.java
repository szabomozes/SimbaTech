package timer;

import core.Resources;
import entity.mobile.person.Ranger;
import map.Coordinate;
import panels.CardPanel;
import safari.Safari;
import safari.Speed;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RangerTimer extends BasicTimer{

    private final Ranger ranger;

    public RangerTimer(Ranger ranger) {
        super();
        this.ranger = ranger;
    }

    @Override
    protected void update() {
        try {
            Instant now = Instant.now();
            Duration elapsed = Duration.between(lastUpdate, now);

            if (elapsed.toNanos() >= Speed.Instance.speedEnum.getRangerNanoSec()) {
                lastUpdate = now;

                if (ranger.isTarget()) {
                    // TODO: átírni a movingos dolgot nem utkereső algoritmusra
                } else if (ranger.isNewPosition()) {
                    ranger.setMovingCoordinates(getReversed(pathFinder(ranger.getNewPositionX(), ranger.getNewPositionY(), Resources.Instance.map.getWidth() - Resources.Instance.ranger.getWidth() / 2, Resources.Instance.map.getHeight() - Resources.Instance.ranger.getHeight() / 2, Safari.Instance.getWrongCoordinates())));
                    ranger.setNewPosition(false);
                    ranger.setMovingNewPosition(true);
                } else if (ranger.isMovingNewPosition()) {
                    for (int i = 0; i < Math.min(Speed.Instance.speedEnum.getRangerSteps(), ranger.getMovingCoordinates().size() - 1); i++) {
                        ranger.deleteLastCoordinateFromMovingCoordinates();
                    }
                    Coordinate coordinate = ranger.deleteLastCoordinateFromMovingCoordinates();
                    if (ranger.getMovingCoordinates().isEmpty()) {
                        ranger.setMovingNewPosition(false);
                    }
                    ranger.setX(coordinate.x - ranger.getWidth() / 2);
                    ranger.setY(coordinate.y - ranger.getHeight() / 2);
                }


                // Frissítjük a megjelenítést
                CardPanel.Instance.repaint();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }














    private List<Coordinate> pathFinder(int goalX, int goalY, int maxWidth, int maxHeight, List<Coordinate> wrongCoordinates) {
        List<Coordinate> path = new ArrayList<>();

        Coordinate start = new Coordinate(ranger.getX() + ranger.getWidth() / 2, ranger.getY() + ranger.getHeight() / 2);
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

        // Minden lehetséges irány (8 irány: 4 egyenes, 4 átlós)
        int[][] directions = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1},
                {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
        };

        while (!openSet.isEmpty()) {
            // Legkisebb fScore-ral rendelkező node kiválasztása
            Node currentNode = openSet.stream().min((a, b) -> Integer.compare(a.fScore, b.fScore)).orElse(null);
            Coordinate current = currentNode.coord;
            openSet.remove(currentNode);

            if (current.equals(goal)) {
                // Útvonal visszafejtése
                Coordinate step = goal;
                while (step != null && !step.equals(start)) {
                    path.add(step);
                    step = cameFrom[step.x][step.y];
                }
                path.add(start);
                Collections.reverse(path);
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
    private int heuristic(Coordinate a, Coordinate b) {
        int dx = Math.abs(a.x - b.x);
        int dy = Math.abs(a.y - b.y);
        return Math.max(dx, dy);
    }
    private List<Coordinate> getReversed(List<Coordinate> path) {
        List<Coordinate> reversed = new ArrayList<>(path);
        Collections.reverse(reversed);
        return reversed;
    }

}
