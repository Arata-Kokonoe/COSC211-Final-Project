import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public static final int GAME_HEIGHT = 720;
    public static final int GAME_WIDTH = 16 * GAME_HEIGHT / 9;

    private BufferStrategy bufferStrategy;
	
	public GameFrame(String title) {
		super(title);
		setResizable(false);
		setSize(GAME_WIDTH, GAME_HEIGHT);
        setBackground(Color.BLACK);
		//
		// Initialize the JFrame ...
		//
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
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        state.player.draw(g2d);
	}
}
