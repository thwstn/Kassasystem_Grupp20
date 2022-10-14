import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductGroupTest {

    public Product Milk, Cream, Pasta,Cola,Fanta,Sprite,Pepsi;
    @Test
    void newGroupTest1() {
        ProductGroup Vegetables = new ProductGroup("Fruit&Vegetables",VAT.VATCategories.VAT12);
        Product Tomato = new Product("Tomato", 20.0, Vegetables, new EAN(5214562345623L));
        ArrayList<Product> VegetablesList = new ArrayList<>();
        VegetablesList.add(Tomato);
        assertEquals(1, VegetablesList.size());
        System.out.println(Vegetables + VegetablesList.toString());
    }
    @Test
    void newGroupTest2() { //fixa
        ProductGroup Dairy = new ProductGroup("Dairy", VAT.VATCategories.VAT12);
        ArrayList<Object> DairyList = new ArrayList<>();
        DairyList.add(Milk);
        DairyList.add(Cream);
        assertEquals(2, DairyList.size());
    }
    @Test
    void newGroupTest3(){
        ProductGroup fresh = new ProductGroup("Dairy", VAT.VATCategories.VAT12);
        ArrayList<Object> FreshList = new ArrayList<>();
        FreshList.add(Pasta);
        assertEquals(1,FreshList.size());
    } //fixa
    @Test
    void newGroupTest4(){
        ProductGroup Meat = new ProductGroup("Meat&Poultry", VAT.VATCategories.VAT25);
        ArrayList<Object> meatList = new ArrayList<>();
        meatList.add(Cola);
        meatList.add(Fanta);
        meatList.add(Sprite);
        meatList.add(Pepsi);
        assertEquals(4,meatList.size());
        System.out.print(meatList);
    } //fixa
    @Test
    void changeNameTest(){
        ProductGroup Vegetables = new ProductGroup("VEGETABLES", VAT.VATCategories.VAT12);
        System.out.println(Vegetables);
        Vegetables.changeCategoryName("Vegetables","BetterVEGETABLES");
        System.out.println(Vegetables);
        assertEquals(Vegetables, Vegetables);

    }
    @Test
    void NameTestNullFail(){
        assertThrows(IllegalArgumentException.class, ()->{new ProductGroup(null, VAT.VATCategories.VAT25);
        });
    }
    @Test
    void NameTestEmptyFail() {
        assertThrows(IllegalArgumentException.class, ()->{new ProductGroup("", VAT.VATCategories.VAT25);
        });
    }
    @Test
    void changeVATTest(){
        ProductGroup Dairy = new ProductGroup("DAIRY", VAT.VATCategories.VAT12);
        Dairy.setVAT(VAT.VATCategories.VAT6);
        assertEquals(0.06,0.06, "Wrong percentage!");
    }
}