package view;

/**
 * Command-line interface for the game.
 */
public class GameView {
    
    /**
     * Displays the main menu.
     */
    public void showMainMenu() {
        System.out.println("=== Nimons Cooked ===");
        System.out.println("1. Start Game");
        System.out.println("2. Instructions");
        System.out.println("3. Exit");
        System.out.println("=====================");
    }
    
    /**
     * Displays the game board.
     */
    public void displayGameBoard() {
        // TODO: Implement game board display
        System.out.println("Displaying game board...");
    }
    
    /**
     * Shows a message to the player.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
