package singleton;

import entity.enemy.Enemy;
import entity.projectile.Projectile;
import entity.turret.Turret;
import level.Level;
import level.LevelFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Arnoud on 8-11-2016.
 */
public class Game implements ActionListener {

    private Timer timer;
    private final int delay = 10;

    private static volatile Game game;

    private Random AI = new Random();
    private Level level;

    private ArrayList<Projectile> projectileList = new ArrayList<>();
    private ArrayList<Enemy> enemyList = new ArrayList<>();

    /**
     * Runs through this code on creation.
     */
    public void init(){

        level = LevelFactory.getLevelFactory().createLevel();
        level.initLevel();

        timer = new Timer(delay, this);
        timer.start();

        GameGrid.getInstance().setStandardGrid();
    }

    /**
     * Use this to get the Game instance that is created or create one if there's none.
     * @return instance of Game
     */
    public static Game getInstance() {
        if (game == null) {
            synchronized (Game.class) {
                if (game == null) {
                    game = new Game();
                }
            }
        }
        return game;
    }

    /**
     * Heartbeat of the program, runs every 10 ms.
     * @param e - ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        moveEntities();
        ParticleManager.maintainParticles();
        manageTimers();
    }

    /**
     * Runs the movement events of all Entities.
     */
    private void moveEntities() {
        Level.getPlayer().move();
        Level.getMouse().move();

        for (int i = 0; i < projectileList.size(); i++) {
            projectileList.get(i).move();
        }

        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).move();
        }

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (GameGrid.getInstance().getEntity(i, j) instanceof Turret) {
                    ((Turret) GameGrid.getInstance().getEntity(i, j)).run();
                }
            }
        }
    }

    /**
     * Manage all timers this class is responsible for.
     */
    private void manageTimers() {
        for (int i = 0; i < Level.getFactoryList().size(); i++) {
            Level.getFactoryList().get(i).runTimers();
        }
    }

    /**
     * Adds a Projectile to the list.
     * @param projectile - projectile to add
     */
    public void addProjectile(Projectile projectile) {
        projectileList.add(projectile);
    }

    /**
     * Removes a Projectile from the list.
     * @param projectile - projectile to remove
     */
    public void removeProjectile(Projectile projectile) {
        projectileList.remove(projectile);
    }

    /**
     * Adds an Enemy to the list.
     * @param enemy - Enemy to add
     */
    public void addEnemy(Enemy enemy) { enemyList.add(enemy); }

    /**
     * Removes an Enemy from the list.
     * @param enemy - Enemy to remove
     */
    public void removeEnemy(Enemy enemy) { enemyList.remove(enemy); }

    /**
     * @return - the list of Enemies
     */
    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }

    /**
     * @return - the current Level
     */
    public Level getLevel() {
        return level;
    }
}
