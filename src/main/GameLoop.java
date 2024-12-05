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
    public void init() {
        // Perform all initializations ...
        state = new GameState();
        canvas.addKeyListener(state.getKeyListener());
        canvas.addMouseListener(state.getMouseListener());
        canvas.addMouseMotionListener(state.getMouseMotionListener());
    }

    @Override
    public void run() {
        /*boolean gameOver = false;
        while (!gameOver) {
            try {
                long start = System.currentTimeMillis();
                //
                state.update();
                canvas.render(state);
                //
                long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                if (delay > 0)
                    Thread.sleep(delay);
            } catch (InterruptedException ex) {
            }
        }*/
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