import java.util.ArrayList;
import java.util.Date;

public class FakeCheckOutSessionDatabase implements CheckOutSessionDatabase {
    ArrayList<CheckOutSession> checkOutSessions = new ArrayList<>();

    @Override
    public ArrayList<CheckOutSession> getCheckOutSessionFromDatabaseWithEmployee(Employee employee) {
        ArrayList<CheckOutSession> checkOutSessionsToReturn = new ArrayList<>();
        for (CheckOutSession checkOutSession : checkOutSessions) {
            if (checkOutSession.getEmployee().equals(employee) ) {
                checkOutSessionsToReturn.add(checkOutSession);
            }
        }
        return checkOutSessionsToReturn;
    }

    @Override
    public CheckOutSession getCheckOutSessionFromDataBaseWithDate(Date date) {
        for (CheckOutSession checkOutSession : checkOutSessions) {
            if (checkOutSession.getStartDate().getTime() < date.getTime() && checkOutSession.getEndDate().getTime() > date.getTime()) {
                return checkOutSession;
            }
        }
        return null;
    }

    @Override
    public ArrayList<CheckOutSession> getCheckOutSessionsFromDatabaseWithinDateInterval(Date startDate, Date endDate) {
        ArrayList<CheckOutSession> checkOutSessionsToReturn = new ArrayList<>();
        for (CheckOutSession checkOutSession : checkOutSessions) {
            if (checkOutSession.getStartDate().getTime() > startDate.getTime() && checkOutSession.getStartDate().getTime() < endDate.getTime()) {
                checkOutSessionsToReturn.add(checkOutSession);
            }
        }
        return checkOutSessionsToReturn;
    }
}
