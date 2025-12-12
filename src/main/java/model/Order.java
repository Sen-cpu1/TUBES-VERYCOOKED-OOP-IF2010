public class Order {
    private int position; // Order queue position
    private Recipe recipe;
    private int timeLimit; // seconds
    private int remainingTime;
    private Timer timer;
    private OrderManager manager;
    
    public Order(int position, Recipe recipe, int timeLimit, OrderManager manager) {
        this.position = position;
        this.recipe = recipe;
        this.timeLimit = timeLimit;
        this.remainingTime = timeLimit;
        this.manager = manager;
        startTimer();
    }
    
    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                remainingTime--;
                if (remainingTime <= 0) {
                    manager.orderExpired(Order.this);
                    cancel();
                }
            }
        }, 1000, 1000); // Every second
    }
    
    public void cancel() {
        if (timer != null) {
            timer.cancel();
        }
    }
    
    public boolean matches(Dish dish) {
        return recipe.matches(dish);
    }
    
    public int getReward() { return recipe.getReward(); }
    public int getPenalty() { return recipe.getPenalty(); }
    public Recipe getRecipe() { return recipe; }
    public int getRemainingTime() { return remainingTime; }
}
