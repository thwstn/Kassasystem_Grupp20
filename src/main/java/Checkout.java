import java.util.ArrayList;

public class Checkout {
    int id;
    CheckOutSession checkOutSession;
    ArrayList<Integer> orderIds;
    int moneyId;
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

    public void changeEmployee(int employeeId) {

        //this.employeeId = employeeId;
        checkOutSession.quitSession();


    }

    public boolean employeeIsLoggedInToCheckout() {
        return checkOutSession != null;
    }

    private void newCheckOutSession() {
        CheckOutSession checkOutSession = new CheckOutSession(employeeId);
        checkOutSessions.add(checkOutSession);
    }
}
