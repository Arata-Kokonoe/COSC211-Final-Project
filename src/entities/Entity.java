package entities;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.nio.file.attribute.PosixFileAttributeView;

import javax.imageio.ImageIO;

import core.Position;
import core.Size;
import main.Camera;
import main.GameFrame;
import main.State;
import main.UtilityTool;

public abstract class Entity {

    protected Position position;
    protected Size size;

    public Entity(){
        //default values
        position = new Position(50, 50);
        size = new Size(GameFrame.TILE_SIZE, GameFrame.TILE_SIZE);
    }

    public abstract void update(State state);
    public abstract void draw(Graphics2D g2, Camera camera);
    public abstract Image getSprite();
    public abstract Hitbox getHitbox();
    public abstract boolean collidesWith(Entity other);

    public BufferedImage setup(String imagePath){

        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res" + imagePath + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }

    //Getters
        public Position getPosition() {
            return position;
        }

        public Size getSize() {
            return size;
        }

    //Setters
        public void setPosition(Position position) {
            this.position = position;
        }
    
}
