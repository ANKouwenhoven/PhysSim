package drawing;

import singleton.ParticleManager;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Created by Arnoud on 9-11-2016.
 */
public class DrawableLine {

    Line2D line;
    private int lifetime = Integer.MAX_VALUE;
    private long spawnTime = System.currentTimeMillis();
    private boolean isBlocking = false;
    private Color color;

    public DrawableLine(int x1, int x2, int x3, int x4) {
        line = new Line2D.Float(x1, x2, x3, x4);
    }

    public DrawableLine setLife(int life) {
        this.lifetime = life;
        return this;
    }

    public DrawableLine setColor(Color color) {
        this.color = color;
        return this;
    }

    /**
     * @return - this Line
     */
    public Line2D getLine() {
        return line;
    }

    /**
     * @return - this Line's Color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return - whether this Line blocks Entities or not
     */
    public boolean getBlocking() {
        return isBlocking;
    }

    /**
     * Runs through this Line's code.
     */
    public void run() {
        if (System.currentTimeMillis() - spawnTime > lifetime) {
            ParticleManager.removeLine(this);
        }
    }
}
