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

import controller.BlankController;
import controller.ChaseController;
import entities.Entity;
import entities.Hitbox;
import entities.Hitpolygon;
import entities.MovingEntity;
import entities.Player;
import main.AnimationManager;
import main.State;
import core.Size;
import core.Steering;

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
    
    }
    @Override
    public void update(State state) {   
        handleMotion();
        animationManager.update(direction.S);

        handleCollisions(state);
        manageDirection();
        decideAnimation();

        position.apply(movement);
    }
    
}
