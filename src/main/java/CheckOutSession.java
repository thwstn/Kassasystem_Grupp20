import java.util.Date;

public class CheckOutSession {
    Date startDate;
    Date endDate;
    int employeeId;

    public CheckOutSession(int employeeId) {
        startDate = new Date();
        endDate = null;
        this.employeeId = employeeId;
    }

    public void quitSession() {
        endDate = new Date();
    }
}
