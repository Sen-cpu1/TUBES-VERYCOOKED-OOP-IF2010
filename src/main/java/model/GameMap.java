

public class GameMap {
    private static final int WIDTH = 14;
    private static final int HEIGHT = 10;
    private Cell[][] grid;
    private List<Chef> chefs;
    private PlateStorage plateStorage;
    
    public GameMap() {
        grid = new Cell[HEIGHT][WIDTH];
        chefs = new ArrayList<>();
        initializePizzaMap();
    }
    
    private void initializePizzaMap() {
        // Parse map layout from spec
        String[] mapLayout = {
            "XATACAAAACAAXX",
            "X..........XX",
            "X.....A.V..SX",
            "X..........SX",
            "XWWAIAIAIAAPX",
            "X..........X",
            "XXXX..A...XXX",
            "XR....V....RX",
            "XXXX......XXX",
            "XXXXAAIAAAAXX"
        };
        
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                char c = mapLayout[y].charAt(x);
                Position pos = new Position(x, y);
                
                switch (c) {
                    case 'X':
                        grid[y][x] = new Cell(pos, CellType.WALL);
                        break;
                    case '.':
                        grid[y][x] = new Cell(pos, CellType.WALKABLE);
                        break;
                    case 'V':
                        grid[y][x] = new Cell(pos, CellType.WALKABLE);
                        // Chef spawn points handled separately
                        break;
                    case 'C':
                        grid[y][x] = new Cell(pos, CellType.STATION);
                        grid[y][x].setStation(new CuttingStation(pos));
                        break;
                    case 'R':
                        grid[y][x] = new Cell(pos, CellType.STATION);
                        grid[y][x].setStation(new CookingStation(pos));
                        break;
                    case 'A':
                        grid[y][x] = new Cell(pos, CellType.STATION);
                        grid[y][x].setStation(new AssemblyStation(pos));
                        break;
                    case 'W':
                        grid[y][x] = new Cell(pos, CellType.STATION);
                        grid[y][x].setStation(new WashingStation(pos));
                        break;
                    case 'S':
                        grid[y][x] = new Cell(pos, CellType.STATION);
                        // Serving counter needs OrderManager & ScoreManager
                        // Will be set later
                        break;
                    case 'I':
                        grid[y][x] = new Cell(pos, CellType.STATION);
                        // Determine ingredient type based on position
                        String ingredientType = determineIngredientType(pos);
                        grid[y][x].setStation(new IngredientStorage(pos, ingredientType));
                        break;
                    case 'P':
                        grid[y][x] = new Cell(pos, CellType.STATION);
                        plateStorage = new PlateStorage(pos, 3); // 3 initial plates
                        grid[y][x].setStation(plateStorage);
                        break;
                    case 'T':
                        grid[y][x] = new Cell(pos, CellType.STATION);
                        grid[y][x].setStation(new TrashStation(pos));
                        break;
                }
            }
        }
    }
    
    private String determineIngredientType(Position pos) {
        // Based on map layout, assign ingredient types to storage positions
        // Map has 4 I positions for: Adonan, Tomat, Keju, Sosis, Ayam
        // You can hardcode based on x,y coordinates
        if (pos.getX() == 8 && pos.getY() == 1) return "Adonan";
        if (pos.getX() == 4 && pos.getY() == 5) return "Tomat";
        if (pos.getX() == 6 && pos.getY() == 5) return "Keju";
        if (pos.getX() == 8 && pos.getY() == 5) return "Sosis";
        if (pos.getX() == 10 && pos.getY() == 5) return "Ayam";
        if (pos.getX() == 7 && pos.getY() == 10) return "Tomat"; // Extra
        return "Adonan"; // Default
    }
    
    public boolean isInBounds(Position pos) {
        return pos.getX() >= 0 && pos.getX() < WIDTH &&
               pos.getY() >= 0 && pos.getY() < HEIGHT;
    }
    
    public boolean isWalkable(Position pos) {
        return grid[pos.getY()][pos.getX()].isWalkable();
    }
    
    public Station getStationAt(Position pos) {
        return grid[pos.getY()][pos.getX()].getStation();
    }
    
    public boolean hasChefAt(Position pos) {
        for (Chef chef : chefs) {
            if (chef.getPosition().equals(pos)) {
                return true;
            }
        }
        return false;
    }
    
    // Other helper methods...
}








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

