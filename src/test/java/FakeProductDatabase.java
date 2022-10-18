import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FakeProductDatabase implements ProductDatabase{
    ArrayList<Product> productData = new ArrayList<>();
    private static final ProductGroup FRUVEG = new ProductGroup("Fruit&Vegetables", VAT.VATCategories.VAT25);
    private static final ProductGroup MEAT = new ProductGroup("Meat&Poultry", VAT.VATCategories.VAT25);
    private static final ProductGroup DAIRY = new ProductGroup("Dairy", VAT.VATCategories.VAT25);
    private static final ProductGroup DRY = new ProductGroup("Dry", VAT.VATCategories.VAT25);
    private static final Product CUCUMBER = new Product("Gurka", 9.0, FRUVEG, new EAN(917563847583L));
    private static final Product PASTA = new Product("Pasta", 19.90, DRY, new EAN(917563927583L));
    private static final Product MILK = new Product("Milk", 12.0, DAIRY, new EAN(917547847583L));
    private static final Product SAUSAGE = new Product("Sausage", 45.90, MEAT, new EAN(917563848693L));
    private static final Product BREAD = new Product("Bread", 45.90, DRY, new EAN(925463847583L));
    private static final Product TOMATO = new Product("Tomato", 10.00, FRUVEG, new EAN(917563849363L));
    private static final Product CHICKPEAS = new Product("Chickpeas", 9.90, DRY, new EAN(917263847583L));
    private static final Product RICE = new Product("Rice", 112.0, DRY, new EAN(917563849363L));
    private static final Product ENTRECOTE = new Product("Entrecote", 359.0, MEAT, new EAN(961063847583L));
    private static final Product YOGHURT = new Product("Yoghurt", 21.90, DAIRY, new EAN(917569267583L));
    private static final Product SALAMI = new Product("Salami", 25.90, MEAT, new EAN(917563840003L));

    public void fillDatabase(){
        productData.addAll(List.of(CUCUMBER, PASTA, MILK, SAUSAGE, BREAD, TOMATO, CHICKPEAS, RICE, ENTRECOTE, YOGHURT, SALAMI));
    }

    @Override
    public Product getProductForOrderLine(EAN ean) {
        for (Product p : productData) {
            if(p.getEan().equals(ean)){
                return p;
            }
        }
    return null;
    }
}
