import enums.IngredientState;
import model.*;

public class CookingStation extends Station {
    private Oven oven; // Fixed oven
    
    public CookingStation(Position pos) {
        this.position = pos;
        this.symbol = "R";
        this.oven = new Oven();
        this.oven.setPosition(pos);
    }
    
    @Override
    public boolean isWalkable() { return false; }
    
    @Override
    public void interact(Chef chef) {
        Item heldItem = chef.getInventory();
        
        // Case 1: Put plate with chopped ingredients into oven
        if (heldItem instanceof Plate) {
            Plate plate = (Plate) heldItem;
            if (!plate.isEmpty() && !plate.isDirty()) {
                // Transfer all ingredients to oven
                for (Preparable p : plate.getContents()) {
                    if (oven.canAccept(p)) {
                        oven.addIngredient(p);
                    }
                }
                plate.getContents().clear();
                chef.setInventory(plate); // Return empty plate
                oven.startCooking();
            }
        }
        // Case 2: Take out cooked pizza (back to plate)
        else if (heldItem instanceof Plate && !oven.getContents().isEmpty()) {
            Plate plate = (Plate) heldItem;
            // Check if cooking is done (all COOKED or BURNED)
            boolean allDone = true;
            for (Preparable p : oven.getContents()) {
                if (p.getState() == IngredientState.COOKING || 
                    p.getState() == IngredientState.CHOPPED) {
                    allDone = false;
                    break;
                }
            }
            
            if (allDone) {
                plate.getContents().addAll(oven.getContents());
                oven.getContents().clear();
                chef.setInventory(plate);
            }
        }
    }
    
    public Oven getOven() { return oven; }
}

