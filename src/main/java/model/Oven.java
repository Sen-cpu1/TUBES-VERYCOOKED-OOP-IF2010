public class Oven extends KitchenUtensils implements CookingDevice {
    private Position ovenPosition; // Fixed position
    private boolean isCooking;
    private Timer cookingTimer;
    
    @Override
    public boolean isPortable() { return false; }
    
    @Override
    public int capacity() { return 10; } // Pizza bisa punya banyak ingredient
    
    @Override
    public boolean canAccept(Preparable ingredient) {
        // Hanya terima ingredient yang sudah CHOPPED
        return ingredient.getState() == IngredientState.CHOPPED;
    }
    
    @Override
    public void startCooking() {
        if (!contents.isEmpty() && !isCooking) {
            isCooking = true;
            // Start 24-second timer (12s COOKED, 12s more BURNED)
            cookingTimer = new Timer();
            cookingTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    transitionToBurned();
                }
            }, 24000); // 24 seconds
            
            // Schedule COOKED at 12s
            cookingTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    transitionToCooked();
                }
            }, 12000); // 12 seconds
        }
    }
    
    private void transitionToCooked() {
        for (Preparable item : contents) {
            if (item instanceof Ingredient) {
                ((Ingredient) item).setState(IngredientState.COOKED);
            }
        }
        // Log or notify
    }
    
    private void transitionToBurned() {
        for (Preparable item : contents) {
            if (item instanceof Ingredient) {
                ((Ingredient) item).setState(IngredientState.BURNED);
            }
        }
        isCooking = false;
        // Log or notify
    }
}