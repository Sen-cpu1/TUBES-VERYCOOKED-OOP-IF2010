
import model.*;
import model.Ingredients.Ingredient;
import model.Ingredients.Pizza.*;

public class AssemblyStation extends Station {
    public AssemblyStation(Position pos) {
        this.position = pos;
        this.symbol = "A";
    }
    
    @Override
    public boolean isWalkable() { return false; }
    
    @Override
    public void interact(Chef chef) {
        Item heldItem = chef.getInventory();
        
        // Drop item
        if (heldItem != null && itemOnStation == null) {
            itemOnStation = heldItem;
            chef.setInventory(null);
        }
        // Pick up item
        else if (heldItem == null && itemOnStation != null) {
            chef.setInventory(itemOnStation);
            itemOnStation = null;
        }
        // Plating: plate in hand + ingredient on station
        else if (heldItem instanceof Plate && itemOnStation instanceof Ingredient) {
            Plate plate = (Plate) heldItem;
            Ingredient ing = (Ingredient) itemOnStation;
            if (ing.canBePlacedOnPlate() && !plate.isDirty()) {
                plate.getContents().add(ing);
                itemOnStation = plate;
                chef.setInventory(null);
            }
        }
    }
}

