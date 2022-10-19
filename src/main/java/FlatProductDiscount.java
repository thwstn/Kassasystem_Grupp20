//no negative
public class FlatProductDiscount extends Discount {

    private final double discount;

    public FlatProductDiscount(DiscountInterface product, double discount) {
        super(product);
        this.discount = discount;
    }

    @Override
    public double getPriceIncVat() {
        if (discount > product.getPriceIncVat()) {
            return 0;
        }
        return product.getPriceIncVat() - discount;
    }
}
