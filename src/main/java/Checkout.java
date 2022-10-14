import java.util.ArrayList;
import java.util.HashSet;

public class Checkout {
    private int id;
    private CheckOutSession checkOutSession;
    private ArrayList<Integer> orderIds;
    private int moneyId;
    ArrayList<CheckOutSession> checkOutSessionsHistory = new ArrayList<>();

    public Checkout(int id, int employeeId, ArrayList<Integer> orderIds, int moneyid) {
        this.id = id;
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

}
