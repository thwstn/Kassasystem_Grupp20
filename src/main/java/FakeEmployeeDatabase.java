import java.util.ArrayList;

public class FakeEmployeeDatabase implements EmployeeDatabase {

    ArrayList<Employee> employees = new ArrayList<>();

    Employee e1 = new Employee("Anna", 130);
    Employee e2 = new Employee("Boris", 140);
    Employee e3 = new Employee("Calle", 150);
    Employee e4 = new Employee("Daniella", 160);
    Employee e5 = new Employee("Evelyn", 170);


    @Override
    public Employee get(Employee employee) {
        return null;
    }

    @Override
    public void add() {

    }
}
