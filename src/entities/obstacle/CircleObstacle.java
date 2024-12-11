package entities.obstacle;

import entities.Entity;
import entities.Hitcircle;
import entities.MovingEntity;

public class CircleObstacle extends Obstacle{
    Hitcircle hitcircle;
    int radius;

    public CircleObstacle(int obsX, int obsY, int obsWidth, int obsHeight, int rad){
        super(obsX, obsY, obsWidth, obsHeight);
        radius = rad;
        collision = true;
        hitcircle = new Hitcircle(obsX, obsY, obsWidth, obsHeight, rad);
    }

    public boolean collidesWith(Entity other){
        if(collision == true) return hitcircle.checkCollision(other.getHitbox());
        else return false;
    }

    @Override
    protected void handleCollision(Entity other){
        if(other instanceof MovingEntity) {
            MovingEntity movingEntity = (MovingEntity)other;
            movingEntity.getMotion().stop();
        }
    } 

}
