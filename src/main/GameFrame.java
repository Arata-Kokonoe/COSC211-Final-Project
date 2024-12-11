package main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import core.Position;
import map.Room;

public class GameFrame extends JFrame {

	public static final int TILE_SIZE = 48;
	public static final int MAX_COL = 20;
	public static final int MAX_ROW = 12;

    public static final int GAME_HEIGHT = TILE_SIZE * MAX_ROW;
    public static final int GAME_WIDTH = TILE_SIZE * MAX_COL;

	private int screenWidth = GAME_WIDTH;
    private int screenHeight = GAME_HEIGHT;
	private BufferedImage tempScreen;

    private BufferStrategy bufferStrategy;

	private GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    private GraphicsDevice gd = ge.getDefaultScreenDevice();
	
	public GameFrame(String title) {
		super(title);
		setResizable(false);
		setSize(GAME_WIDTH, GAME_HEIGHT);
        setBackground(Color.BLACK);
		//
		// Initialize the JFrame ...
		//
		tempScreen = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
	}
	
	/**
	 * This must be called once after the JFrame is shown:
	 *    frame.setVisible(true);
	 * and before any rendering is started.
	 */
	public void initBufferStrategy() {
		// Triple-buffering
		createBufferStrategy(3);
		bufferStrategy = getBufferStrategy();
	}

	
	/**
	 * Game rendering with triple-buffering using BufferStrategy.
	 */
	public void render(GameState state) {
		// Get a new graphics context to render the current frame
		Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
		try {
			// Do the rendering
			doRendering(graphics, state);
		} finally {
			// Dispose the graphics, because it is no more needed
			graphics.dispose();
		}
		// Display the buffer
		bufferStrategy.show();
		// Tell the system to do the drawing NOW;
		// otherwise it can take a few extra ms and will feel jerky!
		Toolkit.getDefaultToolkit().sync();
	}
	
	/**
	 * Rendering all game elements based on the game state.
	 */
	private void doRendering(Graphics2D g2d, GameState state) {
		//
		// Draw all game elements according 
		//  to the game 'state' using 'g2d' ...
		//
		Graphics2D g2temp = (Graphics2D)tempScreen.getGraphics();

		if(state.settingsChanged){
			if(state.fullscreen == true) setFullscreen();
			else if(state.fullscreen == false) setWindowed();
			state.settingsChanged = false;
		}
	
		//draw background
		g2temp.setColor(Color.WHITE);
		g2temp.fillRect(0, 0, screenWidth, screenHeight);
		
		//draw all elements to
		renderRoom(state, g2temp);
        Camera camera = state.getCamera();
        state.getEntities().stream()
                .filter(entity -> camera.isInView(entity))
                .forEach(entity -> g2temp.drawImage(
                entity.getSprite(),
                entity.getPosition().intX() - camera.getPosition().intX() - entity.getSize().getWidth() / 2,
                entity.getPosition().intY() - camera.getPosition().intY() - entity.getSize().getHeight() / 2,
                null
        		));
		g2temp.setColor(Color.RED);
		state.getEntities().stream()
                .filter(entity -> camera.isInView(entity))
                .forEach(entity -> entity.getHitbox().draw(g2temp)
        		);

		g2temp.dispose();

		//draw to screen buffer
		g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, screenWidth, screenHeight);
		g2d.drawImage(tempScreen, 0, 0, screenWidth, screenHeight, null);
	}

	private void renderRoom(State state, Graphics2D graphics) {
        Room room = state.getCurrentRoom();
        Camera camera = state.getCamera();

        Position start = room.getViewableStartingGridPosition(camera);
        Position end = room.getViewableEndingGridPosition(camera);

        for(int x = start.intX(); x < end.intX(); x++) {
            for(int y = start.intY(); y < end.intY(); y++) {
                graphics.drawImage(
                        room.getTileArr()[x][y].getSprite(),
                        x * TILE_SIZE - camera.getPosition().intX(),
                        y * TILE_SIZE - camera.getPosition().intY(),
                        null
                );
            }
        }
    }

	public void setFullscreen(){
        //GET LOCAL SCREEN DEVICE
        
		setVisible(false);
        dispose();
        setUndecorated(true);
        gd.setFullScreenWindow(this);
        

        //GET FULLSCREEN WIDTH AND HEIGHT
        screenWidth = this.getWidth();
        screenHeight = this.getHeight();
		System.out.println(screenHeight);
    }

	public void setWindowed(){

		dispose();
        setUndecorated(false);
        gd.setFullScreenWindow(null);
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);

		screenWidth = this.getWidth();
        screenHeight = this.getHeight();
		System.out.println(screenHeight);
	}
}
