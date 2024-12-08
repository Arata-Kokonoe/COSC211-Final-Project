package entities.obstacle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import entities.Entity;
import entities.Hitbox;

public class Obstacle extends Entity{
    
    Hitbox hitbox;
    int x, y, width, height;
    public boolean collision;

    public final static int MAX_OBS = 2;
    
    public Obstacle(int obsX, int obsY, int obsWidth, int obsHeight){
        x = obsX;
        y = obsY;
        width = obsWidth;
        height = obsHeight;
        hitbox = new Hitbox(obsX, obsY, obsWidth, obsHeight);
        collision = false;
    }

    public void draw(Graphics2D g2){
        //blank
    }

}
