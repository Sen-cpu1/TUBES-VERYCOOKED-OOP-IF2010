import enums.IngredientState;

public class Tomat extends Ingredient {
    public Tomat() {
        this.name = "Tomat";
        this.state = IngredientState.RAW;
    }
    
    @Override
    public boolean canBeChopped() { return state == IngredientState.RAW; }
    
    @Override
    public boolean canBeCooked() { return false; }
    
    @Override
    public boolean canBePlacedOnPlate() { return state == IngredientState.CHOPPED; }
}