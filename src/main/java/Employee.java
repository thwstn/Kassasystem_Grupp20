import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Employee {
    private String name;
    private int monthly_salary;
    private final UUID employeeId;
    private LocalDate employmentStartDate;

    public Employee(String name, int monthly_salary) {
        this.name = name;
        this.monthly_salary = monthly_salary;
        this.employeeId = UUID.randomUUID();
        employmentStartDate = LocalDate.now();
    }

    public String getName(){
        return name;
    }

    public int getSalary() {
        return monthly_salary;
    }

    public UUID getEmployeeID() {
        return employeeId;
    }

    public void changeMonthlySalary(int amount) {
        this.monthly_salary = monthly_salary + amount;
    }

}
