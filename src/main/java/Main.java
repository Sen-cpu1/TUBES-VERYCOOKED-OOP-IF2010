// Main.java
import controller.GameManager;
import enums.GameState;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import view.CLIView;

public class Main {
    public static void main(String[] args) {
        GameManager gm = GameManager.getInstance();
        CLIView view = new CLIView(gm);
        Scanner scanner = new Scanner(System.in);
        
        // Show main menu
        System.out.println("===== NIMONSCOOKED =====");
        System.out.println("1. Start Game");
        System.out.println("2. How to Play");
        System.out.println("3. Exit");
        
        String choice = scanner.nextLine();
        
        if (choice.equals("1")) {
            gm.startNewGame();
            
            // Game loop
            Timer renderTimer = new Timer();
            renderTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    view.render();
                }
            }, 0, 100); // Render every 100ms
            
            // Input loop
            while (gm.getCurrentState() == GameState.PLAYING) {
                if (scanner.hasNextLine()) {
                    String input = scanner.nextLine();
                    gm.handleInput(input);
                }
            }
            
            renderTimer.cancel();
        }
        
        scanner.close();
    }
}