import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VATTest {
    @Test
    void getPercentSix() {
        VAT vat = new VAT(VAT.VATCategories.VAT6);
        assertEquals(0.06, vat.getPercent(),"Wrong percentage");

    }
    /*@Test
    void getPercentTwelve() {
        VAT vat = new VAT(VAT.VATCategories.VAT12);
        assertEquals(0.12, vat.getPercent(),"Wrong percentage");

    }
    @Test
    void getPercentTwentyfive() {
        VAT vat = new VAT(VAT.VATCategories.VAT25);
        assertEquals(0.25, vat.getPercent(),"Wrong percentage");

    }*/
}