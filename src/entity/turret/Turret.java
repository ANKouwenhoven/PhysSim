package entity.turret;

import entity.Entity;
import entity.enemy.Enemy;
import singleton.Game;
import window.GameWindow;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by Arnoud on 11-11-2016.
 */
public abstract class Turret extends Entity {

    private Enemy target;
    private Random AI = new Random();
    private BufferedImage sprite;
    private double damage;

    private int updateInterval = 600;
    private int fireInterval = 600;
    private long targetTimer = System.currentTimeMillis();
    private long fireTimer = System.currentTimeMillis();

    public Turret(int x, int y) {
        super(x, y);
    }

    /**
     * Runs through this Turret's code.
     */
    public abstract void run();

    /**
     * @return - this Turret's Sprite image
     */
    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(String image) {
        try {
            this.sprite = ImageIO.read(getClass().getResource("/resources/sprites/" + image + ".png"));
        } catch (Exception e) {
            System.out.println("Could not find the sprite for " + image);
        }
    }

    /**
     * Acquire a new target.
     */
    public void updateTarget() {
        if (!Game.getInstance().getEnemyList().isEmpty()) {
            target = Game.getInstance().getEnemyList().get(AI.nextInt(Game.getInstance().getEnemyList().size()));
        }
    }

    /**
     * @return - this Entity's current target
     */
    public Enemy getTarget() {
        return target;
    }

    /**
     * @return - this Entity's target update interval
     */
    public int getUpdateInterval() {
        return updateInterval;
    }

    /**
     * @return - this Entity's fire interval
     */
    public int getFireInterval() {
        return fireInterval;
    }

    /**
     * @return - this Entity's target update timer
     */
    public long getTargetTimer() {
        return targetTimer;
    }

    /**
     * @return - this Entity's fire timer
     */
    public long getFireTimer() {
        return fireTimer;
    }

    /**
     * @return - the amount of damage this Turret does
     */
    public double getDamage() {
        return damage;
    }

    /**
     * Sets the amount of damage this Turret does per shot.
     * @param dmg - damage
     */
    public void setDamage(double dmg) {
        damage = dmg;
    }

    /**
     * Resets the target update timer.
     */
    public void setTargetTimer() {
        targetTimer = System.currentTimeMillis();
    }

    /**
     * Resets the fire timer.
     */
    public void setFireTimer() {
        fireTimer = System.currentTimeMillis();
    }

    /**
     * Sets a new target update interval for this Entity.
     * @param interval - new update interval
     */
    public void setUpdateInterval(int interval) {
        updateInterval = interval;
    }

    /**
     * Sets a new fire interval for this Entity.
     * @param interval - new fire interval
     */
    public void setFireInterval(int interval) {
        fireInterval = interval;
    }

    public Random getAI() {
        return AI;
    }

}
