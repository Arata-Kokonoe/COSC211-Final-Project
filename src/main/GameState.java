package main;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import controller.PlayerController;
import core.Size;
import entities.Player;
import entities.enemies.EnemyHandler;
import map.MapGraph;
import map.Room;

public class GameState extends State{
	
    private Player player;

	public boolean settingsChanged;
	public boolean fullscreen;

	private int status;
	public final int TITLE_STATE = 0;
	public final int PLAY_STATE = 1;
	public final int SETTINGS_STATE = 2;

	private MapGraph mapGraph;
	private String currentFloor;

	private EnemyHandler enemyHandler;

	private Random rng;
	//private MouseHandler mouseHandler;
	
	public GameState(long seed) {
		super(new Size(GameFrame.MAX_COL * GameFrame.TILE_SIZE, GameFrame.MAX_ROW * GameFrame.TILE_SIZE), new Input());

		rng = new Random(seed);

		//mouseHandler = new MouseHandler();
		status = PLAY_STATE;

		initializePlayer();
		enemyHandler = new EnemyHandler();

		currentFloor = "start";
		mapGraph = new MapGraph(this);
		mapGraph.makeGraph(currentFloor);

	}
	
	/**
	 * The method which updates the game state.
	 */
	public void update() {
		if(status == PLAY_STATE){
			super.update();
			enemyHandler.update(player);
		}
		else if (status == SETTINGS_STATE){

		}
		//
		// Update the state of all game elements 
		//  based on user input and elapsed time ...
		//
	}	

	public void initializePlayer(){
		Player player = new Player(new PlayerController(input));
        entities.add(player);
        camera.focusOn(player);
	}
	
	public KeyListener getInput() {
		return input;
	}
	/*public MouseListener getMouseListener() {
		return mouseHandler;
	}
	public MouseMotionListener getMouseMotionListener() {
		return mouseHandler;
	}*/

	public Random getRNG(){
		return rng;
	}
	public void setCurrentRoom(Room newRoom){
		currentRoom = newRoom;
		enemyHandler.addRoomEnemies(currentRoom.getEnemyList());
	}
	public int getStatus(){
		return status;
	}



	/**
	 *      if(e.getKeyCode() == KeyEvent.VK_F){
            if (fullscreen == false) fullscreen = true;
            else if (fullscreen == true) fullscreen = false;
            settingsChanged = true;
        } 
	 */

}
