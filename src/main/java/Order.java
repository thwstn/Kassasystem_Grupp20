import java.util.*;

public class Order {

    private List<OrderLine> orderLines = new ArrayList<>();
    private static final Customer NOT_REGISTERED_CUSTOMER = new Customer(UUID.randomUUID(),"Kund", 0);
    private final UUID orderID;
    private final Employee employee;
    private final Date date;
    private final Customer customer;
    private double totalAmount;
    public Order(UUID orderID, Employee employee, Customer customer) {
        this.orderID = orderID;
        this.employee = employee;
        this.date = new Date();
        this.customer = customer;
    }
    public Order(UUID orderID, Employee employee) {
        this.orderID = orderID;
        this.employee = employee;
        this.date = new Date();
        this.customer = NOT_REGISTERED_CUSTOMER;
    }
    public Order( UUID orderID, Employee employee,Customer customer, OrderLine ... orderLine) {
        orderLines.addAll(Arrays.asList(orderLine));
        this.orderID = orderID;
        this.employee = employee;
        this.date = new Date();
        this.customer = customer;
        for (OrderLine o : orderLines ) {
            this.totalAmount += o.getTotalAmount();
        }
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

    public double getTotalAmount(){
        return this.totalAmount;
    }

    public void addOrderLineToList(OrderLine orderLine){
        if(orderLine == null)
            throw new IllegalArgumentException("Orderline can't be null");
        if(!orderLines.contains(orderLine)) {
            orderLines.add(orderLine);
            this.totalAmount += orderLine.getTotalAmount();
        }
    }

    public void removeOrderLineFromList(OrderLine orderLine) {
        if (orderLines.contains(orderLine)) {
            orderLines.removeIf(o -> o.equals(orderLine));
            this.totalAmount -= orderLine.getTotalAmount();

        }

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

    public String getReceipt(){
        this.groupAllOrderLinesTogether();
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t\tWillys\n").append
                ("\tHandla smart, Bunkra hårt").append
                ("\n").append
                (this).append
                ("\n").append
                ("Totalt:\t").append(this.getTotalAmount()).append(":-").append
                ("\n").append
                ("Du betjänades av: ").append(this.employee.getName()).append
                ("\nTack för att du handlade hos oss ").append(this.customer.getName()).append("!");
       return sb.toString();
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
