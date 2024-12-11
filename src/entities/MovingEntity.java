package entities;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import controller.Controller;
import core.Direction;
import core.Motion;
import main.AnimationManager;
import main.State;

public abstract class MovingEntity extends Entity{

    protected Controller controller;
    protected Motion movement;
    protected BufferedImage animationSheet;
    protected AnimationManager animationManager;
    protected int speed;
    protected Direction direction;
    protected boolean move;

    public MovingEntity(Controller controller) {
        super();
        this.controller = controller;
        animationSheet = setup("/sprites/blank");
        this.animationManager = new AnimationManager(this);
        speed = 3;
        this.movement = new Motion(speed);
        this.direction = Direction.S;
    }

    @Override
    public void update(State state) {  

        move = true;
        handleMotion();
        Rectangle toMove = getHitbox().apply(movement);
        List<Entity> collided = state.getCollidingGameObjects(toMove);
        int i = 0;
        while(i < collided.size()){
            this.handleCollision(collided.get(i));
            i++;
        }
        if(move == true) position.apply(movement);
        else{
            
        }

        /*
        moveUp = true; moveDown = true; moveLeft = true; moveRight = true;
        handleMotion();
        animationManager.update(direction);

        handleCollisions(state);
        manageDirection();
        decideAnimation();

        if(moveUp && moveDown && moveLeft && moveRight) position.apply(movement);
        else {
            //if((controller.up() || controller.down()) && (controller.left() || controller.right()));
            if (!moveUp || !moveDown) position.setX(position.getX() + movement.getVector().getX());
            if (!moveLeft || !moveRight) position.setY(position.getY() + movement.getVector().getY());
        }
        */
    }

    protected void handleCollisions(State state) {
        state.getCollidingGameObjects(this).forEach(this::handleCollision);
    }

    protected abstract void handleCollision(Entity other);

    protected void handleMotion() {
        movement.update(controller);
    }

    protected void decideAnimation() {
    }

    protected void manageDirection() {
        if(movement.isMoving()) {
            this.direction = Direction.fromMotion(movement);
        }
    }

    @Override
    public boolean collidesWith(Entity other) {
        if(getHitbox().collidesWith(other.getHitbox())) return true;
        else return false;
    }

    @Override
    public Hitbox getHitbox() {
        return new Hitbox(
            new Rectangle(
                position.intX() - size.getWidth()/2,
                position.intY() - size.getHeight()/2,
                size.getWidth(),
                size.getHeight()
            )
        );
    }

    @Override
    public Image getSprite() {
        return animationManager.getSprite();
    }

    public Controller getController() {
        return controller;
    }

    public BufferedImage getAnimationSheet(){
        return animationSheet;
    }

    public Motion getMotion(){
        return movement;
    }

    public void multiplySpeed(double multiplier) {
        movement.multiply(multiplier);
    }

}
