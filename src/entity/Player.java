package entity;

import entity.particle.OrbitalParticle;
import singleton.ParticleManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Arnoud on 8-11-2016.
 */
public class Player extends Entity implements MovingEntity {

    private OrbitalParticle orbitalParticle;

    private int dx = 0;
    private int dy = 0;

    public Player(int x, int y) {
        super(x, y);
        orbitalParticle = new OrbitalParticle(this, 4, 5, 0, 7, new ArrayList<>(Arrays.asList(Color.BLACK))).trails(100).oscillate(10, 20, 1);
        ParticleManager.addOrbitParticle(orbitalParticle);
    }

    /**
     * Runs through this Entity's timers.
     */
    public void move() {

        int newX = this.getX() + dx;
        int newY = this.getY() + dy;

        this.setX(newX);
        this.setY(newY);
    }

    /**
     * Sets a new dx for the Player.
     * @param newDx - new dx
     */
    public void setDx(int newDx) {
        dx = newDx;
    }

    /**
     * Sets a new dy for the Player.
     * @param newDy - new dy
     */
    public void setDy(int newDy) {
        dy = newDy;
    }

    /**
     * Handles all events on death.
     */
    public void triggerDeath() {
        ParticleManager.particleCloud(this.getX(), this.getY(), 80, 50, 1000, Color.BLACK);
        ParticleManager.particleBurst(this.getX(), this.getY(), 5, 5, 50, 200, Color.BLACK);
    }
}
