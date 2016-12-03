package entity.projectile;

import singleton.ParticleManager;

import java.awt.*;

/**
 * Created by Arnoud on 8-11-2016.
 */
public class ProjSpeeder extends Projectile {

    private double shotSpeed = 0.3;

    public ProjSpeeder(int x, int y, double tX, double tY, int life, double dmg) {
        super(x, y, tX, tY, life, dmg);
    }

    public void move() {
        shotSpeed *= 1.04;

        if (System.currentTimeMillis() - getSpawnTime() > getLifetime()) {
            triggerDeath();
        }

        checkForCollision();

        setActualX(getActualX() + Math.cos(Math.toRadians(getAngle() + 90)) * shotSpeed);
        setActualY(getActualY() + Math.sin(Math.toRadians(getAngle() + 90)) * shotSpeed);

        setX(getActualX());
        setY(getActualY());

        ParticleManager.particleCloud(this.getX(), this.getY(), 5, 2, 500, Color.BLACK);
    }
}
