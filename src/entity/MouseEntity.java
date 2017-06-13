package entity;

import window.GameWindow;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Arnoud on 8-11-2016.
 */
public class MouseEntity extends JPanel {

    private Point location;

    public MouseEntity(int x, int y){
        location = new Point(x, y);
    }

    /**
     * @return - x position of this Entity
     */
    public int getX(){
        return location.x;
    }

    /**
     * @return - y position of this Entity
     */
    public int getY(){
        return location.y;
    }

    /**
     * Sets a new X position for this Entity.
     * @param newX - new X position
     */
    public void setX(double newX){
        location.x = (int)Math.round(newX);
    }

    /**
     * Sets a new Y position for this Entity.
     * @param newY - new Y position
     */
    public void setY(double newY){
        location.y = (int)Math.round(newY);
    }

    /**
     * Runs through this Entity's timers.
     */
    public void move() {
        setX((int)(MouseInfo.getPointerInfo().getLocation().getX() - GameWindow.getPos().getX()));
        setY((int)(MouseInfo.getPointerInfo().getLocation().getY() - GameWindow.getPos().getY()) - 16);
    }
}
