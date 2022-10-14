import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
public class Personal {
    String name;
    int salary;
    Personal(String name, int salary) {
    this.name = name;
    this.salary = salary;
    }
    public soldProducts(); {}
    public soldSum();  {}
    public calculateChristmasBonus();  {}
}
 */

public class EmployeeTest {

    Employee employee;

    @BeforeEach
    void init() {
        employee = new Employee("Lisa", 150);
    }


    @Test
    void ctr_setsCorrectDataFields() {
        assertEquals("Lisa", employee.getName());
        assertEquals(150, employee.getSalary());
    }

}
