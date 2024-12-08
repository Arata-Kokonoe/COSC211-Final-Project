package entities;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GameFrame;
import main.UtilityTool;

public class Entity {

    int x, y, width, height, speed;
    Hitbox hitbox;

    public Entity(){
        //default values
        x = 50;
        y = 50;
        width = 48;
        height = 96;
        speed = 3;

        hitbox = new Hitbox(x, y, width, height);
    }

    public BufferedImage setup(String imagePath){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res" + imagePath + ".png"));
            image = uTool.scaleImage(image, GameFrame.TILE_SIZE, GameFrame.TILE_SIZE);
        
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }

    public void draw(Graphics2D g2){

    }

    public void update(){

    }
}
