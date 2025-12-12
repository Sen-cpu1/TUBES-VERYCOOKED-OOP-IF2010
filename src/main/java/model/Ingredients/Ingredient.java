public abstract class Ingredient extends Item implements Preparable {
    protected IngredientState state;
    protected int choppingProgress; // 0-100
    protected int cookingTime; // in seconds
    
    @Override
    public void chop() {
        if (canBeChopped()) {
            state = IngredientState.CHOPPED;
        }
    }
    
    @Override
    public void cook() {
        state = IngredientState.COOKING;
        // Timer akan handle transisi ke COOKED/BURNED
    }
}