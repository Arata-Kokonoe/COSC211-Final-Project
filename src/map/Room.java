package map;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import entities.enemies.Enemy;
import entities.enemies.TriangleEnemy;
import entities.obstacle.CircleObstacle;
import entities.obstacle.Obstacle;
import entities.obstacle.SquareObstacle;
import main.GameFrame;

public class Room{

    public static int MAX_ROOMS = 2;

    private Enemy[][] enemyArr;
    private Obstacle[][] roomArr;
    private List<Obstacle> obsList;
    private List<Enemy> enemyList;

    public Room(int type){
        roomArr = new Obstacle[GameFrame.MAX_COL][GameFrame.MAX_ROW];
        enemyArr = new Enemy[GameFrame.MAX_COL][GameFrame.MAX_ROW];
        obsList = new List<Obstacle>();
        enemyList = new List<Enemy>();

        if (type == 0){
            setupRoom(0);
            setupEnemies(0);
        }
        else if (type == 1){
            setupRoom(1);
            setupEnemies(1);
        }

    }

    public void setupRoom(int roomNum){
        try {
            InputStream is = getClass().getResourceAsStream("/res/rooms/" + roomNum + "r");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;

            for (int i = 0; i < row; i++){
                br.readLine();
            }

            while(col < GameFrame.MAX_COL && row < GameFrame.MAX_ROW){
                
                String line = br.readLine();
                
                while (col < GameFrame.MAX_COL) {
                    
                    String numbers[] = line.split(",");

                    int obsNum = Integer.parseInt(numbers[col]);

                    obsSwitch(col, row, obsNum);
                    
                    col++;
                }

                if (col == GameFrame.MAX_COL) {
                    col = 0;
                    row++;
                }

            }

        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupEnemies(int roomNum){
        try {
            InputStream is = getClass().getResourceAsStream("/res/rooms/roomEnemies/" + roomNum + "e");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;

            for (int i = 0; i < row; i++){
                br.readLine();
            }

            while(col < GameFrame.MAX_COL && row < GameFrame.MAX_ROW){
                
                String line = br.readLine();
                
                while (col < GameFrame.MAX_COL) {
                    
                    String numbers[] = line.split(",");

                    int enemyNum = Integer.parseInt(numbers[col]);

                    enemySwitch(col, row, enemyNum);
                    
                    col++;
                }

                if (col == GameFrame.MAX_COL) {
                    col = 0;
                    row++;
                }

            }

        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void obsSwitch(int col, int row, int obsNum){
        switch (obsNum) {
            case 0:
                roomArr[col][row] = new Obstacle(col * GameFrame.TILE_SIZE, row * GameFrame.TILE_SIZE, GameFrame.TILE_SIZE, GameFrame.TILE_SIZE);
                break;
            case 1:
                roomArr[col][row] = new SquareObstacle(col * GameFrame.TILE_SIZE, row * GameFrame.TILE_SIZE, GameFrame.TILE_SIZE, GameFrame.TILE_SIZE);
                obsList.add(roomArr[col][row]);
                break;
            case 2:
                roomArr[col][row] = new CircleObstacle(col * GameFrame.TILE_SIZE, row * GameFrame.TILE_SIZE, GameFrame.TILE_SIZE, GameFrame.TILE_SIZE, GameFrame.TILE_SIZE/2);
                obsList.add(roomArr[col][row]);
            default:
                break;
        }
    }

    private void enemySwitch(int col, int row, int enemyNum){
        switch(enemyNum){
            case 0:
                break;
            case 1:
                enemyArr[col][row] = new TriangleEnemy(col * GameFrame.TILE_SIZE, row * GameFrame.TILE_SIZE, GameFrame.TILE_SIZE, GameFrame.TILE_SIZE);
                enemyList.add(enemyArr[col][row]);
                break;
            default:
                break;
        }
    }

    public void draw(Graphics2D g2){
        
        for(int i = 0; i < obsList.size(); i++){
            obsList.get(i).getValue().draw(g2);
        }

    }

    public List<Obstacle> getObsList(){
        return obsList;
    }

    public List<Enemy> getEnemyList(){
        return enemyList;
    }

    public Obstacle[][] getRoomArr(){
        return roomArr;
    }

}
