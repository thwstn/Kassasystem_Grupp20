import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class CheckOutSessionTest {
    @Test
    void addCheckOutSessionHistory() {
        CheckOutSession checkOutSession = new CheckOutSession(123);
        checkOutSession.addSessionToHistory();
        checkOutSession.addSessionToHistory();
        checkOutSession.addSessionToHistory();
        checkOutSession.addSessionToHistory();
        checkOutSession.addSessionToHistory();
        assertEquals(true, CheckOutSession.getCheckOutSessionsHistory().size() > 4, "Wrong number of sessions in history");
    }
    @Test
    void startDateExistAndIsWithin5Seconds() {
        CheckOutSession checkOutSession = new CheckOutSession(123);
        Date compareDate = new Date();
        //System.out.println(checkOutSession.getStartDate().getTime() + "\n" + (compareDate.getTime()-5));
        assertEquals(true, (checkOutSession.getStartDate().getTime()>(compareDate.getTime()-500) && checkOutSession.getStartDate().getTime() < compareDate.getTime()+500));
    }
    @Test
    void addEndDateToSession() {
        CheckOutSession checkOutSession = new CheckOutSession(123);
        checkOutSession.quitSession();
        assertEquals(true, checkOutSession.getEndDate() != null, "End Date does not exist!");
    }

    @Test
    void employeeSetsCorrectly() {
        CheckOutSession checkOutSession = new CheckOutSession(123123123);
        assertEquals(123123123, checkOutSession.getEmployeeId(), "Wrong employeeId");
    }

}
