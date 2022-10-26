
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

public class Product implements DiscountInterface{

    private String name;
    private double price;
    private ProductGroup productGroup;
    private EAN ean;
    private int amount;

    public Product(String name, double price, ProductGroup productGroup, EAN ean) {
        this.name = name;
        this.price = price;
        this.productGroup = productGroup;
        this.ean = ean;
    }

    public Product(String name, double price, ProductGroup productGroup, EAN ean, int amount) {
        this.name = name;
        this.price = price;
        this.productGroup = productGroup;
        this.ean = ean;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    @Override
    public double getPriceIncVat() {
        return price * (1.0 + getProductGroup().getVAT().getPercent());
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public EAN getEan() {
        return ean;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void decreaseAmount(int amountToDecrease) {
        this.amount = amount - amountToDecrease;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name);
    }

    @Override
    public String getDescription() {
        return name;
    }

    @Override
    public String toString() {
        return "Products{" +
                "name: " + name + ',' +
                " price: " + price +
                ", productGroup: " + productGroup +
                ", ean: " + ean +
                ", amount: " + amount +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
