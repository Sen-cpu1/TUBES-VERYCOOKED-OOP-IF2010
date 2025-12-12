// util/IngredientFactory.java (Factory Pattern)
import model.Ingredients.Ingredient;
import model.Ingredients.Pizza.*;

public class IngredientFactory {
    private static IngredientFactory instance;
    
    private IngredientFactory() {}
    
    public static IngredientFactory getInstance() {
        if (instance == null) {
            instance = new IngredientFactory();
        }
        return instance;
    }
    
    public Ingredient createIngredient(String type) {
        switch (type) {
            case "Adonan": return new Adonan();
            case "Tomat": return new Tomat();
            case "Keju": return new Keju();
            case "Sosis": return new Sosis();
            case "Ayam": return new Ayam();
            default: throw new IllegalArgumentException("Unknown ingredient: " + type);
        }
    }
}
