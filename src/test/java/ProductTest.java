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
        //vegetablesMock = Mockito.mock(ProductGroup.class);
        //Mockito.when(eanCucumber.getEANCode()).thenReturn(12345678910L);
        //Mockito.when(vegetables.getVat().getPercent()).thenReturn(0.25);
        cucumber = new Product("Cucumber",11.0,vegetables, eanCucumber);
    }

    @Test
    void ctr_setsArgumentsAsCorrectDataFields() {
        assertEquals("Cucumber", cucumber.getName());
        assertEquals(11.66, cucumber.getPriceIncVat());
        assertEquals(vegetables, cucumber.getProductGroup());
        assertEquals(eanCucumber, cucumber.getEan());
    }


    @Test
    void setAmountSetsCorrectAmount() {
        cucumber.setAmount(21);
        assertEquals(21, cucumber.getAmount());
    }

    @Test
    void getPriceReturnsPriceIncVat25() {
        assertEquals(11.66, cucumber.getPriceIncVat());
    }







}
