import java.util.ArrayList;

public interface EmployeeDatabase {

    void fillDatabase();

    void add();

    Employee getEmployee(String name);

    ArrayList<Employee> get();
}
