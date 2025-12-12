public abstract class Item {
    protected String name;
    protected Position position; // null if in inventory
    
    public abstract String getDisplaySymbol();
    public abstract boolean isEdible();
}
