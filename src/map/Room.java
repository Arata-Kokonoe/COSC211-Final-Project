package map;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import core.Position;
import entities.enemies.Enemy;
import entities.enemies.TriangleEnemy;
import entities.obstacle.BlankObstacle;
import entities.obstacle.CircleObstacle;
import entities.obstacle.Obstacle;
import entities.obstacle.SquareObstacle;
import main.Camera;
import main.GameFrame;

public class Room{

    public final static int MAX_ROOMS = 2;
    public final int BUFFER = 2;

    private Tile[][] tileArr;
    private Obstacle[][] obsArr;
    private Enemy[][] enemyArr;
    
    private List<Obstacle> obsList;
    private List<Enemy> enemyList;

    public Room(int type){
        tileArr = new Tile[GameFrame.MAX_COL][GameFrame.MAX_ROW];
        obsArr = new Obstacle[GameFrame.MAX_COL][GameFrame.MAX_ROW];
        enemyArr = new Enemy[GameFrame.MAX_COL][GameFrame.MAX_ROW];
        obsList = new List<Obstacle>();
        enemyList = new List<Enemy>();

        setupTiles(type);
        setupObstacles(type);
        setupEnemies(type);

    }

    public void setupTiles(int roomNum){
        try {
            InputStream is = getClass().getResourceAsStream("/res/rooms/roomTiles/" + roomNum + "t");
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

                    int num = Integer.parseInt(numbers[col]);

                    tileSwitch(col, row, num);
                    
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

    public void setupObstacles(int roomNum){
        try {
            InputStream is = getClass().getResourceAsStream("/res/rooms/roomObstacles/" + roomNum + "o");
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

                    int num = Integer.parseInt(numbers[col]);

                    obstacleSwitch(col, row, num);
                    
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

                    int num = Integer.parseInt(numbers[col]);

                    enemySwitch(col, row, num);
                    
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



    private void obstacleSwitch(int col, int row, int obsNum){
        switch (obsNum) {
            case 0:
                obsArr[col][row] = new BlankObstacle((col) * GameFrame.TILE_SIZE, (row) * GameFrame.TILE_SIZE, GameFrame.TILE_SIZE, GameFrame.TILE_SIZE);
                break;
            case 1:
                obsArr[col][row] = new SquareObstacle((col) * GameFrame.TILE_SIZE, (row) * GameFrame.TILE_SIZE, GameFrame.TILE_SIZE, GameFrame.TILE_SIZE);
                obsList.add(obsArr[col][row]);
                break;
            case 2:
                obsArr[col][row] = new CircleObstacle((col) * GameFrame.TILE_SIZE, (row) * GameFrame.TILE_SIZE, GameFrame.TILE_SIZE, GameFrame.TILE_SIZE, GameFrame.TILE_SIZE/2);
                obsList.add(obsArr[col][row]);
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

    private void tileSwitch(int col, int row, int tileNum){
        switch(tileNum){
            case 0:
                tileArr[col][row] = new Tile();
                break;
            default:
                break;
        }
    }

    public Position getRandomPosition() {
        double x = Math.random() * tileArr.length * GameFrame.TILE_SIZE;
        double y = Math.random() * tileArr[0].length * GameFrame.TILE_SIZE;

        return new Position(x, y);
    }

    public Position getViewableStartingGridPosition(Camera camera) {
        return new Position(
                Math.max(0, camera.getPosition().getX() / GameFrame.TILE_SIZE - BUFFER),
                Math.max(0, camera.getPosition().getY() / GameFrame.TILE_SIZE - BUFFER)
        );
    }

    public Position getViewableEndingGridPosition(Camera camera) {
        return new Position(
                Math.min(tileArr.length, camera.getPosition().getX() / GameFrame.TILE_SIZE + camera.getSize().getWidth() / GameFrame.TILE_SIZE + BUFFER),
                Math.min(tileArr[0].length, camera.getPosition().getY() / GameFrame.TILE_SIZE + camera.getSize().getHeight() / GameFrame.TILE_SIZE + BUFFER)
        );
    }

    //Getters
    public Obstacle[][] getObsArr(){
        return obsArr;
    }
    public Tile[][] getTileArr(){
        return tileArr;
    }
    public Enemy[][] getEnemyArr(){
        return enemyArr;
    }
    public List<Obstacle> getObsList(){
        return obsList;
    }
    public List<Enemy> getEnemyList(){
        return enemyList;
    }
    public int getWidth(){
        return tileArr.length * GameFrame.TILE_SIZE;
    }
    public int getHeight(){
        return tileArr[0].length * GameFrame.TILE_SIZE;
    }

    

}
