package entities.enemies;

import java.awt.Graphics2D;

import controller.BlankController;
import controller.ChaseController;
import controller.Controller;
import core.Position;
import entities.Entity;
import entities.MovingEntity;
import entities.Player;
import entities.obstacle.Obstacle;

public class Enemy extends MovingEntity{

    public Enemy(){
        super(new BlankController());
    }
    
    @Override
    protected void handleCollision(Entity other) {
        if(other != this && other instanceof MovingEntity || other instanceof Obstacle){
            this.movement.stop();
        }

    }

    public void setController(ChaseController controller){
        this.controller = controller;
    }

}
