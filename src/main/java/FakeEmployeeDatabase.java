import java.util.ArrayList;
import java.util.List;

public class FakeEmployeeDatabase implements EmployeeDatabase {

    ArrayList<Employee> employees = new ArrayList<>();

    private final Employee E1 = new Employee("Anna", 130);
    private final Employee E2 = new Employee("Boris", 140);
    private final Employee E3 = new Employee("Calle", 150);
    private final Employee E4 = new Employee("Daniella", 160);
    private final Employee E5 = new Employee("Evelyn", 170);


    @Override
    public void fillDatabase() {
        employees.addAll(List.of(E1,E2,E3,E4,E5));
    }

    @Override
    public Employee get(Employee employee) {
        return null;
    }

    @Override
    public void add() {

    }
}
