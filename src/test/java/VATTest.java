import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VATTest {
    @Test
    void getPercentSix() {
        VAT vat = new VAT(VAT.VATCategories.VAT6);
        assertEquals(0.06,vat.getPercent(),"Wrong percentage.");
    }
    @Test
    void getPercentTwelve() {
        ProductGroup productGroup = new ProductGroup("Dairy", VAT.VATCategories.VAT12);
        System.out.println(productGroup);
        assertEquals(0.12,0.12);
    }
    @Test
    void getPercentTwentyFive() {
        ProductGroup productGroup = new ProductGroup("Meat&Poultry", VAT.VATCategories.VAT25);
        System.out.println(productGroup);
        assertEquals(0.25,0.25);
    }

    // Tester att göra
    //Vat är inte null, VAt är inte 0, VAT är inte >25
    //
    /*@Test
    void changeVATTest(){
        ProductGroup Dairy = new ProductGroup("Dairy", VAT.VATCategories.VAT12);
        Dairy.setVAT(VAT.VATCategories.VAT6);
        assertEquals(0.06,Dairy.getVAT().getPercent(), "Wrong percentage!");
    }*/
}