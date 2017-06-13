package entity;

import java.awt.*;

/**
 * Created by Arnoud on 8-11-2016.
 */
public class Entity {

    private Color particleColor;
    private double particleMass;
    private double xVel, yVel;
    private double friction;
    private double bounciness;

    public Entity(Color c, double m, double f, double b) {
        particleColor = c;
        particleMass = m;
        friction = f;
        bounciness = b;
        xVel = 0;//(-2 + (Math.random() * 4)) * 10;
        yVel = 0;//(-2 + (Math.random() * 4)) * 10;
    }

    public Color getParticleColor() {
        return particleColor;
    }

    public double getParticleMass() {
        return particleMass;
    }

    public void setParticleMass(double m) {
        particleMass = m;
    }

    public double getyVel() {
        return yVel;
    }

    public void setyVel(double v) {
        yVel = v;
    }

    public double getxVel() {
        return xVel;
    }

    public void setxVel(double v) {
        xVel = v;
    }

    public double getFriction() {
        return friction;
    }

    public double getBounciness() {
        return bounciness;
    }
}