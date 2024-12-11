package controller;

import core.Position;
import core.Vector2D;
import entities.MovingEntity;
import entities.Player;
import main.Input;

public class ChaseController implements Controller {

    private MovingEntity chaser, chased;

    public ChaseController(MovingEntity chaser, MovingEntity chased) {
        this.chaser = chaser;
        this.chased = chased;
    }

    @Override
    public boolean up() {
        if (chased.getPosition().getY() < chaser.getPosition().getY()) return true;
        else return false;
    }

    @Override
    public boolean down() {
        if (chased.getPosition().getY() > chaser.getPosition().getY()) return true;
        else return false;
    }

    @Override
    public boolean left() {
        if (chased.getPosition().getX() < chaser.getPosition().getX()) return true;
        else return false;
    }

    @Override
    public boolean right() {

        if (chased.getPosition().getX() > chaser.getPosition().getX()) return true;
        else return false;
    }

    public Vector2D fromToVector(){
        double diffX = chased.getPosition().getX() - chaser.getPosition().getX();
        double diffY = chased.getPosition().getY() - chaser.getPosition().getY();

        float angle = (float)Math.atan2(diffY, diffX);

        return new Vector2D(chaser.getSpeed() * Math.cos(angle), chased.getSpeed() * Math.sin(angle));
    }

    public Vector2D toPlayer(int x, int y){
        double diffX = chased.getPosition().getX() - x;
        double diffY = chased.getPosition().getY() - y;

        float angle = (float)Math.atan2(diffY, diffX);

        return new Vector2D(chaser.getSpeed() * Math.cos(angle), chased.getSpeed() * Math.sin(angle));
    }

}
