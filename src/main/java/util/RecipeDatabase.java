public class RecipeDatabase {
    private static RecipeDatabase instance;
    private List<Recipe> pizzaRecipes;
    
    private RecipeDatabase() {
        initializePizzaRecipes();
    }
    
    public static RecipeDatabase getInstance() {
        if (instance == null) {
            instance = new RecipeDatabase();
        }
        return instance;
    }
    
    private void initializePizzaRecipes() {
        pizzaRecipes = new ArrayList<>();
        
        // Pizza Margherita
        Map<String, IngredientState> margherita = new HashMap<>();
        margherita.put("Adonan", IngredientState.COOKED);
        margherita.put("Tomat", IngredientState.COOKED);
        margherita.put("Keju", IngredientState.COOKED);
        pizzaRecipes.add(new Recipe("Pizza Margherita", margherita, 120, 50));
        
        // Pizza Sosis
        Map<String, IngredientState> sosis = new HashMap<>();
        sosis.put("Adonan", IngredientState.COOKED);
        sosis.put("Tomat", IngredientState.COOKED);
        sosis.put("Keju", IngredientState.COOKED);
        sosis.put("Sosis", IngredientState.COOKED);
        pizzaRecipes.add(new Recipe("Pizza Sosis", sosis, 150, 60));
        
        // Pizza Ayam
        Map<String, IngredientState> ayam = new HashMap<>();
        ayam.put("Adonan", IngredientState.COOKED);
        ayam.put("Tomat", IngredientState.COOKED);
        ayam.put("Keju", IngredientState.COOKED);
        ayam.put("Ayam", IngredientState.COOKED);
        pizzaRecipes.add(new Recipe("Pizza Ayam", ayam, 150, 60));
    }
    
    public List<Recipe> getAllRecipes() { return pizzaRecipes; }
    public Recipe getRandomRecipe() {
        return pizzaRecipes.get(new Random().nextInt(pizzaRecipes.size()));
    }
}