package singleton;

import drawing.PhysicsEngine;
import entity.Entity;
import entity.MouseEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Arnoud on 8-11-2016.
 */
public class Game implements ActionListener {

    private Timer timer;
    private final int delay = 10;

    private static volatile Game game;

    private MouseEntity mouse;

    /**
     * Runs through this code on creation.
     */
    public void init() {
        timer = new Timer(delay, this);
        timer.start();
        mouse = new MouseEntity(0, 0);
        PhysicsEngine.init();
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
        manageTimers();
    }

    /**
     * Runs the movement events of all Entities.
     */
    private void moveEntities() {
        mouse.move();
        PhysicsEngine.step();
    }

    /**
     * Manage all timers this class is responsible for.
     */
    private void manageTimers() {

    }

    public MouseEntity getMouse() {
        return mouse;
    }
}
