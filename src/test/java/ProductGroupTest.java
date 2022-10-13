import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductGroupTest {

    public ProductGroup Vegetables, Dairy, Fresh, Soda, BetterVegetables;
    public Product Tomato, Milk, Cream, Pasta,Cola,Fanta,Sprite,Pepsi;
    @Test
    void newGroupTest1() {
        ProductGroup vegetables = new ProductGroup(Vegetables, VAT.VATCategories.VAT6);
        ArrayList<Object> VegetablesList = new ArrayList<>();
        VegetablesList.add(Tomato);
        assertEquals(1, VegetablesList.size());
    }
    @Test
    void newGroupTest2() {
        ProductGroup dairy = new ProductGroup(Dairy, VAT.VATCategories.VAT12);
        ArrayList<Object> DairyList = new ArrayList<>();
        DairyList.add(Milk);
        DairyList.add(Cream);
        assertEquals(2, DairyList.size());
    }
    @Test
    void newGroupTest3(){
        ProductGroup fresh = new ProductGroup(Fresh, VAT.VATCategories.VAT12);
        ArrayList<Object> FreshList = new ArrayList<>();
        FreshList.add(Pasta);
        assertEquals(1,FreshList.size());
    }
    @Test
    void newGroupTest4(){
        ProductGroup soda = new ProductGroup(Soda, VAT.VATCategories.VAT25);
        ArrayList<Object> SodaList = new ArrayList<>();
        SodaList.add(Cola);
        SodaList.add(Fanta);
        SodaList.add(Sprite);
        SodaList.add(Pepsi);
        assertEquals(4,SodaList.size());
    }
    @Test
    void addGroupToList(){
        ArrayList<ProductGroup> Pgroup = new ArrayList<>();
        Pgroup.add(Vegetables);
        Pgroup.add(Dairy);
        Pgroup.add(Fresh);
        Pgroup.add(Soda);
        assertEquals(4,Pgroup.size());
    }
    @Test
    void changeNameTest(){
        ProductGroup betterVegetables = new ProductGroup(BetterVegetables, VAT.VATCategories.VAT12);
        ProductGroup vegetables = new ProductGroup(Vegetables, VAT.VATCategories.VAT12);
        vegetables.changeCategoryName(vegetables, betterVegetables);
        assertEquals(betterVegetables.getProductGroup(),vegetables.getProductGroup(),"Wrong Category");
        System.out.println(betterVegetables.getProductGroup());
    }
}