import java.util.UUID;

public class Checkout {
    private final UUID ID;
    private CheckOutSession checkOutSession;
    private Money money;
    private Order order;
    protected FakeCheckOutSessionDatabase checkOutSessionDatabase = new FakeCheckOutSessionDatabase();
    protected FakeProductDatabase productDatabase = new FakeProductDatabase();
    protected FakeOrderDatabase orderDatabase = new FakeOrderDatabase();

    public Checkout() {
        ID = UUID.randomUUID();
    }

    public UUID getID() {
        return ID;
    }
    public CheckOutSession getCheckOutSession() {
        return checkOutSession;
    }

    public Money getMoney() {
        return money;
    }

    public Order getOrder() {
        return order;
    }

    private void addNewEmptyOrder() {
        Employee employee = checkOutSession.getEmployee();
        order = new Order(employee);
    }
    public void removeOrder() {
        order = null;
    }
    public void loginEmployee(Employee employee) {
        if (checkOutSession == null) {
            checkOutSession = new CheckOutSession(employee);
        } else throw new IllegalStateException("You cant login! Someone else is already logged in!");
    }
    public void logoutEmployee() {
        if (checkOutSession != null) {
            checkOutSession.addEndDateToSession();
            checkOutSessionDatabase.addCheckOutSession(checkOutSession);
            checkOutSession = null;
        } else throw new IllegalStateException("Nobody is logged in!");
    }
    public void changeEmployee(Employee employee) {
        checkOutSession.addEndDateToSession();
        checkOutSessionDatabase.addCheckOutSession(checkOutSession);
        checkOutSession = new CheckOutSession(employee);
    }

    public void scanEAN(long ean) {
        EAN eanToCheck = new EAN(ean);
        Product product = productDatabase.getProductFromDatabase(eanToCheck);
        if (product == null) {
            //hantera att EAN inte finns
            return;
        }
        OrderLine orderLine = new OrderLine(product.getName(), product.getPriceIncVat(), 1);
        if(order == null) {
            addNewEmptyOrder();
            order.addOrderLineToList(orderLine);
        } else {
            order.addOrderLineToList(orderLine);
        }
    }

    public void payWithCard() {
        orderDatabase.addOrder(order);
        order = null;
    }
}

/*public class Checkout{
    private UUID id;
    private CheckOutSession checkOutSession;
    private Money money;
    private Order order;

    //private ArrayList<Order> orders;
    ArrayList<CheckOutSession> checkOutSessionsHistory = new ArrayList<>();


    public Checkout(int employeeId, ArrayList<Integer> orderIds, int moneyid) {
        this.id = UUID.randomUUID();
        //this.employeeId = employeeId;
        this.orderIds = orderIds;
        this.moneyId = moneyid;
    }

    public int getId() {
        return id;
    }


    public CheckOutSession getSession() {
        return checkOutSession;
    }

    public ArrayList<Integer> getOrderIds() {
        return orderIds;
    }

    public int getMoneyId() {
        return moneyId;
    }

    public ArrayList<CheckOutSession> getCheckOutSessionsHistory() {
        return checkOutSessionsHistory;
    }

    public void loginEmployee(int employeeId) {
        if (checkOutSession == null) {
            checkOutSession = new CheckOutSession(employeeId);
        }
    }

    public void logoutEmployee() {
        if (checkOutSession != null) {
            checkOutSession.addEndDateToSession();
            //CheckOutSession.getCheckOutSessionsHistory().add(checkOutSession);
            checkOutSessionsHistory.add(checkOutSession);
            checkOutSession = null;
        }
    }

    public void changeEmployee(int employeeId) {
        logoutEmployee();
        loginEmployee(employeeId);
    }

    public boolean employeeIsLoggedInToCheckout() {
        return checkOutSession != null;
    }

    public HashSet<Integer> getUniqueEmployeeIDsFromSessionHistory() {
        HashSet<Integer> employeeIDs = new HashSet<>();
        for (CheckOutSession checkOutSession : checkOutSessionsHistory) {
            employeeIDs.add(checkOutSession.getEmployeeId());
        }
        return employeeIDs;
    }
    public void createOrder() {

    }

    private void readAllProductsFromDatabase(){ //Jacob fixar

    }

}
*/