package level;

import entity.enemy.EnemyFactory;

/**
 * Created by Arnoud on 9-11-2016.
 */
public class LevelFactory {

    private static Level level;

    private static LevelFactory levelFactory;

    private LevelFactory() {

    }

    /**
     * @return - the Singleton that is this LevelFactory
     */
    public static LevelFactory getLevelFactory() {
        if (levelFactory == null) {
            levelFactory = new LevelFactory();
        }
        return levelFactory;
    }

    /**
     * Creates a level.
     * @return - new Level
     */
    public static Level createLevel() {
        if (level == null) {
            level = new Level();
        }

        level.addEnemyFactory(new EnemyFactory(2, -1, 500).setSide(1));
        //level.addEnemyFactory(new EnemyFactory(3, -1, 500).setSide(2));

        return level;
    }
}
