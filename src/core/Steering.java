package core;

import controller.ChaseController;
import controller.Controller;

public class Steering extends Motion {

    public Steering(double speed) {
        super(speed);
    }

    public void update(ChaseController controller) {
        vector = controller.fromToVector();
        vector.normalize();
        vector.multiply(speed);
    }

    public Vector2D getVector() {
        return vector;
    }
    
    public boolean isMoving() {
        return vector.length() > 0;
    }

    public void multiply(double multiplier) {
        vector.multiply(multiplier);
    }

    public void stop() {
        vector = new Vector2D(0, 0);
    }
}
