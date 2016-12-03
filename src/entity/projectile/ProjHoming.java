package entity.projectile;

import entity.Entity;
import singleton.ParticleManager;

import java.awt.*;

/**
 * Created by Arnoud on 9-11-2016.
 */
public class ProjHoming extends Projectile {

    private Entity target;

    public ProjHoming(int x, int y, double tX, double tY, Entity trg, int life, double dmg) {
        super(x, y, tX, tY, life, dmg);
        target = trg;
    }

    @Override
    public void move() {
        checkTimer();
        checkForCollision();

        if (target != null) {
            angle = -Math.toDegrees(Math.atan2(getX() - target.getX(), getY() - target.getY())) + 180;
        }

        actualX += Math.cos(Math.toRadians(angle + 90)) * shotSpeed;
        actualY += Math.sin(Math.toRadians(angle + 90)) * shotSpeed;

        setX(actualX);
        setY(actualY);

        ParticleManager.particleCloud(this.getX(), this.getY(), 5, 2, 500, Color.BLACK);
    }
}
