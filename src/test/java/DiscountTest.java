
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountTest {

    private static final FakeProductDatabase PRODUCT_DB = new FakeProductDatabase();
    private PercentProductDiscount tenPercentDiscountOnTomato;
    private FlatProductDiscount oneHundredSEKDiscountOnTomato;
    private FlatProductDiscount tenSEKDiscountOnBread;
    @BeforeEach
    void init() {
        PRODUCT_DB.fillDatabase();
        tenPercentDiscountOnTomato = new PercentProductDiscount(PRODUCT_DB.getProductForOrderLine(
                new EAN(917563849363L)), 10);
        oneHundredSEKDiscountOnTomato = new FlatProductDiscount(PRODUCT_DB.getProductForOrderLine(
                new EAN(917563849363L)), 100);
        tenSEKDiscountOnBread = new FlatProductDiscount(PRODUCT_DB.getProductForOrderLine(
                new EAN(925463847583L)), 10);
    }
    @Test
    void percentDiscountCorrectlyAppliedToPrice() {
        assertEquals(11.25, tenPercentDiscountOnTomato.getPriceIncVat());
    }

    @Test
    void discountCorrectlyAppliedTwice() {
        PercentProductDiscount secondTomatoDiscount = new PercentProductDiscount(
                tenPercentDiscountOnTomato, 10);
        assertEquals(10.125, secondTomatoDiscount.getPriceIncVat());
    }

    @Test
    void discountLargerThanBasePriceReturnsZero() {
        assertEquals(0, oneHundredSEKDiscountOnTomato.getPriceIncVat());
    }

    @Test
    void flatDiscountCorrectlyAppliedToPrice() {
        assertEquals(47.375, tenSEKDiscountOnBread.getPriceIncVat());
    }

    @Test
    void differentDiscountTypesCorrectlyApplyToPrice() {
        PercentProductDiscount tenPercent = new PercentProductDiscount(
                tenSEKDiscountOnBread, 10);
        assertEquals(42.6375, tenPercent.getPriceIncVat());
    }
}
