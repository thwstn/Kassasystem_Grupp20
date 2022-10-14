public record  OrderLine(String name, double price, int quantity) implements Comparable<OrderLine> {

    @Override
    public int compareTo(OrderLine o) {
        if (this.name().compareTo(o.name()) != 0) {
            return this.name().compareTo(o.name());
        } else if (Double.compare(this.price(), o.price()) != 0) {
            return Double.compare(this.price(), o.price());
        } else {
            return Integer.compare(this.quantity(), o.quantity());
        }
    }
}
