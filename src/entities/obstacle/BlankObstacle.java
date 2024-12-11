package entities.obstacle;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entities.Entity;
import entities.Hitbox;
import main.State;
import core.Size;

public class BlankObstacle extends Obstacle{
    
    public boolean collision;
    private BufferedImage sprite;

    public final static int MAX_OBS = 3;
    
    public BlankObstacle(int obsX, int obsY, int obsWidth, int obsHeight){
        super(obsX, obsY, obsWidth, obsHeight);
    }

    public boolean collidesWith(Entity other){
        if(collision == true) return getHitbox().collidesWith(other.getHitbox());
        else return false;
    }

    public void update(State state){
        handleCollisions(state);
    }

    private void handleCollisions(State state) {
        state.getCollidingGameObjects(this).forEach(this::handleCollision);
    }

    protected void handleCollision(Entity other){
        //do nothing
    }

    public Image getSprite(){
        return sprite;
    }

    public Hitbox getHitbox(){
        return new Hitbox(
            new Rectangle(
                position.intX(),
                position.intY(),
                size.getWidth(),
                size.getHeight()
            )
        );
    };

}
