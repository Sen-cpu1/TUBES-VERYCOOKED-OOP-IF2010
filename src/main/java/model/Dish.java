public class Dish {
    private String name;
    private List<Preparable> components;
    private Plate plate;
    
    public Dish(Plate plate) {
        this.plate = plate;
        this.components = new ArrayList<>(plate.getContents());
    }
    
    public List<Preparable> getComponents() { return components; }
}