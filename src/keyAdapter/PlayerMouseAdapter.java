package keyAdapter;

import drawing.PhysicsEngine;
import entity.*;
import singleton.Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Arnoud on 8-11-2016.
 */
public class PlayerMouseAdapter extends MouseAdapter {

    private int mouseX, mouseY = 0;

    /**
     * Runs when a mouse button is pressed.
     * @param e - MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = Math.round((Game.getInstance().getMouse().getX()));
        mouseY = Math.round((Game.getInstance().getMouse().getY()));

        switch(e.getButton()) {
            case MouseEvent.BUTTON1:
                for (int i = -10; i < 10; i++) {
                    PhysicsEngine.getGameGrid()[mouseX + (int)(Math.random() * i)]
                            [mouseY + (int)(Math.random() * i)] = new EntitySand();
                    PhysicsEngine.getGameGrid()[mouseX + (int)(Math.random() * i)]
                            [mouseY + (int)(Math.random() * i)] = new EntityGravel();
                    PhysicsEngine.getGameGrid()[mouseX + (int)(Math.random() * i)]
                            [mouseY + (int)(Math.random() * i)] = new EntitySnow();

                }
                break;
            case MouseEvent.BUTTON3:
                break;
        }
    }
}
