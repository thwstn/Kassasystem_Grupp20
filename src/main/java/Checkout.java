import java.util.ArrayList;

public class Checkout {
    int id;
    Integer employeeId;
    ArrayList<Integer> orderIds;
    int balanceID;
    public Checkout(int id, int employeeId, ArrayList<Integer> orderIds, int balanceId) {
        this.id = id;
        this.employeeId = employeeId;
        this.orderIds = orderIds;
        this.balanceID = balanceId;
    }

    public int getId() {
        return id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public boolean employeeIsLoggedInToCheckout() {
        return employeeId != null;
    }

    public void changeEmployee(int employeeId) {
        this.employeeId = employeeId;
    }
}
