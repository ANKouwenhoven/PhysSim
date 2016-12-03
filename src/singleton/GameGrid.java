package singleton;

import drawing.DrawableLine;
import entity.Entity;
import entity.turret.LaserTurret;
import entity.turret.ShootingTurret;

import java.awt.*;

/**
 * Created by Arnoud on 10-11-2016.
 */
public class GameGrid {

    private Entity[][] grid;

    private static volatile GameGrid gameGridInstance;

    /**
     * Private constructor of GameGrid class.
     */
    private GameGrid() {
        grid = new Entity[30][30];
    }

    /**
     * Use this to get the GameGrid instance that is created or create one if there's none.
     * @return instance of GameGrid
     */
    public static GameGrid getInstance() {
        if (gameGridInstance == null) {
            synchronized (GameGrid.class) {
                if (gameGridInstance == null) {
                    gameGridInstance = new GameGrid();
                }
            }
        }
        return gameGridInstance;
    }

    /**
     * Returns the 2d integer array that stores all the values of the coordinates of the GameGrid.
     * @return 2d integer array with values of all the coordinates
     */
    public Entity[][] getGrid() {
        return grid;
    }

    /**
     * Returns the value of a certain coordinate specified by an x and y coordinate.
     * @param x - x coordinate
     * @param y - y coordinate
     * @return Entity on the specified coordinate
     */
    public Entity getEntity(int x, int y) {
        return grid[x][y];
    }

    /**
     * Changes the value at the specified coordinate by an x and y coordinate, to a value specified by value.
     * @param x - x coordinate
     * @param y - y coordinate
     * @param entity - new Entity for the specified coordinate
     */
    public void setEntity(int x, int y, String entity) {
        if (x > 29 || y > 29) {
            System.out.println("Invalid coordinates for placing an Entity.");
            return;
        } else if (entity.equals("ShootingTurret")) {
            grid[x][y] = new ShootingTurret(x * 16 + 8, y * 16 + 8);
        } else if (entity.equals("LaserTurret")) {
            grid[x][y] = new LaserTurret(x * 16 + 8, y * 16 + 8);
        }
    }

    /**
     * Sets the default config of the grid.
     */
    public void setStandardGrid() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (i == 0) {
                    ParticleManager.addLine(new DrawableLine(0, j * 16 + 16, 480, j *16 + 16).setColor(Color.GRAY));
                }
                if (j == 0) {
                    ParticleManager.addLine(new DrawableLine(i * 16 + 16, 0, i * 16 + 16, 480).setColor(Color.GRAY));
                }
            }
        }
    }

}
