
public class ServingCounter extends Station {
    private OrderManager orderManager;
    private ScoreManager scoreManager;
    
    public ServingCounter(Position pos, OrderManager om, ScoreManager sm) {
        this.position = pos;
        this.symbol = "S";
        this.orderManager = om;
        this.scoreManager = sm;
    }
    
    @Override
    public boolean isWalkable() { return false; }
    
    @Override
    public void interact(Chef chef) {
        Item heldItem = chef.getInventory();
        
        if (heldItem instanceof Plate) {
            Plate plate = (Plate) heldItem;
            if (!plate.isEmpty() && !plate.isDirty()) {
                Dish dish = new Dish(plate);
                Order matchedOrder = orderManager.findMatchingOrder(dish);
                
                if (matchedOrder != null) {
                    // Success
                    scoreManager.addScore(matchedOrder.getReward());
                    orderManager.completeOrder(matchedOrder);
                    
                    // Schedule plate return (10 seconds later)
                    Timer returnTimer = new Timer();
                    returnTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            plate.makeDirty();
                            // Add to plate storage (handled by game loop)
                        }
                    }, 10000);
                } else {
                    // Wrong dish
                    scoreManager.subtractScore(50); // penalty
                    plate.makeDirty();
                }
                
                chef.setInventory(null);
            }
        }
    }
}

