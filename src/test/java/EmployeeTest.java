import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeTest {

    Employee employee;

    @BeforeEach
    void init() {
        employee = new Employee("Lisa", 150);
    }

    @Test
    void getNameReturnsCorrectName() {
        assertEquals("Lisa", employee.getName());
    }

    @Test
    void getSalaryReturnsCorrectSalary() {
        assertEquals(150, employee.getSalary());
    }

    @Test
    void getEmployeeIDNotNull() {
        assertNotNull(employee.getEmployeeID());
    }

    @Test
    void increaseMonthlySalary() {

    }

}
