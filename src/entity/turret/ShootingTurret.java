package entity.turret;

import entity.projectile.Projectile;
import singleton.Game;
import window.GameWindow;

import javax.imageio.ImageIO;

/**
 * Created by Arnoud on 10-11-2016.
 */
public class ShootingTurret extends Turret {

    public ShootingTurret(int _x, int _y) {
        super(_x, _y);

        setSprite("turretShooting");

        setDamage(50);
        setUpdateInterval(600);
        setFireInterval(600);
    }

    /**
     * Runs through this Turret's code.
     */
    public void run() {
        if (System.currentTimeMillis() - getUpdateInterval() > getTargetTimer()) {
            setTargetTimer();
            if (!Game.getInstance().getEnemyList().contains(getTarget())) {
                updateTarget();
            }
        }

        if (System.currentTimeMillis() - getFireInterval() > getFireTimer()) {
            setFireTimer();
            if (getTarget() != null && Game.getInstance().getEnemyList().contains(getTarget())) {
                Game.getInstance().addProjectile(new Projectile(getX(), getY(), getTarget().getX(), getTarget().getY(), 900, getDamage()).setSpeed(5));
            }
        }
    }
}
