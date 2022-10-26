public abstract class DiscountDecorator implements DiscountInterface{

    public final DiscountInterface product;

    public DiscountDecorator(DiscountInterface product) {
        this.product = product;

    }

    @Override
    public double getPriceIncVat() {
        return product.getPriceIncVat();
    }

    @Override
    public String getDescription() {
        return product.getDescription();
    }
}

