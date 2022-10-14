import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VATTest {
    @Test
    void getPercentSix() {
        ProductGroup productGroup = new ProductGroup("VEGETABLES", VAT.VATCategories.VAT6);
        System.out.print(productGroup);
        assertEquals(0.06,0.06);
    }
    @Test
    void getPercentTwelve() {
        ProductGroup productGroup = new ProductGroup("Soda", VAT.VATCategories.VAT12);
        System.out.println(productGroup);
        assertEquals(0.12,0.12);
    }
    @Test
    void getPercentTwentyFive() {
        ProductGroup productGroup = new ProductGroup("Fresh", VAT.VATCategories.VAT25);
        System.out.println(productGroup);
        assertEquals(0.25,0.25);
    }
}