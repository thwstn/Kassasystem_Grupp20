//no negative, not above 100
public class PercentDiscount extends DiscountDecorator {

    private final double discount;

    public PercentDiscount(DiscountInterface product, double discount) {
        super(product);
        this.discount = discount;
    }

    @Override
    public double getPriceIncVat() {
        return product.getPriceIncVat() * (1 - (discount / 100));
    }
}
