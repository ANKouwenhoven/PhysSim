package level;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Arnoud on 11-11-2016.
 */
public class Path {

    private ArrayList<Point> path = new ArrayList<>();

    /**
     * Draws a horizontal line of pathing. (Note: x1 has to be smaller than x2)
     * @param x1 - first x coord
     * @param x2 - second x coord
     * @param y - y coord
     */
    public void setHorLine(int x1, int x2, int y) {
        for(int i = x1; i < x2 + 1; i++) {
            setPathNode(i, y);
        }
    }

    /**
     * Draws a vertical line of pathing. (Note: y1 has to be smaller than y2)
     * @param y1 - first y coord
     * @param y2 - second y coord
     * @param x - x coord
     */
    public void setVerLine(int y1, int y2, int x) {
        for(int i = y1; i < y2 + 1; i++) {
            setPathNode(x, i);
        }
    }

    /**
     * Sets one pathing node on the map.
     * @param x - x coord
     * @param y - y coord
     */
    private void setPathNode(int x, int y) {
        Point point = new Point(x * 16 + 8, y * 16 + 8);
        if (!path.contains(point)) {
            path.add(point);
        }
    }

    /**
     * @return - this Path
     */
    public ArrayList<Point> getPath() {
        return path;
    }
}
