import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<CheckOutSession> checkOutSessions = new ArrayList<>();
    Employee e1 = new Employee("Lena", 140);
    Employee e2 = new Employee("Hasse", 150);
    Employee e3 = new Employee("Boris", 160);


    @Test
    void ctr_setsCorrectDataFields() {
        assertEquals("Lisa", employee.getName());
        assertEquals(150, employee.getSalary());
    }

    @Test
    void getSoldProducts() {

    }


    @Test
    void getEmployeesCheckOutSessions() {


    }

}
