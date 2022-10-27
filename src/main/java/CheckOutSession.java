import java.util.Date;

public class CheckOutSession {
    private final Date startDate;
    private Date endDate;
    private final Employee employee;

    public CheckOutSession(Employee employee) {
        startDate = new Date();
        endDate = null;
        this.employee = employee;
    }

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
    }
    public String getSessionLengthInString() {
        long sessionLengthInSeconds = endDate.getTime() - startDate.getTime();
        int seconds;
        seconds = (int) sessionLengthInSeconds;
        return "Session length in seconds: " + seconds;
    }
    public int getSessionLengthInSeconds() {
        return (int) (endDate.getTime() - startDate.getTime());
    }
}
