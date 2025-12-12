
public class PlateStorage extends Station {
    private Stack<Plate> plates; // Clean plates at bottom, dirty on top
    
    public PlateStorage(Position pos, int initialCleanPlates) {
        this.position = pos;
        this.symbol = "P";
        this.plates = new Stack<>();
        
        // Initialize with clean plates
        for (int i = 0; i < initialCleanPlates; i++) {
            plates.push(new Plate());
        }
    }
    
    @Override
    public boolean isWalkable() { return false; }
    
    @Override
    public void interact(Chef chef) {
        if (chef.getInventory() == null && !plates.isEmpty()) {
            // Take one plate (top of stack)
            Plate plate = plates.pop();
            chef.setInventory(plate);
        }
        // Cannot drop items here
    }
    
    public void addDirtyPlate(Plate plate) {
        plate.makeDirty();
        plates.push(plate);
    }
    
    public boolean hasCleanPlate() {
        return !plates.isEmpty() && !plates.peek().isDirty();
    }
}

