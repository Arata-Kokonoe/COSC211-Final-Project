package entities;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import entities.obstacle.Obstacle;
import main.GameFrame;
import main.UtilityTool;

public class Player extends Entity{

    private BufferedImage back1, back2, back3, front1, front2, front3, left1, left2, left3, right1, right2, right3, current;
    private int spriteCounter, standCounter, spriteNum;
    private String direction;

    public Player(){
        super();
        width = GameFrame.TILE_SIZE;
        height = GameFrame.TILE_SIZE*2;
        hitbox = new Hitbox(x, y, width, height, width/2, (int)(height/1.5));
        getPlayerImage();
        direction = "front";
    }

    public void getPlayerImage(){

        back1 = setup("back1");
        back2 = setup("back2");
        back3 = setup("back3");
        front1 = setup("front1");
        front2 = setup("front2");
        front3 = setup("front3");
        left1 = setup("left1");
        left2 = setup("left2");
        left3 = setup("left3");
        right1 = setup("right1");
        right2 = setup("right2");
        right3 = setup("right3");

    }

    public BufferedImage setup(String imagePath){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/player/" + imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }


    public void draw(Graphics2D g2d){

        switch (spriteNum) {
            case 1:
                switch (direction) {
                    case "front":
                        current = front1;
                        break;
                    case "back":
                        current = back1;
                        break;
                    case "left":
                        current = left1;
                        break;
                    case "right":
                        current = right1;
                        break;
                }
                break;
            case 2:
                switch (direction) {
                    case "front":
                        current = front2;
                        break;
                    case "back":
                        current = back2;
                        break;
                    case "left":
                        current = left2;
                        break;
                    case "right":
                        current = right2;
                        break;
                }
                break;
            case 3:
                switch (direction) {
                    case "front":
                        current = front1;
                        break;
                    case "back":
                        current = back1;
                        break;
                    case "left":
                        current = left1;
                        break;
                    case "right":
                        current = right1;
                        break;
                }
                break;
            case 4:
                switch (direction) {
                    case "front":
                        current = front3;
                        break;
                    case "back":
                        current = back3;
                        break;
                    case "left":
                        current = left3;
                        break;
                    case "right":
                        current = right3;
                        break;
                }
                break;
        }

        g2d.drawImage(current, x, y, width, height, null);
        hitbox.draw(g2d);
    }

    public void update(boolean u, boolean d, boolean l, boolean r, Obstacle[][] roomArr){

        int tileRow, tileCol;
        Obstacle tile1, tile2, tile3;

        if(u || d || l || r){
            if(u){
                direction = "back";
                tileRow = (hitbox.y - speed) / GameFrame.TILE_SIZE;    //what row is entity trying to move up into?
                tile1 = roomArr[hitbox.x/GameFrame.TILE_SIZE][tileRow];    //two possible tiles (since player could be between two tiles)
                tile2 = roomArr[(hitbox.x + hitbox.width) / GameFrame.TILE_SIZE][tileRow];
                if(tile1.collision == true || tile2.collision == true){
                    u = false;  //if either tile has collision on, change boolean to make entity unable to move into them
                }
            }
            if(d){
                direction = "front";
                tileRow = (hitbox.y + hitbox.height + speed) / GameFrame.TILE_SIZE;    //what row is entity trying to move up into?
                tile1 = roomArr[hitbox.x/GameFrame.TILE_SIZE][tileRow];    //two possible tiles (since player could be between two tiles)
                tile2 = roomArr[(hitbox.x + hitbox.width) / GameFrame.TILE_SIZE][tileRow];
                if(tile1.collision == true || tile2.collision == true){
                    d = false;  //if either tile has collision on, change boolean to make entity unable to move into them
                }
            }
            if(l){
                direction = "left";
                tileCol = (hitbox.x - speed) / GameFrame.TILE_SIZE;    //what row is entity trying to move up into?
                tile1 = roomArr[tileCol][hitbox.y / GameFrame.TILE_SIZE];    //three possible tiles (since player is a little taller than 1 tile)
                tile2 = roomArr[tileCol][(hitbox.y + hitbox.height) / GameFrame.TILE_SIZE];
                tile3 = roomArr[tileCol][(hitbox.y + hitbox.height/2) / GameFrame.TILE_SIZE]; 
                if(tile1.collision == true || tile2.collision == true || tile3.collision == true){
                    l = false;  //if either tile has collision on, change boolean to make entity unable to move into them
                }
            }
            if(r){
                direction = "right";
                tileCol = (hitbox.x + hitbox.width + speed) / GameFrame.TILE_SIZE;    //what row is entity trying to move up into?
                tile1 = roomArr[tileCol][hitbox.y / GameFrame.TILE_SIZE];    //two possible tiles (since player is a little taller than 1 tile)
                tile2 = roomArr[tileCol][(hitbox.y + hitbox.height) / GameFrame.TILE_SIZE];
                tile3 = roomArr[tileCol][(hitbox.y + hitbox.height/2) / GameFrame.TILE_SIZE]; 
                if(tile1.collision == true || tile2.collision == true || tile3.collision == true){
                    r = false;  //if either tile has collision on, change boolean to make entity unable to move into them
                }
            }
    
            if(u) y -= speed;
            if(d) y += speed;
            if(l) x -= speed;
            if(r) x += speed;
    
            hitbox.update(x, y);

            spriteCounter++;
            if(spriteCounter > 24){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2){
                    spriteNum = 3;
                }
                else if (spriteNum == 3){
                    spriteNum = 4;
                }
                else if (spriteNum == 4){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        else {
            standCounter++;
            if(standCounter == 20){
                spriteNum = 1;
                standCounter = 0;
            }
        }
        
    }
}
