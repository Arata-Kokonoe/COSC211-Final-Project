package entities;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

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
import main.GameState;
import main.State;
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
    public void update(State state) {  
        super.update(state);

        if(position.getX() < 0){
            System.out.println("too far left");
            if(state.getCurrentRoom().left != null) ((GameState)(state)).setCurrentRoom(state.getCurrentRoom().left);
            else movement.multiplyX(-1);
        }
        else if (position.getX() > GameFrame.GAME_WIDTH){
            if(state.getCurrentRoom().right != null) ((GameState)(state)).setCurrentRoom(state.getCurrentRoom().right);
            else movement.multiplyX(-1);
        }
        else if (position.getY() < 0){
            if(state.getCurrentRoom().up != null) {
                ((GameState)(state)).setCurrentRoom(state.getCurrentRoom().up);
            }
            else movement.multiplyY(-1);
        }
        else if (position.getY() > GameFrame.GAME_HEIGHT){
            if(state.getCurrentRoom().down != null) ((GameState)(state)).setCurrentRoom(state.getCurrentRoom().down);
            else movement.multiplyY(-1);
        }
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
                 ((position.getX() + getHitbox().getHitbox().getWidth()/2) - 10<= other.getPosition().getX()-other.getSize().getWidth()/2)) {
                    position.applyY((int)movement.getVector().getY());
                    movement.multiplyX(-1);
                 }
                 

            else{
                if(controller.left() && ((position.getX() - getHitbox().getHitbox().getWidth()/2) >= other.getPosition().getX()+other.getSize().getWidth()/2)) position.applyY((int)movement.getVector().getY()); 
                if(controller.right() && ((position.getX() + getHitbox().getHitbox().getWidth()/2) - 10 <= other.getPosition().getX()-other.getSize().getWidth()/2)) position.applyY((int)movement.getVector().getY()); 
            }
            if(controller.up() && position.getY()+10 >= other.getPosition().getY()+other.getSize().getHeight()/2) position.applyX((int)movement.getVector().getX());
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
