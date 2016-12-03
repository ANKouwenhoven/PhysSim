package singleton;

import drawing.DrawableLine;
import entity.particle.OrbitalParticle;
import entity.particle.Particle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Arnoud on 8-11-2016.
 */
public class ParticleManager {

    private static ArrayList<Particle> particleList = new ArrayList<>();
    private static ArrayList<OrbitalParticle> orbitalParticleList = new ArrayList<>();
    private static ArrayList<DrawableLine> linesList = new ArrayList<>();
    private static Random r = new Random();

    /**
     * Creates a burst of Particles.
     * @param x - x position of burst
     * @param y - y position of burst
     * @param xSpeed - horizontal speed of burst
     * @param ySpeed - vertical speed of burst
     * @param amount - amount of particles in burst
     * @param lifetime - lifetime of the particles
     * @param color - color of the particles
     */
    public static void particleBurst(int x, int y, double xSpeed, double ySpeed, int amount, int lifetime, Color color) {
        for (int i = 0; i < amount; i++) {
            addParticle(new Particle(x, y, xSpeed * (r.nextDouble() - r.nextDouble()), ySpeed * (r.nextDouble() - r.nextDouble()), color).setLife(lifetime));
        }
    }

    /**
     * Creates a cloud of Particles.
     * @param x - x position of cloud
     * @param y - y position of cloud
     * @param dist - area size of cloud
     * @param amount - amount of particles in cloud
     * @param lifetime - lifetime of particles
     * @param color - color of particles
     */
    public static void particleCloud(int x, int y, double dist, int amount, int lifetime, Color color) {
        for (int i = 0; i < amount; i++) {
            int randomX = (int)Math.round(x + dist * (r.nextDouble() - r.nextDouble()));
            int randomY = (int)Math.round(y + dist * (r.nextDouble() - r.nextDouble()));
            addParticle(new Particle(randomX, randomY, .3 * (r.nextDouble() - r.nextDouble()), .3 * (r.nextDouble() - r.nextDouble()), color).setLife((int)(0.5 * lifetime + r.nextInt(lifetime))));
        }
    }

    /**
     * Adds a Particle to the list.
     * @param particle - particle to add
     */
    public static void addParticle(Particle particle) {
        if (!particleList.contains(particle)) {
            particleList.add(particle);
        }
    }

    /**
     * Removes a Particle to the list.
     * @param particle - particle to remove
     */
    public static void removeParticle(Particle particle) {
        if (particleList.contains(particle)) {
            particleList.remove(particle);
        }
    }

    /**
     * Adds an Orbit Particle to the list.
     * @param particle - Orbit Particle to add
     */
    public static void addOrbitParticle(OrbitalParticle particle) {
        if (!orbitalParticleList.contains(particle)) {
            orbitalParticleList.add(particle);
        }
    }

    /**
     * Removes an Orbit Particle from the list.
     * @param particle - Orbit Particle to remove
     */
    public static void removeOrbitParticle(OrbitalParticle particle) {
        if (orbitalParticleList.contains(particle)) {
            orbitalParticleList.remove(particle);
        }
    }

    /**
     * Adds a Line to the list.
     * @param line - line to add
     */
    public static void addLine(DrawableLine line) {
        if (!linesList.contains(line)) {
            linesList.add(line);
        }
    }

    /**
     * Removes a Line from the list.
     * @param line - line to remove
     */
    public static void removeLine(DrawableLine line) {
        if (linesList.contains(line)) {
            linesList.remove(line);
        }
    }


    /**
     * Runs through the code of this Orbit Particle's Orbitals.
     */
    public static void maintainParticles() {
        for (int i = 0; i < orbitalParticleList.size(); i++) {
            orbitalParticleList.get(i).maintainOrbit();
        }

        for (int i = 0; i < particleList.size(); i++) {
            particleList.get(i).move();
        }

        for (int i = 0; i < linesList.size(); i++) {
            linesList.get(i).run();
        }
    }

    /**
     * @return - all Particles
     */
    public static ArrayList<Particle> getParticleList() {
        return particleList;
    }

    /**
     * @return - all Orbit Particles
     */
    public static ArrayList<OrbitalParticle> getOrbitalParticleList() {
        return orbitalParticleList;
    }

    /**
     * @return - all Lines
     */
    public static ArrayList<DrawableLine> getLinesList() {
        return linesList;
    }
}
