

public class Employee {

    private String name;
    private int salary;

    public Employee() {
        name = "no_name";
        salary = 0;
    }

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName(){
        return name;
    }

    public int getSalary() {
        return salary;
    }
}
