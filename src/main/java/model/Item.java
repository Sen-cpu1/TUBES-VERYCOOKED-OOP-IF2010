public abstract class Item {
    protected String name;
    protected Position position; // null if in inventory
    
    public String getDisplaySymbol() { return name; }
    public boolean isEdible() { return false; }
    public String getName() { return name; }
    public Position getPosition() { return position; }
    public void setPosition(Position position) { this.position = position; }
}
