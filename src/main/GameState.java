package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import entities.Player;
import map.MapGraph;

public class GameState {
	
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    public Player player;

	public MapGraph mapGraph;

	private KeyHandler keyHandler;
	private MouseHandler mouseHandler;
	
	public GameState() {
		//
		// Initialize the game state and all elements ...
		//

        player = new Player();

		keyHandler = new KeyHandler();
		mouseHandler = new MouseHandler();
	}
	
	/**
	 * The method which updates the game state.
	 */
	public void update() {
        player.update(upPressed, downPressed, leftPressed, rightPressed);
		//
		// Update the state of all game elements 
		//  based on user input and elapsed time ...
		//
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
