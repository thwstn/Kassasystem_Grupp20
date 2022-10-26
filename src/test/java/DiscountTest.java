import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountTest {

    private static final FakeProductDatabase PRODUCT_DB = new FakeProductDatabase();
    private PercentProductDiscount tenPercentDiscountOnTomato;
    private FlatProductDiscount oneHundredSEKDiscountOnTomato;
    private FlatProductDiscount tenSEKDiscountOnBread;
    private final Order readyMadeOrder = new Order(new Employee("Tom", 43000),
            new OrderLine("Potatis", 10, 1),
            new OrderLine("Potatis", 10, 1),
            new OrderLine("Potatis", 10, 1),
            new OrderLine("Potatis", 10, 1));

    @BeforeEach
    void init() {
        tenPercentDiscountOnTomato = new PercentProductDiscount(PRODUCT_DB.getProductFromDatabase(
                new EAN(917563849363L)), 10);
        oneHundredSEKDiscountOnTomato = new FlatProductDiscount(PRODUCT_DB.getProductFromDatabase(
                new EAN(917563849363L)), 100);
        tenSEKDiscountOnBread = new FlatProductDiscount(PRODUCT_DB.getProductFromDatabase(
                new EAN(925463847583L)), 10);
    }

    @Test
    void percentDiscountCorrectlyAppliedToPrice() {
        assertEquals(10.125, tenPercentDiscountOnTomato.getPriceIncVat());
    }

    @Test
    void discountCorrectlyAppliedTwice() {
        PercentProductDiscount secondTomatoDiscount = new PercentProductDiscount(
                tenPercentDiscountOnTomato, 10);
        assertEquals(9.1125, secondTomatoDiscount.getPriceIncVat());
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

    @Test
    void flatDiscountOnOrderCorrectlyAppliedToCost() {
        FlatProductDiscount tenSEK = new FlatProductDiscount(readyMadeOrder, 10);
        assertEquals(30, tenSEK.getPriceIncVat());
    }

    @Test
    void getDescReturnsNameOfCorrectProduct() {
        PercentProductDiscount tenPercentOnBread = new PercentProductDiscount(tenSEKDiscountOnBread, 10);
        assertEquals("Bread", tenPercentOnBread.getDescription());
    }

    @Test
    void addDiscountToOrderLine() {
        OrderLine ol = new OrderLine(tenPercentDiscountOnTomato.getDescription(), tenPercentDiscountOnTomato.getPriceIncVat(),
                1);
        assertEquals(10.125, ol.getPrice());
    }

    @Test
    void addDiscountedOrderLineToOrder() {
        OrderLine ol = new OrderLine(tenPercentDiscountOnTomato.getDescription(), tenPercentDiscountOnTomato.getPriceIncVat(),
                1);
        readyMadeOrder.addOrderLineToList(ol);
        assertEquals(50.125, readyMadeOrder.getTotalAmount());
    }

    @Test
    void addDiscountedOrderLineAndDiscountOrder() {
        OrderLine ol = new OrderLine(tenSEKDiscountOnBread.getDescription(), tenSEKDiscountOnBread.getPriceIncVat(),
                1);
        readyMadeOrder.addOrderLineToList(ol);
        PercentProductDiscount tenPercentDiscountOnOrder = new PercentProductDiscount(readyMadeOrder, 10);
        assertEquals(78.6375,tenPercentDiscountOnOrder.getPriceIncVat());
    }
}