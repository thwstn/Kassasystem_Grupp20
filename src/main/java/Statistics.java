
import java.util.ArrayList;

public class Statistics {

    FakeEmployeeDatabase fakeEmployeeDatabase;
    FakeOrderDatabase fakeOrderDatabase;


    public Statistics() {
        fakeEmployeeDatabase = new FakeEmployeeDatabase();
        fakeOrderDatabase = new FakeOrderDatabase();

    }

    public int getAverageSalary() {
        int totalSalary = 0;
        int counter = 0;
        for (Employee e : fakeEmployeeDatabase.get()) {
            totalSalary = totalSalary + e.getSalary();
            counter++;
        }
        return totalSalary / counter;
    }

    public Product getCustomerMostSold(Customer customer) {
        //ArrayList<Order> customerOrders = fakeOrderDatabase.getAllOrdersByCustomer(customer);
        return null;
    }

    //kundens mest köpta vara



}
