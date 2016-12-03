package entity.enemy;

import entity.Entity;
import entity.particle.OrbitalParticle;
import singleton.Game;
import singleton.ParticleManager;
import window.GameWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Arnoud on 9-11-2016.
 */
public class EnemyFactory {

    private Random AI = new Random();
    private int x, y, spawnInterval = 0;
    private long localSpawnTimer = System.currentTimeMillis();
    private OrbitalParticle particle;
    private int amountSpawned = 0;
    private int maxSpawnable = 1;
    private int side;

    public EnemyFactory(int x, int y, int interval) {
        this.x = x * 16 + 8;
        this.y = y * 16 + 8;

        spawnInterval = interval;

        particle = new OrbitalParticle(new Entity(this.x, this.y), 4, 5, 0, 7, new ArrayList<>(Arrays.asList(Color.BLACK))).trails(300).oscillate(40, 60, 6.5);
        //ParticleManager.addOrbitParticle(particle);
    }

    /**
     * Sets the side of the path to spawn enemies on.
     * @param in - side of the path
     * @return - modified Factory
     */
    public EnemyFactory setSide(int in) {
        this.side = in;
        return this;
    }

    /**
     * Creates one randomly selected Enemy.
     * @param x - x position to create Enemy
     * @param y - y position to create Enemy
     */
    public void createRandomEnemy(int x, int y) {
    }

    /**
     * Runs through this Factory's timers.
     */
    public void runTimers() {
        if (amountSpawned < maxSpawnable && System.currentTimeMillis() - localSpawnTimer > spawnInterval) {
            localSpawnTimer = System.currentTimeMillis();
            createStandardEnemy(this.x, this.y);
            amountSpawned++;
        }
    }

    /**
     * Creates a Standard Enemy.
     * @param x - x position to create
     * @param y - y position to create
     */
    public void createStandardEnemy(int x, int y) {
        Game.getInstance().addEnemy(new Enemy(x, y).setSide(side));
    }
}
