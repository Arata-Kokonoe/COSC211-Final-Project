package main;
public class GameLoop implements Runnable {
	
    /**
     * Frame Per Second.
     * Higher is better, but any value above 24 is fine.
     */
    public static final int FPS = 60;
    
    private GameFrame canvas;
    private GameState state;

    public GameLoop(GameFrame frame) {
        canvas = frame;
    }
    
    /**
     * This must be called before the game loop starts.
     */
    public void init(long seed) {
        // Perform all initializations ...
        state = new GameState(seed);
        canvas.addKeyListener(state.getInput());
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(this != null){
            
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                state.update();
                canvas.render(state);
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
}