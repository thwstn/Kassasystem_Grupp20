import java.util.Date;

public class CheckOutSession {
    private Date startDate;
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
    public String getSessionLenghtInString() {
        long sessionLenghtInSeconds = endDate.getTime() - startDate.getTime();
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        while (sessionLenghtInSeconds >= 3600) {
            sessionLenghtInSeconds -= 3600;
            hours++;
        }
        while (sessionLenghtInSeconds >= 60) {
            sessionLenghtInSeconds -= 60;
            minutes++;
        }
        seconds = (int) sessionLenghtInSeconds;
        String returnString = "Session lenght: " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds";
        return returnString;
    }
    public int  getSessionLenghtInSeconds() {
        return (int) (endDate.getTime() - startDate.getTime());
    }
}
