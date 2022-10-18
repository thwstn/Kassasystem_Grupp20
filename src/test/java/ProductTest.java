import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashMap;

import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    ProductGroup vegetablesMock;
    ProductGroup vegetables;
    EAN eanCucumber;
    Product cucumber;

    @BeforeEach
    void init() {
        vegetablesMock = mock(ProductGroup.class);
        vegetables = new ProductGroup("Fruit&Vegetables",(VAT.VATCategories.VAT6));
        eanCucumber = mock(EAN.class);
        cucumber = new Product("Cucumber",11.0,vegetables, eanCucumber, 100);
    }

    @Test
    void ctr_setsArgumentsAsCorrectDataFields() {
        assertEquals("Cucumber", cucumber.getName());
        assertEquals(11.66, cucumber.getPriceIncVat());//menade du vat 6?
        assertEquals(vegetables, cucumber.getProductGroup());
        assertEquals(eanCucumber, cucumber.getEan());
        assertEquals(100, cucumber.getAmount());
    }

    @Test
    void setAmountSetsCorrectAmount() {
        cucumber.setAmount(21);
        assertEquals(21, cucumber.getAmount());
    }

    @Test
    void getPriceReturnsPriceIncVat25() {//Menade du vat 6?
        assertEquals(11.66, cucumber.getPriceIncVat());
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







}
