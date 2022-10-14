import java.util.*;

public class Order {

    private final List<OrderLine> orderLines = new ArrayList<>();
    private final UUID orderID;
    private final Employee employee;
    private final Date date;
    public Order(UUID orderID, Employee employee) {
        this.orderID = orderID;
        this.employee = employee;
        this.date = new Date();
    }

    public Order( UUID orderID, Employee employee, OrderLine ... orderLine) {
        orderLines.addAll(Arrays.asList(orderLine));
        this.orderID = orderID;
        this.employee = employee;
        this.date = new Date();
    }

    public OrderLine getOrderLineAtIndex(int i) {
        return orderLines.get(i);
    }

    public OrderLine getOrderLine(String orderLine) {
        for (OrderLine o: orderLines) {
            if (o.name().equalsIgnoreCase(orderLine)){
                return o;
            }
        }
        return null; //Returns null if specified orderLine is not in list
    }

    public void addOrderLineToList(OrderLine orderLine){
        if(orderLine == null)
            throw new IllegalArgumentException("Orderline can't be null");
        if(!orderLines.contains(orderLine)) {
            orderLines.add(orderLine);
        }
    }

    public void removeOrderLineFromList(OrderLine orderLine) {
        orderLines.removeIf(o -> o.equals(orderLine));


    }

    public void sortByAlphabeticalOrderAscending() {
        Collections.sort(orderLines);

    }

    public void sortByAlphabeticalOrderDescending() {
        orderLines.sort(Collections.reverseOrder());
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
