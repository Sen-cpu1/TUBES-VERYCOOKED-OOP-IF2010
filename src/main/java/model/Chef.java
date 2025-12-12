// model/Chef.java
public class Chef {
    private String id;
    private String name;
    private Position position;
    private Direction direction;
    private Item inventory; // Single slot
    private ChefAction currentAction;
    private boolean isActive; // Currently controlled by player
    
    public Chef(String id, String name, Position startPos) {
        this.id = id;
        this.name = name;
        this.position = startPos;
        this.direction = Direction.DOWN;
        this.inventory = null;
        this.currentAction = ChefAction.IDLE;
        this.isActive = false;
    }
    
    // Movement with collision detection
    public boolean move(Direction dir, GameMap map) {
        if (currentAction != ChefAction.IDLE) {
            return false; // Can't move while busy
        }
        
        Position newPos = new Position(
            position.getX() + dir.getDx(),
            position.getY() + dir.getDy()
        );
        
        // Check boundaries
        if (!map.isInBounds(newPos)) {
            return false;
        }
        
        // Check walkability
        if (!map.isWalkable(newPos)) {
            return false;
        }
        
        // Check other chef collision
        if (map.hasChefAt(newPos)) {
            return false;
        }
        
        // Move successful
        position = newPos;
        direction = dir;
        return true;
    }
    
    // Interact with station in front
    public void interact(GameMap map) {
        Position frontPos = new Position(
            position.getX() + direction.getDx(),
            position.getY() + direction.getDy()
        );
        
        Station station = map.getStationAt(frontPos);
        if (station != null) {
            station.interact(this);
        } else {
            // Pick up / Drop on floor
            Item itemOnFloor = map.getItemAt(frontPos);
            if (inventory == null && itemOnFloor != null) {
                inventory = itemOnFloor;
                map.removeItemAt(frontPos);
            } else if (inventory != null && itemOnFloor == null) {
                map.placeItemAt(frontPos, inventory);
                inventory = null;
            }
        }
    }
    
    // Getters/Setters
    public Item getInventory() { return inventory; }
    public void setInventory(Item item) { this.inventory = item; }
    public ChefAction getCurrentAction() { return currentAction; }
    public void setCurrentAction(ChefAction action) { this.currentAction = action; }
    public Position getPosition() { return position; }
    public Direction getDirection() { return direction; }
    public booleanisActive() { return isActive; }
    public void setActive(boolean active) { this.isActive = active; }
}