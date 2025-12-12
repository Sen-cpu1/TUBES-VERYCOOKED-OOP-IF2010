// util/StationFactory.java (Factory Pattern)
public class StationFactory {
    public static Station createStation(String type, Position pos) {
        switch (type) {
            case "C": return new CuttingStation(pos);
            case "R": return new CookingStation(pos);
            case "A": return new AssemblyStation(pos);
            case "W": return new WashingStation(pos);
            case "S": return new ServingCounter(pos, null, null); // Set managers later
            case "T": return new TrashStation(pos);
            case "P": return new PlateStorage(pos, 3);
            default: throw new IllegalArgumentException("Unknown station: " + type);
        }
    }
}