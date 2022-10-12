public class OrderLine implements Comparable<OrderLine>{

    private final String name;
    private final double price;

    public OrderLine(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice(){
        return price;
    }

    @Override
    public int compareTo(OrderLine o) {
        if(this.getName().compareTo(o.getName()) != 0){
            return this.getName().compareTo(o.getName());
        }
        return Double.compare(this.getPrice(), o.getPrice());
    }
}
