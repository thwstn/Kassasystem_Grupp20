import java.util.*;

public class Order implements DiscountInterface{

    private List<OrderLine> orderLines = new ArrayList<>();
    private static final Customer NOT_REGISTERED_CUSTOMER = new Customer("Kund", 0);
    private Employee employee;
    private final Date date;
    private Customer customer;
    private double totalAmount;
    private boolean orderIsPaid;

    public Order(Employee employee, OrderLine... orderLine) {
        orderLines.addAll(Arrays.asList(orderLine));
        this.employee = employee;
        this.date = new Date();
        this.customer = NOT_REGISTERED_CUSTOMER;
        for (OrderLine o : orderLines) {
            this.totalAmount += o.getTotalAmount();
        }
    }

    public Order(Employee employee, Customer customer, OrderLine... orderLine) {
        orderLines.addAll(Arrays.asList(orderLine));
        this.employee = employee;
        this.date = new Date();
        this.customer = customer;
        for (OrderLine o : orderLines) {
            this.totalAmount += o.getTotalAmount();
        }
    }

    public OrderLine getOrderLineAtIndex(int i) {
        return orderLines.get(i);
    }

    public OrderLine getOrderLine(String orderLine) {
        for (OrderLine o : orderLines) {
            if (o.getName().equalsIgnoreCase(orderLine)) {
                return o;
            }
        }
        return null; //Returns null if specified orderLine is not in list
    }

    public double getTotalAmount() {
        return this.totalAmount;
    }

    public Date getDate() {
        return this.date;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Collection<OrderLine> getOrderLineList() {
        return Collections.unmodifiableList(orderLines);
    }

    public boolean isOrderPaid() {
        return orderIsPaid;
    }


    public void addOrderLineToList(OrderLine orderLine) {
        if (this.orderIsPaid) {
            throw new IllegalStateException("Order is already paid, no more products can be added to order");
        }
        if (orderLine == null)
            throw new IllegalArgumentException("Orderline can't be null");
        if (!orderLines.contains(orderLine)) {
            orderLines.add(orderLine);
            this.totalAmount += orderLine.getTotalAmount();
        }
    }

    public void removeOrderLineFromList(OrderLine orderLine) {
        if (this.orderIsPaid) {
            throw new IllegalStateException("Order is already paid, no products can be removed from order");
        }
        if (orderLines.contains(orderLine)) {
            orderLines.removeIf(o -> o.equals(orderLine));
            this.totalAmount -= orderLine.getTotalAmount();

        }

    }

    public void sortByAlphabeticalOrderAscending() {
        orderLines.sort((Comparator.comparing(OrderLine::getName)));
    }

    public void sortByAlphabeticalOrderDescending() {
        orderLines.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
    }

    public void sortByQuantityHighestToLowest() {
        orderLines.sort((o1, o2) -> o2.getQuantity() - o1.getQuantity());
    }

    public void groupAllOrderLinesTogether() {
        ArrayList<OrderLine> newList = new ArrayList<>();
        boolean itemExistsInNewList = false;

        for (OrderLine o : orderLines) {
            if (newList.size() == 0) {
                newList.add(o);
            } else {
                for (OrderLine ol : newList) {
                    if (ol.getName().equals(o.getName())) {
                        ol.setQuantity(ol.getQuantity() + o.getQuantity());
                        itemExistsInNewList = true;
                    }
                }
                if (!itemExistsInNewList) {
                    newList.add(o);
                }
            }
            itemExistsInNewList = false;
        }
        this.orderLines = newList;
    }


    public String finalizeOrder() {
        orderIsPaid = true;
        return this.getReceipt();

    }


    public String getReceipt() {
        this.groupAllOrderLinesTogether();
        return "\t\t\tBillys\n" +
                "\tHandla smart, Bunkra hårt" +
                "\n" +
                this +
                "\n" +
                "Totalt:\t" + this.getTotalAmount() + ":-" +
                "\n" +
                "Du betjänades av: " + this.employee.getName() +
                "\nTack för att du handlade hos oss " + this.customer.getName() + "!" +
                "\n" +
                this.date.toString();
    }


    //Discount methods
    @Override
    public double getPriceIncVat() {
        return totalAmount;
    }

    @Override
    public String getDescription() {
        return this.toString();
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