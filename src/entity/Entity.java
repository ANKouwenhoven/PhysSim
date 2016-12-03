package entity;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Arnoud on 8-11-2016.
 */
public class Entity extends JPanel {

    private Point location;

    public Entity(int x, int y){
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
}