package entities.enemies;

import java.awt.Graphics2D;

import controller.ChaseController;
import core.Position;
import entities.Entity;
import entities.MovingEntity;
import entities.Player;
import entities.obstacle.Obstacle;

public class Enemy extends MovingEntity{

    public Enemy(){
        super(new ChaseController(new Position(0, 0)));
    }

    public void update(Player player){
        
    }

    @Override
    protected void handleCollision(Entity other) {
        if(other instanceof Obstacle) {
            movement.multiply(-1);
        }
        else if (other instanceof Enemy){
            movement.multiply(-1);
        }
    }

}
