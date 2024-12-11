package main;

import core.Position;
import core.Size;
import entities.Entity;
import map.Room;

import java.util.List;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public abstract class State {

    protected Room currentRoom;
    protected List<Entity> entities;
    protected Input input;
    protected Camera camera;

    public State(Size windowSize, Input input) {
        this.input = input;
        entities = new ArrayList<>();
        camera = new Camera(windowSize);
    }

    public void update() {
        sortObjectsByPosition();
        entities.forEach(entity -> entity.update(this));
        camera.update(this);
    }

    private void sortObjectsByPosition() {
        entities.sort(Comparator.comparing(entity -> entity.getPosition().getY()));
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Camera getCamera() {
        return camera;
    }

    public Position getRandomPosition() {
        return currentRoom.getRandomPosition();
    }

    public List<Entity> getCollidingGameObjects(Entity entity) {
        return entities.stream()
                .filter(other -> other.collidesWith(entity))
                .collect(Collectors.toList());
    }
    public List<Entity> getCollidingGameObjects(Rectangle hitbox) {
        return entities.stream()
                .filter(other -> other.getHitbox().getHitbox().intersects(hitbox))
                .collect(Collectors.toList());
    }
}
