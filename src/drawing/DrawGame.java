package drawing;

import entity.particle.OrbitalParticle;
import entity.particle.Particle;
import entity.turret.Turret;
import level.Level;
import singleton.GameGrid;
import singleton.ParticleManager;

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
        for (Particle particle : ParticleManager.getParticleList()) {
            particle.draw(g2d);
        }

        for (OrbitalParticle particle : ParticleManager.getOrbitalParticleList()) {
            particle.draw(g2d);
        }

        for (DrawableLine line : ParticleManager.getLinesList()) {
            g2d.setPaint(line.getColor());
            g2d.drawLine((int)line.getLine().getX1(), (int)line.getLine().getY1(), (int)line.getLine().getX2(), (int)line.getLine().getY2());
        }

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (GameGrid.getInstance().getEntity(i, j) instanceof Turret) {
                    g2d.drawImage(((Turret) GameGrid.getInstance().getEntity(i, j)).getSprite(), i * 16, j * 16, null);
                }
            }
        }

        for (Point point : Level.getPath(1).getPath()) {
            ParticleManager.addParticle(new Particle((int)point.getX(), (int)point.getY(), Color.BLACK).setLife(1));
        }
        for (Point point : Level.getPath(2).getPath()) {
            ParticleManager.addParticle(new Particle((int)point.getX(), (int)point.getY(), Color.RED).setLife(1));
        }
    }
}
