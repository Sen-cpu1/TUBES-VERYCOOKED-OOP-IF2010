//(Observer Pattern)
public class OrderManager {
    private Queue<Order> activeOrders;
    private int maxDisplayOrders = 4;
    private int consecutiveFailures = 0;
    private static final int MAX_FAILURES = 5;
    private RecipeDatabase recipeDB;
    private List<OrderObserver> observers;
    private ScoreManager scoreManager;
    
    public OrderManager(ScoreManager sm) {
        this.activeOrders = new LinkedList<>();
        this.recipeDB = RecipeDatabase.getInstance();
        this.observers = new ArrayList<>();
        this.scoreManager = sm;
        generateInitialOrders();
    }
    
    private void generateInitialOrders() {
        for (int i = 0; i < maxDisplayOrders; i++) {
            addNewOrder();
        }
    }
    
    public void addNewOrder() {
        if (activeOrders.size() < maxDisplayOrders) {
            Recipe recipe = recipeDB.getRandomRecipe();
            Order order = new Order(activeOrders.size() + 1, recipe, 60, this);
            activeOrders.offer(order);
            notifyObservers();
        }
    }
    
    public Order findMatchingOrder(Dish dish) {
        for (Order order : activeOrders) {
            if (order.matches(dish)) {
                return order;
            }
        }
        return null;
    }
    
    public void completeOrder(Order order) {
        activeOrders.remove(order);
        order.cancel();
        consecutiveFailures = 0; // Reset failure counter
        addNewOrder(); // Generate new order
        notifyObservers();
    }
    
    public void orderExpired(Order order) {
        activeOrders.remove(order);
        scoreManager.subtractScore(order.getPenalty());
        consecutiveFailures++;
        
        if (consecutiveFailures >= MAX_FAILURES) {
            // Trigger game over
            notifyGameOver();
        } else {
            addNewOrder();
        }
        notifyObservers();
    }
    
    // Observer pattern methods
    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }
    
    private void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.onOrdersUpdated(new ArrayList<>(activeOrders));
        }
    }
    
    private void notifyGameOver() {
        for (OrderObserver observer : observers) {
            observer.onTooManyFailures();
        }
    }
    
    public List<Order> getActiveOrders() {
        return new ArrayList<>(activeOrders);
    }
}