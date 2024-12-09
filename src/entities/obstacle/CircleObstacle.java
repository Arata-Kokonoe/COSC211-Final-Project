package entities.obstacle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import entities.Hitbox;
import entities.Hitcircle;

public class CircleObstacle extends Obstacle{
    Hitcircle hitcircle;
    int radius;

    public CircleObstacle(int obsX, int obsY, int obsWidth, int obsHeight, int rad){
        super(obsX, obsY, obsWidth, obsHeight);
        radius = rad;
        collision = true;
        hitcircle = new Hitcircle(obsX, obsY, obsWidth, obsHeight, rad);
    }

    public boolean checkCollision(Hitcircle other){
        return hitcircle.checkCollision(other);
    }

    public boolean checkCollision(Hitbox other){
        return hitcircle.checkCollision(other);
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(3));
        g2.drawOval(x, y, radius*2, radius*2);
    }
}
