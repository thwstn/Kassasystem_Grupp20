import java.util.ArrayList;
import java.util.Date;

public class CheckOutSession {
    //private static ArrayList<CheckOutSession> checkOutSessionsHistory = new ArrayList<>();
    private Date startDate;
    private Date endDate;
    private Employee employee;

    public CheckOutSession(Employee employee) {
        startDate = new Date();
        endDate = null;
        this.employee = employee;
    }

    /*public static ArrayList<CheckOutSession> getCheckOutSessionsHistory() {
        return checkOutSessionsHistory;
    }*/

    public Employee getEmployee() {
        return employee;
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
