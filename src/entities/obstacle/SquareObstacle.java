package entities.obstacle;

import entities.Entity;
import entities.MovingEntity;

public class SquareObstacle extends Obstacle{

    public SquareObstacle(int obsX, int obsY, int obsWidth, int obsHeight){
        super(obsX, obsY, obsWidth, obsHeight);
        collision = true;
        sprite = setup("/sprites/obstacles/square");
    }

    @Override
    protected void handleCollision(Entity other){
        if(other instanceof MovingEntity) {
            MovingEntity movingEntity = (MovingEntity)other;
            movingEntity.getMotion().stop();
        }
    } 

}
