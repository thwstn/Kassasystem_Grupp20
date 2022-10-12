import java.util.ArrayList;

public class Checkout {
    int id;
    Integer employeeId;
    ArrayList<Integer> orderIds;
    Money money;
    public Checkout(int id, int employeeId, ArrayList<Integer> orderIds, Money money) {
        this.id = id;
        this.employeeId = employeeId;
        this.orderIds = orderIds;
        this.money = money;
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
