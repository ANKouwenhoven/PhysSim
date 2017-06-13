package keyAdapter;

import drawing.PhysicsEngine;
import entity.*;
import singleton.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Created by Arnoud on 8-11-2016.
 */
public class PlayerKeyAdapter extends KeyAdapter {

    /**
     * Runs when a key is pressed.
     * @param e - KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        int mouseX = Math.round((Game.getInstance().getMouse().getX()));
        int mouseY = Math.round((Game.getInstance().getMouse().getY()));

        switch(key) {
            case KeyEvent.VK_A:
                for (int i = 0; i < PhysicsEngine.getGameGrid().length; i++) {
                    for (int j = 0; j < PhysicsEngine.getGameGrid().length; j++) {
                        if (PhysicsEngine.getGameGrid()[i][j] != null) {
                            PhysicsEngine.getGameGrid()[i][j].setxVel(-3);
                        }
                    }
                }
                break;

            case KeyEvent.VK_D:
                for (int i = 0; i < PhysicsEngine.getGameGrid().length; i++) {
                    for (int j = 0; j < PhysicsEngine.getGameGrid().length; j++) {
                        if (PhysicsEngine.getGameGrid()[i][j] != null) {
                            PhysicsEngine.getGameGrid()[i][j].setxVel(3);
                        }
                    }
                }
                break;

            case KeyEvent.VK_W:
                for (int i = 0; i < PhysicsEngine.getGameGrid().length; i++) {
                    for (int j = 0; j < PhysicsEngine.getGameGrid().length; j++) {
                        if (PhysicsEngine.getGameGrid()[i][j] != null) {
                            PhysicsEngine.getGameGrid()[i][j].setyVel(-3);
                        }
                    }
                }
                break;

            case KeyEvent.VK_S:
                for (int i = 0; i < PhysicsEngine.getGameGrid().length; i++) {
                    for (int j = 0; j < PhysicsEngine.getGameGrid().length; j++) {
                        if (PhysicsEngine.getGameGrid()[i][j] != null) {
                            PhysicsEngine.getGameGrid()[i][j].setyVel(3);
                        }
                    }
                }
                break;

            case KeyEvent.VK_P:
                int randomx;
                int randomy;
                for (int i = -10; i < 10; i++) {
                    for (int j = -5; j < 5; j++) {
                        randomx = (int) (Math.random() * i * 5);
                        randomy = (int) (Math.random() * j * 10);
                        if (PhysicsEngine.isFree(mouseX + randomx, mouseY + randomy)) {
                            PhysicsEngine.getGameGrid()[mouseX + randomx][mouseY + randomy] = spawnParticle();
                        }
                    }
                }
                break;
        }
    }

    private Entity spawnParticle() {
        Entity entity = new EntityDarkmatter();
        Random r = new Random();
        switch (r.nextInt(4)) {
            case 0: entity = new EntitySand();
                break;

            case 1: entity = new EntityGravel();
                break;

            case 2: entity = new EntityDarkmatter();
                break;

            case 3: entity = new EntityWhitematter();
                break;
        }

        return entity;
    }

    /**
     * Runs when a key is released.
     * @param e - KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        switch(key){
            default:
                break;
        }
    }

}