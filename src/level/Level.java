package level;

import entity.MouseEntity;
import entity.Player;
import entity.enemy.EnemyFactory;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Arnoud on 9-11-2016.
 */
public  class Level {

    private static Player player;
    private static MouseEntity mouse;
    private static Path path1, path2;
    private static Point win1, win2;

    private static ArrayList<EnemyFactory> enemyFactoryList = new ArrayList<>();

    /**
     * Initializes the level.
     */
    public void initLevel() {
        player = new Player(300, 300);
        mouse = new MouseEntity(0, 0);
        path1 = new PathLevel1(1);
        path2 = new PathLevel1(2);
        win1 = new Point(29 * 16 + 8, 25 * 16 + 8);
        win2 = new Point(29 * 16 + 8, 24 * 16 + 8);
    }

    /**
     * @return - the Level's Player
     */
    public static Player getPlayer() {
        return player;
    }

    /**
     * @return - the Level's Mouse Entity
     */
    public static MouseEntity getMouse() {
        return mouse;
    }

    /**
     * @return the Level's FactoryList
     */
    public static ArrayList<EnemyFactory> getFactoryList() {
        return enemyFactoryList;
    }

    public void addEnemyFactory(EnemyFactory factory) {
        if (!enemyFactoryList.contains(factory)) {
            enemyFactoryList.add(factory);
        }
    }

    public static Path getPath(int side) {
        if (side == 1) {
            return path1;
        } else {
            return path2;
        }
    }

    public static Point getWin(int side) {
        if (side == 1) {
            return win1;
        } else {
            return win2;
        }
    }
}
