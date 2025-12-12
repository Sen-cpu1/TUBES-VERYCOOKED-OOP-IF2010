// observer/OrderObserver.java (Observer Pattern)
public interface OrderObserver {
    void onOrdersUpdated(List<Order> orders);
    void onTooManyFailures();
}