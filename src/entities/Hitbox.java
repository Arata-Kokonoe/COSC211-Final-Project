package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Hitbox extends Rectangle{
    private int entityWidth, entityHeight;

    public Hitbox(int entX, int entY, int entWidth, int entHeight){
        x = entX;
        y = entY;
        width = entWidth;
        height = entHeight;
        entityWidth = entWidth;
        entityHeight = entHeight;
    }

    public Hitbox(int entX, int entY, int entWidth, int entHeight, int hitboxWidth, int hitboxHeight){
        x = (entX + entWidth/2) - hitboxWidth/2;
        y = (entY + entHeight) - hitboxHeight;
        width = hitboxWidth;
        height = hitboxHeight;
        entityWidth = entWidth;
        entityHeight = entHeight;
    }

    public void update(int entityX, int entityY){
        x = (entityX + entityWidth/2) - width/2;
        y = (entityY + entityHeight) - height;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.RED);
        g2.draw(this);
    }

    public boolean checkCollision(Hitbox other){
        if (this.intersects(other)) return true;
        else return false;
    }
}
