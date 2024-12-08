package entities.obstacle;

import java.awt.Graphics2D;

import entities.Entity;

public class Obstacle extends Entity{
    
    protected int x, y, width, height;
    public boolean collision;

    public final static int MAX_OBS = 2;
    
    public Obstacle(int obsX, int obsY, int obsWidth, int obsHeight){
        x = obsX;
        y = obsY;
        width = obsWidth;
        height = obsHeight;
        collision = false;
    }

    public void draw(Graphics2D g2){
        //blank
    }

}
