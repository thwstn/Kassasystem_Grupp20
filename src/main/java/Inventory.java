import java.util.HashMap;

public class Inventory {

    private static final HashMap<EAN, Integer> INVENTORY = new HashMap<>();

    public void add(Product p, int amount) {
        INVENTORY.put(p.getEAN(), amount);
    }

    public int getAmount(Product p) {
        return INVENTORY.get(p.getEAN());
    }
}
