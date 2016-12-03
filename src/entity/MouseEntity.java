package entity;

import entity.particle.OrbitalParticle;
import singleton.ParticleManager;
import window.GameWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Arnoud on 8-11-2016.
 */
public class MouseEntity extends Entity implements MovingEntity {

    private OrbitalParticle particle;

    public MouseEntity(int x, int y) {
        super(x, y);
        particle = new OrbitalParticle(this, 5, 10, 0, 5, new ArrayList<>(Arrays.asList(Color.BLACK)));
        ParticleManager.addOrbitParticle(particle);
    }

    /**
     * Runs through this Entity's timers.
     */
    public void move() {
        setX((int)(MouseInfo.getPointerInfo().getLocation().getX() - GameWindow.getPos().getX()));
        setY((int)(MouseInfo.getPointerInfo().getLocation().getY() - GameWindow.getPos().getY()) - 16);
    }
}
