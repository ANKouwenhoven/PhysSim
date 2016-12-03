package entity.particle;

import entity.Entity;
import entity.MovingEntity;
import singleton.ParticleManager;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Arnoud on 8-11-2016.
 */
public class Particle extends Entity implements MovingEntity {

    private Color color;
    private int particleSize = 1;
    private int particlePosition = particleSize / 2 + 1;
    private double dx, dy, actualX, actualY = 0;
    private int lifetime = Integer.MAX_VALUE;

    private long spawnTime = System.currentTimeMillis();

    private Rectangle2D rectangle;
    private Shape shape;

    public Particle(int _x, int _y, double _dx, double _dy, Color _color) {
        super(_x, _y);
        dx = _dx;
        dy = _dy;
        color = _color;

        init();
    }

    public Particle(int _x, int _y, Color _color) {
        super(_x, _y);
        color = _color;

        init();
    }

    /**
     * Sets a new size for this Particle.
     * @param size - new size for the Particle
     * @return - modified Particle
     */
    public Particle setSize(int size) {
        this.particleSize = size;
        return this;
    }

    /**
     * Sets a new lifetime for this Particle.
     * @param life - new lifetime for the Particle
     * @return - modified Particle
     */
    public Particle setLife(int life) {
        this.lifetime = life;
        return this;
    }

    /**
     * Runs through this Particle's code.
     */
    public void move() {

        if (System.currentTimeMillis() - spawnTime > lifetime) {
            ParticleManager.removeParticle(this);
        }

        actualX += dx;
        actualY += dy;

        setX(actualX);
        setY(actualY);

        rectangle.setRect(getX(), getY(), particleSize, particleSize);
    }

    /**
     * Runs through code on Particle creation.
     */
    private void init() {
        rectangle = new Rectangle2D.Double(this.getX() - particlePosition, this.getY() - particlePosition, particleSize, particleSize);
        shape = rectangle;

        actualX = getX();
        actualY = getY();
    }

    /**
     * @return - the Color of this Particle
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return - the Shape of this Particle
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * @return - the Rectangle of this Particle
     */
    public Rectangle2D getRect() {
        return rectangle;
    }

    /**
     * @return - the Size of this Particle
     */
    public int getParticleSize() {
        return particleSize;
    }

    /**
     * @return - the relative position of this Particle
     */
    public int getParticlePosition() {
        return particlePosition;
    }

    /**
     * Draws this Particle.
     * @param g2d - Graphics2D object
     */
    public void draw(Graphics2D g2d) {
        g2d.setPaint(getColor());
        g2d.draw(getShape());
    }
}
