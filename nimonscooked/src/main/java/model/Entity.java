package model;

/**
 * Base class for game entities like players, ingredients, stations, etc.
 */
public abstract class Entity {
    protected int x;
    protected int y;
    
    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
