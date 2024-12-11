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
        return input.isPressed(KeyEvent.VK_W);
    }

    @Override
    public boolean down() {
        return input.isPressed(KeyEvent.VK_S);
    }

    @Override
    public boolean left() {
        return input.isPressed(KeyEvent.VK_A);
    }

    @Override
    public boolean right() {
        return input.isPressed(KeyEvent.VK_D);
    }
}
