package entities.enemies;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;
import java.util.List;

import controller.BlankController;
import controller.ChaseController;
import entities.Entity;
import entities.Hitbox;
import entities.Hitpolygon;
import entities.MovingEntity;
import entities.Player;
import main.AnimationManager;
import main.Camera;
import main.State;
import core.Size;
import core.Steering;
import core.Vector2D;

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
        int speed = 3;
        movement = new Steering(speed);

        animationSheet = setup("/sprites/enemies/triangle");
        this.animationManager = new AnimationManager(this);

        xPoints = new int[NUM_POINTS];
        yPoints = new int[NUM_POINTS];
        xPoints[0] = (int)(position.getX() - size.getWidth()/2);
        yPoints[0] = (int)(position.getY());
        xPoints[1] = (int)(position.getX() + size.getWidth()/2);
        yPoints[1] = (int)(position.getY() - size.getHeight()/2);
        xPoints[2] = (int)(position.getX() + size.getWidth()/2);
        yPoints[2] = (int)(position.getY() + size.getHeight()/2);
    
    }
    @Override
    public void update(State state) {   
        move = true;
        handleMotion();
        angle = (float)(Math.atan2(-((ChaseController)(controller)).fromToVector().getY(), -((ChaseController)(controller)).fromToVector().getX()));

        Rectangle toMove = getHitbox().apply(movement);
        List<Entity> collided = state.getCollidingGameObjects(toMove);
        int i = 0;
        while(i < collided.size()){
            this.handleCollision(collided.get(i));
            i++;
        }
        if(move == true) position.apply(movement);

        xPoints[0] = (int)(position.getX() - size.getWidth()/2);
        yPoints[0] = (int)(position.getY());
        xPoints[1] = (int)(position.getX() + size.getWidth()/2);
        yPoints[1] = (int)(position.getY() - size.getHeight()/2);
        xPoints[2] = (int)(position.getX() + size.getWidth()/2);
        yPoints[2] = (int)(position.getY() + size.getHeight()/2);

    }

    public void draw(Graphics2D g2, Camera camera){
        g2.setColor(Color.GREEN);

        xPoints[0] -= camera.getPosition().intX(); 
        xPoints[1] -= camera.getPosition().intX(); 
        xPoints[2] -= camera.getPosition().intX(); 
        yPoints[0] -= camera.getPosition().intY(); 
        yPoints[1] -= camera.getPosition().intY(); 
        yPoints[2] -= camera.getPosition().intY(); 

        Graphics2D g2temp = (Graphics2D) g2.create();
        AffineTransform at = new AffineTransform();
        at.rotate(angle, position.getX() - camera.getPosition().intX(), position.getY() - camera.getPosition().intY());
        g2temp.setTransform(at);
        g2temp.drawPolygon(xPoints, yPoints, 3);
        // Guide
        g2temp.setColor(Color.RED);
        g2temp.drawLine((int)getPosition().getX(), (int)getPosition().getY(), (int)getPosition().getX() - getSize().getWidth()/2, (int)getPosition().getY());
        g2temp.dispose();

    }
    
}
