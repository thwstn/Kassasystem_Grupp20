import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class Checkout {
    private final UUID ID;
    private CheckOutSession checkOutSession;
    private Money money;
    private Order order;
    private final ArrayList<Order> parkedOrders = new ArrayList<>();
    protected CheckOutSessionDatabase checkOutSessionDatabase;
    protected ProductDatabase productDatabase;
    protected OrderDatabaseIO orderDatabase;
    protected EmployeeDatabase employeeDatabase;
    protected CustomerDataBase customerDatabase;


    public Checkout(CheckOutSessionDatabase checkOutSessionDatabase, ProductDatabase productDatabase,
                    OrderDatabaseIO orderDatabase, EmployeeDatabase employeeDatabase,
                    CustomerDataBase customerDataBase) {
        this.customerDatabase = customerDataBase;
        this.checkOutSessionDatabase = checkOutSessionDatabase;
        this.productDatabase = productDatabase;
        this.orderDatabase = orderDatabase;
        this.employeeDatabase = employeeDatabase;
        ID = UUID.randomUUID();
        money = new Money();
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

    public void addCustomerToOrder(Customer customer){
        this.order.setCustomer(customer);
    }
    public void scanEAN(long ean) {
        EAN eanToCheck = new EAN(ean);
        Product product = productDatabase.getProductFromDatabase(eanToCheck);
        if (product == null) {
            //hantera att EAN inte finns
            return;
        }
        OrderLine orderLine = new OrderLine(product.getName(), product.getPriceIncVat(), 1);
        if (order == null) {
            addNewEmptyOrder();
            order.addOrderLineToList(orderLine);
        } else {
            order.addOrderLineToList(orderLine);
        }
    }

    public String payWithCard() {
        if (!this.order.getEmployee().equals(this.checkOutSession.getEmployee())){
            order.setEmployee(this.checkOutSession.getEmployee());
        }
        String receipt = order.finalizeOrder();
        orderDatabase.addOrder(order);
        order = null;
        return receipt;
    }

    public void addMoney(Money money) {
        this.money = this.money.add(money);
    }

    public String payWithCash(Money moneyFromCustomer) {
        if (moneyFromCustomer.getBalance() < order.getTotalAmount() * 100) {
            throw new IllegalArgumentException("You have not paid enough money");
        }

        double moneyToGet = moneyFromCustomer.getBalance() - Math.round(order.getTotalAmount()) * 100;
        money = money.add(moneyFromCustomer);
        if (money.giveChange(moneyToGet) == null) {
            money = money.remove(moneyFromCustomer);
            throw new IllegalStateException("Not enough change in checkout");
        }

        money = money.remove(money.giveChange(moneyToGet));
        if (!this.order.getEmployee().equals(this.checkOutSession.getEmployee())){
            order.setEmployee(this.checkOutSession.getEmployee());
        }
        String receipt = order.finalizeOrder();
        orderDatabase.addOrder(order);
        order = null;
        return receipt;
    }

    public void parkOrder() {
        parkedOrders.add(this.order);
        this.order = null;

    }

    public Order getParkedOrder(String customerName) {
        for (Order o : parkedOrders) {
            if(o.getCustomer().getName().equals(customerName)){
                return o;
            }
        }
        return null;
    }

    public void unparkOrder(String customerName) {
        Iterator<Order> iterator = parkedOrders.iterator();
        while(iterator.hasNext()){
            Order o = iterator.next();
            if(o.getCustomer().getName().equals(customerName)){
                this.order = o;
                iterator.remove();
            }
        }
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