package main;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import entities.Player;
import map.MapGraph;
import map.Room;

public class GameState {
	
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private Player player;

	public boolean settingsChanged;
	public boolean fullscreen;

	private int state;
	public final int TITLE_STATE = 0;
	public final int PLAY_STATE = 1;
	public final int SETTINGS_STATE = 2;

	private MapGraph mapGraph;
	private Room currentRoom;
	private String currentFloor;

	private Random rng;

	private KeyHandler keyHandler;
	private MouseHandler mouseHandler;
	
	public GameState(long seed) {
		//
		// Initialize the game state and all elements ...
		//

		rng = new Random(seed);

        player = new Player();

		keyHandler = new KeyHandler();
		mouseHandler = new MouseHandler();
		state = PLAY_STATE;

		currentFloor = "start";
		mapGraph = new MapGraph(this);
		mapGraph.makeGraph(currentFloor);
	}
	
	/**
	 * The method which updates the game state.
	 */
	public void update() {
		if(state == PLAY_STATE){
			player.update(upPressed, downPressed, leftPressed, rightPressed, currentRoom.roomArr);
		}
		else if (state == SETTINGS_STATE){

		}
		//
		// Update the state of all game elements 
		//  based on user input and elapsed time ...
		//
	}	

	public void draw(Graphics2D g2){
		player.draw(g2);
		currentRoom.draw(g2);
	}
	
	public KeyListener getKeyListener() {
		return keyHandler;
	}
	public MouseListener getMouseListener() {
		return mouseHandler;
	}
	public MouseMotionListener getMouseMotionListener() {
		return mouseHandler;
	}

	public Random getRNG(){
		return rng;
	}
	public void setCurrentRoom(Room newRoom){
		currentRoom = newRoom;
	}
	public int getState(){
		return state;
	}



	/**
	 * The keyboard handler.
	 */
	class KeyHandler implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
        
		}

		@Override
		public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_W) upPressed = true;
            if(e.getKeyCode() == KeyEvent.VK_S) downPressed = true;
            if(e.getKeyCode() == KeyEvent.VK_A) leftPressed = true;
            if(e.getKeyCode() == KeyEvent.VK_D) rightPressed = true;

			if(e.getKeyCode() == KeyEvent.VK_F){
				if (fullscreen == false) fullscreen = true;
				else if (fullscreen == true) fullscreen = false;
				settingsChanged = true;
			} 
		}

		@Override
		public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_W) upPressed = false;
            if(e.getKeyCode() == KeyEvent.VK_S) downPressed = false;
            if(e.getKeyCode() == KeyEvent.VK_A) leftPressed = false;
            if(e.getKeyCode() == KeyEvent.VK_D) rightPressed = false;
		}

	}

	/**
	 * The mouse handler.
	 */
	class MouseHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}
	}
}
