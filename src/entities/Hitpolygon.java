package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;

public class Hitpolygon extends Polygon{

    int minX, maxX, minY, maxY;

    public Hitpolygon(int[] xPoints, int[] yPoints, int nPoints){
        xpoints = xPoints;
        ypoints = yPoints;
        npoints = nPoints;

        minX = xpoints[0];
        maxX = xpoints[0];
        minY = ypoints[0];
        maxY = ypoints[0];
        for(int i = 0; i < npoints; i++){
            if (xpoints[i] > maxX) maxX = xpoints[i];
            else if(xpoints[i] < minX) minX = xpoints[i];
            if(ypoints[i] > maxY) maxY = ypoints[i];
            else if(ypoints[i] < minY) minY = ypoints[i];
        }



        bounds = new Rectangle(minX, minY, maxX - minX, maxY - minY);
    }

    public void update(int[] newXs, int[] newYs){
        for(int i = 0; i < npoints; i++){
            xpoints[i] = newXs[i];
            ypoints[i] = newYs[i];
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.RED);
        g2.draw(this);
    }

    public boolean checkCollision(Hitbox other){
        if (this.intersects(other.getHitbox())) return true;
        else return false;
    }

    public int getMiddleX(){
        return (maxX - minX) / 2;
    }

    public int getMiddleY(){
        return (maxY - minY) / 2;
    }
}
