import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class EmployeeTest {

    Employee employee;
    Employee employeeForManyYears;
    final String uuidString = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";

    @BeforeEach
    void init() {
        employee = new Employee("Lisa", 15_000);
        employeeForManyYears = new Employee("Pia", 30_000);
    }

    @Test
    void getNameReturnsCorrectName() {
        assertEquals("Lisa", employee.getName());
    }

    @Test
    void getEmployeeIDNotNull() {
        assertNotNull(employee.getEmployeeID());
    }

    @Test
    void increaseMonthlySalaryIncreasesSalary() {
        employee.changeMonthlySalary(1500);
        assertEquals(16_500, employee.getSalary());
    }

    @Test
    void decreaseMonthlySalaryDecreasesSalary() {
        employee.changeMonthlySalary(-1500);
        assertEquals(13_500, employee.getSalary());
    }

    @Test
    void salaryCannotBeDecreasedBelow10_000() {
        employee.changeMonthlySalary(-6000);
        assertEquals(10_000, employee.getSalary());
    }

    @Test
    void giveSalaryIncreaseForTwentyYearsOfServiceGivesTwentyPercentIncrease() {
        ReflectionTestUtils.setField(employeeForManyYears, "employmentStartDate", LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()).minusYears(20));
        employeeForManyYears.giveSalaryIncreaseBasedOnAmountOfYearsEmployed();
        assertEquals(36_000, employeeForManyYears.getSalary());
    }

    @Test
    void giveSalaryIncreaseForNineteenYearsOfServiceGivesTenPercentIncrease() {
        ReflectionTestUtils.setField(employeeForManyYears, "employmentStartDate", LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()).minusYears(19).minusMonths(11));
        employeeForManyYears.giveSalaryIncreaseBasedOnAmountOfYearsEmployed();
        assertEquals(33_000, employeeForManyYears.getSalary());
    }

    @Test
    void giveSalaryIncreaseForSevenYearsOfServiceGivesFivePercentIncrease() {
        ReflectionTestUtils.setField(employeeForManyYears, "employmentStartDate", LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()).minusYears(7));
        employeeForManyYears.giveSalaryIncreaseBasedOnAmountOfYearsEmployed();
        assertEquals(31_500, employeeForManyYears.getSalary());
    }

    @Test
    void giveSalaryIncreaseForOneYearOfServiceGivesTwoPercentIncrease() {
        ReflectionTestUtils.setField(employeeForManyYears, "employmentStartDate", LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()).minusYears(1));
        employeeForManyYears.giveSalaryIncreaseBasedOnAmountOfYearsEmployed();
        assertEquals(30_600, employeeForManyYears.getSalary());
    }

    @Test
    void giveNoSalaryIncreaseToLessThanOneYearService() {
        employeeForManyYears.giveSalaryIncreaseBasedOnAmountOfYearsEmployed();
        assertEquals(30_000, employeeForManyYears.getSalary());
    }

    @Test
    void randomUUIDIsInProperFormat() {
        assertTrue(Pattern.compile(uuidString).matcher(employee.getEmployeeID().toString()).matches());
    }

}
