public class OrderLine implements Comparable<OrderLine>{

    private final String name;
    private final double price;
    private final int quantity;

    public OrderLine(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }

    @Override
    public int compareTo(OrderLine o) {
        if(this.getName().compareTo(o.getName()) != 0){
            return this.getName().compareTo(o.getName());
        }
        else if(Double.compare(this.getPrice(), o.getPrice()) != 0){
            return Double.compare(this.getPrice(), o.getPrice());
        }
        else {return Integer.compare(this.getQuantity(), o.getQuantity());
        }
    }
}
