public class PercentProductDiscount extends Discount{

    private final double discount;

    public PercentProductDiscount(DiscountInterface product, double discount) {
        super(product);
        this.discount = discount;
    }

    @Override
    public double getPriceIncVat() {
        return product.getPriceIncVat() * (1 - (discount / 100));
    }
}
