package entities.enemies;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import entities.Player;

public class TriangleEnemy extends Enemy{

    int[] xPoints, yPoints;
    final int NUM_POINTS = 4;

    public TriangleEnemy(int x, int y, int width, int height){
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        xPoints = new int[NUM_POINTS];
        yPoints = new int[NUM_POINTS];
    }

    public void update(Player player){
        
        int diffX = player.getMiddleX() - x;
        int diffY = player.getMiddleY() - y;

        float angle = (float)Math.atan2(diffY, diffX);

        hitbox.x += speed * Math.cos(angle);
        //if(hitbox.checkCollision(player.getHitbox()));
        //else x += speed * Math.cos(angle);
        hitbox.y += speed * Math.sin(angle);
        if(hitbox.checkCollision(player.getHitbox()));
        else {
            x += speed * Math.cos(angle);
            y += speed * Math.sin(angle);
        }
        hitbox.update(x, y);

    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.GREEN);
        g2.drawRect(x, y, width, height);
    }

    
    
}
