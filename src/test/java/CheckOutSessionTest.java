import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class CheckOutSessionTest {
    @Test
    void startDateExistAndIsWithin0Point5Seconds() {
        CheckOutSession checkOutSession = new CheckOutSession(new Employee("Lisa", 30000));
        Date compareDate = new Date();
        //System.out.println(checkOutSession.getStartDate().getTime() + "\n" + (compareDate.getTime()-5));
        assertTrue((checkOutSession.getStartDate().getTime() > (compareDate.getTime() - 500) && checkOutSession.getStartDate().getTime() < compareDate.getTime() + 500));
    }
    @Test
    void addEndDateToSession() {
        CheckOutSession checkOutSession = new CheckOutSession(new Employee("Lisa", 30000));
        checkOutSession.addEndDateToSession();
        assertTrue(checkOutSession.getEndDate() != null, "End Date does not exist!");
    }

    @Test
    void employeeSetsCorrectly() {
        Employee employee = new Employee("Lisa", 30000);
        CheckOutSession checkOutSession = new CheckOutSession(employee);
        assertEquals(employee, checkOutSession.getEmployee(), "Wrong employeeId");
    }
    @Test
    void getLengthOfSessionInSecondsIsAnIntAndWithin100MsFrom2Seconds() throws InterruptedException {
        CheckOutSession checkOutSession = new CheckOutSession(new Employee("Lisa", 30000));
        TimeUnit.SECONDS.sleep(2);
        checkOutSession.addEndDateToSession();
        //long timeDiff = checkOutSession.getEndDate().getTime() - checkOutSession.getStartDate().getTime();
        assertTrue(checkOutSession.getSessionLengthInSeconds() < 2100 && checkOutSession.getSessionLengthInSeconds() > 1900, "Time diff is too large or too short!");
        //assertEquals(5, checkOutSession.getEndDate().getTime() - checkOutSession.getStartDate().getTime(), "Should be 5 seconds!");
    }
    @Test
    void getLengthOfSessionInText() {
        CheckOutSession checkOutSession = new CheckOutSession(new Employee("Lisa", 30000));
        checkOutSession.addEndDateToSession();
        assertTrue(checkOutSession.getSessionLengthInString().contains("Session length in seconds: "), "String is wrong or session is longer than 59 seconds.");
    }
}
