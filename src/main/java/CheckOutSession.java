import java.util.ArrayList;
import java.util.Date;

public class CheckOutSession {
    private static ArrayList<CheckOutSession> checkOutSessionsHistory = new ArrayList<>();
    private Date startDate;
    private Date endDate;
    private int employeeId;

    public CheckOutSession(int employeeId) {
        startDate = new Date();
        endDate = null;
        this.employeeId = employeeId;
    }

    public static ArrayList<CheckOutSession> getCheckOutSessionsHistory() {
        return checkOutSessionsHistory;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void quitSession() {
        endDate = new Date();
        addSessionToHistory();
    }

    protected void addSessionToHistory() {
        checkOutSessionsHistory.add(this);
    }
}
