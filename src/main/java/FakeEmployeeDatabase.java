import java.util.ArrayList;
import java.util.List;

public class FakeEmployeeDatabase implements EmployeeDatabase {

    ArrayList<Employee> employees = new ArrayList<>();

    private final Employee E1 = new Employee("Anna", 28_000);
    private final Employee E2 = new Employee("Boris", 140_000);
    private final Employee E3 = new Employee("Calle", 35_000);
    private final Employee E4 = new Employee("Daniella", 16_000);
    private final Employee E5 = new Employee("Evelyn", 17_000);


    public void FakeEmployeeDataBase() {
        fillDatabase();
    }

    @Override
    public void fillDatabase() {
        employees.addAll(List.of(E1,E2,E3,E4,E5));
    }


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
