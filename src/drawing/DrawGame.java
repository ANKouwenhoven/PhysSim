package drawing;

import entity.Entity;
import singleton.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Arnoud on 8-11-2016.
 */
public class DrawGame extends JPanel {
    private Graphics2D g2d;

    /**
     * Jpanel method to start drawing.
     * @param g - Graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    /**
     * Draw all drawable elements of the game.
     * @param g Graphics object
     */
    private void doDrawing(Graphics g) {
        if (g2d == null) {
            g2d = (Graphics2D) g.create();
        }

        drawEntities();
        repaint();
    }

    /**
     * Draw all Entities in the game.
     */
    private void drawEntities() {
        for (int i = 0; i < PhysicsEngine.getGameGrid().length; i++) {
            for (int j = 0; j < PhysicsEngine.getGameGrid().length; j++) {
                if (PhysicsEngine.getGameGrid()[i][j] != null) {
                    g2d.setPaint(PhysicsEngine.getGameGrid()[i][j].getParticleColor());
                    g2d.draw(new Rectangle(i , j, 1, 1));
                }
            }
        }
    }
}
