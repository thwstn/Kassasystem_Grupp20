import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class CheckOutSessionTest {
    /*@Test
    void addCheckOutSessionHistory() {
        CheckOutSession checkOutSession = new CheckOutSession(123);
        checkOutSession.addSessionToHistory();
        checkOutSession.addSessionToHistory();
        checkOutSession.addSessionToHistory();
        checkOutSession.addSessionToHistory();
        checkOutSession.addSessionToHistory();
        assertEquals(true, CheckOutSession.getCheckOutSessionsHistory().size() > 4, "Wrong number of sessions in history");
    }*/
    @Test
    void startDateExistAndIsWithin0Point5Seconds() {
        CheckOutSession checkOutSession = new CheckOutSession(new Employee("Lisa", 30000));
        Date compareDate = new Date();
        //System.out.println(checkOutSession.getStartDate().getTime() + "\n" + (compareDate.getTime()-5));
        assertEquals(true, (checkOutSession.getStartDate().getTime()>(compareDate.getTime()-500) && checkOutSession.getStartDate().getTime() < compareDate.getTime()+500));
    }
    @Test
    void addEndDateToSession() {
        CheckOutSession checkOutSession = new CheckOutSession(new Employee("Lisa", 30000));
        checkOutSession.addEndDateToSession();
        assertEquals(true, checkOutSession.getEndDate() != null, "End Date does not exist!");
    }

    @Test
    void employeeSetsCorrectly() {
        Employee employee = new Employee("Lisa", 30000);
        CheckOutSession checkOutSession = new CheckOutSession(employee);
        assertEquals(employee, checkOutSession.getEmployee(), "Wrong employeeId");
    }
    @Test
    void getLenghtOfSessionInText() throws InterruptedException {
        CheckOutSession checkOutSession = new CheckOutSession(new Employee("Lisa", 30000));
        TimeUnit.SECONDS.sleep(2);
        checkOutSession.addEndDateToSession();
        //long timeDiff = checkOutSession.getEndDate().getTime() - checkOutSession.getStartDate().getTime();
        assertTrue(checkOutSession.getSessionLenghtInSeconds() < 2100 && checkOutSession.getSessionLenghtInSeconds() > 1900, "Time diff is too large or too short!");
        //assertEquals(5, checkOutSession.getEndDate().getTime() - checkOutSession.getStartDate().getTime(), "Should be 5 seconds!");
    }
    @Test
    void getLenghtOfSessionInSecondsIsAnIntAndLongerThanMinus1() {
        CheckOutSession checkOutSession = new CheckOutSession(new Employee("Lisa", 30000));
        checkOutSession.addEndDateToSession();
        assertTrue(checkOutSession.getSessionLenghtInString().contains("Session lenght: 0 hours, 0 minutes, "), "String is wrong or session is longer than 59 seconds.");
        //continue here
    }
}
