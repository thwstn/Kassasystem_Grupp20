import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    ProductGroup vegetables;
    Product tomato;

    @BeforeEach
    void init() {
        vegetables = mock(ProductGroup.class);
        tomato = new Product("Tomat", 3.49, vegetables, 4534, 100);
    }

    @Test
    void ctr_setsArgumentsAsCorrectDataFields() {
        Product p = new Product("Gurka",11.0,vegetables,234,49);
        assertEquals("Gurka", p.getName());
        assertEquals(11.0, p.getPrice());
        assertEquals(vegetables, p.getProductGroup());
        assertEquals(234, p.getEan());
        assertEquals(49, p.getAmount());
    }

    @Test
    void ctr_setsProductGroup_to_existingProductGroup() {
        Product p = new Product("Morötter",5.0,vegetables,123,99);
        assertEquals(p.getProductGroup(), vegetables);
    }

    /*
    @Test
    void ctr_doesntCreateProduct_if_productAlreadyExists() {
        assertThrows(Exception.class, ()-> {
            Mockito.when(vegetables.getProduct()).thenReturn(tomato);
            Product p = new Product("Tomat", 3.49, vegetables, 4534, 100);
        });
    }
    */








}
