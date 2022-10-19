
import java.util.ArrayList;
import java.util.Collection;

public class Statistics {

    FakeEmployeeDatabase fakeEmployeeDatabase;
    FakeOrderDatabase fakeOrderDatabase;
    FakeProductDatabase fakeProductDatabase;


    public Statistics() {
        fakeEmployeeDatabase = new FakeEmployeeDatabase();
        fakeOrderDatabase = new FakeOrderDatabase();
        fakeProductDatabase = new FakeProductDatabase();

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
        Order fuling = new Order(new Employee("Theo", 12));
        ArrayList<Order> customerOrders = fakeOrderDatabase.getAllOrdersByCustomer(customer);
        for (Order o : customerOrders) {
            Collection<OrderLine> ol = o.getOrderLineList();
            for (OrderLine orderLine : ol) {
                fuling.addOrderLineToList(orderLine);
            }
        }
        fuling.groupAllOrderLinesTogether();
        String product = null;
        int highestAmount = 0;
        for (OrderLine orderLine : fuling.getOrderLineList()) {
            if (orderLine.getQuantity() > highestAmount) {
                highestAmount = orderLine.getQuantity();
                product = orderLine.getName();
            }
        }
        Product p = fakeProductDatabase.getProductFromDatabase(product);
        return p;
    }

    //kundens mest köpta vara



}
