package entities;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import controller.Controller;
import controller.PlayerController;
import core.Size;
import entities.enemies.Enemy;
import entities.obstacle.Obstacle;
import main.AnimationManager;
import main.GameFrame;
import main.UtilityTool;

public class Player extends MovingEntity{

    public Player(Controller controller){
        super(controller);
        int width = GameFrame.TILE_SIZE;
        int height = GameFrame.TILE_SIZE*2;
        size = new Size(width, height);
        animationSheet = setup("/sprites/player/playerAnimationSheet");
        this.animationManager = new AnimationManager(this);
    }

    @Override
    protected void handleCollision(Entity other) {
        if(other instanceof Obstacle) {
            movement.multiply(-1);
        }
        else if (other instanceof Enemy){
            movement.multiply(-1);
        }
    }
    /*
    public void update(PlayerController pController, Obstacle[][] roomArr){

        if(pController.up() || pController.down() || pController.left() || pController.right()){
            if((pController.up() && pController.down())){
                //only check left and right pressed
                if(pController.left()){
                    leftPressed(roomArr);
                }

                if(pController.right()){
                    rightPressed(roomArr);
                }
            }
            else if (pController.left() && pController.right()){
                //only check up and down pressed
                if(pController.up()){
                    upPressed(roomArr);
                }
                if(pController.down()){
                    downPressed(roomArr);
                }
            }

            else{
                if(pController.up()){
                    upPressed(roomArr);
                }

                if(pController.down()){
                    downPressed(roomArr);
                }

                if(pController.left()){
                    leftPressed(roomArr);
                }

                if(pController.right()){
                    rightPressed(roomArr);
                }
            }

            
        }

        else {

            }
        }
        
    }

    private void upPressed(Obstacle[][] roomArr){
        boolean u = true;
        direction = "back";
        int tileRow = (hitbox.y - speed) / GameFrame.TILE_SIZE;    //what row is entity trying to move up into?
        Obstacle tile1 = roomArr[hitbox.x/GameFrame.TILE_SIZE][tileRow];    //two possible tiles (since player could be between two tiles)
        Obstacle tile2 = roomArr[(hitbox.x + hitbox.width) / GameFrame.TILE_SIZE][tileRow];
        hitbox.y -= speed;
        if(tile1.checkCollision(hitbox) == true || tile2.checkCollision(hitbox) == true){
            u = false;  //if either tile has checkCollision(this.hitbox) on, change boolean to make entity unable to move into them
        }
        //checkCorner(tile1, tile2, 'x');

        if(u) y -= speed;
        hitbox.update(x, y);
    }

    private void downPressed(Obstacle[][] roomArr){
        boolean d = true;
        direction = "front";
        int tileRow = (hitbox.y + hitbox.height + speed) / GameFrame.TILE_SIZE;    //what row is entity trying to move up into?
        Obstacle tile1 = roomArr[hitbox.x/GameFrame.TILE_SIZE][tileRow];    //two possible tiles (since player could be between two tiles)
        Obstacle tile2 = roomArr[(hitbox.x + hitbox.width) / GameFrame.TILE_SIZE][tileRow];
        hitbox.y += hitbox.height + speed;
        if(tile1.checkCollision(hitbox) == true || tile2.checkCollision(hitbox) == true){
            d = false;  //if either tile has checkCollision(this.hitbox) on, change boolean to make entity unable to move into them
        }
        //checkCorner(tile1, tile2, 'x');

        if(d) y += speed;
        hitbox.update(x, y);
    }

    private void leftPressed(Obstacle[][] roomArr){
        boolean l = true;
        direction = "left";
        int tileCol = (hitbox.x - speed) / GameFrame.TILE_SIZE;    //what row is entity trying to move up into?
        Obstacle tile1 = roomArr[tileCol][hitbox.y / GameFrame.TILE_SIZE];    //three possible tiles (since player is a little taller than 1 tile)
        Obstacle tile2 = roomArr[tileCol][(hitbox.y + hitbox.height/2) / GameFrame.TILE_SIZE]; 
        Obstacle tile3 = roomArr[tileCol][(hitbox.y + hitbox.height) / GameFrame.TILE_SIZE];

        hitbox.x -= speed;
        if(tile1.checkCollision(hitbox) == true || tile2.checkCollision(hitbox) == true || tile3.checkCollision(this.hitbox) == true){
            l = false;  //if either tile has checkCollision(this.hitbox) on, change boolean to make entity unable to move into them
        }
        //checkCorner(tile1, tile2, 'y');

        if(l) x -= speed;
        hitbox.update(x, y);
    }

    public void rightPressed(Obstacle[][] roomArr){
        boolean r = true;
        direction = "right";
        int tileCol = (hitbox.x + hitbox.width + speed) / GameFrame.TILE_SIZE;    //what row is entity trying to move up into?
        Obstacle tile1 = roomArr[tileCol][hitbox.y / GameFrame.TILE_SIZE];    //two possible tiles (since player is a little taller than 1 tile)
        Obstacle tile2 = roomArr[tileCol][(hitbox.y + hitbox.height/2) / GameFrame.TILE_SIZE];
        Obstacle tile3 = roomArr[tileCol][(hitbox.y + hitbox.height) / GameFrame.TILE_SIZE];

        hitbox.x += hitbox.width + speed;
        if(tile1.checkCollision(hitbox) == true || tile2.checkCollision(hitbox) == true || tile3.checkCollision(this.hitbox) == true){
            r = false;  //if either tile has checkCollision(this.hitbox) on, change boolean to make entity unable to move into them
        }
        //checkCorner(tile1, tile2, 'y');
    
        if(r) x += speed;
        hitbox.update(x, y);
    }
    
    
    private void checkCorner(Obstacle tile1, Obstacle tile2, char xOrY){
        boolean shift1 = true, shift2 = true;
        
        if(xOrY == 'x') hitbox.x -= speed*5;
        else if (xOrY == 'y') hitbox.y -= speed*5;
        if(tile1.checkCollision(hitbox) == true || tile2.checkCollision(hitbox) == true){
            shift1 = false;
        }
        if(xOrY == 'x') hitbox.x += speed*10;
        else if (xOrY == 'y') hitbox.y += speed*10;
        if(tile1.checkCollision(hitbox) == true || tile2.checkCollision(hitbox) == true){
            shift2 = false;
        }
        if(shift1) {
            if(xOrY == 'x') x-=1;
            else if (xOrY == 'y') y-=1;
        }
        if(shift2) {
            if(xOrY == 'x') x+=1;
            else if (xOrY == 'y') y+=1;
        }
    }*/

    @Override
    public Hitbox getHitbox() {
        int hitboxWidth = size.getWidth()/2;
        int hitboxX = position.intX() + size.getWidth()/2 - hitboxWidth/2;
        int hitboxHeight = (int)(size.getHeight()/2.4);
        int hitboxY = position.intY() + size.getHeight() - hitboxHeight;
        return new Hitbox(
            new Rectangle(
                hitboxX,
                hitboxY,
                hitboxWidth,
                hitboxHeight
            )
        );
    }


    public int getX(){
        return (int)position.getX();
    }

    public int getMiddleX(){
        return (int) position.getX() + size.getWidth()/2;
    }

    public int getRightX(){
        return (int)position.getX() + size.getWidth();
    }

    public int getY(){
        return (int)position.getY();
    }

    public int getMiddleY(){
        return (int)position.getY() + size.getHeight()/2;
    }

    public int getBottomY(){
        return (int)position.getY() + size.getHeight();
    }

}
