import java.util.ArrayList;
import java.util.Date;

public interface CheckOutSessionDatabase {
    void addCheckOutSession(CheckOutSession checkOutSession);
    ArrayList<CheckOutSession> getCheckOutSessionFromDatabaseWithEmployee(Employee employee);
    CheckOutSession getCheckOutSessionFromDataBaseWithDate(Date date);
    ArrayList<CheckOutSession> getCheckOutSessionsFromDatabaseWithinDateInterval(Date startDate, Date endDate);
}
