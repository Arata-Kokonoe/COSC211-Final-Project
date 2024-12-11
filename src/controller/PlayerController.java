package controller;

import java.awt.event.KeyEvent;

import main.Input;

public class PlayerController implements Controller{

    private Input input;

    public PlayerController(Input input) {
        this.input = input;
    }

    @Override
    public boolean up() {
        return input.isPressed(KeyEvent.VK_UP);
    }

    @Override
    public boolean down() {
        return input.isPressed(KeyEvent.VK_DOWN);
    }

    @Override
    public boolean left() {
        return input.isPressed(KeyEvent.VK_LEFT);
    }

    @Override
    public boolean right() {
        return input.isPressed(KeyEvent.VK_RIGHT);
    }
}
