package keyAdapter;

import entity.projectile.ProjSpeeder;
import singleton.Game;
import singleton.GameGrid;
import singleton.ParticleManager;
import window.GameWindow;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Arnoud on 8-11-2016.
 */
public class PlayerMouseAdapter extends MouseAdapter {

    private int mouseX, mouseY, mouseGridX, mouseGridY = 0;

    /**
     * Runs when a mouse button is pressed.
     * @param e - MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = Math.round((Game.getInstance().getLevel().getMouse().getX()));
        mouseY = Math.round((Game.getInstance().getLevel().getMouse().getY()));
        mouseGridX = (mouseX - 8) / 16;
        mouseGridY = (mouseY - 8) / 16;
        int convertGridX = mouseGridX * 16 + 8;
        int convertGridY = mouseGridY * 16 + 8;

        ParticleManager.particleBurst(convertGridX, convertGridY, .2, .2, 20, 200, Color.black);

        switch(e.getButton()) {
            case MouseEvent.BUTTON1:
                Game.getInstance().addProjectile(new ProjSpeeder(Game.getInstance().getLevel().getPlayer().getX(), Game.getInstance().getLevel().getPlayer().getY(),
                        mouseX, mouseY, 1200, Integer.MAX_VALUE).setSpeed(5));
                break;
            case MouseEvent.BUTTON3:
                GameGrid.getInstance().setEntity(mouseGridX, mouseGridY, "LaserTurret");
                break;
        }
    }
}
