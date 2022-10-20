import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductGroupTest {
    private final FakeProductDatabase productDatabase = new FakeProductDatabase();

    @Test
    void changeNameNewProductGroupTest() {
        ProductGroup vegetables = new ProductGroup("Fruit&Vegetables", VAT.VATCategories.VAT12);
        System.out.println(vegetables);
        vegetables.changeCategoryName("Dry");
        System.out.println(vegetables);
        assertEquals("Dry", vegetables.getProductGroupName());
    }

    @Test
    void changeNameTestFail() {
        ProductGroup vegetables = new ProductGroup("Dry", VAT.VATCategories.VAT25);
        Assertions.assertThrows(IllegalArgumentException.class, () -> vegetables.changeCategoryName("Berries"));
        System.out.println(vegetables.getProductGroupName());
    }

    @Test
    void NameNullFailTest() {
        assertThrows(NullPointerException.class, () -> new ProductGroup(null, VAT.VATCategories.VAT25));
    }

    @Test
    void NameEmptyFail() {
        assertThrows(IllegalArgumentException.class, () -> new ProductGroup("", VAT.VATCategories.VAT25));
    }

    @Test
    void getProductGroupFromDatabaseTest() {
        for (Product pGroup : productDatabase.productData) {
            System.out.println(pGroup.getProductGroup());
            assertEquals(15,productDatabase.productData.size());
        }
    }
    @Test
    void getVATTestAndPercentage(){
        ProductGroup vegetables = new ProductGroup("Fruit&Vegetables", VAT.VATCategories.VAT25);
        assertEquals(0.25,vegetables.getVAT().getPercent());
        System.out.println(vegetables.getVAT().getPercent());
    }
}
    /*@Test
    void removeByNameTest() {
        ProductGroup vegetables = new ProductGroup("Fruit&Vegetables", VAT.VATCategories.VAT12);
        vegetables.addProduct(new Product("Tomato",25.0,vegetables,new EAN(121212121212L)));
        System.out.println(vegetables);
        if(vegetables.containsProductByName("Tomato")){
            vegetables.removeProductByName("Tomato",1);
        }
        assertEquals(0,vegetables);
    }*/
    /*

    @Test
    void newGroupTestVegetables() {
        ProductGroup vegetables = new ProductGroup("Fruit&Vegetables",VAT.VATCategories.VAT12);
        Product tomato = new Product("Tomato", 20.0, vegetables, new EAN(5214562345623L));
        vegetables.addProduct(tomato);
        assertEquals(1, vegetables.getAllProducts().size());
        System.out.println(vegetables + vegetables.getAllProducts().toString());
    }
    @Test
    void newGroupTestDairy() { //fixa
        ProductGroup dairy = new ProductGroup("Dairy", VAT.VATCategories.VAT12);
        Product milk = new Product("Milk",12,dairy,new EAN(111111111111L));
        dairy.addProduct(milk);
        assertEquals(1, dairy.getAllProducts().size());
    }
    @Test
    void newGroupTestDry(){
        ProductGroup dry = new ProductGroup("Dry", VAT.VATCategories.VAT12);
        Product pasta = new Product("Pasta",9,dry,new EAN(111111111112L));
        dry.addProduct(pasta);
        assertEquals(1,dry.getAllProducts().size());
    } //fixa
    @Test
    void newGroupTestMeat(){
        ProductGroup meat = new ProductGroup("Meat&Poultry", VAT.VATCategories.VAT25);
        meat.addProduct(new Product("Pork",25.0,meat,new EAN(111111111111L)));
        assertEquals(1,meat.getAllProducts().size());

    } //fixa
   */
