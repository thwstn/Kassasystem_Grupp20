//no negative, not above 100
public class PercentProductDiscount extends DiscountDecorator {

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
