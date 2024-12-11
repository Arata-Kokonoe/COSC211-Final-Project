package entities;

import java.awt.Color;
import java.awt.Graphics2D;

public class Hitcircle{
    private int x, y, entityWidth, entityHeight, radius;

    public Hitcircle(int entX, int entY, int entWidth, int entHeight, int rad){
        x = entX + entWidth/2;
        y = entY + entHeight/2;
        radius = rad;
    }

    public void update(int entityX, int entityY){
        x = entityX + entityWidth/2;
        y = entityY + entityHeight/2;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.GREEN);
        g2.drawOval(x, y, radius*2, radius*2);
    }

    public boolean checkCollision(Hitcircle other){
        int minDistance = other.radius + this.radius;
        if(dist(other.x, other.y, this.x, this.y) < minDistance) return true;
        else return false;
    }
    public boolean checkCollision(Hitbox other){
        //separating axis theorem
        int testX = x, testY = y, distX, distY, distance;

        if (x < other.getHitbox().getX()) testX = (int)other.getHitbox().getX();        // left edge
        else if (x > other.getHitbox().getX() + other.getHitbox().getWidth()) testX = (int)(other.getHitbox().getX() + other.getHitbox().getWidth());     // right edge

        if (y < other.getHitbox().getY()) testY = (int)other.getHitbox().getY();        // top edge
        else if (y > other.getHitbox().getY() + other.getHitbox().getHeight()) testY = (int)(other.getHitbox().getY() + other.getHitbox().getHeight());     // bottom edge

        distX = testX - x;
        distY = testY - y;

        distance = (int)(Math.sqrt(distX*distX + distY*distY));

        if(distance <= radius) return true;
        else return false;
    }

    public int dist(int x1, int y1, int x2, int y2) {
        return (int)(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }
}
