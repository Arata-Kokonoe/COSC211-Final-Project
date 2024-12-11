package controller;

import main.Input;

public class BlankController implements Controller{

    public BlankController() {
    }

    @Override
    public boolean up() {
        return false;
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
