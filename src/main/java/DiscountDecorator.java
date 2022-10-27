public abstract class DiscountDecorator implements DiscountInterface{

    public final DiscountInterface product;

    public DiscountDecorator(DiscountInterface product) {
        this.product = product;

    }

    @Override
    public abstract double getPriceIncVat();


    @Override
    public String getDescription() {
        return product.getDescription();
    }
}

