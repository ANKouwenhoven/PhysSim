package keyAdapter;

import singleton.Game;
import window.GameWindow;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

        switch(key) {
            case KeyEvent.VK_A:
                Game.getInstance().getLevel().getPlayer().setDx(-1);
                break;

            case KeyEvent.VK_W:
                Game.getInstance().getLevel().getPlayer().setDy(-1);
                break;

            case KeyEvent.VK_D:
                Game.getInstance().getLevel().getPlayer().setDx(1);
                break;

            case KeyEvent.VK_S:
                Game.getInstance().getLevel().getPlayer().setDy(1);
                break;

            case KeyEvent.VK_R:
                break;

            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
        }
    }

    /**
     * Runs when a key is released.
     * @param e - KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        switch(key){
            case KeyEvent.VK_A:
            case KeyEvent.VK_D:
                Game.getInstance().getLevel().getPlayer().setDx(0);
                break;

            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
                Game.getInstance().getLevel().getPlayer().setDy(0);
                break;

            default:
                break;
        }
    }

}