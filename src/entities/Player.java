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
        hitbox = new Hitbox(x, y, width, height, width/2, (int)(height/2.4));
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
        Hitbox tempHitbox;
        boolean shiftLeft = true, shiftRight = true, shiftUp = true, shiftDown = true;

        if(u || d || l || r){
            if(u){
                direction = "back";
                tileRow = (hitbox.y - speed) / GameFrame.TILE_SIZE;    //what row is entity trying to move up into?
                tile1 = roomArr[hitbox.x/GameFrame.TILE_SIZE][tileRow];    //two possible tiles (since player could be between two tiles)
                tile2 = roomArr[(hitbox.x + hitbox.width) / GameFrame.TILE_SIZE][tileRow];
                tempHitbox = new Hitbox(hitbox.x, hitbox.y - speed, hitbox.width, hitbox.height);
                if(tile1.checkCollision(tempHitbox) == true || tile2.checkCollision(tempHitbox) == true){
                    u = false;  //if either tile has checkCollision(this.hitbox) on, change boolean to make entity unable to move into them
                }

                tempHitbox.x -= speed*5;
                if(tile1.checkCollision(tempHitbox) == true || tile2.checkCollision(tempHitbox) == true){
                    shiftLeft = false;
                }
                tempHitbox.x += speed*10;
                if(tile1.checkCollision(tempHitbox) == true || tile2.checkCollision(tempHitbox) == true){
                    shiftRight = false;
                }

                if(u) y -= speed;
                if(shiftLeft) {
                    x -= 1;
                }
                if(shiftRight) {
                    x += 1;
                }
            }

            if(d){
                direction = "front";
                tileRow = (hitbox.y + hitbox.height + speed) / GameFrame.TILE_SIZE;    //what row is entity trying to move up into?
                tile1 = roomArr[hitbox.x/GameFrame.TILE_SIZE][tileRow];    //two possible tiles (since player could be between two tiles)
                tile2 = roomArr[(hitbox.x + hitbox.width) / GameFrame.TILE_SIZE][tileRow];
                tempHitbox = new Hitbox(hitbox.x, hitbox.y + hitbox.height + speed, hitbox.width, hitbox.height);
                if(tile1.checkCollision(tempHitbox) == true || tile2.checkCollision(tempHitbox) == true){
                    d = false;  //if either tile has checkCollision(this.hitbox) on, change boolean to make entity unable to move into them
                }

                tempHitbox.x -= speed*5;
                if(tile1.checkCollision(tempHitbox) == true || tile2.checkCollision(tempHitbox) == true){
                    shiftLeft = false;
                }
                tempHitbox.x += speed*10;
                if(tile1.checkCollision(tempHitbox) == true || tile2.checkCollision(tempHitbox) == true){
                    shiftRight = false;
                }

                if(d) y += speed;
                if(shiftLeft) {
                    x -= 1;
                }
                if(shiftRight) {
                    x += 1;
                }
            }

            if(l){
                direction = "left";
                tileCol = (hitbox.x - speed) / GameFrame.TILE_SIZE;    //what row is entity trying to move up into?
                tile1 = roomArr[tileCol][hitbox.y / GameFrame.TILE_SIZE];    //three possible tiles (since player is a little taller than 1 tile)
                tile2 = roomArr[tileCol][(hitbox.y + hitbox.height) / GameFrame.TILE_SIZE];
                //tile3 = roomArr[tileCol][(hitbox.y + hitbox.height/2) / GameFrame.TILE_SIZE]; 
                tempHitbox = new Hitbox(hitbox.x - speed, hitbox.y, hitbox.width, hitbox.height);
                if(tile1.checkCollision(tempHitbox) == true || tile2.checkCollision(tempHitbox) == true /*|| tile3.checkCollision(this.hitbox) == true*/){
                    l = false;  //if either tile has checkCollision(this.hitbox) on, change boolean to make entity unable to move into them
                }

                tempHitbox.y -= speed*5;
                if(tile1.checkCollision(tempHitbox) == true || tile2.checkCollision(tempHitbox) == true){
                    shiftUp = false;
                }
                tempHitbox.y += speed*10;
                if(tile1.checkCollision(tempHitbox) == true || tile2.checkCollision(tempHitbox) == true){
                    shiftDown = false;
                }

                if(l) x -= speed;
                if(shiftUp) {
                    y -= 1;
                }
                if(shiftDown) {
                    y += 1;
                }

            }

            if(r){
                direction = "right";
                tileCol = (hitbox.x + hitbox.width + speed) / GameFrame.TILE_SIZE;    //what row is entity trying to move up into?
                tile1 = roomArr[tileCol][hitbox.y / GameFrame.TILE_SIZE];    //two possible tiles (since player is a little taller than 1 tile)
                tile2 = roomArr[tileCol][(hitbox.y + hitbox.height) / GameFrame.TILE_SIZE];
                //tile3 = roomArr[tileCol][(hitbox.y + hitbox.height/2) / GameFrame.TILE_SIZE];
                tempHitbox = new Hitbox(hitbox.x + hitbox.width + speed, hitbox.y, hitbox.width, hitbox.height); 
                if(tile1.checkCollision(tempHitbox) == true || tile2.checkCollision(tempHitbox) == true /*|| tile3.checkCollision(this.hitbox) == true*/){
                    r = false;  //if either tile has checkCollision(this.hitbox) on, change boolean to make entity unable to move into them
                }

                tempHitbox.y -= speed*5;
                if(tile1.checkCollision(tempHitbox) == true || tile2.checkCollision(tempHitbox) == true){
                    shiftUp = false;
                }
                tempHitbox.y += speed*10;
                if(tile1.checkCollision(tempHitbox) == true || tile2.checkCollision(tempHitbox) == true){
                    shiftDown = false;
                }

                if(r) x += speed;
                if(shiftUp) {
                    y -= 1;
                }
                if(shiftDown) {
                    y += 1;
                }

            }
    
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
                direction = "front";
                standCounter = 0;
            }
        }
        
    }

    public int getX(){
        return x;
    }

    public int getMiddleX(){
        return x + width/2;
    }

    public int getRightX(){
        return x + width;
    }

    public int getY(){
        return y;
    }

    public int getMiddleY(){
        return y + height/2;
    }

    public int getBottomY(){
        return y + height;
    }

    public Hitbox getHitbox(){
        return hitbox;
    }
}
