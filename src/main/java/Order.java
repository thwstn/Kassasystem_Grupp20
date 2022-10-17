import java.util.*;

public class Order {

    private List<OrderLine> orderLines = new ArrayList<>();
    private final UUID orderID;
    private final Employee employee;
    private final Date date;
    private final Customer customer;
    public Order(UUID orderID, Employee employee, Customer customer) {
        this.orderID = orderID;
        this.employee = employee;
        this.date = new Date();
        this.customer = customer;
    }

    public Order( UUID orderID, Employee employee,Customer customer, OrderLine ... orderLine) {
        orderLines.addAll(Arrays.asList(orderLine));
        this.orderID = orderID;
        this.employee = employee;
        this.date = new Date();
        this.customer = customer;
    }

    public OrderLine getOrderLineAtIndex(int i) {
        return orderLines.get(i);
    }

    public OrderLine getOrderLine(String orderLine) {
        for (OrderLine o: orderLines) {
            if (o.getName().equalsIgnoreCase(orderLine)){
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
    public void groupAllOrderLinesTogether() {
        ArrayList<OrderLine> newList = new ArrayList<>();
        boolean itemExistsInNewList = false;

        for (OrderLine o : orderLines) {
            if (newList.size() == 0) {
                newList.add(o);
            }
            else {
                for (OrderLine ol : newList) {
                    if (ol.getName().equals(o.getName())) {
                        ol.setQuantity(ol.getQuantity() + o.getQuantity());
                        itemExistsInNewList = true;
                    }
                }
                if(!itemExistsInNewList){
                    newList.add(o);
                }
            }
            itemExistsInNewList = false;
        }
        this.orderLines = newList;
    }
        @Override
        public String toString () {
            StringBuilder sb = new StringBuilder();
            for (OrderLine o : orderLines) {
                sb.append(o.toString()).append("\n");
            }
            return sb.toString();
        }


    }
