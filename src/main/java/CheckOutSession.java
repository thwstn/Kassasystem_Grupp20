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

}
