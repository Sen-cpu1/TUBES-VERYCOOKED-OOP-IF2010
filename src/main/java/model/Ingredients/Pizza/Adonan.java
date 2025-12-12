import enums.IngredientState;

public class Adonan extends Ingredient {
    public Adonan() {
        this.name = "Adonan";
        this.state = IngredientState.RAW;
    }
    
    @Override
    public boolean canBeChopped() { return state == IngredientState.RAW; }
    
    @Override
    public boolean canBeCooked() { return false; } // Dimasak saat sudah di pizza
    
    @Override
    public boolean canBePlacedOnPlate() { return state == IngredientState.CHOPPED; }
}
