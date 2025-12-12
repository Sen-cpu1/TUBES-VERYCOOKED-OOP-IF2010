import enums.IngredientState;
import java.util.HashMap;
import java.util.Map;
import model.Preparable;

public class Recipe {
    private String name;
    private Map<String, IngredientState> requiredIngredients; // <IngredientName, State>
    private int reward;
    private int penalty;
    
    public Recipe(String name, Map<String, IngredientState> ingredients, 
                  int reward, int penalty) {
        this.name = name;
        this.requiredIngredients = ingredients;
        this.reward = reward;
        this.penalty = penalty;
    }
    
    public boolean matches(Dish dish) {
        // Check if dish components match required ingredients
        // Order doesn't matter, just ingredient name + state
        Map<String, IngredientState> dishIngredients = new HashMap<>();
        for (Preparable p : dish.getComponents()) {
            dishIngredients.put(p.getName(), p.getState());
        }
        
        return dishIngredients.equals(requiredIngredients);
    }
}