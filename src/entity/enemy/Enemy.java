package entity.enemy;

import entity.Entity;
import entity.MovingEntity;
import entity.particle.OrbitalParticle;
import level.Level;
import singleton.Game;
import singleton.ParticleManager;
import window.GameWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Arnoud on 8-11-2016.
 */
public class Enemy extends Entity implements MovingEntity {

    private int side;
    private OrbitalParticle particle;
    private double health;
    private Point previousPoint;

    public Enemy(int x, int y) {
        super(x, y);
        this.health = 30;
        previousPoint = new Point(x, y);

        particle = new OrbitalParticle(this, 3, 5, 0, 5, new ArrayList<>(Arrays.asList(Color.BLACK))).trails(100).oscillate(15, 20, 1);
        ParticleManager.addOrbitParticle(particle);
    }

    /**
     * Sets the side of the path this Enemy should walk on.
     * @param in - side of the path
     * @return - modified Enemy
     */
    public Enemy setSide(int in) {
        this.side = in;
        return this;
    }

    /**
     * Triggers all events that happen on death of this Enemy.
     */
    public void triggerDeath() {
        ParticleManager.particleBurst(getX(), getY(), 3, 3, 50, 200, Color.BLACK);
        ParticleManager.removeOrbitParticle(particle);
        Game.getInstance().removeEnemy(this);
    }

    /**
     * Runs through this Entity's code.
     */
    @Override
    public void move() {
        boolean pathFound = false;
        if (new Point(getX(), getY()).equals(Level.getWin(side))) {
            triggerDeath();
            pathFound = true;
        }
        if (!pathFound) {
            pathFound = testForY(-1);
        }
        if (!pathFound) {
            pathFound = testForX(-1);
        }
        if (!pathFound) {
            pathFound = testForY(1);
        }
        if (!pathFound) {
            pathFound = testForX(1);
        }
        if (!pathFound) {
            System.out.println("I failed to find the way at: " + new Point(getX(), getY()));
        }
    }

    /**
     * Checks for pathing nodes above / below the Enemy.
     * @param test - 1 for below, -1 for above
     * @return - whether there is a pathing node or not
     */
    private boolean testForY(int test) {
        for (Point point : Level.getPath(side).getPath()) {
            for (int i = 1; i < 17; i++) {
                if (point.getX() == getX() && point.getY() == getY() + i * test) {
                    if (!previousPoint.equals(new Point(getX(), getY() + test))) {
                        previousPoint = new Point(getX(), getY());
                        setY(getY() + test);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks for pathing nodes to the left / right the Enemy.
     * @param test - 1 for right, -1 for left
     * @return - whether there is a pathing node or not
     */
    private boolean testForX(int test) {
        for (Point point : Level.getPath(side).getPath()) {
            for (int i = 1; i < 17; i++) {
                if (point.getX() == getX() + i * test && point.getY() == getY()) {
                    if (!previousPoint.equals(new Point(getX() + test, getY()))) {
                        previousPoint = new Point(getX(), getY());
                        setX(getX() + test);
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * Removes the amount of damage dealt from this Enemy's health.
     * @param damage - amount of damage dealt
     */
    public void dealDamage(double damage) {
        this.health -= damage;
        if (this.health <= 0) {
            triggerDeath();
        }
    }
}
