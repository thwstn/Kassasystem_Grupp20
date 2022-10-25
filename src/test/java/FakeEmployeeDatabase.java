import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FakeEmployeeDatabase implements EmployeeDatabase {

    ArrayList<Employee> employees = new ArrayList<>();

    private final Employee E1 = new Employee("Anna", 20_000);
    private final Employee E2 = new Employee("Calle", 40_000);
    private final Employee E3 = new Employee("Daniella", 50_000);


    public FakeEmployeeDatabase() {
        fillDatabase();
    }

    @Override
    public void fillDatabase() {
        employees.addAll(List.of(E1,E2,E3));
    }

    @Override
    public Employee getEmployee(String name) {
        for (Employee e : employees) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public ArrayList<Employee> get() {
        return employees;
    }

    @Override
    public void add() {

    }
}
