import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountTest {

    private static final FakeProductDatabase PRODUCT_DB = new FakeProductDatabase();
    private PercentDiscount tenPercentDiscountOnTomato;
    private FlatDiscount oneHundredSEKDiscountOnTomato;
    private FlatDiscount tenSEKDiscountOnBread;
    private Order readyMadeOrder;

    @BeforeEach
    void init() {
        tenPercentDiscountOnTomato = new PercentDiscount(PRODUCT_DB.getProductFromDatabase(
                new EAN(917563849363L)), 10);
        oneHundredSEKDiscountOnTomato = new FlatDiscount(PRODUCT_DB.getProductFromDatabase(
                new EAN(917563849363L)), 100);
        tenSEKDiscountOnBread = new FlatDiscount(PRODUCT_DB.getProductFromDatabase(
                new EAN(925463847583L)), 10);
        readyMadeOrder = new Order(new Employee("Tom", 43000));
        readyMadeOrder.addOrderLineToList(new OrderLine("Potatis", 10, 1));
        readyMadeOrder.addOrderLineToList(new OrderLine("Potatis", 10, 1));
        readyMadeOrder.addOrderLineToList(new OrderLine("Potatis", 10, 1));
        readyMadeOrder.addOrderLineToList(new OrderLine("Potatis", 10, 1));
    }

    @Test
    void percentDiscountCorrectlyAppliedToPrice() {
        assertEquals(10.125, tenPercentDiscountOnTomato.getPriceIncVat());
    }

    @Test
    void discountCorrectlyAppliedTwice() {
        PercentDiscount secondTomatoDiscount = new PercentDiscount(
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
        PercentDiscount tenPercent = new PercentDiscount(
                tenSEKDiscountOnBread, 10);
        assertEquals(42.6375, tenPercent.getPriceIncVat());
    }

    @Test
    void flatDiscountOnOrderCorrectlyAppliedToCost() {
        FlatDiscount tenSEK = new FlatDiscount(readyMadeOrder, 10);
        assertEquals(30, tenSEK.getPriceIncVat());
    }

    @Test
    void getDescReturnsNameOfCorrectProduct() {
        PercentDiscount tenPercentOnBread = new PercentDiscount(tenSEKDiscountOnBread, 10);
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
        OrderLine ol = new OrderLine(tenSEKDiscountOnBread.getDescription(),
                tenSEKDiscountOnBread.getPriceIncVat(), 1);
        readyMadeOrder.addOrderLineToList(ol);
        PercentDiscount tenPercentDiscountOnOrder = new PercentDiscount(readyMadeOrder, 10);
        assertEquals(78.6375, tenPercentDiscountOnOrder.getPriceIncVat());
    }

    @Test
    void orderGetDescriptionReturnsReadableOrder() {
        readyMadeOrder.addOrderLineToList(new OrderLine((tenSEKDiscountOnBread.getDescription()),
                tenSEKDiscountOnBread.getPriceIncVat(), 2));
        assertEquals("""
                Potatis: 10.0 x1 10.0:-
                Potatis: 10.0 x1 10.0:-
                Potatis: 10.0 x1 10.0:-
                Potatis: 10.0 x1 10.0:-
                Bread: 47.375 x2 94.75:-
                """, readyMadeOrder.getDescription());
    }
}