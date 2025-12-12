

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

