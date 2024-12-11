package map;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GameFrame;
import main.UtilityTool;

public class Tile {
    private BufferedImage image;
    private boolean collision;

    public Tile(){
        image = setup("blank");
        collision = false;
    }

    public BufferedImage getSprite() {
        return image;
    }

    public boolean getCollision(){
        return collision;
    }

    public BufferedImage setup(String imagePath){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imagePath + ".png"));
            image = uTool.scaleImage(image, GameFrame.TILE_SIZE, GameFrame.TILE_SIZE);
        
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }
}
