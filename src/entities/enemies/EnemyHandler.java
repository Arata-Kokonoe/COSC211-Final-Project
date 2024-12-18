package entities.enemies;

import java.awt.Graphics2D;

import entities.Entity;
import entities.Player;
import map.List;
import map.Node;

public class EnemyHandler {

    public List<Enemy> enemies;

    public EnemyHandler(){
        enemies = new List<Enemy>();
    }

    public void addRoomEnemies(List<Enemy> enemyList){
        enemies = enemyList;
    }

    public void update(Player player){
        int i = 0;
        while(i < enemies.size()){ 
            enemies.get(i).getValue().update(player);
            i++;
        }
    }

    public void draw(Graphics2D g2){
        int i = 0;
        while(i < enemies.size()){ 
            enemies.get(i).getValue().draw(g2);
            i++;
        }
    }

}
