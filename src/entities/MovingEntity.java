package entities;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

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
        handleMotion();
        animationManager.update(direction);

        handleCollisions(state);
        manageDirection();
        decideAnimation();

        position.apply(movement);
    }

    private void handleCollisions(State state) {
        state.getCollidingGameObjects(this).forEach(this::handleCollision);
    }

    protected abstract void handleCollision(Entity other);

    private void handleMotion() {
        movement.update(controller);
    }

    private void decideAnimation() {
    }

    private void manageDirection() {
        if(movement.isMoving()) {
            this.direction = Direction.fromMotion(movement);
        }
    }

    @Override
    public boolean collidesWith(Entity other) {
        return getHitbox().collidesWith(other.getHitbox());
    }

    @Override
    public Hitbox getHitbox() {
        return new Hitbox(
            new Rectangle(
                position.intX(),
                position.intY(),
                size.getWidth(),
                size.getHeight()
            )
        );
    }

    @Override
    public Image getSprite() {
        System.out.println(this);
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
