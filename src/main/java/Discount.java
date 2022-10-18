
//discounts on products or orders/orderline or product/productg/order/orderline?
//why is abstract decorator necessary
public abstract class Discount implements DiscountInterface{

    private final DiscountInterface product;

    public Discount(DiscountInterface product) {
        this.product = product;
    }

    @Override
    public double getPriceIncVat() {
        return product.getPriceIncVat();
    }
}

