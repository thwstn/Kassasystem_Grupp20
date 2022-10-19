
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountTest {

    private final FakeProductDatabase productDB = new FakeProductDatabase();

    @Test
    void discountCorrectlyAppliedToPrice() {
        productDB.fillDatabase();

        PercentProductDiscount ppd = new PercentProductDiscount(productDB.getProductFromDatabase(new EAN(917563849363L)), 10);
        assertEquals(11.25, ppd.getPriceIncVat());
    }

    @Test
    void discountCorrectlyAppliedTwice() {
        productDB.fillDatabase();
        PercentProductDiscount ppd = new PercentProductDiscount(productDB.getProductFromDatabase(new EAN(917563849363L)), 10);
        PercentProductDiscount ppd2 = new PercentProductDiscount(ppd, 10);
        assertEquals(10.125, ppd2.getPriceIncVat());
    }
}
