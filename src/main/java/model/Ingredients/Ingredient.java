import enums.IngredientState;

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

    @Override
    public IngredientState getState() { return state; }

    public void setState(IngredientState state) { this.state = state; }

    @Override
    public String getName() { return name; }

    @Override
    public boolean isEdible() { return state == IngredientState.COOKED; }
}