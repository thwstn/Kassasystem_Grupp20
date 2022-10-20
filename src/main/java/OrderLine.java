
public class OrderLine {

    private final String name;
    private final double price;
    private int quantity;

    public OrderLine(String name, double price, int quantity) {
        if(price <= 0){
            throw new IllegalArgumentException("Price has to be more than 0");
        }
        else if(name.isBlank()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
        this.price = price;
        this.setQuantity(quantity);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int newQuantity){
       if(newQuantity <= 0){
            throw new IllegalArgumentException("Quantity has to be more than 0");
        }
       this.quantity = newQuantity;
    }

    public double getTotalAmount(){
        return this.getPrice() * this.getQuantity();
    }

  /*  @Override
    public int compareTo(OrderLine o) {
        if (this.getName().compareTo(o.getName()) != 0) {
            return this.getName().compareTo(o.getName());
        } /*else if (Double.compare(this.getPrice(), o.getPrice()) != 0) {
            return Double.compare(this.getPrice(), o.getPrice());  TODO Tror inte denna behövs då alla varor med samma namn har samma pris
        } else {
            return Integer.compare(this.getQuantity(), o.getQuantity());
        }
    }
*/
    @Override
    public String toString(){
        return this.getName() + ": " + this.getPrice() + " x" + this.getQuantity() + " " + this.getTotalAmount() + ":-";
    }
}