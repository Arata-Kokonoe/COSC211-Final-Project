package controller;

import core.Position;
import main.Input;

public class ChaseController implements Controller {

    private Position playerPos;

    public ChaseController(Position playerPos) {
        this.playerPos = playerPos;
    }

    @Override
    public boolean up() {
        return true;
    }

    @Override
    public boolean down() {
        return false;
    }

    @Override
    public boolean left() {
        return false;
    }

    @Override
    public boolean right() {
        return false;
    }
}
