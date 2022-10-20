
import com.sun.source.tree.Tree;

import java.util.*;

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



    //5Most sold products
    public TreeMap<Integer, String> getTopFiveSoldProductsEver(){
        Collection<Order> allOrders = fakeOrderDatabase.getAllOrders();
        TreeMap<Integer, String> topFive = new TreeMap<>(Collections.reverseOrder());
        Order combinedOrder = new Order(new Employee("NA", 0));
        for (Order o : allOrders) {
            Collection<OrderLine> currentOrderLines = o.getOrderLineList();
            for (OrderLine ol : currentOrderLines) {
                combinedOrder.addOrderLineToList(ol);
            }
        }
        combinedOrder.groupAllOrderLinesTogether();
        combinedOrder.sortByQuantityHighestToLowest();
        for(int i = 0; i < 5; i++){
            Product p = fakeProductDatabase.getProductFromDatabase(combinedOrder.getOrderLineAtIndex(i).getName());
            int quantity = combinedOrder.getOrderLineAtIndex(i).getQuantity();
            topFive.put(quantity, p.getName());
        }

        return topFive;
    }



}
