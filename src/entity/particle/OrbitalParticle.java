package entity.particle;

import entity.Entity;
import singleton.ParticleManager;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by Arnoud on 8-11-2016.
 */
public class OrbitalParticle {

    private int amount;
    private int distance;
    private int x, y;
    private ArrayList<Color> colors = new ArrayList<Color>();
    private ArrayList<Particle> orbitingParticles = new ArrayList<Particle>();
    private double degree = 0;

    private int incline;

    private boolean distanceFlip = false;
    private boolean bounce = false;
    private double tempDist = 0;
    private int bounceMax = 0;
    private int bounceMin = 0;
    private double bounceSpeed = 1;
    private double rotationSpeed = 1;

    private boolean trailsOn = false;
    private int trailLength = 0;

    private Entity target;

    /**
     * Creates the Orbital Particle.
     * @param t    - Target entity to orbit
     * @param amount - Amount of particles to orbit with
     * @param dist - Distance from center
     * @param c    - Particle color
     * @param inc - incline of particle
     * @param rot - Rotational speed of particle
     */
    public OrbitalParticle(Entity t, int amount, int dist, int inc, double rot, ArrayList<Color> c) {
        this.amount = amount;
        colors = c;
        distance = dist;
        tempDist = dist;
        incline = inc;
        target = t;
        rotationSpeed = rot;

        setOrbitingParticles();
    }

    public OrbitalParticle(int x, int y, int amount, int dist, int inc, double rot, ArrayList<Color> c) {
        this.x = 16 * x;
        this.y = 16 * y;
        this.amount = amount;
        colors = c;
        distance = dist;
        tempDist = dist;
        incline = inc;
        rotationSpeed = rot;

        setOrbitingParticles();
    }

    /**
     * Draws this particle.
     * @param g2d - g2d used to draw
     */
    public void draw(Graphics2D g2d) {
        for (Particle p : orbitingParticles) {
            g2d.setPaint(p.getColor());
            p.draw(g2d);
        }
    }

    /**
     * Spawns all particles orbiting this point.
     */
    private void setOrbitingParticles() {
        int colorNumber = 0;
        for (int i = 0; i < amount; i++) {
            orbitingParticles.add(new Particle(this.x, this.y - distance, colors.get(colorNumber)));
            if (colorNumber + 1 == colors.size()) {
                colorNumber = 0;
            } else {
                colorNumber++;
            }
        }
    }

    /**
     * Sets new locations of all particles to animate rotation.
     */
    public void maintainOrbit() {

        if (!target.equals(null)) {
            this.x = target.getX();
            this.y = target.getY();
        }

        double counter = 1;

        for (Particle p : orbitingParticles) {
            p.setX((int) Math.ceil(target.getX() + (distance * (sin(Math.toRadians(360 * (counter / amount) + degree)))) - p.getParticlePosition()));
            p.setY((int) Math.ceil(target.getY() + (distance * (cos(Math.toRadians(360 * (counter / amount) + degree + incline)))) - p.getParticlePosition()));

            p.getRect().setRect(p.getX(), p.getY(), p.getParticleSize(), p.getParticleSize());
            counter++;

            if (trailsOn) {
                ParticleManager.addParticle(new Particle(p.getX(), p.getY(), p.getColor()).setLife(trailLength));
            }
        }

        degree += rotationSpeed;

        if (bounce) {
            distanceFlip();
        }
    }

    /**
     * Increase / decrease center distance to create wobble.
     */
    public void distanceFlip() {
        if (!distanceFlip) {
            tempDist += bounceSpeed;
            if (tempDist > bounceMax) {
                distanceFlip = true;
            }

        } else {
            tempDist -= bounceSpeed;
            if (tempDist < bounceMin) {
                distanceFlip = false;
            }
        }

        distance = (int) Math.ceil(tempDist / 3);
    }

    /**
     * Set the oscillation of the orbit.
     * @param min   - Minimum distance to center
     * @param max   - Maximum distance to center
     * @param speed - Oscillation speed
     * @return - modified Orbital Particle
     */
    public OrbitalParticle oscillate(int min, int max, double speed) {
        this.bounce = true;
        this.bounceMin = min;
        this.bounceMax = max;
        this.bounceSpeed = speed;
        return this;
    }

    /**
     * Sets trails for the orbiting Particles.
     * @param length - trail length
     * @return - modified Orbital Particle
     */
    public OrbitalParticle trails(int length) {
        this.trailsOn = true;
        this.trailLength = length;
        return this;
    }

    /**
     * @return List of currently orbiting particles.
     */
    public ArrayList<Particle> getOrbitingParticles() {
        return orbitingParticles;
    }
}