// Main.java
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
````

---

### **Phase 8: Factory Pattern (Hari 13)**
````java
// util/IngredientFactory.java (Factory Pattern)
public class IngredientFactory {
    private static IngredientFactory instance;
    
    private IngredientFactory() {}
    
    public static IngredientFactory getInstance() {
        if (instance == null) {
            instance = new IngredientFactory();
        }
        return instance;
    }
    
    public Ingredient createIngredient(String type) {
        switch (type) {
            case "Adonan": return new Adonan();
            case "Tomat": return new Tomat();
            case "Keju": return new Keju();
            case "Sosis": return new Sosis();
            case "Ayam": return new Ayam();
            default: throw new IllegalArgumentException("Unknown ingredient: " + type);
        }
    }
}

// util/StationFactory.java (Factory Pattern)
public class StationFactory {
    public static Station createStation(String type, Position pos) {
        switch (type) {
            case "C": return new CuttingStation(pos);
            case "R": return new CookingStation(pos);
            case "A": return new AssemblyStation(pos);
            case "W": return new WashingStation(pos);
            case "S": return new ServingCounter(pos, null, null); // Set managers later
            case "T": return new TrashStation(pos);
            case "P": return new PlateStorage(pos, 3);
            default: throw new IllegalArgumentException("Unknown station: " + type);
        }
    }
}
````

---

### **Phase 9: Testing (Hari 14)**
````java
// Test scenarios:
1. Test ingredient state transitions (RAW → CHOPPED → COOKING → COOKED → BURNED)
2. Test plating mechanism (multiple ingredients on plate)
3. Test recipe matching (order matters or not)
4. Test oven cooking timer (12s COOKED, 24s BURNED)
5. Test chef switching during busy state
6. Test plate storage (1 by 1 taking)
7. Test washing station queue
8. Test order expiration
9. Test concurrent actions (both chefs working)
10. Test collision detection
````

---

### **Phase 10: Documentation & Buklet (Hari 14-15)**
````markdown
# README.md

## Compilation
```bash
./gradlew build
```

## Running
```bash
./gradlew run
```

## Controls
- WASD: Move active chef
- C/V: Interact with station/item
- B: Switch between chefs

## Gameplay
1. Take ingredients from storage (I)
2. Chop ingredients at cutting station (C)
3. Assemble chopped ingredients on plate
4. Put plate into oven (R)
5. Wait 12 seconds for pizza to cook
6. Take out cooked pizza
7. Serve at counter (S)
8. Wash dirty plates (W)
````

---

## **TIMELINE CHECKLIST**

| Day | Tasks | Status |
|-----|-------|--------|
| 1-2 | Setup, Architecture, Enums, Interfaces | ⬜ |
| 2-4 | Item System, Ingredients, Utensils, Recipes | ⬜ |
| 4-6 | Station System (all 9 stations) | ⬜ |
| 6-8 | Chef, PlayerController | ⬜ |
| 8-9 | GameMap (Pizza layout) | ⬜ |
| 9-10 | Order System, OrderManager | ⬜ |
| 10-12 | GameManager, ScoreManager, Timers | ⬜ |
| 12-13 | CLI View & Game Loop | ⬜ |
| 13 | Factory Patterns | ⬜ |
| 14 | Testing & Bug Fixes | ⬜ |
| 14-15 | Documentation & Buklet | ⬜ |

---

## **CRITICAL NOTES FOR PIZZA MAP**

1. **Oven is NOT portable** - tetap di cooking station position
2. **Pizza workflow**: Chop all ingredients → Assemble on plate → Transfer to oven → Wait → Take out → Serve
3. **Cooking timer**: 12s → COOKED, 24s → BURNED (dari awal mulai masak)
4. **Recipe validation**: Ingredient order tidak penting, hanya name + state
5. **Plate storage**: Ambil 1 per 1 (clean atau dirty)
6. **Assembly**: Ingredient ditambahkan bertahap ke plate

Apakah ada bagian yang perlu saya detail lebih lanjut? Atau ada pertanyaan tentang implementasi tertentu?