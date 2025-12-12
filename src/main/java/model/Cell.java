
import enums.CellType;

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

    public Position getPosition() { return position; }
    public CellType getType() { return type; }
    public void setType(CellType type) { this.type = type; }
    public Station getStation() { return station; }
    public void setStation(Station station) { this.station = station; }
    public Item getItemOnFloor() { return itemOnFloor; }
    public void setItemOnFloor(Item itemOnFloor) { this.itemOnFloor = itemOnFloor; }
}

