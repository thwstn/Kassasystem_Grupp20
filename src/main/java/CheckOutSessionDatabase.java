import java.util.ArrayList;

public interface CheckOutSessionDatabase {
    ArrayList<CheckOutSession> checkOutSessions = new ArrayList<>();

    public ArrayList<CheckOutSession> getCheckOutSessions(int employeeId);
}
