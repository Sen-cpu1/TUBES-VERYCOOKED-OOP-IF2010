import java.util.HashSet;
import java.util.Set;

public abstract class KitchenUtensils extends Item {
    protected Set<Preparable> contents;
    
    public KitchenUtensils() {
        this.contents = new HashSet<>();
    }

    public Set<Preparable> getContents() { return contents; }
}