// controller/ScoreManager.java
public class ScoreManager {
    private int score;
    private int ordersCompleted;
    private int ordersFailed;
    
    public ScoreManager() {
        this.score = 0;
        this.ordersCompleted = 0;
        this.ordersFailed = 0;
    }
    
    public synchronized void addScore(int points) {
        score += points;
        ordersCompleted++;
    }
    
    public synchronized void subtractScore(int points) {
        score = Math.max(0, score - points);
        ordersFailed++;
    }
    
    public int getScore() { return score; }
    public int getOrdersCompleted() { return ordersCompleted; }
    public int getOrdersFailed() { return ordersFailed; }
}
