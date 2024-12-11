package entities;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import controller.Controller;
import controller.PlayerController;
import core.Position;
import core.Size;
import core.Vector2D;
import entities.enemies.Enemy;
import entities.obstacle.Obstacle;
import main.AnimationManager;
import main.GameFrame;
import main.UtilityTool;

public class Player extends MovingEntity{

    public Player(Controller controller){
        super(controller);
        int width = GameFrame.TILE_SIZE;
        int height = GameFrame.TILE_SIZE*2;
        size = new Size(width, height);
        animationSheet = setup("/sprites/player/playerAnimationSheet");
        this.animationManager = new AnimationManager(this);
    }

    @Override
    protected void handleCollision(Entity other) {
        if (other instanceof Enemy){

        }
        else if (other instanceof Obstacle){
            if (((Obstacle)other).getCollision() == true) move = false;
            else move = true;


            if(( (controller.left() && controller.down()) && 
                ((position.getX() - getHitbox().getHitbox().getWidth()/2) >= other.getPosition().getX()+other.getSize().getWidth()/2)) ||
                 (controller.right() && controller.down()) && 
                 ((position.getX() + getHitbox().getHitbox().getWidth()/2) <= other.getPosition().getX()-other.getSize().getWidth()/2)) {
                    position.applyY((int)movement.getVector().getY());
                    movement.multiplyX(0);
                 }
                 

            else{
                if(controller.left() && ((position.getX() - getHitbox().getHitbox().getWidth()/2) >= other.getPosition().getX()+other.getSize().getWidth()/2)) position.applyY((int)movement.getVector().getY()); 
                if(controller.right() && ((position.getX() + getHitbox().getHitbox().getWidth()/2) <= other.getPosition().getX()-other.getSize().getWidth()/2)) position.applyY((int)movement.getVector().getY()); 
            }
            if(controller.up() && position.getY()+7 >= other.getPosition().getY()+other.getSize().getHeight()/2) position.applyX((int)movement.getVector().getX());
            if(controller.down() && position.getY()+getHitbox().getHitbox().getHeight()/2 <= other.getPosition().getY()-other.getSize().getHeight()/2) position.applyX((int)movement.getVector().getX()); 
            
        }
    }

    @Override
    public Hitbox getHitbox() {
        int hitboxWidth = size.getWidth()/2;
        int hitboxX = position.intX() - hitboxWidth/2;
        int hitboxHeight = (int)(size.getHeight()/2.4);
        int hitboxY = position.intY() + size.getHeight()/2 - hitboxHeight;
        return new Hitbox(
            new Rectangle(
                hitboxX,
                hitboxY,
                hitboxWidth,
                hitboxHeight
            )
        );
    }


    public int getX(){
        return (int)position.getX();
    }

    public int getMiddleX(){
        return (int) position.getX() + size.getWidth()/2;
    }

    public int getRightX(){
        return (int)position.getX() + size.getWidth();
    }

    public int getY(){
        return (int)position.getY();
    }

    public int getMiddleY(){
        return (int)position.getY() + size.getHeight()/2;
    }

    public int getBottomY(){
        return (int)position.getY() + size.getHeight();
    }

}
