package core;

import controller.Controller;

public class Motion {

    protected Vector2D vector;
    protected double speed;

    public Motion(double speed) {
        this.speed = speed;
        this.vector = new Vector2D(0, 0);
    }

    public void update(Controller controller) {
        int deltaX = 0;
        int deltaY = 0;

        if(controller.up()) {
            deltaY--;
        }

        if(controller.down()) {
            deltaY++;
        }

        if(controller.left()) {
            deltaX--;
        }

        if(controller.right()) {
            deltaX++;
        }

        vector = new Vector2D(deltaX, deltaY);
        vector.normalize();
        vector.multiply(speed);

    }

    public Vector2D getVector() {
        return vector;
    }

    public void setVector(Vector2D newVector){
        vector = newVector;
    }

    public boolean isMoving() {
        return vector.length() > 0;
    }

    public void multiply(double multiplier) {
        vector.multiply(multiplier);
    }

    public void multiplyX(double multiplier) {
        vector.multiplyX(multiplier);
    }

    public void multiplyY(double multiplier) {
        vector.multiplyY(multiplier);
    }

    public void stop() {
        vector = new Vector2D(0, 0);
    }
}
