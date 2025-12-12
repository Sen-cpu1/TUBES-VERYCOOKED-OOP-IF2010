
public class IngredientStorage extends Station {
    private String ingredientType; // "Adonan", "Tomat", etc.
    private IngredientFactory factory;
    
    public IngredientStorage(Position pos, String type) {
        this.position = pos;
        this.symbol = "I";
        this.ingredientType = type;
        this.factory = IngredientFactory.getInstance();
    }
    
    @Override
    public boolean isWalkable() { return false; }
    
    @Override
    public void interact(Chef chef) {
        // Case 1: Take ingredient from storage
        if (chef.getInventory() == null && itemOnStation == null) {
            Ingredient newIngredient = factory.createIngredient(ingredientType);
            chef.setInventory(newIngredient);
        }
        // Case 2: Pick up item on top of storage
        else if (chef.getInventory() == null && itemOnStation != null) {
            chef.setInventory(itemOnStation);
            itemOnStation = null;
        }
        // Case 3: Drop item on storage (works like assembly)
        else if (chef.getInventory() != null && itemOnStation == null) {
            itemOnStation = chef.getInventory();
            chef.setInventory(null);
        }
        // Case 4: Plating
        else if (chef.getInventory() instanceof Plate && itemOnStation instanceof Ingredient) {
            Plate plate = (Plate) chef.getInventory();
            Ingredient ing = (Ingredient) itemOnStation;
            if (ing.canBePlacedOnPlate() && !plate.isDirty()) {
                plate.getContents().add(ing);
                itemOnStation = plate;
                chef.setInventory(null);
            }
        }
    }
}

