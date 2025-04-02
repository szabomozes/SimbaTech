package road;


import map.Coordinate;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Road {
    public final int startX;
    public final int startY;
    public final int endX;
    public final int endY;

    private List<Coordinate> mid;

    public Road(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.mid = calculateMidPoints();
    }

    private List<Coordinate> calculateMidPoints() {
        List<Coordinate> points = new ArrayList<>();
        int dx = endX - startX;
        int dy = endY - startY;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        for (int i = 0; i <= steps; i++) {
            int x = startX + i * dx / steps;
            int y = startY + i * dy / steps;
            points.add(new Coordinate(x, y));
        }
        return points;
    }

    public int getPixel() {
        int sum = 0;
        sum += mid.size();
        System.out.println("asd");
        System.out.println(sum);
        System.out.println("asd");
        return sum;
    }

    public void draw(Graphics g, int offsetX, int offsetY) {
        Graphics2D g2d = (Graphics2D) g;
        Stroke originalStroke = g2d.getStroke();

        g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(startX + offsetX, startY + offsetY, endX + offsetX, endY + offsetY);

        g2d.setStroke(originalStroke);
    }
}