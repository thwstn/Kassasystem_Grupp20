import java.util.HashMap;

public class Inventory {

    HashMap<EAN, Integer> inventory = new HashMap<>();

    private void add(Product p, int amount) {
        inventory.put(p.getEAN(), amount);
    }
}
