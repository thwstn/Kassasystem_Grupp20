import org.junit.jupiter.api.Test;

import java.util.Date;

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

}
