import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    private EAN eanCucumber;
    private Product cucumber;
    private Product milk;

    @BeforeEach
    void init() {
        ProductGroup productGroupVat6 = new ProductGroup("Fruit&Vegetables",(VAT.VATCategories.VAT6));
        ProductGroup productGroupVat25 = new ProductGroup("Dairy", VAT.VATCategories.VAT25);
        eanCucumber = mock(EAN.class);
        cucumber = new Product("Cucumber",11.0, productGroupVat6, eanCucumber, 100);
        EAN eanTomato = mock(EAN.class);
        milk = new Product("Mjölk", 33.90, productGroupVat25, eanTomato);
    }

    @Test
    void setAmountSetsCorrectAmountWhenAmountIsZero() {
        milk.setAmount(21);
        assertEquals(21, milk.getAmount());
    }

    @Test
    void setAmountSetsCorrectAmountWhenAmountIsNotZero() {
        cucumber.setAmount(21);
        assertEquals(21, cucumber.getAmount());
    }

    @Test
    void getPriceReturnsPriceIncVat() {
        assertEquals(11.66, cucumber.getPriceIncVat());
    }

    @Test
    void increaseAmountWithCurrentAmountNotZeroIncreasesCorrectAmount() {
        cucumber.increaseAmount(55);
        assertEquals(155, cucumber.getAmount());
    }

    @Test
    void increaseAmountWithCurrentAmountZeroIncreasesCorrectAmount() {
        milk.increaseAmount(33);
        assertEquals(33, milk.getAmount());
    }

    @Test
    void decreaseAmountDecreasesValidAmount() {
        cucumber.decreaseAmount(5);
        assertEquals(95, cucumber.getAmount());
    }

    @Test
    void decreaseAmountDecreasesTooMany() {
        cucumber.decreaseAmount(101);
        assertEquals(-1, cucumber.getAmount());
    }

    @Test
    void getNameReturnsName() {
        assertEquals("Mjölk", milk.getName());
    }

    @Test
    void getDescriptionReturnsDescription() {
        assertEquals("Mjölk", milk.getDescription());
    }

    @Test
    void getEanReturnsEan() {
        assertEquals(eanCucumber, cucumber.getEan());
    }

    @Test
    void toStringReturnsCorrectValues() {
        assertEquals("Products{" +
                "name: " + milk.getName() + ',' +
                " price: " + milk.getPriceIncVat() +
                ", productGroup: " + milk.getProductGroup() +
                ", ean: " + milk.getEan() +
                ", amount: " + milk.getAmount() +
                '}', milk.toString());
    }

}
