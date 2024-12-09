package entities.enemies;

import java.awt.Graphics2D;

import entities.Entity;
import entities.Player;

public class Enemy extends Entity{

    public Enemy(){
        super();
    }

    public void update(Player player){
        
        int diffX = player.getX() - x;
        int diffY = player.getY() - y;

        float angle = (float)Math.atan2(diffY, diffX);

        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);
    }

    public void draw(Graphics2D g2){
        g2.fillRect(x, y, width, height);
        g2.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }
}
