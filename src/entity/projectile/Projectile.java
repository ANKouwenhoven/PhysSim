package entity.projectile;

import entity.Entity;
import entity.MovingEntity;
import singleton.Game;
import singleton.ParticleManager;
import window.GameWindow;

import java.awt.*;

/**
 * Created by Arnoud on 8-11-2016.
 */
public class Projectile extends Entity implements MovingEntity {

    public double actualX, actualY, lifetime, angle, shotSpeed = 0;
    private long spawnTime = System.currentTimeMillis();
    private double damage = 0;

    public Projectile(int x, int y, double tX, double tY, int life, double dmg) {
        super(x, y);
        lifetime = life;
        damage = dmg;

        actualX = getX();
        actualY = getY();

        angle = -Math.toDegrees(Math.atan2(getX() - tX, getY() - tY)) + 180;
    }

    @Override
    public void move() {

        checkTimer();
        checkForCollision();

        actualX += Math.cos(Math.toRadians(angle + 90)) * shotSpeed;
        actualY += Math.sin(Math.toRadians(angle + 90)) * shotSpeed;

        setX(actualX);
        setY(actualY);

        ParticleManager.particleCloud(this.getX(), this.getY(), 5, 2, 500, Color.BLACK);
    }

    public void checkTimer() {
        if (System.currentTimeMillis() - spawnTime > lifetime) {
            triggerDeath();
        }
    }

    public void checkForCollision() {
        for (double i = -shotSpeed - 1; i < shotSpeed + 1; i++) {
            for (double j = -shotSpeed - 1; j < shotSpeed + 1; j++) {
                for (int k = 0; k < Game.getInstance().getEnemyList().size(); k++) {
                    if (getX() + i == Game.getInstance().getEnemyList().get(k).getX() && getY() + j == Game.getInstance().getEnemyList().get(k).getY()) {
                        Game.getInstance().getEnemyList().get(k).dealDamage((int)Math.round(damage));
                        triggerDeath();
                    }
                }
            }
        }
    }

    public Projectile setSpeed(double speed) {
        shotSpeed = speed;
        return this;
    }

    public void triggerDeath() {
        ParticleManager.particleCloud(this.getX(), this.getY(), 80, 50, 1000, Color.BLACK);
        ParticleManager.particleBurst(this.getX(), this.getY(), 5, 5, 50, 200, Color.BLACK);
        Game.getInstance().removeProjectile(this);
    }

    public double getLifetime() {
        return lifetime;
    }

    public long getSpawnTime() {
        return spawnTime;
    }

    public double getAngle() {
        return angle;
    }

    public double getActualX() {
        return actualX;
    }

    public double getActualY() {
        return actualY;
    }

    public void setActualX(double x) {
        this.actualX = x;
    }

    public void setActualY(double y) {
        this.actualY = y;
    }
}
