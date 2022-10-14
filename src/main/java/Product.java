
/*
- Product (Theo)
    - Namn
    - GrundPris (ex vat)
    - Produktkategori (som klass) Grönsak
    - EAN
    - toString
    - Konstruktorn lägger till produkten i en ProductCategory
    - Sätt rabatt (som productCategory kan nå)
    - get price = price + vat
    - rabatt = price - grönsak.getrabatt
 */

import java.io.InvalidObjectException;
import java.util.Objects;

public class Product {

    private String name;
    private double price;
    private ProductGroup productGroup;
    private long ean;
    private int amount;

    public Product(String name, double price, ProductGroup productGroup, long ean, int amount) {
        //if (productGroup.getProduct(name).getName().equals(name)) {
        //    throw new IllegalArgumentException("No");
        //}
        this.name = name;
        this.price = price;
        this.productGroup = productGroup;
        this.ean = ean;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public long getEan() {
        return ean;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
