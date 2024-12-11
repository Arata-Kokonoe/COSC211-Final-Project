package main;

import core.Position;
import core.Size;
import entities.Entity;

//i love you
import java.awt.*;
import java.util.Optional;

public class Camera {

    private static final int SAFETY_SPACE = 2 * GameFrame.TILE_SIZE;

    private Position position;
    private Size windowSize;

    private Rectangle viewBounds;

    private Optional<Entity> objectWithFocus;

    public Camera(Size windowSize) {
        this.position = new Position(0, 0);
        this.windowSize = windowSize;
        calculateViewBounds();
    }

    private void calculateViewBounds() {
        viewBounds = new Rectangle(
                position.intX(),
                position.intY(),
                windowSize.getWidth() + SAFETY_SPACE,
                windowSize.getHeight() + SAFETY_SPACE
        );
    }

    public void focusOn(Entity object) {
        this.objectWithFocus = Optional.of(object);
    }

    public void update(State state) {
        if(objectWithFocus.isPresent()) {
            Position objectPosition = objectWithFocus.get().getPosition();

            this.position.setX(objectPosition.getX() - windowSize.getWidth() / 2);
            this.position.setY(objectPosition.getY() - windowSize.getHeight() / 2);

            clampWithinBounds(state);
            calculateViewBounds();
        }
    }

    private void clampWithinBounds(State state) {
        if(position.getX() < 0) {
            position.setX(0);
        }

        if(position.getY() < 0) {
            position.setY(0);
        }

        if(position.getX() + windowSize.getWidth() > state.getCurrentRoom().getWidth()) {
            position.setX(state.getCurrentRoom().getWidth() - windowSize.getWidth());
        }

        if(position.getY() + windowSize.getHeight() > state.getCurrentRoom().getHeight()) {
            position.setY(state.getCurrentRoom().getHeight() - windowSize.getHeight());
        }
    }

    public Position getPosition() {
        return position;
    }

    public boolean isInView(Entity entity) {
        return viewBounds.intersects(
                entity.getPosition().intX(),
                entity.getPosition().intY(),
                entity.getSize().getWidth(),
                entity.getSize().getHeight());
    }

    public Size getSize() {
        return windowSize;
    }
}