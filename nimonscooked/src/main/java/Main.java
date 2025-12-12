import controller.GameController;
import view.GameView;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Nimons Cooked!");
        
        // Initialize game components
        GameController gameController = new GameController();
        GameView gameView = new GameView();
        
        // Show main menu
        gameView.showMainMenu();
        
        // Initialize and start the game
        gameController.initialize();
        gameController.start();
    }
}
