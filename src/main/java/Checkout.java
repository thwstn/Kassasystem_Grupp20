import java.util.ArrayList;

public class Checkout {
    int id;
    Integer employeeId;
    ArrayList<Integer> orderIds;
    int moneyId;


    public Checkout(int id, int employeeId, ArrayList<Integer> orderIds, int moneyid) {
        this.id = id;
        this.employeeId = employeeId;
        this.orderIds = orderIds;
        this.moneyId = moneyid;
    }

    public int getId() {
        return id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public ArrayList<Integer> getOrderIds() {
        return orderIds;
    }

    public int getMoneyId() {
        return moneyId;
    }

    public void changeEmployee(int employeeId) {
        this.employeeId = employeeId;
    }

    public boolean employeeIsLoggedInToCheckout() {
        return employeeId != null;
    }
}
