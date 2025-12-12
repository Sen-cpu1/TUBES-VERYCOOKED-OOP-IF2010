// controller/GameManager.java (Singleton)
import enums.GameState;
import java.util.Timer;
import java.util.TimerTask;
import model.*;

public class GameManager {
    private static GameManager instance;
    private GameState currentState;
    private GameMap map;
    private PlayerController playerController;
    private OrderManager orderManager;
    private ScoreManager scoreManager;
    private Timer gameTimer;
    private int gameDuration = 180; // 3 minutes in seconds
    private int remainingTime;
    
    private GameManager() {
        this.scoreManager = new ScoreManager();
        this.orderManager = new OrderManager(scoreManager);
    }
    
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
    
    public void startNewGame() {
        currentState = GameState.PLAYING;
        map = new GameMap();
        
        // Initialize chefs at spawn points
        Chef chef1 = new Chef("chef1", "Kebin", new Position(8, 3));
        Chef chef2 = new Chef("chef2", "Stewart", new Position(6, 8));
        
        playerController = new PlayerController(chef1, chef2, map);
        map.addChef(chef1);
        map.addChef(chef2);
        
        remainingTime = gameDuration;
        startGameTimer();
    }
    
    private void startGameTimer() {
        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                remainingTime--;
                if (remainingTime <= 0) {
                    endGame();
                    cancel();
                }
            }
        }, 1000, 1000);
    }
    
    public void endGame() {
        currentState = GameState.STAGE_OVER;
        if (gameTimer != null) {
            gameTimer.cancel();
        }
        
        // Cancel all orders
        for (Order order : orderManager.getActiveOrders()) {
            order.cancel();
        }
        
        // Show results
        showResults();
    }
    
    private void showResults() {
        int finalScore = scoreManager.getScore();
        int minPassScore = 300; // Example threshold
        
        boolean passed = finalScore >= minPassScore;
        System.out.println("===== GAME OVER =====");
        System.out.println("Final Score: " + finalScore);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
    }
    
    public void handleInput(String input) {
        if (currentState == GameState.PLAYING) {
            playerController.handleInput(input);
        }
    }
    
    // Getters
    public GameMap getMap() { return map; }
    public PlayerController getPlayerController() { return playerController; }
    public OrderManager getOrderManager() { return orderManager; }
    public ScoreManager getScoreManager() { return scoreManager; }
    public int getRemainingTime() { return remainingTime; }
    public GameState getCurrentState() { return currentState; }
}
