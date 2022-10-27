//no negative
public class FlatDiscount extends DiscountDecorator {

    private final double discount;

    public FlatDiscount(DiscountInterface product, double discount) {
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
