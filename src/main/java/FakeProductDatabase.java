import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FakeProductDatabase implements ProductDatabase {
    ArrayList<Product> productData = new ArrayList<>();
    private static final ProductGroup FRUVEG = new ProductGroup("Fruit&Vegetables", VAT.VATCategories.VAT25);
    private static final ProductGroup MEAT = new ProductGroup("Meat&Poultry", VAT.VATCategories.VAT25);
    private static final ProductGroup DAIRY = new ProductGroup("Dairy", VAT.VATCategories.VAT25);
    private static final ProductGroup DRY = new ProductGroup("Dry", VAT.VATCategories.VAT25);
    private static final Product CUCUMBER = new Product("Cucumber", 9.0, FRUVEG, new EAN(917563847583L));
    private static final Product PASTA = new Product("Pasta", 12.0, DRY, new EAN(917563927583L));
    private static final Product MILK = new Product("Milk", 5.0, DAIRY, new EAN(917547847583L));
    private static final Product SAUSAGE = new Product("Sausage", 45.90, MEAT, new EAN(917563848693L));
    private static final Product BREAD = new Product("Bread", 45.90, DRY, new EAN(925463847583L));
    private static final Product TOMATO = new Product("Tomato", 9.0, FRUVEG, new EAN(917563849363L));
    private static final Product CHICKPEAS = new Product("Chickpeas", 4.0, DRY, new EAN(917263847583L));
    private static final Product RICE = new Product("Rice", 112.0, DRY, new EAN(917563849363L));
    private static final Product ENTRECOTE = new Product("Entrecote", 359.0, MEAT, new EAN(961063847583L));
    private static final Product YOGHURT = new Product("Yoghurt", 21.90, DAIRY, new EAN(917569267583L));
    private static final Product SALAMI = new Product("Salami", 25.90, MEAT, new EAN(917563840003L));
    private static final Product  MINCED_MEAT = new Product("Minced Meat", 5.0, MEAT, new EAN (928374658273L));
    private static final Product CREAM = new Product("Cream", 5.0, DAIRY, new EAN(9485736253926L));
    private static final Product BUTTER = new Product("Butter", 45.0, DAIRY, new EAN(9684736485769L));
    private static final Product CRUSHED_TOMATOES = new Product("Crushed Tomatoes", 8.0, DRY, new EAN(8573928374659L));

    public FakeProductDatabase() {
        fillDatabase();
    }

    public void fillDatabase() {
        productData.addAll(List.of(CUCUMBER, PASTA, MILK, SAUSAGE, BREAD, TOMATO, CHICKPEAS, RICE, ENTRECOTE, YOGHURT, SALAMI, MINCED_MEAT, BUTTER, CREAM, CRUSHED_TOMATOES));
    }

    @Override
    public Product getProductFromDatabase(EAN ean) {
        for (Product p : productData) {
            if (p.getEan().equals(ean)) {
                return p;
            }
        }
        return null;
    }
    @Override
    public Product getProductFromDatabase(String name) {
        for (Product p : productData) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void addProductToDatabase(Product product) {
        productData.add(product);

    }

    @Override
    public void removeProductFromDatabase(Product product) {
        productData.remove(product);

    }

}
