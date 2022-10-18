import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class Checkout {
    private UUID ID;
    private CheckOutSession checkOutSession;
    private Money money;
    private Order order;

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