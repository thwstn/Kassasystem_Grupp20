import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {

    private final List<OrderLine> orderLines = new ArrayList<>();

    public Order() {
    }

    public Order(OrderLine ... orderLine ) {
        orderLines.addAll(Arrays.asList(orderLine));
    }

    public void addOrderLineToList(OrderLine orderLine){
        if(orderLine == null)
            throw new IllegalArgumentException("Orderline can't be null");
        if(!orderLines.contains(orderLine))
            orderLines.add(orderLine);
    }

    public OrderLine getOrderLine(String orderLine) {
        for (OrderLine o: orderLines) {
            if (o.getName().equalsIgnoreCase(orderLine)){
                return o;
            }
        }
        return null; //Returns null if specified orderLine is not in list
    }

    public void removeOrderLineFromList(OrderLine orderLine) {
        orderLines.removeIf(o -> o.equals(orderLine));


    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (OrderLine o : orderLines) {
            sb.append(o.toString()).append("\n");
        }
        return sb.toString();
    }

}
