package entities.obstacle;

import java.awt.Graphics2D;

import entities.Entity;
import entities.Hitbox;

public class Obstacle extends Entity{

    protected Hitbox hitbox;
    
    protected int x, y, width, height;
    public boolean collision;

    public final static int MAX_OBS = 3;
    
    public Obstacle(int obsX, int obsY, int obsWidth, int obsHeight){
        x = obsX;
        y = obsY;
        width = obsWidth;
        height = obsHeight;
        collision = false;
    }

    public boolean checkCollision(Hitbox other){
        if(hitbox != null) return hitbox.checkCollision(other);
        else return false;
    }

    public void draw(Graphics2D g2){
        //blank
    }

}
