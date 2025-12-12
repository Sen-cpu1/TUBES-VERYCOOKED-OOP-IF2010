public class WashingStation extends Station {
    private Chef currentChef;
    private int washingProgress; // 0-100
    private Timer progressTimer;
    private Queue<Plate> dirtyPlates;
    private Stack<Plate> cleanPlates;
    private static final int WASHING_DURATION = 3000; // 3 seconds per plate
    
    public WashingStation(Position pos) {
        this.position = pos;
        this.symbol = "W";
        this.dirtyPlates = new LinkedList<>();
        this.cleanPlates = new Stack<>();
    }
    
    @Override
    public boolean isWalkable() { return false; }
    
    @Override
    public void interact(Chef chef) {
        Item heldItem = chef.getInventory();
        
        // Case 1: Drop dirty plates
        if (heldItem instanceof Plate) {
            Plate plate = (Plate) heldItem;
            if (plate.isDirty()) {
                dirtyPlates.offer(plate);
                chef.setInventory(null);
                // Auto-start washing if not already washing
                if (currentChef == null && !dirtyPlates.isEmpty()) {
                    startWashing(chef);
                }
            }
        }
        // Case 2: Pick up clean plate
        else if (heldItem == null && !cleanPlates.isEmpty()) {
            chef.setInventory(cleanPlates.pop());
        }
    }
    
    private void startWashing(Chef chef) {
        if (dirtyPlates.isEmpty()) return;
        
        currentChef = chef;
        chef.setCurrentAction(ChefAction.WASHING);
        
        progressTimer = new Timer();
        progressTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                washingProgress += 10;
                if (washingProgress >= 100) {
                    finishWashing();
                    cancel();
                    // Start next plate if available
                    if (!dirtyPlates.isEmpty()) {
                        startWashing(currentChef);
                    }
                }
            }
        }, 0, 300);
    }
    
    private void finishWashing() {
        Plate plate = dirtyPlates.poll();
        if (plate != null) {
            plate.clean();
            cleanPlates.push(plate);
        }
        washingProgress = 0;
        if (currentChef != null && dirtyPlates.isEmpty()) {
            currentChef.setCurrentAction(ChefAction.IDLE);
            currentChef = null;
        }
    }
    
    public void stopWashing() {
        if (progressTimer != null) {
            progressTimer.cancel();
        }
        if (currentChef != null) {
            currentChef.setCurrentAction(ChefAction.IDLE);
        }
        currentChef = null;
    }
}

