public abstract class Station {
    protected Position position;
    protected String symbol;
    protected Item itemOnStation; // nullable
    
    public abstract boolean canInteract(Chef chef);
    public abstract void interact(Chef chef);
    public abstract boolean isWalkable();
    
    public boolean hasItem() { return itemOnStation != null; }
    public Item getItemOnStation() { return itemOnStation; }
    public void setItemOnStation(Item item) { this.itemOnStation = item; }
}
