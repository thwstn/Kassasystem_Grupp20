public class Product {

    private final String name;
    private double price;
    private final EAN ean;

    public Product(String name, EAN ean) {
        this.name = name;
        this.ean = ean;
    }

    public String getName() {
        return name;
    }

    public EAN getEAN() {
        return ean;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", eanCode=" + ean +
                '}';
    }
}
