package entity.turret;

import drawing.DrawableLine;
import singleton.Game;
import singleton.ParticleManager;
import window.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;

/**
 * Created by Arnoud on 11-11-2016.
 */
public class LaserTurret extends Turret {

    public LaserTurret(int x, int y) {
        super(x, y);

        setSprite("turretLaser");

        setUpdateInterval(20);
        setFireInterval(20);
        setDamage(1);
    }

    /**
     * Runs through this Turret's code.
     */
    @Override
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
                ParticleManager.addLine(new DrawableLine(getX(), getY(), getTarget().getX() + getAI().nextInt(3) - getAI().nextInt(3),
                        getTarget().getY() + getAI().nextInt(3) - getAI().nextInt(3)).setColor(Color.black).setLife(100));
                getTarget().dealDamage(getDamage());
                ParticleManager.particleBurst(getTarget().getX(), getTarget().getY(), 1.5, 1.5, 10, 100, Color.black);
            }
        }
    }
}
