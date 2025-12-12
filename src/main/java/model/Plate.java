import model.KitchenUtensils;

public class Plate extends KitchenUtensils {
    private boolean isDirty;
    
    public Plate() {
        this.name = "Plate";
        this.isDirty = false;
    }
    
    public void makeDirty() { isDirty = true; }
    public void clean() { isDirty = false; contents.clear(); }
    public boolean isDirty() { return isDirty; }
    public boolean isEmpty() { return contents.isEmpty(); }
}