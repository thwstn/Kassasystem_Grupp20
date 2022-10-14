import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class CheckOutSession {
    //private static ArrayList<CheckOutSession> checkOutSessionsHistory = new ArrayList<>();
    private Date startDate;
    private Date endDate;
    private int employeeId;

    private ArrayList<UUID> orderIDs;

    public CheckOutSession(int employeeId) {
        startDate = new Date();
        endDate = null;
        this.employeeId = employeeId;
    }

    /*public static ArrayList<CheckOutSession> getCheckOutSessionsHistory() {
        return checkOutSessionsHistory;
    }*/

    public int getEmployeeId() {
        return employeeId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void addEndDateToSession() {
        endDate = new Date();
        //addSessionToHistory();
    }

    /*protected void addSessionToHistory() {
        checkOutSessionsHistory.add(this);
    }*/
}