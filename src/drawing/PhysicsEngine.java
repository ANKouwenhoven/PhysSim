package drawing;

import entity.Entity;

/**
 * Created by Arnoud on 30-5-2017.
 */
public class PhysicsEngine {
    private static int GROUND_LEVEL = 500;
    private static int CEILING_LEVEL = 30;
    private static int WALL_LEFT = 50;
    private static int WALL_RIGHT = 550;
    private static double GRAVITY = 0.002;
    private static double AIR_RESISTANCE = 1;
    private static double MAX_VELOCITY = 100;
    private static double MIN_VELOCITY = -100;

    private static Entity[][] gameGrid = new Entity[600][600];

    public static Entity[][] getGameGrid() {
        return gameGrid;
    }

    public static void init() {

    }

    private static boolean checkForFall(Entity particle, int i, int j) {
        if (isFree(i + xVel(particle), j + yVel(particle))) {
            swapParticles(i, j, i + xVel(particle), j + yVel(particle));
            //moveThroughAir(particle);
            return true;
        } else {
            //particle.setyVel(0);
            return false;
        }
    }

    private static void moveThroughAir(Entity particle) {
        particle.setxVel(particle.getxVel() * AIR_RESISTANCE);
        particle.setyVel(particle.getyVel() * AIR_RESISTANCE);
    }

    private static int yVel(Entity particle) {
        return (int) (Math.round(particle.getyVel()));
    }

    private static int xVel(Entity particle) {
        return (int) (Math.round(particle.getxVel()));
    }

    public static boolean isFree(int xPos, int yPos) {
        return (gameGrid[xPos][yPos] == null);
    }

    private static boolean checkForDisplace(Entity particle, int i, int j) {
        if (!(isFree(i + xVel(particle), j + yVel(particle)))) {
            if (frictionCheck(particle, gameGrid[i + xVel(particle)][j + yVel(particle)])) {
                swapParticles(i, j, i + xVel(particle), j + yVel(particle));
            }
            velocityTransfer(particle, gameGrid[i + xVel(particle)][j + yVel(particle)]);
            return true;
        } else {
            return false;
        }
    }

    private static void velocityTransfer(Entity particle1, Entity particle2) {
        double totalDx = particle1.getxVel() - particle2.getxVel();
        double totalDy = particle1.getyVel() - particle2.getyVel();
        double totalMass = particle1.getParticleMass() + particle2.getParticleMass();
        double particle1Fraction = particle1.getParticleMass() / totalMass;
        double particle2Fraction = particle2.getParticleMass() / totalMass;
        double p1b = 0.1; particle1.getBounciness();
        double p2b = 0.1;particle2.getBounciness();
        particle1.setxVel(particle1.getxVel() + particle2Fraction * totalDx * p1b);
        particle1.setyVel(particle1.getyVel() + particle2Fraction * totalDy * p1b);
        particle2.setxVel(particle2.getxVel() + particle1Fraction * totalDx * p2b);
        particle2.setyVel(particle2.getyVel() + particle1Fraction * totalDy * p2b);
    }

    private static boolean frictionCheck(Entity particle1, Entity particle2) {
        boolean weight;
        if ((Math.random() * particle1.getParticleMass()) > (Math.random() * particle2.getParticleMass()))
            weight = true;
        else weight = false;
        weight = particle1.getParticleMass() > particle2.getParticleMass();
        return weight && (Math.random() * Math.min(100, Math.abs(particle1.getParticleMass() - particle2.getParticleMass()))
                > (Math.random() * (particle1.getFriction() + particle2.getFriction()) * 50));
    }

    private static void swapParticles(int i1, int j1, int i2, int j2) {
        Entity temp = gameGrid[i1][j1];
        gameGrid[i1][j1] = gameGrid[i2][j2];
        gameGrid[i2][j2] = temp;
    }

    private static void moveParticle(int i, int j) {
        Entity particle = gameGrid[i][j];
        if (j < GROUND_LEVEL - particle.getyVel() && j > CEILING_LEVEL + particle.getyVel()
                && i > WALL_LEFT + particle.getxVel() && i < WALL_RIGHT - particle.getxVel()) {

            particle.setyVel(particle.getyVel() + GRAVITY * particle.getParticleMass());

            if (particle.getyVel() > MAX_VELOCITY) {
                particle.setyVel(MAX_VELOCITY);
            }
            if (particle.getyVel() < MIN_VELOCITY) {
                particle.setyVel(MIN_VELOCITY);
            }
            if (particle.getxVel() > MAX_VELOCITY) {
                particle.setxVel(MAX_VELOCITY);
            }
            if (particle.getxVel() < MIN_VELOCITY) {
                particle.setxVel(MIN_VELOCITY);
            }

            if (!(checkForFall(particle, i, j))) {
                checkForDisplace(particle, i, j);
            }

        } else {
            if (j > GROUND_LEVEL - particle.getyVel() || j < CEILING_LEVEL + particle.getyVel())  {
                //particle.setyVel(particle.getyVel() * -0.3);
                particle.setyVel(0);
            }
            if (i < WALL_LEFT + particle.getxVel() || i > WALL_RIGHT - particle.getxVel()) {
                particle.setxVel(0);
            }
        }
        //if (particle.getyVel() > 100) {
            //System.out.println(particle.getyVel());
        //}
    }

    public static void step() {
        for (int i = gameGrid.length - 1; i > 0; i--) {
            for (int j = gameGrid.length - 1; j > 0; j--) {
                if ((gameGrid[j][i] != null)) {
                    moveParticle(j, i);
                }
            }
        }
    }
}
