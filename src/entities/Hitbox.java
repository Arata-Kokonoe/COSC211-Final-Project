package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import core.Position;
import core.Size;

public class Hitbox{
    
    private Rectangle hitbox;

    public Hitbox(Rectangle hb){
        hitbox = hb;
    }

    public boolean collidesWith(Hitbox other) {
        return hitbox.intersects(other.getHitbox());
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.RED);
        g2.draw(hitbox);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
    
    
    /*
    int xOffset, yOffset;

    
    public Hitbox(int hitboxXOffset, int hitboxYOffset, int hitboxX, int hitboxY, int hitboxWidth, int hitboxHeight){
        xOffset = hitboxXOffset;
        yOffset = hitboxYOffset;
        x = hitboxX + xOffset;
        y = hitboxY + yOffset;
        width = hitboxWidth;
        height = hitboxHeight;
    }

    public Hitbox(int hitboxX, int hitboxY, int hitboxWidth, int hitboxHeight){
        xOffset = 0;
        yOffset = 0;
        x = hitboxX;
        y = hitboxY;
        width = hitboxWidth;
        height = hitboxHeight;
    }

    public void update(int newX, int newY){
        x = newX + xOffset;
        y = newY + yOffset;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.RED);
        g2.draw(this);
    }

    public boolean checkCollision(Hitbox other){
        if (this.intersects(other)) return true;
        else return false;
    }
        */
    public int getMiddleX(){
        return (int)(hitbox.getX() + hitbox.getWidth()/2);
    }

    public int getMiddleY(){
        return (int)(hitbox.getY() + hitbox.getHeight()/2);
    }

}
