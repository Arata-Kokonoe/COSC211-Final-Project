package entities.obstacle;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entities.Entity;
import entities.Hitbox;
import main.State;
import core.Size;

public abstract class Obstacle extends Entity{
    
    public boolean collision;
    private BufferedImage sprite;

    public final static int MAX_OBS = 3;
    
    public Obstacle(int obsX, int obsY, int obsWidth, int obsHeight){
        position.setX(obsX);
        position.setY(obsY);
        size = new Size(obsWidth, obsHeight);
        collision = false;
        sprite = setup("/sprites/obstacles/blank");
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

    protected abstract void handleCollision(Entity other);

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
