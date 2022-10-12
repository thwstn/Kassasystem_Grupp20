import java.util.ArrayList;
import java.util.Date;

public class CheckOutSession {
    static ArrayList<CheckOutSession> checkOutSessionsHistory = new ArrayList<>();
    Date startDate;
    Date endDate;
    int employeeId;

    public CheckOutSession(int employeeId) {
        startDate = new Date();
        endDate = null;
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void quitSession() {
        endDate = new Date();
    }
}
