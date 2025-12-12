public abstract class KitchenUtensils extends Item {
    protected Set<Preparable> contents;
    
    public KitchenUtensils() {
        this.contents = new HashSet<>();
    }
}