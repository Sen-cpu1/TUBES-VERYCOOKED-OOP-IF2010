package controller;

/**
 * Main game controller that manages game flow and logic.
 */
public class GameController {
    private boolean isRunning;
    
    public GameController() {
        this.isRunning = false;
    }
    
    /**
     * Initializes the game.
     */
    public void initialize() {
        System.out.println("Initializing game...");
        // TODO: Load maps, initialize entities, etc.
    }
    
    /**
     * Starts the game loop.
     */
    public void start() {
        isRunning = true;
        System.out.println("Game started!");
        // TODO: Implement game loop
    }
    
    /**
     * Stops the game.
     */
    public void stop() {
        isRunning = false;
        System.out.println("Game stopped.");
    }
    
    public boolean isRunning() {
        return isRunning;
    }
}
