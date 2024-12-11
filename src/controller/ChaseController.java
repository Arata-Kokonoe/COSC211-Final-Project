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
        return new Vector2D(chased.getPosition().getX() - chaser.getPosition().getX(),
                            chased.getPosition().getY() - chaser.getPosition().getY());
    }

}
