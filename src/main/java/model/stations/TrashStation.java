
public class TrashStation extends Station {
    public TrashStation(Position pos) {
        this.position = pos;
        this.symbol = "T";
    }
    
    @Override
    public boolean isWalkable() { return false; }
    
    @Override
    public void interact(Chef chef) {
        Item heldItem = chef.getInventory();
        
        if (heldItem != null) {
            // If kitchen utensils, only remove contents
            if (heldItem instanceof KitchenUtensils) {
                KitchenUtensils utensil = (KitchenUtensils) heldItem;
                utensil.getContents().clear();
                // Keep the utensil in inventory
            } else {
                // Remove ingredient completely
                chef.setInventory(null);
            }
        }
    }
}