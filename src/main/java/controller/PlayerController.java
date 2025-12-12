public class PlayerController {
private Chef chef1;
private Chef chef2;
private Chef activeChef;
private GameMap map;public PlayerController(Chef c1, Chef c2, GameMap map) {
    this.chef1 = c1;
    this.chef2 = c2;
    this.activeChef = chef1;
    this.map = map;
    chef1.setActive(true);
}

public void handleInput(String input) {
    switch (input.toUpperCase()) {
        case "W": activeChef.move(Direction.UP, map); break;
        case "A": activeChef.move(Direction.LEFT, map); break;
        case "S": activeChef.move(Direction.DOWN, map); break;
        case "D": activeChef.move(Direction.RIGHT, map); break;
        case "C": activeChef.interact(map); break;
        case "V": activeChef.interact(map); break; // Same as C
        case "B": switchChef(); break;
    }
}

private void switchChef() {
    activeChef.setActive(false);
    activeChef = (activeChef == chef1) ? chef2 : chef1;
    activeChef.setActive(true);
}

public Chef getActiveChef() { return activeChef; }
public Chef getChef1() { return chef1; }
public Chef getChef2() { return chef2; }
}