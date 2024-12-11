package entities.enemies;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;

import controller.ChaseController;
import entities.Entity;
import entities.Hitbox;
import entities.Hitpolygon;
import entities.MovingEntity;
import entities.Player;
import main.AnimationManager;
import core.Size;

public class TriangleEnemy extends Enemy{

    int[] xPoints, yPoints;
    final int NUM_POINTS = 3;
    float angle;
    AffineTransform at;
    Polygon body;
    Point center;
    Hitpolygon hitpolygon;

    public TriangleEnemy(int x, int y, int width, int height){
        super();
        position.setX(x);
        position.setY(y);
        size = new Size(width, height);
        controller = new ChaseController(position);

        animationSheet = setup("/sprites/enemies/triangle");
        this.animationManager = new AnimationManager(this);

        xPoints = new int[NUM_POINTS];
        yPoints = new int[NUM_POINTS];
        
        /*
        int hitboxX = x;
        int hitboxY = y;
        int hitboxWidth = (int)(width);
        int hitboxHeight = (int)(height);
        int hitboxXOffset = -hitboxWidth/2;
        int hitboxYOffset = -hitboxHeight/2;
        hitbox = new Hitbox(hitboxXOffset, hitboxYOffset, hitboxX, hitboxY, hitboxWidth, hitboxHeight);
        */

        xPoints[0] = x + width/2;
        yPoints[0] = y;
        xPoints[1] = x - width/2;
        yPoints[1] = y + height/2;
        xPoints[2] = x - width/2;
        yPoints[2] = y - height/2;

        hitpolygon = new Hitpolygon(xPoints, yPoints, NUM_POINTS);

        at = new AffineTransform();
        body = new Polygon();

        center = new Point(x, y);
    }

    public void update(Player player){
        /*
        int diffX = player.hitbox.getMiddleX() - x;
        int diffY = player.hitbox.getMiddleY() - y;

        angle = (float)Math.atan2(diffY, diffX);

        hitbox.x += speed * Math.cos(angle);
        //if(hitbox.checkCollision(player.getHitbox()));
        //else x += speed * Math.cos(angle);
        hitbox.y += speed * Math.sin(angle);
        if(hitbox.checkCollision(player.getHitbox()));
        else {
            x += speed * Math.cos(angle);
            y += speed * Math.sin(angle);
        }

        center.x = x;
        center.y = y;
        hitbox.update(x, y);
        */

        int diffX = (int)(player.getHitbox().getMiddleX() - position.getX());
        int diffY = (int)(player.getHitbox().getMiddleY() - position.getY());

        angle = (float)Math.atan2(diffY, diffX);

        for(int i = 0; i < NUM_POINTS; i++){
            hitpolygon.xpoints[i] += speed * Math.cos(angle);
            hitpolygon.ypoints[i] += speed * Math.sin(angle);
        }

        if(hitpolygon.checkCollision(player.getHitbox())) System.out.println("hit");
        else {
            position.setX(position.getX() + (speed * Math.cos(angle)));
            position.setX(position.getY() + (speed * Math.sin(angle)));
        }

        center.x = (int)position.getX();
        center.y = (int)position.getY();

        xPoints[0] = (int)(position.getX() + size.getWidth()/2);
        yPoints[0] = (int)(position.getY());
        xPoints[1] = (int)(position.getX() - size.getWidth()/2);
        yPoints[1] = (int)(position.getY() + size.getHeight()/2);
        xPoints[2] = (int)(position.getX() - size.getWidth()/2);
        yPoints[2] = (int)(position.getY() - size.getHeight()/2);

        at = new AffineTransform();
        at.rotate(angle, position.getX(), position.getY());

        ArrayList<double[]> areaPoints = new ArrayList<double[]>();
        double[] coords = new double[6];

        for (PathIterator pi = at.createTransformedShape(hitpolygon).getPathIterator(null); !pi.isDone(); pi.next()) {
            // The type will be SEG_LINETO, SEG_MOVETO, or SEG_CLOSE
            // Because the Area is composed of straight lines
            int type = pi.currentSegment(coords);
            // We record a double array of {segment type, x coord, y coord}
            double[] pathIteratorCoords = {type, coords[0], coords[1]};
            areaPoints.add(pathIteratorCoords);
        }

        for(int i = 0; i < NUM_POINTS; i++){
            xPoints[i] = (int)areaPoints.get(i)[1];
            yPoints[i] = (int)areaPoints.get(i)[2];
        }

        hitpolygon.update(xPoints, yPoints);
        body = hitpolygon;

    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.GREEN);
        g2.drawLine(center.x, center.y, center.x, center.y);
        hitpolygon.draw(g2);
        g2.drawPolygon(body);

    }

    
    
}
