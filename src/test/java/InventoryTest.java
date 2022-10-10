import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryTest {

    @Test
    void addProductCorrectlyToInventory() {
        EAN ean = new EAN(1234567891234L);
        Product product = new Product("Flingor", ean);
        Inventory inventory = new Inventory();
        inventory.add(product, 1);
        assertEquals(1, inventory.getAmount(product));
    }
}
