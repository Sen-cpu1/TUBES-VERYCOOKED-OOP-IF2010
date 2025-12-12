public abstract class Station {
    protected Position position;
    protected String symbol;
    protected Item itemOnStation; // nullable
    
    // Most stations are interactable; subclasses can override if needed
    public boolean canInteract(Chef chef) { return true; }
    public abstract void interact(Chef chef);
    public abstract boolean isWalkable();
    
    public boolean hasItem() { return itemOnStation != null; }
    public Item getItemOnStation() { return itemOnStation; }
    public void setItemOnStation(Item item) { this.itemOnStation = item; }
    public String getSymbol() { return symbol; }
}
