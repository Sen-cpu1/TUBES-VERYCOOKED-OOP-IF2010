// view/CLIView.java
public class CLIView {
    private GameManager gameManager;
    
    public CLIView(GameManager gm) {
        this.gameManager = gm;
    }
    
    public void render() {
        clearScreen();
        renderMap();
        renderOrders();
        renderScore();
        renderTimer();
        renderChefStatus();
    }
    
    private void renderMap() {
        GameMap map = gameManager.getMap();
        Chef chef1 = gameManager.getPlayerController().getChef1();
        Chef chef2 = gameManager.getPlayerController().getChef2();
        
        System.out.println("===== PIZZA KITCHEN =====");
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 14; x++) {
                Position pos = new Position(x, y);
                
                // Check if chef is here
                if (chef1.getPosition().equals(pos)) {
                    System.out.print(chef1.isActive() ? "1*" : "1 ");
                } else if (chef2.getPosition().equals(pos)) {
                    System.out.print(chef2.isActive() ? "2*" : "2 ");
                } else {
                    // Render cell
                    Cell cell = map.getCell(pos);
                    if (cell.getStation() != null) {
                        System.out.print(cell.getStation().getSymbol() + " ");
                    } else if (cell.getType() == CellType.WALL) {
                        System.out.print("██");
                    } else {
                        System.out.print("· ");
                    }
                }
            }
            System.out.println();
        }
    }
    
    private void renderOrders() {
        System.out.println("\n===== ORDERS =====");
        List<Order> orders = gameManager.getOrderManager().getActiveOrders();
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.printf("%d. %s (%ds) [%d pts]\n", 
                i+1, 
                order.getRecipe().getName(),
                order.getRemainingTime(),
                order.getReward());
        }
    }
    
    private void renderScore() {
        ScoreManager sm = gameManager.getScoreManager();
        System.out.println("\n===== SCORE =====");
        System.out.printf("Score: %d | Completed: %d | Failed: %d\n",
            sm.getScore(), sm.getOrdersCompleted(), sm.getOrdersFailed());
    }
    
    private void renderTimer() {
        int time = gameManager.getRemainingTime();
        System.out.printf("\nTime Remaining: %d:%02d\n", time/60, time%60);
    }
    
    private void renderChefStatus() {
        Chef active = gameManager.getPlayerController().getActiveChef();
        System.out.println("\n===== CHEF STATUS =====");
        System.out.printf("Active: %s | Action: %s\n", 
            active.getName(), active.getCurrentAction());
        System.out.printf("Inventory: %s\n", 
            active.getInventory() != null ? active.getInventory().getName() : "Empty");
        System.out.println("\nControls: WASD=Move | C/V=Interact | B=Switch Chef");
    }
    
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

