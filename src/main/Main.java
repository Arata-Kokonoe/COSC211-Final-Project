package main;
import java.awt.EventQueue;
import javax.swing.JFrame;

//Graph backed tower defense or some machine that is connected nodes with edges, something something something


public class Main {
    public static void main(String[] args) {
		// Initialize the global thread-pool
		ThreadPool.init();
		
		// Show the game menu ...
		
		// After the palyer clicks 'PLAY' ...
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				GameFrame frame = new GameFrame("Game Title");
				frame.setLocationRelativeTo(null); // put frame at center of screen
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.initBufferStrategy();
				// Create and execute the game-loop
				GameLoop game = new GameLoop(frame);
				game.init();
				ThreadPool.execute(game);
				// and the game starts ...
			}
		});
    }

}
