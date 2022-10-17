import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductGroupTest {

    public Product Milk, Cream, Pasta,Cola,Fanta,Sprite,Pepsi;
    @Test
    void newGroupTest1() {
        ProductGroup vegetables = new ProductGroup("Fruit&Vegetables",VAT.VATCategories.VAT12);
        Product tomato = new Product("Tomato", 20.0, vegetables, new EAN(5214562345623L));
        vegetables.addProduct(tomato);
        assertEquals(1, vegetables.getAllProducts().size());
        System.out.println(vegetables + vegetables.getAllProducts().toString());
    }
    @Test
    void newGroupTest2() { //fixa
        ProductGroup dairy = new ProductGroup("Dairy", VAT.VATCategories.VAT12);
        Product milk = new Product("Milk",12,dairy,new EAN(111111111111L));
        dairy.addProduct(milk);
        assertEquals(1, dairy.productList.size());
    }
    @Test
    void newGroupTest3(){
        ProductGroup Dry = new ProductGroup("Dry", VAT.VATCategories.VAT12);
        ArrayList<Object> DryList = new ArrayList<>();
        DryList.add(Pasta);
        assertEquals(1,DryList.size());
    } //fixa
    @Test
    void newGroupTest4(){
        ProductGroup Meat = new ProductGroup("Meat&Poultry", VAT.VATCategories.VAT25);
        ArrayList<Object> meatList = new ArrayList<>();
        String Pork;
        meatList.add(new Product("Pork",25.0,Meat,new EAN(111111111111L)));
        assertEquals(1,meatList.size());
        System.out.print(meatList);
    } //fixa
    @Test
    void changeNameTest() {
        ProductGroup vegetables = new ProductGroup("Fruit&Vegetables", VAT.VATCategories.VAT12);
        System.out.println(vegetables);
        vegetables.changeCategoryName("Dry");
        System.out.println(vegetables);
        assertEquals("Dry", vegetables.getProductGroupName());
    }
    //gör test som ska faila med fel namn
    @Test
    void NameTestNullFail(){
        assertThrows(NullPointerException.class, ()->{new ProductGroup(null, VAT.VATCategories.VAT25);
        });
    }
    @Test
    void NameTestEmptyFail() {
        assertThrows(IllegalArgumentException.class, ()->{new ProductGroup("", VAT.VATCategories.VAT25);
        });
    }

}