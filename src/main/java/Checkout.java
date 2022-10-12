import java.util.ArrayList;

public class Checkout {
    private int id;
    private CheckOutSession checkOutSession;
    private ArrayList<Integer> orderIds;
    private int moneyId;
    //ArrayList<CheckOutSession> checkOutSessionsHistory = new ArrayList<>();

    public Checkout(int id, int employeeId, ArrayList<Integer> orderIds, int moneyid) {
        this.id = id;
        //this.employeeId = employeeId;
        this.orderIds = orderIds;
        this.moneyId = moneyid;
    }

    public int getId() {
        return id;
    }


    /*public int getEmployeeId() {
        return employeeId;
    }*/

    public CheckOutSession getSession() {
        return checkOutSession;
    }

    public ArrayList<Integer> getOrderIds() {
        return orderIds;
    }

    public int getMoneyId() {
        return moneyId;
    }

    public void loginEmployee(int employeeId) {
        if (checkOutSession == null) {
            checkOutSession = new CheckOutSession(employeeId);
        }
    }

    public void logoutEmployee() {
        if (checkOutSession != null) {
            checkOutSession.quitSession();
            CheckOutSession.getCheckOutSessionsHistory().add(checkOutSession);
            checkOutSession = null;
        }
    }

    public void changeEmployee(int employeeId) {
        //checkOutSession.addSessionToHistory();
        //this.employeeId = employeeId;
        checkOutSession.quitSession();
        checkOutSession = new CheckOutSession(employeeId);

    }

    public boolean employeeIsLoggedInToCheckout() {
        return checkOutSession != null;
    }

    /*private void newCheckOutSession() {
        CheckOutSession checkOutSession = new CheckOutSession(employeeId);
        checkOutSessions.add(checkOutSession);
    }*/
}
