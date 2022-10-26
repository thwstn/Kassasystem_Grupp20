import java.time.LocalDate;
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
        if (this.monthly_salary < 10_000) {
            this.monthly_salary = 10_000;
        }
    }

    public void giveSalaryIncreaseBasedOnAmountOfYearsEmployed() {
        int years = calculateNumberOfYearsAsEmployee();
        double salaryIncrease = monthly_salary * calculateSalaryPercentageIncrease(years);
        changeMonthlySalary((int)salaryIncrease);
    }

    private int calculateNumberOfYearsAsEmployee() {
        int numberOfYears = LocalDate.now().getYear() - employmentStartDate.getYear();
        if (LocalDate.now().getDayOfYear() - employmentStartDate.getDayOfYear() < 0) {
            numberOfYears -= 1;
        }
        return numberOfYears;
    }

    private double calculateSalaryPercentageIncrease(int years) {
        if (years >= 20) {
            return 0.20;
        } else if (years >= 10) {
            return 0.10;
        } else if (years >= 5) {
            return 0.05;
        } else if (years == 0){
            return 0.00;
        } else {
            return 0.02;
        }
    }
}
