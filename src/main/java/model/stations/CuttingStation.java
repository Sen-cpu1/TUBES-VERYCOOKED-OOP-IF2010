import enums.ChefAction;
import enums.IngredientState;
import java.util.Timer;
import java.util.TimerTask;
import model.*;

public class CuttingStation extends Station {
    private Chef currentChef; // null if not in use
    private int cuttingProgress; // 0-100
    private Timer progressTimer;
    private static final int CUTTING_DURATION = 3000; // 3 seconds
    
    public CuttingStation(Position pos) {
        this.position = pos;
        this.symbol = "C";
        this.cuttingProgress = 0;
    }
    
    @Override
    public boolean isWalkable() { return false; }
    
    @Override
    public void interact(Chef chef) {
        Item heldItem = chef.getInventory();
        
        // Case 1: Chef holding ingredient → Start cutting
        if (heldItem instanceof Ingredient && itemOnStation == null) {
            Ingredient ing = (Ingredient) heldItem;
            if (ing.canBeChopped() && ing.getState() == IngredientState.RAW) {
                itemOnStation = ing;
                chef.setInventory(null);
                startCutting(chef);
            }
        }
        // Case 2: Pick up finished ingredient
        else if (heldItem == null && itemOnStation != null) {
            Ingredient ing = (Ingredient) itemOnStation;
            if (ing.getState() == IngredientState.CHOPPED) {
                chef.setInventory(itemOnStation);
                itemOnStation = null;
                cuttingProgress = 0;
            }
        }
        // Case 3: Plating on cutting station (works like assembly)
        else if (heldItem instanceof Plate && itemOnStation instanceof Ingredient) {
            // Plating logic
            Plate plate = (Plate) heldItem;
            Ingredient ing = (Ingredient) itemOnStation;
            if (ing.canBePlacedOnPlate() && !plate.isDirty()) {
                plate.getContents().add(ing);
                itemOnStation = plate;
                chef.setInventory(null);
            }
        }
    }
    
    private void startCutting(Chef chef) {
        currentChef = chef;
        chef.setCurrentAction(ChefAction.CUTTING);
        
        progressTimer = new Timer();
        progressTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                cuttingProgress += 10; // 10% per 300ms
                if (cuttingProgress >= 100) {
                    finishCutting();
                    cancel();
                }
            }
        }, 0, 300); // Update every 300ms for 3 seconds
    }
    
    private void finishCutting() {
        if (itemOnStation instanceof Ingredient) {
            ((Ingredient) itemOnStation).chop();
        }
        if (currentChef != null) {
            currentChef.setCurrentAction(ChefAction.IDLE);
        }
        currentChef = null;
        cuttingProgress = 0;
    }
    
    public void stopCutting() {
        if (progressTimer != null) {
            progressTimer.cancel();
        }
        if (currentChef != null) {
            currentChef.setCurrentAction(ChefAction.IDLE);
        }
        currentChef = null;
    }
}
