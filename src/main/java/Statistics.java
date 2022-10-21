
import com.sun.source.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Statistics {

    FakeEmployeeDatabase fakeEmployeeDatabase;
    FakeOrderDatabase fakeOrderDatabase;
    FakeProductDatabase fakeProductDatabase;
    public FakeCheckOutSessionDatabase fakeCheckOutSessionDatabase;


    public Statistics() {
        fakeEmployeeDatabase = new FakeEmployeeDatabase();
        fakeOrderDatabase = new FakeOrderDatabase();
        fakeProductDatabase = new FakeProductDatabase();
        fakeCheckOutSessionDatabase = new FakeCheckOutSessionDatabase();

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

    //totalSalary

    public Product getCustomerMostSold(Customer customer) {
        Order tempOrder = new Order(new Employee("Theo", 12));
        List<Order> customerOrders = fakeOrderDatabase.getAllOrdersByCustomer(customer);
        for (Order o : customerOrders) {
            Collection<OrderLine> ol = o.getOrderLineList();
            for (OrderLine orderLine : ol) {
                tempOrder.addOrderLineToList(new OrderLine(orderLine.getName(), orderLine.getPrice(), orderLine.getQuantity()));
            }
        }
        tempOrder.groupAllOrderLinesTogether();
        String product = null;
        int highestAmount = 0;
        for (OrderLine orderLine : tempOrder.getOrderLineList()) {
            if (orderLine.getQuantity() > highestAmount) {
                highestAmount = orderLine.getQuantity();
                product = orderLine.getName();
            }
        }
        return fakeProductDatabase.getProductFromDatabase(product);
    }

    //kundens mest köpta vara

    //den som scannar flest produkter per arbetad timme

    /*
    public LinkedHashMap<String, Double> getEmployeesSortedBySpeed(ArrayList<CheckOutSession> checkOutSessions) {
        TreeMap<String, Integer> employeeNoOfProducts = new TreeMap<>();
        for (Employee employee : fakeEmployeeDatabase.get()) {
            int quantity = 0;
            for (Order order : employee.getOrders()) {
                for(OrderLine orderLine : order.getOrderLineList()) {
                    quantity = quantity + orderLine.getQuantity();
                }
            }
            employeeNoOfProducts.put(employee.getName(), quantity);
        }
//        TreeMap<String, Integer> employeeTotalSessionTime = new TreeMap<>();
//        for (Employee employee : fakeEmployeeDatabase.get()) {
//            int totalTime = 0;
//            for (CheckOutSession checkOutSession : employee.getCheckOutSessions()) {
//                totalTime = totalTime + checkOutSession.getSessionLenghtInSeconds();
//            }
//            employeeTotalSessionTime.put(employee.getName(), totalTime);
//        }
        TreeMap<String, Integer> employeeTotalSessionTime = new TreeMap<>();
        for (CheckOutSession checkOutSession : checkOutSessions) {
            String name = checkOutSession.getEmployee().getName();
            int totalTime = checkOutSession.getSessionLenghtInSeconds();
            employeeTotalSessionTime.put(name, totalTime);
        }


        TreeMap<String, Double> employeesWithSpeed = new TreeMap<>();
        for (Employee employee : fakeEmployeeDatabase.get()) {
            int products = employeeNoOfProducts.get(employee.getName());
            int time = employeeTotalSessionTime.get(employee.getName());
            double speed = (double) products / time;
            employeesWithSpeed.put(employee.getName(), speed);
        }
        LinkedHashMap<String, Double> employeesWithSpeedSorted = new LinkedHashMap<>();
        employeesWithSpeed.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> employeesWithSpeedSorted.put(x.getKey(), x.getValue()));
        //Hämta alla employees
        //För varje employee, hämta antal ordrar och antal sessions
        //Räkna ut hur många varor de gör per session
        //Returnera lista ordnad efter mest effektiv...
        return employeesWithSpeedSorted;
    }
    */

    public int getAverageCheckOutSessionLength(Employee employee, FakeCheckOutSessionDatabase fos) {
        ArrayList<CheckOutSession> checkOutSessions = fos.getCheckOutSessionFromDatabaseWithEmployee(employee);
        int totalSeconds = 0;
        int counter = 0;
        for (CheckOutSession checkOutSession : checkOutSessions) {
            totalSeconds = totalSeconds + checkOutSession.getSessionLenghtInSeconds();
            counter++;
        }
        return totalSeconds / counter;
    }

    public Map<String, Integer> getTopFiveSoldProductsEver(){
        Collection<Order> allOrders = fakeOrderDatabase.getAllOrders();
        TreeMap<String, Integer> topFive = new TreeMap<>(Collections.reverseOrder());
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
            topFive.put(p.getName(), quantity);
        }
        Map<String, Integer> top = topFive.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
              return top;
    }

    public Map.Entry<Customer, Integer> getCustomerWithMostOrders() {
        TreeMap<Customer, Integer> allCustomers = new TreeMap<>();
        Collection<Order> allOrders = fakeOrderDatabase.getAllOrders();
        for (Order o : allOrders) {

            if(allCustomers.containsKey(o.getCustomer())){
                allCustomers.put(o.getCustomer(), allCustomers.get(o.getCustomer()) + 1);
            }
            else{
                allCustomers.put(o.getCustomer(), 1);
            }
        }
        return Collections.max(allCustomers.entrySet(), Comparator.comparingInt(Map.Entry::getValue));

    }
}
