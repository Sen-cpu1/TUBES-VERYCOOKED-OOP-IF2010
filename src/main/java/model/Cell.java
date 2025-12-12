
public class Cell {

    private Position position;
    private CellType type;
    private Station station; // nullable
    private Item itemOnFloor; // nullable
    
    public Cell(Position pos, CellType type) {
        this.position = pos;
        this.type = type;
    }
    
    public boolean isWalkable() {
        return type == CellType.WALKABLE;
    }
    
    // Getters/Setters
}

