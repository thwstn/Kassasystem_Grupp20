import java.util.ArrayList;
import java.util.UUID;

public class Employee {

    private String name;
    private int salary;
    private final UUID employeeId;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
        this.employeeId = UUID.randomUUID();
    }

    public String getName(){
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public UUID getEmployeeID() {
        return employeeId;
    }

    /*
    @Override
    public ArrayList<CheckOutSession> getCheckOutSessions(int employeeId) {
        ArrayList<CheckOutSession> employeeCheckOutSessions = new ArrayList<>();
        for (CheckOutSession cos : checkOutSessions) {
            if (cos.getEmployeeId() == employeeId) {
                employeeCheckOutSessions.add(cos);
            }
        }
        return employeeCheckOutSessions;
    }
    */

}
