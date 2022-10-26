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

    public void increaseAmount(int amountToIncrease) {
        this.amount = amount + amountToIncrease;
    }

    public void decreaseAmount(int amountToDecrease) {
        this.amount = amount - amountToDecrease;
    }

    @Override
    public String getDescription() {
        return name;
    }

    @Override
    public String toString() {
        return "Products{" +
                "name: " + name + ',' +
                " price: " + getPriceIncVat() +
                ", productGroup: " + productGroup +
                ", ean: " + ean +
                ", amount: " + amount +
                '}';
    }

}
